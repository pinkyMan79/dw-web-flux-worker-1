package one.terenin.webfluxconsumer1.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.terenin.webfluxconsumer1.configuration.propertysource.KafkaConfigurationPropertySource;
import one.terenin.webfluxconsumer1.holder.DataHolder;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataConsumerService {

    private final KafkaConfigurationPropertySource propertySource;
    // reactor data concentrators
    private final Sinks.Many<String> sinksJson = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<byte[]> sinksParquet = Sinks.many().multicast().onBackpressureBuffer();

    @KafkaListener(topics = "jsonTopic",
            groupId = "cg-web-flux-net",
            containerFactory = "kafkaJsonListenerContainerFactory",
            topicPartitions = @TopicPartition(
                    topic = "jsonTopic", partitions = {"0"}
            )
    )
    public void listenJson(String messages) {
        Sinks.EmitResult emitResult = sinksJson.tryEmitNext(messages);
        if (emitResult.isFailure()) {
            log.warn("Can't process message json: {}", messages);
        }
    }
    
    @KafkaListener(topics = "parquetTopic",
            groupId = "cg-web-flux-net",
            containerFactory = "kafkaParquetListenerContainerFactory",
            topicPartitions = @TopicPartition(
            topic = "parquetTopic", partitions = {"0"}
            )
    )
    public void listenParquet(byte[] messages) {
        Sinks.EmitResult emitResult = sinksParquet.tryEmitNext(messages);
        if (emitResult.isFailure()) {
            log.warn("Can't process message parquet: {}", messages);
        }
    }

    public Flux<String> jsonDataAsFlux() {
        return sinksJson.asFlux();
    }

    public Flux<byte[]> parquetDataAsFlux() {
        return sinksParquet.asFlux();
    }
}
