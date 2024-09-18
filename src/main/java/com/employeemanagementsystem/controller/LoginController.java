package com.employeemanagementsystem.controller;


import com.employeemanagementsystem.dto.ApiResponse;
import com.employeemanagementsystem.dto.LoginDto;
import com.employeemanagementsystem.dto.TokenDto;
import com.employeemanagementsystem.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
            manager.authenticate(authenticationToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
            String token = jwtHelper.generateToken(userDetails);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setMessage("Success");
            tokenDto.setToken(token);
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }catch (Exception ex){
            TokenDto tokenDto = new TokenDto("No token","Failed");
            return new ResponseEntity<>(tokenDto,HttpStatus.UNAUTHORIZED);
        }



    }

    @GetMapping("hello-world-new")
    public String helloWorld(){
        return "Hello world";
    }
}
