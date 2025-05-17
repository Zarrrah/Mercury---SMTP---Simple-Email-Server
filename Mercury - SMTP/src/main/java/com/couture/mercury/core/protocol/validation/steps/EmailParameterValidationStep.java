package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.util.ValidationConstants;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.session.SessionContext;

import java.util.regex.Pattern;

/**
 * Validation step that validates an email address parameter.
 */
public class EmailParameterValidationStep extends ValidationStep {
    private final Pattern m_emailPattern;
    private final int m_parameterIndex;

    /**
     * Creates a new email parameter validation step for the first parameter.
     */
    public EmailParameterValidationStep(){
        this(0);
    }

    /**
     * Creates a new email parameter validation step.
     *
     * @param parameterIndex The index parameter to validate as an email address.
     */
    public EmailParameterValidationStep(int parameterIndex){
        m_emailPattern = Pattern.compile(ValidationConstants.EMAIL_PATTERN);
        m_parameterIndex = parameterIndex;
    }

    @Override
    protected ValidationResult doValidation(Command command, SessionContext context){
        String[] parameters = command.getParameters();

        // This step assumes previous steps have already verified parameter count.
        if(parameters.length <= m_parameterIndex){
            return ValidationResultFactory.missingParameter("Email Address");
        }

        String email = parameters[m_parameterIndex];
        if(email == null || email.isEmpty()){
            return ValidationResultFactory.missingParameter("Email Address");
        }

        if(!m_emailPattern.matcher(email).matches()){
            return ValidationResultFactory.invalidEmailAddress(email);
        }

        return ValidationResultFactory.success();
    }
}
