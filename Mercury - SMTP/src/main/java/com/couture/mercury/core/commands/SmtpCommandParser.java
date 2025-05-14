package com.couture.mercury.core.commands;

import com.couture.mercury.core.session.SessionState;

/**
 * Parses raw SMTP command input SmtpCommandObjects.
 */
public class SmtpCommandParser {

    /**
     * Parses a raw string into an SmtpCommand object.
     *
     * @param rawCommand Raw command string to parse.
     * @return Parsed SmtpCommand object.
     * @throws SmtpCommandException If parsing fails.
     */
    SmtpCommand parse(String rawCommand) throws SmtpCommandException{
        throw new UnsupportedOperationException("Command parsing not yet implemented");
    }
}