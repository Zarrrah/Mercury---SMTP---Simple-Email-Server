package com.couture.mercury.core.commands.validation.steps;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.validation.SmtpValidationStep;
import com.couture.mercury.core.commands.validation.ValidationConstants;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.ValidationResultFactory;
import com.couture.mercury.core.session.SessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validation step that checks if the command exceeds the maximum allowed line length.
 * SMTP protocol (RFC 5321) specifies a maximum line length of 512 characters including CRLF.
 */
public class MaxLineLengthValidationStep extends SmtpValidationStep {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaxLineLengthValidationStep.class);
    private final int m_maxLength;

    /**
     * Creates a new max line length validation step with the default maximum length.
     */
    public MaxLineLengthValidationStep(){
        m_maxLength = ValidationConstants.MAX_COMMAND_LENGTH;
    }

    @Override
    protected ValidationResult doValidation(SmtpCommand command, SessionContext context){
        String rawCommand = command.toString();

        // If the command doesn't include the raw command string, we can't validate.
        if(rawCommand == null){
            LOGGER.warn("Cannot validate command length: raw command string is null. This is likely a programming error");
            return ValidationResultFactory.success();
        }

        // Calculate the total length, including CRLF that would be added.
        int totalLength = rawCommand.length() + ValidationConstants.LINE_ENDING.length();

        if(totalLength > m_maxLength){
            return ValidationResultFactory.commandTooLong(m_maxLength);
        }

        return ValidationResultFactory.success();
    }
}
