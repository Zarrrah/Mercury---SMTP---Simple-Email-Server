package com.couture.mercury.core.protocol.util;

import com.couture.mercury.core.protocol.session.SessionState;

/**
 * Constants related to SMTP commands.
 * Centralizes command-specific constants, particularly those related to protocol states
 * and validation as defined in RFC 5321.
 */
public final class CommandConstants {

    /**
     * Valid states for each SMTP command according to RFC 5321.
     */
    public static final class States {
        /**
         * Valid states for the HELO command.
         */
        public static final SessionState[] HELO_VALID_STATES = {SessionState.CONNECT, SessionState.HELO};

        /**
         * Valid states for the EHLO command (same as HELO).
         */
        public static final SessionState[] EHLO_VALID_STATES = HELO_VALID_STATES;

        /**
         * Valid states for the MAIL command.
         */
        public static final SessionState[] MAIL_VALID_STATES = {SessionState.HELO};

        /**
         * Valid states for the RCPT command.
         */
        public static final SessionState[] RCPT_VALID_STATES = {SessionState.MAIL, SessionState.RCPT};

        /**
         * Valid states for the DATA command.
         */
        public static final SessionState[] DATA_VALID_STATES = {SessionState.RCPT};

        /**
         * Valid states for the RSET command.
         */
        public static final SessionState[] RSET_VALID_STATES = {
                SessionState.HELO, SessionState.MAIL, SessionState.RCPT, SessionState.DATA
        };

        /**
         * Valid states for the QUIT command (valid in any state).
         */
        public static final SessionState[] QUIT_VALID_STATES = {
                SessionState.CONNECT, SessionState.HELO, SessionState.MAIL,
                SessionState.RCPT, SessionState.DATA
        };

        /**
         * Valid states for the NOOP command (valid in any state).
         */
        public static final SessionState[] NOOP_VALID_STATES = QUIT_VALID_STATES;

        // Private constructor to prevent instantiation
        private States() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Response codes for each SMTP command as defined in RFC 5321.
     */
    public static final class ResponseCodes {
        /**
         * Success response code.
         */
        public static final int SUCCESS = 250;

        /**
         * Service ready response code.
         */
        public static final int SERVICE_READY = 220;

        /**
         * Service closing response code.
         */
        public static final int SERVICE_CLOSING = 221;

        /**
         * Start mail input response code.
         */
        public static final int START_MAIL_INPUT = 354;

        /**
         * Syntax error response code.
         */
        public static final int SYNTAX_ERROR = 500;

        /**
         * Parameters error response code.
         */
        public static final int PARAMETER_ERROR = 501;

        /**
         * Command not implemented response code.
         */
        public static final int COMMAND_NOT_IMPLEMENTED = 502;

        /**
         * Bad sequence response code.
         */
        public static final int BAD_SEQUENCE = 503;

        // Private constructor to prevent instantiation
        private ResponseCodes() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Constants related to session attributes.
     */
    public static final class Attributes {
        /**
         * Session attribute key for recipients.
         */
        public static final String RECIPIENTS_KEY = "RECIPIENTS";

        /**
         * Minimum number of recipients required for DATA command.
         */
        public static final int MIN_RECIPIENT_COUNT = 1;

        // Private constructor to prevent instantiation
        private Attributes() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    // Private constructor to prevent instantiation
    private CommandConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}