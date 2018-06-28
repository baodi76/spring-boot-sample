package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example
{

    @RequestMapping( "/hello" )
    String hellohome()
    {
        return "Hello World- from GG/DD/PP!----->";
    }

    @RequestMapping( "/" )
    String home()
    {
        return "Hello World- from GG/DD/PP @ HOME!<<<<<<<====";
    }

    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run( Example.class, args );
    }

}
