package com.couture.mercury.core.commands.implementation;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.SmtpCommandException;
import com.couture.mercury.core.commands.SmtpCommandResult;
import com.couture.mercury.core.commands.SmtpCommandType;

/**
 * Concrete implementation of the SMTP EHLO command.
 * EHLO (Extended HELO) introduces the client to the server and requests extended mode.
 */
public class EHLOCommand extends SmtpCommand {

    /**
     * Creates a new EHLO command with the specified domain.
     *
     * @param domain The client domain.
     * @throws IllegalArgumentException If domain is null.
     */
    public EHLOCommand(String domain) {
        super(SmtpCommandType.EHLO, new String[]{domain});
    }

    /**
     * Executes the EHLO command.
     *
     * @return The result of executing the EHLO command.
     * @throws SmtpCommandException If execution fails.
     */
    @Override
    public SmtpCommandResult execute() throws SmtpCommandException {
        // The actual execution logic would be handled by a command handler.
        // This base implementation simply indicates successful construction.
        // TODO: Implement the actual execution logic once the CommandHandler is implemented.
        return new SmtpCommandResult(true, "EHLO command received", 250);
    }
}