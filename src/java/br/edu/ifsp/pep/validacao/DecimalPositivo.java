package br.edu.ifsp.pep.validacao;

import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, 
    ElementType.METHOD, 
    ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, 
    ElementType.PARAMETER} )
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@NotNull
@DecimalMin("0")
public @interface DecimalPositivo {

    @OverridesAttribute(constraint = DecimalMin.class, name = "message")
    String message() default "{br.edu.ifsp.pep.NumeroDecimal.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
