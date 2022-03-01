package karolisw.rest.oving.springbootrestcalculator;

import karolisw.rest.oving.springbootrestcalculator.model.MathExpression;
import karolisw.rest.oving.springbootrestcalculator.repo.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
public class SpringbootRestCalculatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestCalculatorApplication.class, args);
	}

	@Autowired
	private ExpressionRepository repository;



	@Override
	public void run(String... args) throws Exception {

		// Creating 3 mock-expressions for testing, as well as to have some data in db automatically
		MathExpression expression1 = MathExpression.builder()
				.expression("2+2")
				.answer(4.0)
				.build();

		MathExpression expression2 = MathExpression.builder()
				.expression("22-7")
				.answer(15.0)
				.build();

		MathExpression expression3 = MathExpression.builder()
				.expression("1-7")
				.answer(-6.0)
				.build();

		// Calling save method from Jpa in order to send the expressions to the db (POST)
		repository.save(expression1);
		repository.save(expression2);
		repository.save(expression3);

	}



}
