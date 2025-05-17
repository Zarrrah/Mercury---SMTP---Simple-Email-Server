package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP HELO command.
 * HELO introduces the client to the server and establishes a session.
 * RFC 5321 defines HELO as requiring a domain parameter identifying the client.
 *
 * <p>Usage: The HELO command is sent as the first command in an SMTP session to
 * identify the client to the server. The client provides its domain name as a parameter.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: HELO example.com
 * S: 250 Hello example.com, pleased to meet you
 * </pre>
 * </p>
 *
 * <p>The HELO command puts the SMTP session into the HELO state where mail transactions
 * can begin. It is required before MAIL and other transaction commands can be used.</p>
 */
public class HELOCommand extends Command {

    /**
     * Creates a new HELO command with the specified domain.
     *
     * @param domain The client domain.
     * @throws IllegalArgumentException If domain is null.
     */
    public HELOCommand(String domain) {
        super(CommandType.HELO, new String[]{domain});
    }

    /**
     * Executes the HELO command.
     *
     * @return The result of executing the HELO command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "HELO command received", 250);
    }
}
