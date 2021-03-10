package com.mfanw.element.configuration;

import com.mfanw.element.configuration.jwt.JwtAuthenticationEntryPoint;
import com.mfanw.element.configuration.jwt.JwtAuthorizationTokenFilter;
import com.mfanw.element.configuration.jwt.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * EnableWebSecurity这个注解必须加，开启Security
 * EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
 *
 * @author mengwei
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 请求无需登录：公共页面URL
     */
    private static final String[] PUBLIC_URLS = {
            "/",
            "/doLogin",
            "/helloPermitAll",
            "/vue-element-admin/user/login",
    };

    /**
     * 请求无需登录：Swagger需要加入Spring Security认证的URL
     */
    private static final String[] SWAGGER_URLS = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtAuthorizationTokenFilter authenticationTokenFilter;

    /**
     * 认证
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsServiceImpl).passwordEncoder(passwordEncoderBean());
    }

    /**
     * 配置拦截专用
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 认证入口点
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        // 页面访问权限控制
        http.authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(SWAGGER_URLS).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                // 剩下所有的验证都需要验证
                .anyRequest().authenticated();
        http.formLogin().loginPage("/login");
        // Spring Security 开启跨域
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}