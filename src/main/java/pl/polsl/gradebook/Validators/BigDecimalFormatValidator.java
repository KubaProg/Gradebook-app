package pl.polsl.gradebook.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BigDecimalFormatValidator implements ConstraintValidator<BigDecimalFormat, BigDecimal> {

    @Override
    public void initialize(BigDecimalFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        // Check if the value is null or already a valid BigDecimal
        if (value == null || value instanceof BigDecimal) {
            return true;
        }

        // Try parsing the value as a BigDecimal
        try {
            new BigDecimal(value.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
