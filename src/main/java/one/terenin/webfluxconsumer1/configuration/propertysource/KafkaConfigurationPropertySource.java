package one.terenin.webfluxconsumer1.configuration.propertysource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:/application.yaml")
public class KafkaConfigurationPropertySource {

    String bootstrapServers;
    Class<?> keyDeserializerString;
    Class<?> keyDeserializerBinary;
    Class<?> valueDeserializerString;
    Class<?> valueDeserializerBinary;
    String groupId;
    String autoOffsetReset;
    Integer maxPollRecords;

    @SneakyThrows
    public KafkaConfigurationPropertySource(@Value("${bootstrap.server.host}") String bootstrapServers,
                                            @Value("${key.deserializer.binary}") String keyDeserializerBinary,
                                            @Value("${value.deserializer.binary}") String valueDeserializerBinary,
                                            @Value("${key.deserializer.string}") String keyDeserializerString,
                                            @Value("${value.deserializer.string}") String valueDeserializerString,
                                            @Value("${group.id}") String groupId,
                                            @Value("${auto.offset.reset}") String autoOffsetReset,
                                            @Value("${max.poll.records}") Integer maxPollRecords
                                            ) {
        this.bootstrapServers = bootstrapServers;
        this.keyDeserializerString = Class.forName(keyDeserializerBinary);
        this.valueDeserializerString = Class.forName(valueDeserializerBinary);
        this.keyDeserializerBinary = Class.forName(keyDeserializerString);
        this.valueDeserializerBinary = Class.forName(valueDeserializerString);
        this.groupId = groupId;
        this.autoOffsetReset = autoOffsetReset;
        this.maxPollRecords = maxPollRecords;
    }

    public Map<String, Object> forStringAsMap() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.keyDeserializerString,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.valueDeserializerString,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.autoOffsetReset,
                ConsumerConfig.GROUP_ID_CONFIG, this.groupId
                //ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.maxPollRecords
        );
    }

    public Map<String, Object> forBinaryaAsMap() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.keyDeserializerBinary,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.valueDeserializerBinary,
                ConsumerConfig.GROUP_ID_CONFIG, this.groupId,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.autoOffsetReset
                //ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.maxPollRecords
        );
    }
}
