package ftt;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bet")
public class GreetingController {

	@CrossOrigin
    @RequestMapping(method= RequestMethod.GET)
    public  Bet bet(@RequestParam(value="state") String state) {
        return (new Bet(state));
    }
	
	@CrossOrigin
    @RequestMapping(method= RequestMethod.POST)
    //@PostMapping
    public  boolean postbet(@RequestParam(value="state") String state) {
		return Bet.postBet(state);
    }

	
	
	   
}