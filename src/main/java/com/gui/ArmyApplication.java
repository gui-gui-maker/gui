package com.gui;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringCloudApplication
public class ArmyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmyApplication.class, args);
    }
    
    @Bean
   	public BCryptPasswordEncoder bCryptPasswordEncoder() {
   		return new BCryptPasswordEncoder();
   	}
}
