package com.employeemanagementsystem.security;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {

        String requestToken = request.getHeader("Authorization");
        String username = null;
        String token;

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        // Bypass JWT filter for permitted endpoints (e.g., /home/**)

        if (requestURI.startsWith("/login")) {
            filterChain.doFilter(request, response); // Skip JWT validation for /login/** endpoints
            return;
        }

        System.out.println(requestToken + "--->  Request token");
        if(requestToken != null && requestToken.startsWith("Bearer")){
            token=requestToken.substring(6);
            System.out.println(token + " --> token");
            try{
                username = this.jwtHelper.getUsernameFromToken(token);
                System.out.println(username + " ---> username");
            }catch (IllegalArgumentException ex){
                System.out.println("Unable to get the Jwt token");
            }catch (ExpiredJwtException ex){
                System.out.println("Jwt token expired");
            }catch(MalformedJwtException ex){
                System.out.println("token not created properly");
            }

        }else{
              throw new JwtException("The JWT token does not starts with the word Bearer");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtHelper.validateToken(token,userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }else{
                System.out.println("Jwt token is not valid");
            }

        }else{

            System.out.println("email is null or context is not null");

        }

        filterChain.doFilter(request,response);






    }
}
