package karolisw.rest.oving.springbootrestcalculator.controller;

import karolisw.rest.oving.springbootrestcalculator.dto.ExpressionDto;
import karolisw.rest.oving.springbootrestcalculator.model.MathExpression;
import karolisw.rest.oving.springbootrestcalculator.repo.ExpressionRepository;
import karolisw.rest.oving.springbootrestcalculator.service.ExpressionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ ResponseBody is not necessary when using RestController, because mapping to JSON is automatic
 * RestController will handle the web communication, meaning this is the top layer
 * The rest of the layers will be abstracted away (not visible to the frontend)
 *
 * Full URL: http://localhost:8080/calculator/expressions
     * mathExpressions contains expression-objects: {id}, {expression}, {answer}
 */
@RestController
@RequestMapping("/calculator") // means all other mappings actually start with this!
@CrossOrigin("http://localhost:8081/") // this allows the api to be used on this address as well as on the current address
public class ExpressionController {
    List<MathExpression> mathExpressions = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(ExpressionController.class);
    /**
     * Dependency injection of service
     */
    @Autowired
    private ExpressionService service;
    @Autowired
    private ExpressionRepository repository;

    /**
     * Write "http://localhost:8080/calculator/expressions" into the browser and this method will automatically -
     * @return a list of all mathExpressions objects (including their attributes) inside the browser
     */
    @GetMapping("/expressions")
    public List<MathExpression> fetchExpressions() {
        return repository.findAll(); // Included in Jpa library!
    }

    //@PutMapping("/mathExpressions")

    /**
     * It is normal to instantiate a Logger in the class that needs it, instead of having a global logger
     * @return

    @RequestMapping("/")
    public Expression message() {
        logger.trace("trace test");
        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");

        // btw: expressionMessage() is just a test to illustrate use of the rest-layers
        return this.service.expressionMessage();

    }
    */

    /**
     * Returns the expression with the specific id
     * @param id is an integer unique key
     * @return a JSON object containing the requested object
     */
    @GetMapping("/expressions/{id}")
    public MathExpression fetchElementById(@PathVariable("id") long id){
        // print the JSON element to show that it is JSON
        System.out.println("Id: " + id);

        // we return the entire JSON object including the expression
        // frontend will take care of the object and display the desired part (answer)
        return repository.getById(id);
    }

    /**
     * The type we annotate with the @RequestBody annotation must correspond to the JSON sent
     * from the client-side Vue application (through use of Axios)
     *
     * @param mathExpression is the string with user input taken from the calculator
     * @return the finished MathExpression!
     */
    @PostMapping(path = "/expressions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MathExpression postExpression(@RequestBody ExpressionDto mathExpression) {
        // 1. calculate the answer
        String answer = service.calculate(mathExpression.getEquation());

        // 2. create a new MathExpression (empty)
        MathExpression newExpression = new MathExpression();

        // 3. set the expression and answer. The id will auto-increment on its own
        newExpression.setExpression(mathExpression.getEquation());
        newExpression.setAnswer(Double.parseDouble(answer));

        // 4. save the new expression in the repository!
        repository.save(newExpression);
        return newExpression;
    }
}
