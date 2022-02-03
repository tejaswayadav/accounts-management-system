package com.hachiman.poc.accountsservice.route;

import com.hachiman.poc.accountsservice.service.AccountCheckService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.springframework.stereotype.Component;

@Component
public class AccountsServiceRoute extends RouteBuilder {

    private final AccountCheckService accountCheckService;

    public AccountsServiceRoute(AccountCheckService accountCheckService) {
        this.accountCheckService = accountCheckService;
    }

    @Override
    public void configure() throws Exception {
        rest()
                .get("/fetch-pots")
                .route()
                .saga()
                .propagation(SagaPropagation.MANDATORY)
                .to("direct:fetchPots")
                .compensation("direct:cancelPotFetching");

        from("direct:fetchPots")
                .bean(this.accountCheckService,
                        "fetchPotAttributes()").marshal().json()
                .log("Pot fetching done");

        from("direct:cancelPotFetching")
                .bean(this.accountCheckService, "cancelPotFetching()")
                .log("Pot fetching cancelled");
    }
}
