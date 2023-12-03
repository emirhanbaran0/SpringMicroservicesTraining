package org.emirhanbaran.customer;


public record CustomerRegistirationRequest(String firstName,
                                           String lastName,
                                           String email) {
}
