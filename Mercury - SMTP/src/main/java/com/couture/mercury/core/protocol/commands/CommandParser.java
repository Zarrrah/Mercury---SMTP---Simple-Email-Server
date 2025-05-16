package com.couture.mercury.core.protocol.commands;

/**
 * Parses raw SMTP command input SmtpCommandObjects.
 */
public class CommandParser {

    /**
     * Parses a raw string into an Command object.
     *
     * @param rawCommand Raw command string to parse.
     * @return Parsed Command object.
     * @throws CommandException If parsing fails.
     */
    Command parse(String rawCommand) throws CommandException {
        throw new UnsupportedOperationException("Command parsing not yet implemented");
    }
}