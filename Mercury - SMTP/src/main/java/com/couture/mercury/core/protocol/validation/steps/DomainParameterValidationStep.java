package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.util.ValidationConstants;
import com.couture.mercury.core.protocol.util.ValidationErrorMessages;
import com.couture.mercury.core.protocol.validation.*;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;

import java.util.regex.Pattern;

/**
 * Validation step that checks if a domain parameter is valid.
 */
public class DomainParameterValidationStep extends ValidationStep {
    private final Pattern m_domainPattern;
    private final int m_parameterIndex;

    /**
     * Creates a new domain parameter validation step for the first parameter.
     */
    public DomainParameterValidationStep(){
        this(0);
    }

    /**
     * Creates a new domain parameter validation step.
     *
     * @param parameterIndex The index of the parameter to validate as a domain.
     */
    public DomainParameterValidationStep(int parameterIndex){
        m_domainPattern = Pattern.compile(ValidationConstants.DOMAIN_PATTERN);
        m_parameterIndex = parameterIndex;
    }

    @Override
    protected ValidationResult doValidation(Command command, SessionContext context){
        String[] parameters = command.getParameters();

        // This step assumes previous steps have already verified parameter count
        if(parameters.length <= m_parameterIndex){
            return ValidationResultFactory.failure(ValidationErrorMessages.HELO_REQUIRES_DOMAIN);
        }

        String domain = parameters[m_parameterIndex];
        if(domain == null || domain.isEmpty()){
            return ValidationResultFactory.failure(ValidationErrorMessages.HELO_REQUIRES_DOMAIN);
        }

        if(!m_domainPattern.matcher(domain).matches()){
            return ValidationResultFactory.invalidDomain(domain);
        }

        return ValidationResultFactory.success();
    }
}
