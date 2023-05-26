package domainapp.modules.simple.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.jdo.annotations.Column;

import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.spec.AbstractSpecification;

@Column(length = Name.MAX_LEN, allowsNull = "false")
@Property(maxLength = Name.MAX_LEN)
@Parameter(maxLength = Name.MAX_LEN)
@PropertyLayout(named = "Apellido")
@ParameterLayout(named = "Apellido")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {

	int MAX_LEN = 40;

	class Spec extends AbstractSpecification<String> {
		@Override
		public String satisfiesSafely(String candidate) {
			for (char prohibitedCharacter : "&%$!".toCharArray()) {
				if (candidate.contains("" + prohibitedCharacter)) {
					return "Character '" + prohibitedCharacter + "' is not allowed.";
				}
			}
			return null;
		}
	}

}
