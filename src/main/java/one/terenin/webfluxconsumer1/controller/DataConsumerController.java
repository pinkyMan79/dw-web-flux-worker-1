package one.terenin.webfluxconsumer1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import one.terenin.webfluxconsumer1.api.Http1Consumer1Api;
import one.terenin.webfluxconsumer1.consumer.DataConsumerService;
import one.terenin.webfluxconsumer1.dto.DataBundle;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
public class DataConsumerController implements Http1Consumer1Api {

    private final DataConsumerService dataConsumerService;
    private final ObjectMapper jacksonObjectMapper;

    @Override
    public Flux<String> streamJsonData() {
        return dataConsumerService.jsonDataAsFlux().subscribeOn(Schedulers.boundedElastic());/*.filter(it -> {
            try {
                // do some work
                return jacksonObjectMapper.readValue(it, DataBundle.class).getOptions().entrySet().size() > 1;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });*/
    }

    @Override
    public Flux<byte[]> streamParquetData() {
        return null;
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
        return null;
    }

    @Override
    public Flux<byte[]> sizedParquetData(int count) {
        return null;
    }

    @Override
    public Flux<String> duplexJsonData(int count) {
        return null;
    }

    @Override
    public Flux<byte[]> duplexParquetData(int count) {
        return null;
    }
}
