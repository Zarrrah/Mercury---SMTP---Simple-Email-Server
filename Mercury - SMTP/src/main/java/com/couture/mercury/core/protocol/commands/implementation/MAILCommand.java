package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP MAIL command.
 * MAIL FROM initiates a mail transaction and specifies the sender's address.
 * RFC 5321 defines MAIL as the command that begins the process of message transmission.
 *
 * <p>Usage: The MAIL command is sent after HELO/EHLO to start a new mail transaction
 * and specify the return path (sender's address). The address is enclosed in angle brackets
 * and preceded by "FROM:" without any spaces between "FROM:" and the address.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: MAIL FROM:<sender@example.com>
 * S: 250 OK
 * </pre>
 * </p>
 *
 * <p>The MAIL command transitions the SMTP session from the HELO state to the MAIL state,
 * allowing recipient addresses to be specified with the RCPT command. Only one MAIL command
 * can be used per message transaction - if a second MAIL command is issued before the
 * current transaction is completed, the server should return an error.</p>
 *
 * <p>The FROM parameter may also include optional ESMTP parameters after the address
 * when using EHLO mode, such as SIZE or BODY.</p>
 */
public class MAILCommand extends Command {

    /**
     * Creates a new MAIL command with the sender's address.
     *
     * @param mailFrom The complete FROM specification including the email address in the format "FROM:<email@example.com>".
     * @throws IllegalArgumentException If mailFrom is null.
     */
    public MAILCommand(String mailFrom) {
        super(CommandType.MAIL, new String[]{mailFrom});
    }

    /**
     * Executes the MAIL command.
     *
     * @return The result of executing the MAIL command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "MAIL command received", 250);
    }
}