package com.example.runnerz.User;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {
}
