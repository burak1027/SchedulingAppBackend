//package com.backend.schedulingsystem.domain.filter;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//@Slf4j
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
//        this.authenticationManager=authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        logger.info("User name is"+ username);
//        logger.info("User password is"+ password);
//
//
//
//        UsernamePasswordAuthenticationToken authenticationToken = null;
////        setDetails(request,authenticationToken);
//        try {
//            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//            System.out.println(authenticationToken.toString());
//
//        }
//        catch (Exception e){
//            logger.info("HATA");
//        }
//        return authenticationManager.authenticate(authenticationToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
//
//        logger.info("Succesful Auth");
//        System.out.println("NNNOOOOOOOOOOOOOOOOOOOO");
//        User user = (User) auth.getPrincipal();
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        String accessToken = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis()+(10*60*1000)))
//                .withIssuer(request.getRequestURL().toString())
//                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .sign(algorithm);
//        String refreshToken = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis()+(10*60*1000)))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//        response.setHeader("acces_token",accessToken);
//        response.setHeader("refresh_token",refreshToken);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        logger.info("UnSuccesful Auth");
//
//        super.unsuccessfulAuthentication(request, response, failed);
//    }
//}
