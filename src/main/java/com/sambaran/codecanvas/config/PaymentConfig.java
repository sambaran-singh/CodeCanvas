package com.sambaran.codecanvas.config;

import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sambaran
 */
@Configuration
public class PaymentConfig {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    @Bean
    public StripeClient stripeClient() {
        return new StripeClient(stripeSecretKey);
    }
}
