package com.couture.mercury.core.tests.commands.validation;

import com.couture.mercury.core.mocks.MockCommand;
import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.session.SessionState;
import com.couture.mercury.core.protocol.validation.implementation.CommandValidator;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for HELOCommandValidator.
 */
public class CommandValidatorTests extends CommandValidatorTestBase {
    private CommandValidator m_validator;

    /**
     * Sets up the validator before each test.
     */
    @BeforeEach
    public void setUp() {
        m_validator = new CommandValidator();
    }

    /**
     * Tests that a valid HELO command passes validation in the CONNECT state.
     */
    @Test
    public void testValidHeloInConnectState() {
        // Arrange
        m_sessionContext.setState(SessionState.CONNECT);
        MockCommand command = createMockCommand(CommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationSuccess(result);
    }

    /**
     * Tests that a valid HELO command passes validation in the HELO state.
     */
    @Test
    public void testValidHeloInHeloState() {
        // Arrange
        m_sessionContext.setState(SessionState.HELO);
        MockCommand command = createMockCommand(CommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationSuccess(result);
    }

    /**
     * Tests that HELO validation fails in the MAIL state.
     */
    @Test
    public void testHeloInInvalidState() {
        // Arrange
        m_sessionContext.setState(SessionState.MAIL);
        MockCommand command = createMockCommand(CommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails when no domain is provided.
     */
    @Test
    public void testHeloWithoutDomain() {
        // Arrange
        MockCommand command = createMockCommand(CommandType.HELO);

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with an invalid domain format.
     */
    @Test
    public void testHeloWithInvalidDomain() {
        // Arrange
        MockCommand command = createMockCommand(CommandType.HELO, "invalid@domain");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with too many parameters.
     */
    @Test
    public void testHeloWithTooManyParameters() {
        // Arrange
        MockCommand command = createMockCommand(CommandType.HELO, "example.com", "extraParam");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with a null command.
     */
    @Test
    public void testNullCommand() {
        // Act
        ValidationResult result = m_validator.validate(null, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with a null session context.
     */
    @Test
    public void testNullSessionContext() {
        // Arrange
        MockCommand command = createMockCommand(CommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, null);

        // Assert
        assertValidationFailure(result);
    }
}
