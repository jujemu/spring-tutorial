package hello.hellospring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @GetMapping("/api/hello")
    public String getIndex() {
        return "Hello world!";
    }
}
