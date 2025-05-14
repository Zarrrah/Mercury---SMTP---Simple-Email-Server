package com.couture.mercury.core.commands;

import java.io.Serializable;

/**
 * Represents an SMTP command with execution and validation capabilities.
 */
public interface SmtpCommand extends Serializable {

    /**
     * Executes the SMTP command.
     *
     * @return Result of command execution.
     * @throws SmtpCommandException If command execution fails.
     */
    SmtpCommandResult execute() throws SmtpCommandException;

    /**
     * Validates the command's parameters and state.
     *
     * @return Detailed validation result.
     */
    ValidationResult validate();

    /**
     * Retrieves the type of SMTP command.
     *
     * @return SmtpCommandType representing the command.
     */
    SmtpCommandType getCommandType();
}