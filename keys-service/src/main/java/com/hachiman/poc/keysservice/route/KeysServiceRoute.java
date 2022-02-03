package com.hachiman.poc.keysservice.route;

import com.hachiman.poc.keysservice.service.KeyGeneratorService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.springframework.stereotype.Component;

@Component
public class KeysServiceRoute extends RouteBuilder {

    private final KeyGeneratorService keyGeneratorService;

    public KeysServiceRoute(KeyGeneratorService keyGeneratorService) {
        this.keyGeneratorService = keyGeneratorService;
    }

    @Override
    public void configure() throws Exception {
        rest()
                .get("/generate-rtc-key")
                .route()
                .saga()
                .propagation(SagaPropagation.MANDATORY)
                .to("direct:generateKey").marshal().json();

        from("direct:generateKey")
                .bean(this.keyGeneratorService,
                        "generateRTCKey()")
                .log("Key generated");
    }
}
