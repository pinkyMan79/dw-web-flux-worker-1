package one.terenin.webfluxconsumer1.controller;

import lombok.RequiredArgsConstructor;
import one.terenin.webfluxconsumer1.api.Http1Consumer1Api;
import one.terenin.webfluxconsumer1.consumer.DataConsumerService;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class DataConsumerController implements Http1Consumer1Api {

    private final DataConsumerService dataConsumerService;

    @Override
    public Flux<String> streamJsonData() {
        return null;
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
