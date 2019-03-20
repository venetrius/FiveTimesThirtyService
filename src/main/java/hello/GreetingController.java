package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
  //@RequestMapping(method=PUT)
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/bye")
    public Bye bye(@RequestParam(value="name", defaultValue="Shoot") String name) {
        return new Bye(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
  /*  @RequestMapping("/bet")
    public Bet bet(@RequestParam(value="state") String name) {
        return new Bet(String value));
    } */
}