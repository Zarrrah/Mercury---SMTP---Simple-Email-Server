package com.couture.mercury.core.commands.validation;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.session.SessionContext;

/**
 * An interface presenting a validator that is capable of validating a specific SMTP command.
 * Follows the Strategy Pattern for command-specific validation.
 */
public interface SmtpCommandValidator<T extends SmtpCommand> {

    /**
     * Validates the entire command.
     *
     * @param command Raw command string.
     * @param sessionState Current SMTP session state.
     * @return Detailed validation result.
     */
    ValidationResult validate(T command, SessionContext sessionContext);
}