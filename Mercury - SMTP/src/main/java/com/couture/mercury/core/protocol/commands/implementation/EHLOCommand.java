package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP EHLO command.
 * EHLO (Extended HELO) introduces the client to the server and requests extended mode.
 */
public class EHLOCommand extends Command {

    /**
     * Creates a new EHLO command with the specified domain.
     *
     * @param domain The client domain.
     * @throws IllegalArgumentException If domain is null.
     */
    public EHLOCommand(String domain) {
        super(CommandType.EHLO, new String[]{domain});
    }

    /**
     * Executes the EHLO command.
     *
     * @return The result of executing the EHLO command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "EHLO command received", 250);
    }
}