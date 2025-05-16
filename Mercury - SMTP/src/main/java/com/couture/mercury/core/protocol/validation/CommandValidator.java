package com.couture.mercury.core.protocol.validation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;

/**
 * An interface presenting a validator that is capable of validating a specific SMTP command.
 * Follows the Strategy Pattern for command-specific validation.
 */
public interface CommandValidator<T extends Command> {

    /**
     * Validates the entire command.
     *
     * @param command Raw command string.
     * @param sessionState Current SMTP session state.
     * @return Detailed validation result.
     */
    ValidationResult validate(T command, SessionContext sessionContext);
}