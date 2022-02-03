package com.hachiman.poc.accountsservice.service;

import com.hachiman.poc.accountsservice.model.PotAttributes;
import org.springframework.stereotype.Service;

@Service
public class AccountCheckService {
    public PotAttributes fetchPotAttributes() {
        return new PotAttributes("not-exists");
    }

    public void cancelPotFetching() {

    }
}
