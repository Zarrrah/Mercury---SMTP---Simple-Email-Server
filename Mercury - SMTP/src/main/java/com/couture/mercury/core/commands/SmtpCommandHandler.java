package com.couture.mercury.core.commands;

import com.couture.mercury.core.session.SessionState;

/**
 * Handles the processing and execution of SMTP commands.
 */
public interface SmtpCommandHandler {

    /**
     * Handles the execution of a given SMTP command.
     *
     * @param command SMTP Command to execution.
     * @param currentState Current session state.
     * @return CommandResult indicating execution outcome.
     * @throws SmtpCommandException If command execution fails.
     */
    SmtpCommandResult handle(SmtpCommand command, SessionState currentState) throws SmtpCommandException;

    /**
     * Determines if a command can be executed in the current session state.
     *
     * @param command SMTP Command to check.
     * @param currentState Current session state.
     * @return Detailed validation result.
     */
    ValidationResult isValidForState(SmtpCommand command, SessionState currentState);
}
