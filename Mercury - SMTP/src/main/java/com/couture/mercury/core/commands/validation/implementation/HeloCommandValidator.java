package com.couture.mercury.core.commands.validation.implementation;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.SmtpCommandType;
import com.couture.mercury.core.commands.validation.*;
import com.couture.mercury.core.commands.validation.steps.CommandTypeValidationStep;
import com.couture.mercury.core.commands.validation.steps.DomainParameterValidationStep;
import com.couture.mercury.core.commands.validation.steps.ParameterCountValidationStep;
import com.couture.mercury.core.commands.validation.steps.StateValidationStep;
import com.couture.mercury.core.session.SessionContext;
import com.couture.mercury.core.session.SessionState;

/**
 * Validator for HELO command.
 * Validates syntax and state for HELO SMTP command using Chain of Responsibility pattern.
 */
public class HeloCommandValidator implements SmtpCommandValidator<SmtpCommand> {
    private final SmtpValidationStep m_validationChain;

    /**
     * Creates a HELO command validator.
     */
    public HeloCommandValidator(){
        // Build the validation chain for HELO commands.
        m_validationChain = new CommandTypeValidationStep(SmtpCommandType.HELO);
        m_validationChain.setNext(new StateValidationStep(SessionState.CONNECT, SessionState.HELO))
                        .setNext(new ParameterCountValidationStep(ValidationConstants.HELO_PARAM_COUNT))
                        .setNext(new DomainParameterValidationStep());
    }

    @Override
    public ValidationResult validate(SmtpCommand command, SessionContext context){
        if(command == null){
            return ValidationResultFactory.nullCommand();
        }

        if(context == null){
            return ValidationResultFactory.nullSession();
        }

        return m_validationChain.validate(command, context);
    }
}
