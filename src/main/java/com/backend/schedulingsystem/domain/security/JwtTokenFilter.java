package com.backend.schedulingsystem.domain.security;

import com.backend.schedulingsystem.domain.Auth.InstructorUserDetailsService;
import com.backend.schedulingsystem.domain.Auth.StudentUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;


public class JwtTokenFilter extends GenericFilterBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    private static final String BEARER = "Bearer";

    private StudentUserDetailsService studentUserDetailsService;
    private InstructorUserDetailsService instructorUserDetailsService;

    public JwtTokenFilter(StudentUserDetailsService studentUserDetailsService, InstructorUserDetailsService instructorUserDetailsService) {
        this.studentUserDetailsService = studentUserDetailsService;
        this.instructorUserDetailsService = instructorUserDetailsService;
    }



    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Process request to check for a JSON Web Token ");
        //Check for Authorization:Bearer JWT
        String headerValue = ((HttpServletRequest)req).getHeader("Authorization");

        if(studentUserDetailsService!=null){
            getBearerToken(headerValue).ifPresent(token-> {
                //Pull the Username and Roles from the JWT to construct the user details
                studentUserDetailsService.loadUserByJwtToken(token).ifPresent(userDetails -> {
                    //Add the user details (Permissions) to the Context for just this API invocation
                    SecurityContextHolder.getContext().setAuthentication(
                            new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
                });
            });

        }
        else if(instructorUserDetailsService!=null){
            getBearerToken(headerValue).ifPresent(token-> {
                //Pull the Username and Roles from the JWT to construct the user details
                instructorUserDetailsService.loadUserByJwtToken(token).ifPresent(userDetails -> {
                    //Add the user details (Permissions) to the Context for just this API invocation
                    SecurityContextHolder.getContext().setAuthentication(
                            new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
                });
            });
        }


        //move on to the next filter in the chains
        filterChain.doFilter(req, res);
    }

    private Optional<String> getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }
        return Optional.empty();
    }
}