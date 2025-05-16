package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP RCPT command.
 * RCPT TO specifies a recipient for the email message.
 * RFC 5321 defines RCPT as the command that identifies an individual recipient of the mail data.
 *
 * <p>Usage: The RCPT command is sent after the MAIL command to specify a recipient's
 * address. The address is enclosed in angle brackets and preceded by "TO:" without
 * any spaces between "TO:" and the address. Multiple RCPT commands can be issued
 * to specify multiple recipients for the same message.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: MAIL FROM:<sender@example.com>
 * S: 250 OK
 * C: RCPT TO:<recipient1@example.com>
 * S: 250 OK
 * C: RCPT TO:<recipient2@example.com>
 * S: 250 OK
 * </pre>
 * </p>
 *
 * <p>The RCPT command transitions the SMTP session from the MAIL state to the RCPT state.
 * At least one successful RCPT command must be issued before the DATA command is allowed.
 * The server must accept or reject each recipient independently, allowing some recipients
 * to be accepted while others are rejected.</p>
 *
 * <p>The TO parameter may also include optional ESMTP parameters after the address
 * when using EHLO mode, though these are less common than with the MAIL command.</p>
 */
public class RCPTCommand extends Command {

    /**
     * Creates a new RCPT command with the recipient's address.
     *
     * @param rcptTo The complete TO specification including the email address in the format "TO:<email@example.com>".
     * @throws IllegalArgumentException If rcptTo is null.
     */
    public RCPTCommand(String rcptTo) {
        super(CommandType.RCPT, new String[]{rcptTo});
    }

    /**
     * Executes the RCPT command.
     *
     * @return The result of executing the RCPT command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "RCPT command received", 250);
    }
}