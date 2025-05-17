package com.couture.mercury.core.tests.util;

/**
 * Constants for test assertion messages.
 * Centralizes test assertion messages to improve maintainability.
 */
public final class ValidationTestMessages {

    /**
     * Messages for state validation tests.
     */
    public static final class State {
        /**
         * Message for valid state validation.
         */
        public static final String VALID_STATE = "Valid state should pass validation";

        /**
         * Message format for single state validation success.
         */
        public static final String VALID_SINGLE_STATE =
                "Validation should succeed when current state matches the only valid state: %s";

        /**
         * Message format for multiple state validation success.
         */
        public static final String VALID_MULTIPLE_STATE =
                "Validation should succeed for valid state: %s";

        private State() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Messages for parameter validation tests.
     */
    public static final class Parameter {
        /**
         * Message for valid parameter count.
         */
        public static final String VALID_PARAMETER_COUNT = "Valid parameter count should pass validation";

        /**
         * Message for exact match validation.
         */
        public static final String EXACT_MATCH_VALID = "Exact parameter count should pass validation";

        /**
         * Message for minimum parameters validation.
         */
        public static final String MINIMUM_COUNT_VALID =
                "Minimum parameter count should pass validation when extra parameters exist";

        private Parameter() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Messages for domain validation tests.
     */
    public static final class Domain {
        /**
         * Message format for valid domain.
         */
        public static final String VALID_DOMAIN = "Valid domain '%s' should pass validation";

        private Domain() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    /**
     * Messages for email validation tests.
     */
    public static final class Email {
        /**
         * Message format for valid email.
         */
        public static final String VALID_EMAIL = "Valid email '%s' should pass validation";

        private Email() {
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
        }
    }

    private ValidationTestMessages() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}