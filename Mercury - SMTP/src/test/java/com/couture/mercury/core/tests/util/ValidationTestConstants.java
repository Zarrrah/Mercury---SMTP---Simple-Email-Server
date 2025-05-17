package com.couture.mercury.core.tests.util;

/**
 * Test constants for validation testing.
 * Centralises test values to avoid duplication and improve maintainability.
 */
public final class ValidationTestConstants {

    /**
     * Valid test values for use in validation tests.
     */
    public static final class Valid {

        /**
         * Valid domain names for testing.
         */
        public static final String[] DOMAINS = {
                "example.com",
                "sub.example.com",
                "example-domain.co.uk",
                "a-valid-domain.org"
        };

        /**
         * Valid email addresses (in angle brackets) for testing.
         */
        public static final String[] EMAIL = {
                "<test@example.com>",
                "<user.name@example.co.uk>",
                "<user-name@sub.domain.org>"
        };

        /**
         * Valid MAIL FROM parameters for testing.
         */
        public static final String[] MAIL_FROM_PARAMS = {
                "FROM:<sender@example.com>",
                "FROM:<user.name@domain.com>",
                "FROM:<admin@sub.domain.co.uk>"
        };

        /**
         * Valid RCPT TO parameters for testing.
         */
        public static final String[] RCPT_TO_PARAMS = {
                "TO:<recipient@example.com>",
                "TO:<user.name@domain.com>",
                "TO:<info@sub.domain.co.uk>"
        };

        private Valid() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Invalid test values for use in validation tests.
     */
    public static final class Invalid {

        /**
         * Invalid domain names for testing.
         */
        public static final String[] DOMAINS = {
                "invalid@domain",
                "example.com/path",
                "domain with spaces",
                "-invalid.com",
                "invalid-.com"
        };

        /**
         * Invalid email addresses for testing.
         */
        public static final String[] EMAILS = {
                "test@example.com", // Missing angle brackets
                "user.name@", // Incomplete address
                "@example.com", // Missing local part
                "invalid email", // No @ symbol
                "<user name@example.com>" // Space in local part
        };

        /**
         * Invalid MAIL FROM parameters for testing.
         */
        public static final String[] MAIL_FROM_PARAMS = {
                "FROM:sender@example.com", // Missing angle brackets
                "FROM: <sender@example.com>", // Space after colon
                "FROM<sender@example.com>", // Missing colon
                "FROM:<@example.com>", // Missing local part
                "FROM:<sender@>" // Missing domain
        };

        /**
         * Invalid RCPT TO parameters for testing.
         */
        public static final String[] RCPT_TO_PARAMS = {
                "TO:recipient@example.com", // Missing angle brackets
                "TO: <recipient@example.com>", // Space after colon
                "TO<recipient@example.com>", // Missing colon
                "TO:<@example.com>", // Missing local part
                "TO:<recipient@>" // Missing domain
        };

        private Invalid() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Test patterns for parameter validation.
     */
    public static final class Patterns {

        /**
         * Pattern for MAIL FROM parameter.
         */
        public static final String MAIL_FROM = "FROM:<[^>]+>";

        /**
         * Pattern for RCPT TO parameter.
         */
        public static final String RCPT_TO = "TO:<[^>]+>";

        /**
         * Description for MAIL FROM pattern.
         */
        public static final String MAIL_FROM_DESC = "FROM:<email> format";

        /**
         * Description for RCPT TO pattern.
         */
        public static final String RCPT_TO_DESC = "TO:<email> format";

        private Patterns() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Test constants for recipient validation.
     */
    public static final class Recipients {

        /**
         * Key for storing recipients in session context.
         */
        public static final String ATTRIBUTE_KEY = "RECIPIENTS";

        /**
         * Minimum required recipient count.
         */
        public static final int MIN_COUNT = 1;

        /**
         * Higher recipient count for testing multiple recipients.
         */
        public static final int MULTIPLE_COUNT = 2;

        /**
         * Sample recipient address.
         */
        public static final String SAMPLE_RECIPIENT = "recipient@example.com";

        /**
         * Second sample recipient address.
         */
        public static final String SAMPLE_RECIPIENT_2 = "another@example.org";

        private Recipients() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    private ValidationTestConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
