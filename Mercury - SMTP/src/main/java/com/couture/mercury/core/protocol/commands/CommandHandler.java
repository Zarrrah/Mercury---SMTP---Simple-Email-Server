package com.couture.mercury.core.protocol.commands;

import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.session.SessionState;

/**
 * Handles the processing and execution of SMTP commands.
 */
public interface CommandHandler {

    /**
     * Handles the execution of a given SMTP command.
     *
     * @param command SMTP Command to execution.
     * @param currentState Current session state.
     * @return CommandResult indicating execution outcome.
     * @throws CommandException If command execution fails.
     */
    CommandResult handle(Command command, SessionState currentState) throws CommandException;

    /**
     * Determines if a command can be executed in the current session state.
     *
     * @param command SMTP Command to check.
     * @param currentState Current session state.
     * @return Detailed validation result.
     */
    ValidationResult isValidForState(Command command, SessionState currentState);
}
