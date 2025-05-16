package com.couture.mercury.core.protocol.commands.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;

/**
 * Concrete implementation of the SMTP EHLO command.
 * EHLO (Extended HELO) introduces the client to the server and requests extended mode.
 * RFC 5321 defines EHLO as the enhanced alternative to HELO that enables SMTP extensions.
 *
 * <p>Usage: The EHLO command is sent as the first command in an SMTP session to
 * identify the client to the server and request extended SMTP mode. The client
 * provides its domain name as a parameter.</p>
 *
 * <p>Example SMTP session:
 * <pre>
 * C: EHLO example.com
 * S: 250-smtp.server.com Hello example.com
 * S: 250-SIZE 14680064
 * S: 250-8BITMIME
 * S: 250-STARTTLS
 * S: 250-ENHANCEDSTATUSCODES
 * S: 250 PIPELINING
 * </pre>
 * </p>
 *
 * <p>Unlike HELO, the EHLO command response includes a list of supported SMTP extensions,
 * each on a separate line with a 250- prefix (except the last line which uses 250 without
 * the hyphen). This allows clients to discover which optional SMTP features the server supports.</p>
 *
 * <p>The EHLO command puts the SMTP session into the HELO state where mail transactions
 * can begin, similar to HELO but with extended capabilities. It is required before
 * MAIL and other transaction commands can be used.</p>
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