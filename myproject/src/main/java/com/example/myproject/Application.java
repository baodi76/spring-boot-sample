package com.example.myproject;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class Application
{

    private static final Logger log          = LoggerFactory.getLogger( Application.class );
    private static RestTemplate restTemplate = null;

    public static void main( String args[] )
    {
        SpringApplication.run( Application.class, args );
    }

    @Bean
    public RestTemplate restTemplate( RestTemplateBuilder builder )
    {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run( RestTemplate restTemplate ) throws Exception
    {
        SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy( Proxy.Type.HTTP,
                new InetSocketAddress( "singtelproxy.is", 80 ) );
        clientHttpReq.setProxy( proxy );

        restTemplate = new RestTemplate( clientHttpReq );
        setRestTemplate( restTemplate );

        return args ->
        {
            Quote quote = getRestTemplate().getForObject(
                    "http://gturnquist-quoters.cfapps.io/api/random", Quote.class );
            log.info( quote.toString() );
        };
    }

    @RequestMapping( "/json" )
    String jsonhome() throws Exception
    {
        log.debug( "into jsonhome--- with proxy RestTemplate" );
        Quote json_response = getRestTemplate().getForObject(
                "http://gturnquist-quoters.cfapps.io/api/random", Quote.class );
        return json_response.toString();
    }

    public static RestTemplate getRestTemplate()
    {
        return restTemplate;
    }

    public static void setRestTemplate( RestTemplate restTemplate )
    {
        Application.restTemplate = restTemplate;
    }

}
