package com.couture.mercury.core.protocol.validation.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.util.ValidationConstants;
import com.couture.mercury.core.protocol.validation.CommandValidator;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.validation.steps.CommandTypeValidationStep;
import com.couture.mercury.core.protocol.validation.steps.DomainParameterValidationStep;
import com.couture.mercury.core.protocol.validation.steps.ParameterCountValidationStep;
import com.couture.mercury.core.protocol.validation.steps.StateValidationStep;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.session.SessionState;

/**
 * Validator for HELO command.
 * Validates syntax and state for HELO SMTP command using Chain of Responsibility pattern.
 */
public class HeloCommandValidator implements CommandValidator<Command> {
    private final ValidationStep m_validationChain;

    /**
     * Creates a HELO command validator.
     */
    public HeloCommandValidator(){
        // Build the validation chain for HELO commands.
        m_validationChain = new CommandTypeValidationStep(CommandType.HELO);
        m_validationChain.setNext(new StateValidationStep(SessionState.CONNECT, SessionState.HELO))
                        .setNext(new ParameterCountValidationStep(ValidationConstants.HELO_PARAM_COUNT))
                        .setNext(new DomainParameterValidationStep());
    }

    @Override
    public ValidationResult validate(Command command, SessionContext context){
        if(command == null){
            return ValidationResultFactory.nullCommand();
        }

        if(context == null){
            return ValidationResultFactory.nullSession();
        }

        return m_validationChain.validate(command, context);
    }
}
