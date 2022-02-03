package com.hachiman.poc.keysservice.service;

import com.hachiman.poc.keysservice.model.Key;
import org.springframework.stereotype.Service;

@Service
public class KeyGeneratorService {
    public Key generateRTCKey() {
        return new Key("public-rtc-key");
    }
}
