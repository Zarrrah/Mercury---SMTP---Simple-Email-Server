package com.couture.mercury.core.tests.commands.validation;

import com.couture.mercury.core.commands.SmtpCommandType;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.implementation.HeloCommandValidator;
import com.couture.mercury.core.mocks.MockSmtpCommand;
import com.couture.mercury.core.session.SessionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for HeloCommandValidator.
 */
public class HeloCommandValidatorTests extends CommandValidatorTestBase {
    private HeloCommandValidator m_validator;

    /**
     * Sets up the validator before each test.
     */
    @BeforeEach
    public void setUp(){
        m_validator = new HeloCommandValidator();
    }

    /**
     * Tests that a valid HELO command passes validation in the CONNECT state.
     */
    @Test
    public void testValidHeloInConnectState(){
        // Arrange
        m_sessionContext.setState(SessionState.CONNECT);
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command,m_sessionContext);

        // Assert
        assertValidationSuccess(result);
    }

    /**
     * Tests that a valid HELO command passes validation in the HELO state.
     */
    @Test
    public void testValidHeloInHeloState(){
        // Arrange
        m_sessionContext.setState(SessionState.HELO);
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationSuccess(result);
    }

    /**
     * Tests that HELO validation fails in the MAIL state.
     */
    @Test
    public void testHeloInInvalidState(){
        // Arrange
        m_sessionContext.setState(SessionState.MAIL);
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails when no domain is provided.
     */
    @Test
    public void testHeloWithoutDomain(){
        // Arrange
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO);

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with an invalid domain format.
     */
    @Test
    public void testHeloWithInvalidDomain(){
        // Arrange
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO, "invalid@domain");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with too many parameters.
     */
    @Test
    public void testHeloWithTooManyParameters(){
        // Arrange
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO, "example.com", "extraParam");

        // Act
        ValidationResult result = m_validator.validate(command, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with a null command.
     */
    @Test
    public void testNullCommand(){
        // Act
        ValidationResult result = m_validator.validate(null, m_sessionContext);

        // Assert
        assertValidationFailure(result);
    }

    /**
     * Tests that HELO validation fails with a null session context.
     */
    @Test
    public void testNullSessionContext(){
        // Arrange
        MockSmtpCommand command = createMockCommand(SmtpCommandType.HELO, "example.com");

        // Act
        ValidationResult result = m_validator.validate(command, null);

        // Assert
        assertValidationFailure(result);
    }
}
