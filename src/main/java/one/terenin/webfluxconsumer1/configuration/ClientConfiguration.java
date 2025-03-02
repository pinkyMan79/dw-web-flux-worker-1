package one.terenin.webfluxconsumer1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public WebClient webClientJson() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/json")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer
                                .defaultCodecs()
                                .maxInMemorySize(8 * 1024 * 1024))
                        .build())
                .build();
    }
    @Bean
    public WebClient webClientParquet() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/parquet")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer
                                .defaultCodecs()
                                .maxInMemorySize(8 * 1024 * 1024))
                        .build())
                .build();
    }
}
