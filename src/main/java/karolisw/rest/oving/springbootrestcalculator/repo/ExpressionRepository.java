package karolisw.rest.oving.springbootrestcalculator.repo;

import karolisw.rest.oving.springbootrestcalculator.model.MathExpression;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface extends JpaRepository, which means that it is not necessary to
 * write code for deleting, saving, updating, storing etc. towards the db as it is included in Jpa
 *
 * @params #1 = the name of the entity
 * @params #2 = the type of the entity key (long id)
 */
public interface ExpressionRepository extends JpaRepository<MathExpression, Long> {

}
