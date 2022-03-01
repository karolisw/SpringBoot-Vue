package karolisw.rest.oving.springbootrestcalculator.model;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "calculations")
/**
 * Class depicts the entity model of the Expression - objects to be stored in the database
 */
public class MathExpression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "expression", nullable = false)
    private String expression;

    @Column(name = "answer", nullable = false)
    private Double answer;
}
