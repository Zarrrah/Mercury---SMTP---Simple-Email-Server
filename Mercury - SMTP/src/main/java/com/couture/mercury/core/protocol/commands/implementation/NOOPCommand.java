package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP NOOP command.
 * NOOP (No Operation) does nothing except generate a success response.
 * RFC 5321 defines NOOP as a command that requires the server to send an OK reply.
 *
 * <p>Usage: The NOOP command can be sent at any time during an SMTP session and
 * requires no parameters. It is primarily used to prevent connection timeouts by
 * maintaining activity on the connection without performing any actual mail operations.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: NOOP
 * S: 250 OK
 * </pre>
 * </p>
 *
 * <p>The NOOP command is valid in any SMTP state after the initial connection and has
 * no effect on the session state or any mail transaction in progress. It simply generates
 * a 250 response, confirming that the server is still responsive.</p>
 *
 * <p>In addition to keeping connections alive, NOOP can be used as a simple way to test
 * if the server is operational or to synchronize client-server communication in certain
 * edge cases.</p>
 */
public class NOOPCommand extends Command {

    /**
     * Creates a new NOOP command.
     * The NOOP command takes no parameters.
     */
    public NOOPCommand() {
        super(CommandType.NOOP, new String[0]);
    }

    /**
     * Executes the NOOP command.
     *
     * @return The result of executing the NOOP command.
     * @throws CommandException If execution fails.
     */
    @Override
    public CommandResult execute() throws CommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new CommandResult(true, "OK", 250);
    }
}