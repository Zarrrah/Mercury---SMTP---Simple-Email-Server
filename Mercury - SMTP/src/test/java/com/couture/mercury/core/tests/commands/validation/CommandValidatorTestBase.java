package com.couture.mercury.core.tests.commands.validation;

import com.couture.mercury.core.commands.SmtpCommandType;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.mocks.MockSessionContext;
import com.couture.mercury.core.mocks.MockSmtpCommand;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Base test class for command validators providing common test utilities and setup.
 */
public abstract class CommandValidatorTestBase {
    protected MockSessionContext m_sessionContext;

    @BeforeEach
    public void setUpBase(){
        m_sessionContext = new MockSessionContext();
    }

    /**
     * Asserts that a validation result indicates success.
     *
     * @param result The validation result to check.
     * @param message Optional failure message.
     */
    protected void assertValidationSuccess(ValidationResult result, String message){
        message = message != null ? message : "Validation should have succeeded";
        assertTrue(result.isValid(), message);
        assertTrue(result.getErrors().isEmpty(), message + " without errors.");
    }

    /**
     * Asserts that a validation result indicates success.
     *
     * @param result The validation result to check.
     */
    protected void assertValidationSuccess(ValidationResult result){
        assertValidationSuccess(result, null);
    }

    /**
     * Asserts a validation result indicates failure.
     *
     * @param result The validation result to check.
     * @param expectedError The expected error message.
     * @param message Optional failure message.
     */
    protected void assertValidationFailure(ValidationResult result, String expectedError, String message){
        message = message != null ? message : "Validation should have failed.";
        assertFalse(result.isValid(),message);

        List<String> errors = result.getErrors();
        assertFalse(errors.isEmpty(), message + " with errors");

        if(expectedError != null){
            assertTrue(errors.contains(expectedError),
                    message + " with error:" + expectedError + ", but got: " + errors);
        }
    }

    /**
     * Asserts a validation result indicates failure with the expected errors.
     *
     * @param result The validation result to check.
     * @param expectedError The expected error message.
     */
    protected void assertValidationFailure(ValidationResult result, String expectedError){
        assertValidationFailure(result, expectedError, null);
    }

    /**
     * Asserts that a validation result indicates failure with any error message.
     *
     * @param result The validation result to check.
     */
    protected void assertValidationFailure(ValidationResult result){
        assertValidationFailure(result, null, null);
    }

    /**
     * Creates a mock command of the specified type with the given parameters.
     *
     * @param commandType The type of command to create.
     * @param parameters The command parameters.
     * @return A mock command.
     */
    protected MockSmtpCommand createMockCommand(SmtpCommandType commandType, String... parameters){
        return new MockSmtpCommand(commandType, parameters);
    }
}