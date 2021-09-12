package com.example.hibernate.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        final Object firstObj = new BeanWrapperImpl(o).getPropertyValue(first);
        final Object secondObj = new BeanWrapperImpl(o).getPropertyValue(second);

        boolean valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);

        if (!valid){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(first)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
