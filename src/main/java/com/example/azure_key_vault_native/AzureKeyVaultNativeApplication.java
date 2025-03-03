package com.example.azure_key_vault_native;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.implementation.models.SecretAttributes;
import com.azure.security.keyvault.secrets.implementation.models.SecretBundle;
import com.azure.spring.cloud.autoconfigure.implementation.context.properties.AzureGlobalProperties;
import com.azure.spring.cloud.core.customizer.AzureServiceClientBuilderCustomizer;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RegisterReflectionForBinding({SecretBundle.class, SecretAttributes.class})
public class AzureKeyVaultNativeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AzureKeyVaultNativeApplication.class, args);
    }

    @Bean
    public AzureGlobalProperties azureGlobalProperties() {
        return new AzureGlobalProperties();
    }

    @Bean
    public AzureServiceClientBuilderCustomizer<SecretClientBuilder> secretClientBuilderCustomizer() {
        return builder -> builder.credential(new DefaultAzureCredentialBuilder().build());
    }

    @Bean
    public ApplicationListener<ApplicationStartedEvent> applicationInitializers(SecretClient secretClient) {
        return event -> {
            System.out.println("Azure Key Vault Native Application Started");
            String password = secretClient.getSecret("some-secret").getValue();
            System.out.println(password);
        };
    }
}
