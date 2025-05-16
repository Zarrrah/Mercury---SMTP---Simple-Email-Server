package com.couture.mercury.core.commands.validation;

/**
 * Constants for SMTP command validation.
 * Centralises all validation constants, patterns, and limits.
 */
public class ValidationConstants {
    // RFC 5321 Limits
    public static final int MAX_COMMAND_LENGTH = 512; // Maximum command line length including CRLF
    public static final int MAX_RECIPIENTS = 100; // Reasonable limit for recipients
    public static final int MAX_FORWARD_PATH_LENGTH = 256; // Maximum length for forward path
    public static final int MAX_REVERSE_PATH_LENGTH = 256; // Maximum length for reverse path

    // Timeout values (in milliseconds)
    public static final long COMMAND_TIMEOUT = 300_000; // 5 minutes for regular commands
    public static final long DATA_TIMEOUT = 600_000; // 10 minutes for DATA transmission

    // REGEX patterns
    public static final String EMAIL_LOCAL_PART_PATTERN = "[A-Za-z0-9!#$%&'*+\\-/=?^_`{|}~]+(\\.[A-Za-z0-9!#$%&'*+\\-/=?^_`{|}~]+)*";
    public static final String DOMAIN_PART_PATTERN = "[A-Za-z0-9]+(\\-[A-Za-z0-9]+)*(\\.[A-Za-z0-9]+(\\-[A-Za-z0-9]+)*)*";

    public static final String EMAIL_PATTERN = "^<" + EMAIL_LOCAL_PART_PATTERN + "@" + DOMAIN_PART_PATTERN + ">$";
    public static final String DOMAIN_PATTERN = "^" + DOMAIN_PART_PATTERN + "$";
    public static final String BARE_EMAIL_PATTERN = "^" + EMAIL_LOCAL_PART_PATTERN + "@" + DOMAIN_PART_PATTERN + "$";

    // Parameter-specific patterns for MAIL and RCPT commands
    public static final String MAIL_FROM_PATTERN = "^FROM:\\s*<([^>]*)>$";
    public static final String RCPT_TO_PATTERN = "^TO:\\s*<([^>]*)>$";

    // Command identification patterns (just command keywords, for parsing)
    public static final String HELO_CMD_PATTERN = "^HELO\\b";
    public static final String EHLO_CMD_PATTERN = "^EHLO\\b";
    public static final String MAIL_CMD_PATTERN = "^MAIL\\b";
    public static final String RCPT_CMD_PATTERN = "^RCPT\\b";
    public static final String DATA_CMD_PATTERN = "^DATA\\b";
    public static final String QUIT_CMD_PATTERN = "^QUIT\\b";
    public static final String RSET_CMD_PATTERN = "^RSET\\b";
    public static final String NOOP_CMD_PATTERN = "^NOOP\\b";
    public static final String VRFY_CMD_PATTERN = "^VRFY\\b";

    // Command parameter counts
    public static final int HELO_PARAM_COUNT = 1;
    public static final int EHLO_PARAM_COUNT = 1;
    public static final int MAIL_PARAM_COUNT = 1;
    public static final int RCPT_PARAM_COUNT = 1;
    public static final int DATA_PARAM_COUNT = 0;
    public static final int QUIT_PARAM_COUNT = 0;
    public static final int RSET_PARAM_COUNT = 0;

    // Special content markers
    public static final String DATA_TERMINATOR = "\r\n.\r\n"; // Terminates DATA command input
    public static final String LINE_ENDING = "\r\n"; // CRLF line endings for SMTP

    // Character sets
    public static final String ALLOWED_COMMAND_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 <>.@:=-";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ValidationConstants()
    {
        // This class should not be instantiated
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}