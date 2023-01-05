package com.pokemon.studi.constraint.validator;

import com.pokemon.studi.constraint.CapaciteConstraint;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapaciteConstraintValidator
        implements ConstraintValidator<CapaciteConstraint,String> {
    @Override
    public void initialize(CapaciteConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String libelle, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasLength(libelle);
    }
}
