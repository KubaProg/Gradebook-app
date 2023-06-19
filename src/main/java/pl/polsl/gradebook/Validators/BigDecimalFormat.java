package pl.polsl.gradebook.Validators;

import pl.polsl.gradebook.Validators.BigDecimalFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BigDecimalFormatValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalFormat {
    String message() default "Invalid BigDecimal format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
