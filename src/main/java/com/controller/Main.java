package com.controller;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
@SecurityScheme(name = "testauthendoc", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class Main {
        
        /** 
         * @param args
         */
        public static void main(String[] args) {
                SpringApplication.run(Main.class, args);
        }

        
        /** 
         * @return CorsFilter
         */
        @Bean
        public CorsFilter corsFilter() {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                // Allow anyone and anything access. Probably ok for Swagger spec
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(false);
                config.addAllowedOrigin("*");
                config.addAllowedHeader("*");
                config.addAllowedMethod("*");
                source.registerCorsConfiguration("/**", config);
                return new CorsFilter(source);
        }

        
        /** 
         * @param customOpenAPI(
         * @return OpenAPI
         */
        @Bean
        public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
                return new OpenAPI()
                                .servers(Lists.newArrayList(
                                                new Server().url("http://localhost:8091/swagger")
                                                                .description("this is description"),
                                                new Server().url("https://localhost:8091/swagger")))
                                .info(new Info()
                                                .title("Myyyyyy Petstore API")
                                                .version(appVersion)
                                                .description(
                                                                "This is a sample server Petstore server. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
                                                .contact(new Contact()
                                                                .email("sanhpv@vnpay.vn")
                                                                .name("sanhpham")
                                                                .url("https://loda.me/"))
                                                .termsOfService("http://swagger.io/terms/")
                                                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
        }
}