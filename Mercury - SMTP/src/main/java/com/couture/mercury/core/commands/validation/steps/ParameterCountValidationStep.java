package com.couture.mercury.core.commands.validation.steps;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.validation.SmtpValidationStep;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.ValidationResultFactory;
import com.couture.mercury.core.session.SessionContext;
import com.couture.mercury.core.session.SessionState;

/**
 * Validation step that checks if the command has the expected number of parameters.
 */
public class ParameterCountValidationStep extends SmtpValidationStep {
    private final int m_expectedCount;
    private final boolean m_exactMatch;

    /**
     * Creates a new parameter count validation step requiring an exact match.
     *
     * @param expectedCount The expected number of parameters.
     */
    public ParameterCountValidationStep(int expectedCount){
        this(expectedCount,true);
    }

    /**
     * Creates a parameter count validation step.
     *
     * @param expectedCount The expected number of parameters.
     * @param exactMatch If true, requires exactly the expected count, if false requires at least the expected count.
     */
    public ParameterCountValidationStep(int expectedCount, boolean exactMatch){
        m_expectedCount = expectedCount;
        m_exactMatch = exactMatch;
    }

    @Override
    protected ValidationResult doValidation(SmtpCommand command, SessionContext context){
        String[] parameters = command.getParameters();

        if(parameters == null){
            return ValidationResultFactory.failure("Command parameters are null");
        }

        if(parameters.length < m_expectedCount){
            return ValidationResultFactory.tooFewParameters();
        }

        if(m_exactMatch && parameters.length > m_expectedCount){
            return ValidationResultFactory.tooManyParameters();
        }

        return ValidationResultFactory.success();
    }
}
