package com.couture.mercury.core.commands.validation.steps;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.validation.*;
import com.couture.mercury.core.session.SessionContext;

import java.util.regex.Pattern;

/**
 * Validation step that checks if a domain parameter is valid.
 */
public class DomainParameterValidationStep extends SmtpValidationStep {
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
    protected ValidationResult doValidation(SmtpCommand command, SessionContext context){
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
