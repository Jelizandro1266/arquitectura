package org.qtx.eventos.utilerias;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.qtx.eventos.exceptions.ValidadorException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRequest {
	
	public void validarPojo(Object object) throws ValidadorException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(object);
		List<String> entradasIncorrectas = new ArrayList<>();
		for (ConstraintViolation<Object> violation : violations) {
		    System.out.println(violation.getMessage());
		    entradasIncorrectas.add(violation.getMessage());
		}
		
		if(entradasIncorrectas.isEmpty())
			return;
			
		System.out.println("EntradasIncorrectas: " +entradasIncorrectas);
		throw new ValidadorException(entradasIncorrectas);
		
		
	}
	

}
