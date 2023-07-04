package com.acme.finalexampractice.platform.nature.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("natureMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public BigTreeMapper bigTreeMapper(){return new BigTreeMapper();}

    @Bean
    public GreenLeafMapper greenLeafMapper(){return new GreenLeafMapper();}
}
