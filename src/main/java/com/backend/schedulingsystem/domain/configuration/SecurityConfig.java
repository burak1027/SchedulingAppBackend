package com.backend.schedulingsystem.domain.configuration;

import com.backend.schedulingsystem.domain.auth.AdminUserDetailsService;
import com.backend.schedulingsystem.domain.auth.InstructorUserDetailsService;
import com.backend.schedulingsystem.domain.auth.StudentUserDetailsService;
import com.backend.schedulingsystem.domain.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableWebSecurity
public class SecurityConfig {
    @Configuration
    @Order(1)
    public static class StudentConfigurationAdapter extends WebSecurityConfigurerAdapter{
        @Autowired
        StudentUserDetailsService studentUserDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(studentUserDetailsService);
            provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
            auth.authenticationProvider(provider);
//            auth.userDetailsService(studentUserDetailsService);

        }
        @Bean
        public GrantedAuthoritiesMapper authoritiesMapper(){
            SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
            authorityMapper.setConvertToUpperCase(true);
//            authorityMapper.setDefaultAuthority("ROLE_STUDENT");
            return authorityMapper;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
//            CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//            customAuthenticationFilter.setFilterProcessesUrl("/login");
            http.csrf().disable();
//            http.authorizeRequests().antMatchers("/student").hasRole("STUDENT");
            http.authorizeRequests().antMatchers("/student/hello").hasAnyAuthority("STUDENT");

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//            http.authorizeRequests().anyRequest().authenticated();

            http.addFilterBefore(new JwtTokenFilter(studentUserDetailsService,null,null), UsernamePasswordAuthenticationFilter.class);
        }
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring();
//            super.configure(web);
//        }
    }
    @Configuration
    @Order(2)
    public static class InstructorConfigurationAdapter extends WebSecurityConfigurerAdapter{
        @Autowired
        InstructorUserDetailsService instructorUserDetailsService;
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(instructorUserDetailsService);
            provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
            auth.authenticationProvider(provider);
//            auth.userDetailsService(instructorUserDetailsService);
        }
        @Bean
        public GrantedAuthoritiesMapper authoritiesMapper2(){
            SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
            authorityMapper.setConvertToUpperCase(true);
            return authorityMapper;
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.authorizeRequests().antMatchers("/student/hello").hasAnyAuthority("INSTRUCTOR");
            http.authorizeRequests().antMatchers("/instructor/accept-list").hasAnyAuthority("INSTRUCTOR");
            http.authorizeRequests().antMatchers("/instructor/all").hasAnyAuthority("INSTRUCTOR");


            http.addFilterBefore(new JwtTokenFilter(null,instructorUserDetailsService,null), UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
        public AuthenticationManager authenticationManagerBean2() throws Exception {
            return super.authenticationManagerBean();
        }
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring();
//            super.configure(web);
//        }
    }
    @Configuration
    @Order(3)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter{
        @Autowired
        AdminUserDetailsService adminUserDetailsService;
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(adminUserDetailsService);
            provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
            auth.authenticationProvider(provider);
//            auth.userDetailsService(instructorUserDetailsService);
        }
        @Bean
        public GrantedAuthoritiesMapper authoritiesMapper3(){
            SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
            authorityMapper.setConvertToUpperCase(true);
            return authorityMapper;
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            http.authorizeRequests().antMatchers("/student/hello").hasAnyAuthority("ADMIN");
            http.authorizeRequests().antMatchers("/course-date/all**").hasAnyAuthority("ADMIN");
            http.authorizeRequests().antMatchers("/course-date/period**").hasAnyAuthority("ADMIN");


            http.addFilterBefore(new JwtTokenFilter(null,null,adminUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
        public AuthenticationManager authenticationManagerBean3() throws Exception {
            return super.authenticationManagerBean();
        }
    }

}
