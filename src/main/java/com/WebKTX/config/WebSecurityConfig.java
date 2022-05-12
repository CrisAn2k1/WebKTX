package com.WebKTX.config;



import com.WebKTX.model.Role;
import com.WebKTX.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/homepage","/login-success","/login"
                        ,"/thong-tin-sinh-vien","/thong-tin-lien-he","/thong-bao"
                        ,"/huong-dan-dang-ky-o-ktx","/form-dang-ky-o-ktx","/hoadon","/admin/**", "/form-chuyen-phong").authenticated()  // các URL bắt buộc đăng nhập
                .antMatchers("/**","/register","/confirm").permitAll().// các URL không bắt buộc đăng nhập
                 antMatchers("/homepage" ,"/thong-tin-sinh-vien","/thong-tin-lien-he","/thong-bao"
                    ,"/huong-dan-dang-ky-o-ktx","/form-dang-ky-o-ktx","/hoadon", "/form-chuyen-phong").hasAnyAuthority("user","admin")
                .antMatchers("/admin/**").hasAnyAuthority("admin").
            and()
                .csrf().csrfTokenRepository( new HttpSessionCsrfTokenRepository()).
            and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/homepage")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//                        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//                        if (roles.contains("admin")) {
//                            response.sendRedirect("/admin");
//                        }
//                        else {
//                            response.sendRedirect("/homepage");
//                        }
//                    }
//                })//trang mặc định khi đăng nhập thành công
                .failureUrl("/login?success=fail")
                .loginProcessingUrl("/j_spring_security_check").
            and().logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/homepage")
                    .addLogoutHandler(new SecurityContextLogoutHandler()
                    ))//.exceptionHandling().accessDeniedPage("/testUser")
        ;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

}