package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP QUIT command.
 * QUIT terminates the SMTP session.
 * RFC 5321 defines QUIT as the command that instructs the server to end the session.
 *
 * <p>Usage: The QUIT command can be sent at any time to gracefully terminate the SMTP session.
 * After sending QUIT, the client should wait for the server's 221 response before closing
 * the connection. The server will close the connection after sending its response.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: QUIT
 * S: 221 smtp.example.com Service closing transmission channel
 * </pre>
 * </p>
 *
 * <p>The QUIT command is valid in any SMTP state and terminates any ongoing mail transaction.
 * It is considered good practice to always issue a QUIT command before closing the connection
 * to allow the server to clean up resources properly.</p>
 *
 * <p>If an SMTP client simply disconnects without sending a QUIT command, the server should
 * still release all resources associated with the session, but may log this as an abnormal
 * termination.</p>
 */
public class QUITCommand extends Command {

    /**
     * Creates a new QUIT command.
     * The QUIT command takes no parameters.
     */
    public QUITCommand() {
        super(CommandType.QUIT, new String[0]);
    }

    /**
     * Executes the QUIT command.
     *
     * @return The result of executing the QUIT command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "Service closing transmission channel", 221);
    }
}