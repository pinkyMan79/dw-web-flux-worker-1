package one.terenin.webfluxconsumer1.api;

import one.terenin.webfluxconsumer1.dto.DataBundle;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RequestMapping("/transfer")
public interface Http1Consumer1Api {

    // produces data stream
    @GetMapping(value = "/stream/string", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> streamJsonData();

    @GetMapping(value = "/stream/parquet", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<byte[]> streamParquetData();

    //produces data stream by octet size
    @GetMapping(value = "/stream/octet/string", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    Flux<String> streamOctetJsonData();

    @GetMapping(value = "/stream/octet/parquet", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    Flux<byte[]> streamOctetParquetData();

    //produces data batches
    @GetMapping("/sized/json/{count}")
    Flux<String> sizedJsonData(@PathVariable("count") int count);

    @GetMapping("/sized/parquet/{count}")
    Flux<byte[]> sizedParquetData(@PathVariable("count") int count);

    // produces data batches with callbacks and fallbacks
    @GetMapping("/duplex/json/{count}")
    Flux<String> duplexJsonData(@PathVariable("count") int count);

    @GetMapping("/duplex/parquet/{count}")
    Flux<byte[]> duplexParquetData(@PathVariable("count") int count);

    @PostMapping(value = "/stream/string", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<DataBundle> consumeStreamJsonData(@RequestBody DataBundle dataBundle);

    @PostMapping(value = "/stream/parquet", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<byte[]> consumeStreamParquetData(@RequestBody String utf8EncodedByteArray);

    //produces data stream by octet size
    @PostMapping(value = "/stream/octet/string", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    Flux<DataBundle> consumeStreamOctetJsonData(@RequestBody DataBundle dataBundle);

    @PostMapping(value = "/stream/octet/parquet", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    Flux<byte[]> consumeStreamOctetParquetData(@RequestBody String utf8EncodedByteArray);

    //produces data batches
    @PostMapping("/sized/json/")
    Flux<DataBundle> consumeSizedJsonData(@RequestBody List<DataBundle> dataBundle);

    @PostMapping("/sized/parquet/")
    Flux<byte[]> consumeSizedParquetData(@RequestBody List<String> utf8EncodedByteArray);

    // produces data batches with callbacks and fallbacks
    @PostMapping("/duplex/json/")
    Flux<DataBundle> consumeDuplexJsonData(@RequestBody List<DataBundle> dataBundle);

    @PostMapping("/duplex/parquet/")
    Flux<byte[]> consumeDuplexParquetData(@RequestBody List<String> utf8EncodedByteArray);
}
