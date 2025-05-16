package com.couture.mercury.core.protocol.util;

/**
 * Constants for validation error messages.
 * Centralises all error messages used during command validation to ensure consistency.
 */
public class ValidationErrorMessages {
    // General error messages
    public static final String INTERNAL_VALIDATION_ERROR = "Internal validation error occured";
    public static final String NULL_COMMAND = "Command cannot be null";
    public static final String NULL_SESSION = "Session context cannot be null";

    // Syntax error messages
    public static final String INVALID_COMMAND_SYNTAX = "Invalid command syntax: %s";
    public static final String COMMAND_TOO_LONG = "Command exceeds maximum length of %d characters";
    public static final String CONTAINS_ILLEGAL_CHARACTERS = "Command contains illegal characters: %s";

    // Parameter error messages
    public static final String MISSING_REQUIRED_PARAMETER = "Missing required parameter: %s";
    public static final String INVALID_PARAMETER_FORMAT = "Invalid parameter format: %s";
    public static final String TOO_MANY_PARAMETERS = "Too many parameters provided";
    public static final String TOO_FEW_PARAMETERS = "Too few parameters provided";

    // State error messages
    public static final String INVALID_STATE = "Command not valid in current state: %s";
    public static final String SEQUENCE_ERROR = "Command issued out of sequence";
    public static final String STATE_PRECONDITION_FAILURE = "Required condition not met: %s";

    // Command-specific error messages
    public static final String HELO_REQUIRES_DOMAIN = "HELO command requires a domain parameter";
    public static final String MAIL_INVALID_ADDRESS_FORMAT = "MAIL FROM command has invalid address format";
    public static final String MAIL_MISSING_FROM_PARAMETER = "MAIL command requires FROM: parameter";
    public static final String RCPT_INVALID_ADDRESS_FORMAT = "RCPT TO command has invalid address format";
    public static final String RCPT_MISSING_TO_PARAMETER = "RCPT command requires TO: parameter";
    public static final String RCPT_NO_RECIPIENTS = "No recipients specified before DATA command";
    public static final String DATA_UNEXPECTED_PARAMETERS = "DATA command should not have parameters";

    // Email address and domain validation
    public static final String INVALID_EMAIL_ADDRESS = "Invalid email address format: %s";
    public static final String INVALID_DOMAIN_FORMAT = "Invalid domain format: %s";
    public static final String ADDRESS_SYNTAX_ERROR = "Address syntax error: %s";

    // Session errors
    public static final String SESSION_NOT_IDENTIFIED = "Session not identified with HELO/EHLO";
    public static final String NO_MAIL_TRANSACTION = "No mail transaction in progress";
    public static final String TRANSACTION_ALREADY_IN_PROGRESS = "Mail transaction already in progress";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ValidationErrorMessages()
    {
        // This class should not be instantiated
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}