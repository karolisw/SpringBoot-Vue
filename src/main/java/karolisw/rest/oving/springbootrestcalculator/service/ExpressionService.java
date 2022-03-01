package karolisw.rest.oving.springbootrestcalculator.service;
import karolisw.rest.oving.springbootrestcalculator.model.MathExpression;
import karolisw.rest.oving.springbootrestcalculator.repo.ExpressionRepository;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequestMapping("/calculator") // means all other mappings actually start with this!
public class ExpressionService {

    /**
     * This is the method that will do the actual calculation of the expression
     *
     * @param expression is provided by the client
     * @return a string containing the calculated value
     */
    public String calculate(String expression){
        Expression newExpression = new ExpressionBuilder(expression).build();
        return String.valueOf(newExpression.evaluate());
    }

}
