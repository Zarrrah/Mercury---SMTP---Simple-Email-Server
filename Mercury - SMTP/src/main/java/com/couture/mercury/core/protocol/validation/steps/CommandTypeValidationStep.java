package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.session.SessionContext;

/**
 * Validation step that checks if the command is of the expected type.
 */
public class CommandTypeValidationStep extends ValidationStep {
    private final CommandType m_expectedType;

    /**
     * Creates a new command type validation step.
     *
     * @param expectedType The expected command type.
     */
    public CommandTypeValidationStep(CommandType expectedType){
        m_expectedType = expectedType;
    }

    @Override
    protected ValidationResult doValidation(Command command, SessionContext context) {
        if(command.getCommandType() != m_expectedType){
            return ValidationResultFactory.failure(
                    "Invalid command type: expected " + m_expectedType + " but got " + command.getCommandType());
        }

        return ValidationResultFactory.success();
    }
}

