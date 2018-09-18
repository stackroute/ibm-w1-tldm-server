package com.stackroute.tldm.config;

import java.util.Map;

import com.stackroute.tldm.model.User;

@FunctionalInterface
public interface SecurityTokenGenerator {
	Map<String, String> generateToken(User user);
}
