package com.couture.mercury.core.commands.validation.steps;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.SmtpCommandType;
import com.couture.mercury.core.commands.validation.SmtpValidationStep;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.ValidationResultFactory;
import com.couture.mercury.core.session.SessionContext;
import com.couture.mercury.core.session.SessionState;

/**
 * Validation step that checks if the command is of the expected type.
 */
public class CommandTypeValidationStep extends SmtpValidationStep {
    private final SmtpCommandType m_expectedType;

    /**
     * Creates a new command type validation step.
     *
     * @param expectedType The expected command type.
     */
    public CommandTypeValidationStep(SmtpCommandType expectedType){
        m_expectedType = expectedType;
    }

    @Override
    protected ValidationResult doValidation(SmtpCommand command, SessionContext context) {
        if(command.getCommandType() != m_expectedType){
            return ValidationResultFactory.failure(
                    "Invalid command type: expected " + m_expectedType + " but got " + command.getCommandType());
        }

        return ValidationResultFactory.success();
    }
}

