package com.godoineto.simplepipe.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    private static final String AUTHORIZATION = "Authorization";
    private static final String REPOSITORY_LICENSE = "https://github.com/godoineto/simple-pipe/blob/master/LICENSE";

    @Bean
    public Docket swaggerSpringfoxApiDocket() {
        Contact contact = new Contact("Neto Godoi",
                "https://github.com/godoineto",
                "netogodoi.dev@gmail.com");

        List<VendorExtension> vendorExtensions = new ArrayList<>();

        ApiInfo apiInfo = new ApiInfo("Simple Pipe",
                "Just a simple solution to integrate with <b><a href=\"https://developers.pipedrive.com/docs/api/v1/\">Pipedrive</a></b>",
                "1.0", "", contact, "MIT Licence",
                REPOSITORY_LICENSE, vendorExtensions);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.godoineto.simplepipe.api"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {authorizationScope};
        return Lists.newArrayList(new SecurityReference(AUTHORIZATION, authorizationScopes));
    }
}
