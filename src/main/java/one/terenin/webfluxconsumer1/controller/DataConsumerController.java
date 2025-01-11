package one.terenin.webfluxconsumer1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import one.terenin.webfluxconsumer1.api.Http1Consumer1Api;
import one.terenin.webfluxconsumer1.consumer.DataConsumerService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
public class DataConsumerController implements Http1Consumer1Api {

    private final DataConsumerService dataConsumerService;
    private final ObjectMapper jacksonObjectMapper;
    private final WebClient webClientJson;
    private final WebClient webClientParquet;

    @Override
    public Flux<String> streamJsonData() {
        return dataConsumerService.jsonDataAsFlux()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<byte[]> streamParquetData() {
        return dataConsumerService.parquetDataAsFlux()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<String> streamOctetJsonData() {
        return null;
    }

    @Override
    public Flux<byte[]> streamOctetParquetData() {
        return null;
    }

    @Override
    public Flux<String> sizedJsonData(int count) {
        return  dataConsumerService.jsonDataAsFlux()
                .limitRate(count)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<byte[]> sizedParquetData(int count) {
        return dataConsumerService.parquetDataAsFlux()
                .limitRate(count)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<String> duplexJsonData(int count) {
        // use Webclient here and make response with it like endless pipe
        return webClientJson.get().exchangeToFlux(it -> it.bodyToFlux(String.class)).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<byte[]> duplexParquetData(int count) {
        return webClientParquet.get().exchangeToFlux(it -> it.bodyToFlux(byte[].class)).subscribeOn(Schedulers.boundedElastic());
    }
}
