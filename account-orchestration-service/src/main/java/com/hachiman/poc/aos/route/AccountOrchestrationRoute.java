package com.hachiman.poc.aos.route;

import com.hachiman.poc.aos.model.PotAttributes;
import com.hachiman.poc.aos.service.AccountOrchestrationService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.SagaPropagation;
import org.springframework.stereotype.Component;

@Component
public class AccountOrchestrationRoute extends RouteBuilder {

    private final AccountOrchestrationService accountOrchestrationService;

    public AccountOrchestrationRoute(AccountOrchestrationService accountOrchestrationService) {
        this.accountOrchestrationService = accountOrchestrationService;
    }

    @Override
    public void configure() throws Exception {
        rest("/ams/orchestrator")
                .post("/open-accounts")
                .route()
                .saga()
                .log("Received new account opening request")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("orderId", header(Exchange.SAGA_LONG_RUNNING_ACTION))
                .to("http://accounts-service:5001/api/fetch-pots?bridgeEndpoint=true")
                .unmarshal(new JacksonDataFormat(PotAttributes.class))
                .process(exchange -> {
                    PotAttributes resp = exchange.getIn().getBody(PotAttributes.class);
                    log.info(resp.toString());
                    if (resp.getExistence().equalsIgnoreCase("exists")) {
                        throw new Exception("Pot already exists");
                    }
                })
                .to("http://keys-service:5003/api/generate-rtc-key?bridgeEndpoint=true")
                .propagation(SagaPropagation.REQUIRED)
                .end();

    }
}
