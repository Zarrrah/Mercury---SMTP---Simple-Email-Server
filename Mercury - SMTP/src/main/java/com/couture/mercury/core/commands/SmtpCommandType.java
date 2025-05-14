package com.couture.mercury.core.commands;

/**
 * Enumeration of SMTP commands as defined in RFC 5321.
 * Provides standardised representation of SMTP protocol commands.
 */
public enum SmtpCommandType {
    // Initial connection commands
    HELO("Initial server greeting"),
    EHLO("Extended initial server greeting"),

    MAIL("Initiate mail transaction"),
    RCPT("Specify message recipient"),
    DATA("Begin message content transfer"),

    RSET("Reset current mail transaction"),
    VRFY("Verify email address"),
    EXPN("Expand mailing list"),
    HELP("Request help information"),
    NOOP("No operation"),
    QUIT("Terminate SMTP session");

    private final String m_description;

    /**
     * Constructs an SMTP command with a description and extension support flag
     *
     * @param description Human-readable description of the command
     */
    SmtpCommandType(String description){
        m_description = description;
    }

    /**
     * Retrieves the description of the SMTP command.
     *
     * @return Command description
     */
    public String getDescription(){
        return m_description;
    }
}