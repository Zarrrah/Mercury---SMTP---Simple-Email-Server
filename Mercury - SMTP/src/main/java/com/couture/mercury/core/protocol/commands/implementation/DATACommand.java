package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP DATA command.
 * DATA initiates the transfer of message content after the envelope has been specified.
 * RFC 5321 defines DATA as the command that signals the SMTP server to accept the message content.
 *
 * <p>Usage: The DATA command is sent after at least one successful RCPT command to
 * begin the transmission of the actual message content. Unlike other commands, DATA
 * prompts an intermediate response (354) from the server, then the client sends the
 * message content, and finally a single line with just a period (.) to indicate the
 * end of the message.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: DATA
 * S: 354 Start mail input; end with &lt;CRLF&gt;.&lt;CRLF&gt;
 * C: This is the body of the test message.
 * C: .
 * S: 250 OK
 * </pre>
 * </p>
 *
 * <p>The DATA command transitions the SMTP session from the RCPT state to the DATA state.
 * After the message content is successfully transmitted, the session returns to the
 * HELO state where a new transaction can begin or the session can be terminated.</p>
 *
 * <p>Special handling is required for lines in the message content that begin with a
 * period - these must be dot-stuffed (an extra period added at the beginning) by the
 * client, and dot-unstuffed by the server.</p>
 */
public class DATACommand extends Command {

    /**
     * Creates a new DATA command.
     * The DATA command takes no parameters.
     */
    public DATACommand() {
        super(CommandType.DATA, new String[0]);
    }

    /**
     * Executes the DATA command.
     *
     * @return The result of executing the DATA command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "Start mail input; end with <CRLF>.<CRLF>", 354);
    }
}