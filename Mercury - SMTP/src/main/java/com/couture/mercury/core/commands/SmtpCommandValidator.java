package com.couture.mercury.core.commands;

import com.couture.mercury.core.session.SessionState;

/**
 * An interface presenting a validator that is capable of validating a specific SMTP command.
 */
public interface SmtpCommandValidator {

    /**
     * Validates the entire command.
     *
     * @param command Raw command string.
     * @return Detailed validation result.
     */
    ValidationResult validate(SmtpCommand command);

    /**
     * Checks if the command is valid for the current session state.
     *
     * @param sessionState Current SMTP session state.
     * @return Detailed validation result.
     */
    ValidationResult isValidForState(SessionState sessionState);

    /**
     * Validates command parameters.
     *
      * @param parameters Command parameters.
     * @return Detailed validation result.
     */
    ValidationResult validateParameters(String[] parameters);
}