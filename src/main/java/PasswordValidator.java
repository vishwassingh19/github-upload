import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean contains = value.contains("hello");
		return contains;
	}

}
