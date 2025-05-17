package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP RSET command.
 * RSET resets the current mail transaction state.
 * RFC 5321 defines RSET as the command that aborts any ongoing mail transaction.
 *
 * <p>Usage: The RSET command is used to abort a mail transaction in progress. It clears
 * any sender, recipients, and message data that has been buffered but not yet completed.
 * This allows a client to start a new mail transaction without closing and reopening
 * the connection.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: MAIL FROM:<sender@example.com>
 * S: 250 OK
 * C: RCPT TO:<recipient@example.com>
 * S: 250 OK
 * C: RSET
 * S: 250 Reset OK
 * </pre>
 * </p>
 *
 * <p>The RSET command transitions the SMTP session back to the HELO state from any
 * other state except the initial connection state. It is particularly useful for
 * recovering from error conditions or abandoning a transaction that is no longer needed.</p>
 *
 * <p>No sender, recipient, or message data information from the previous transaction
 * should be retained for use in any subsequent transaction after an RSET command.
 * However, the HELO/EHLO state information is preserved.</p>
 */
public class RSETCommand extends Command {

    /**
     * Creates a new RSET command.
     * The RSET command takes no parameters.
     */
    public RSETCommand() {
        super(CommandType.RSET, new String[0]);
    }

    /**
     * Executes the RSET command.
     *
     * @return The result of executing the RSET command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "Reset OK", 250);
    }
}