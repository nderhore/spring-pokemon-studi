package com.pokemon.studi.constraint;

import com.pokemon.studi.constraint.validator.CapaciteConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {CapaciteConstraintValidator.class})
public @interface CapaciteConstraint {

    String message() default "le libelle est vide ou null";

    Class<?> [] groups() default {};

    Class <? extends Payload> [] payload() default {};
}
