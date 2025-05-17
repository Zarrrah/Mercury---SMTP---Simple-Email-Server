package com.couture.mercury.core.tests.commands.validation;

import com.couture.mercury.core.mocks.MockCommand;
import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.session.SessionState;
import com.couture.mercury.core.protocol.util.ValidationErrorMessages;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.steps.StateValidationStep;
import com.couture.mercury.core.tests.util.ValidationTestConstants;
import com.couture.mercury.core.tests.util.ValidationTestMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * Tests for the {@link StateValidationStep} class.
 * Verifies that state validation correctly evaluates command validity based on session state.
 */
public class StateValidationStepTests extends CommandValidatorTestBase {

    /**
     * Tests that validation passes when the session is in a valid state.
     */
    @Test
    @DisplayName("Validation passes when session is in an allowed state.")
    public void testValidState_Succeeds() {
        // Arrange
        StateValidationStep validationStep = new StateValidationStep(SessionState.CONNECT, SessionState.HELO);
        m_sessionContext.setState(SessionState.CONNECT);
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Act
        ValidationResult result = validationStep.validate(command, m_sessionContext);

        // Assert
        assertValidationSuccess(result, ValidationTestMessages.State.VALID_STATE);
    }

    /**
     * Tests that validation fails when the session is in an invalid state.
     */
    @Test
    @DisplayName("Validation fails when session is in a non-allowed state")
    public void testInvalidState_Fails() {
        // Arrange
        StateValidationStep validationStep = new StateValidationStep(SessionState.CONNECT, SessionState.HELO);
        m_sessionContext.setState(SessionState.MAIL);
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Act
        ValidationResult result = validationStep.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result, String.format(ValidationErrorMessages.INVALID_STATE, m_sessionContext.getState()));
    }

    /**
     * Tests that validation fails when the session context is null.
     */
    @Test
    @DisplayName("Validation fails when session context is null")
    public void testNullSession_Fails() {
        // Arrange
        StateValidationStep validationStep = new StateValidationStep(SessionState.CONNECT);
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Act
        ValidationResult result = validationStep.validate(command, null);

        // Assert
        assertValidationFailure(result, ValidationErrorMessages.NULL_SESSION);
    }

    /**
     * Tests that a validation step created with no valid states rejects all states.
     */
    @Test
    @DisplayName("Validation fails when no valid states are specified")
    public void testNoValidStates_FailsAllStates() {
        // Arrange
        StateValidationStep validationStep = new StateValidationStep();
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Test with all possible session states
        for (SessionState state : SessionState.values()) {
            // Arrange
            m_sessionContext.setState(state);

            // Act
            ValidationResult result = validationStep.validate(command, m_sessionContext);

            // Assert
            assertValidationFailure(result, String.format(ValidationErrorMessages.INVALID_STATE, state));
        }
    }

    /**
     * Tests that validation passes for each state when that state is the only valid state.
     */
    @ParameterizedTest
    @EnumSource(SessionState.class)
    @DisplayName("Validation passes for each state when it's the only allowed state")
    public void testSingleValidState_MatchingState_Succeeds(SessionState state) {
        // Arrange
        StateValidationStep validationStep = new StateValidationStep(state);
        m_sessionContext.setState(state);
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Act
        ValidationResult result = validationStep.validate(command, m_sessionContext);

        // Assert
        assertValidationSuccess(result, String.format(ValidationTestMessages.State.VALID_SINGLE_STATE, state));
    }

    /**
     * Tests that validation fails for non-matching states when only one state is valid.
     */
    @ParameterizedTest
    @EnumSource(SessionState.class)
    @DisplayName("Validation fails for non-matching states when only one state is allowed")
    public void testSingleValidState_NonMatchingStates_Fail(SessionState validState) {
        // Arrange
        StateValidationStep validationStep = new StateValidationStep(validState);
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Test with all possible session states except the valid one
        for (SessionState currentState : SessionState.values()) {
            if (currentState != validState) {
                // Arrange
                m_sessionContext.setState(currentState);

                // Act
                ValidationResult result = validationStep.validate(command, m_sessionContext);

                // Assert
                assertValidationFailure(result, String.format(ValidationErrorMessages.INVALID_STATE, currentState));
            }
        }
    }

    /**
     * Tests validation with multiple valid states.
     */
    @Test
    @DisplayName("Validation with multiple valid states passes for any included state")
    public void testMultipleValidStates() {
        // Arrange
        SessionState[] validStates = {SessionState.CONNECT, SessionState.HELO, SessionState.MAIL};
        StateValidationStep validationStep = new StateValidationStep(validStates);
        MockCommand command = createMockCommand(CommandType.HELO, ValidationTestConstants.Valid.DOMAINS[0]);

        // Test with all valid states
        for (SessionState state : validStates) {
            // Arrange
            m_sessionContext.setState(state);

            // Act
            ValidationResult result = validationStep.validate(command, m_sessionContext);

            // Assert
            assertValidationSuccess(result, String.format(ValidationTestMessages.State.VALID_MULTIPLE_STATE, state));
        }

        // Test with all invalid states
        for (SessionState state : SessionState.values()) {
            boolean isValid = false;
            for (SessionState validState : validStates) {
                if (state == validState) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                // Arrange
                m_sessionContext.setState(state);

                // Act
                ValidationResult result = validationStep.validate(command, m_sessionContext);

                // Assert
                assertValidationFailure(result, String.format(ValidationErrorMessages.INVALID_STATE, state));
            }
        }
    }
}