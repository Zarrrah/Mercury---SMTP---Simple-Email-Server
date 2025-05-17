package com.couture.mercury.core.protocol.util;

/**
 * Pattern descriptions for SMTP command parameter validation.
 * Provides user-friendly descriptions of pattern expectations for validation error messages.
 */
public final class ValidationDescriptions {

    /**
     * MAIL command pattern descriptions.
     */
    public static final class Mail {
        /**
         * Description of the expected MAIL FROM parameter format.
         */
        public static final String FROM_PATTERN = "FROM:<email@example.com> format";
    }

    /**
     * RCPT command pattern descriptions.
     */
    public static final class Rcpt {
        /**
         * Description of the expected RCPT TO parameter format.
         */
        public static final String TO_PATTERN = "TO:<email@example.com> format";
    }

    /**
     * Email format descriptions.
     */
    public static final class Email {
        /**
         * Description of the expected email address format.
         */
        public static final String ADDRESS_FORMAT = "local-part@domain format";

        /**
         * Description of the expected domain format.
         */
        public static final String DOMAIN_FORMAT = "valid domain name (e.g., example.com)";
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private ValidationDescriptions() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}