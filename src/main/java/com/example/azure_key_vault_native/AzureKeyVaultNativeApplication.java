package com.example.azure_key_vault_native;

import com.azure.spring.cloud.autoconfigure.implementation.context.properties.AzureGlobalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AzureKeyVaultNativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzureKeyVaultNativeApplication.class, args);
    }

    @Bean
    public AzureGlobalProperties azureGlobalProperties() {
        return new AzureGlobalProperties();
    }
}
