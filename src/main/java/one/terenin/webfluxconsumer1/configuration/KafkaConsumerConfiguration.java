package one.terenin.webfluxconsumer1.configuration;

import lombok.RequiredArgsConstructor;
import one.terenin.webfluxconsumer1.configuration.propertysource.KafkaConfigurationPropertySource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfiguration {

    private final KafkaConfigurationPropertySource kafkaConfigurationPropertySource;

    @Bean
    public ConsumerFactory<String, String> consumerFactoryJson() {
        return new DefaultKafkaConsumerFactory<>(kafkaConfigurationPropertySource.forStringAsMap());
    }

    @Bean
    public ConsumerFactory<String, byte[]> consumerFactoryParquet() {
        return new DefaultKafkaConsumerFactory<>(kafkaConfigurationPropertySource.forBinaryaAsMap());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaJsonListenerContainerFactory(
            @Qualifier("consumerFactoryJson") ConsumerFactory<String, String> cf
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cf);
        //factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaParquetListenerContainerFactory(
            @Qualifier("consumerFactoryParquet") ConsumerFactory<String, byte[]> cf
    ) {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cf);
        //factory.setBatchListener(true);
        return factory;
    }

}
