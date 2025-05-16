package com.couture.mercury.core.mocks;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.SmtpCommandException;
import com.couture.mercury.core.commands.SmtpCommandResult;
import com.couture.mercury.core.commands.SmtpCommandType;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.ValidationResultFactory;
import com.couture.mercury.core.session.SessionContext;

import java.util.Arrays;

/**
 * Mock implementation of SmtpCommand for testing purposes.
 * Provides configurable behaviour to facilitate testing of command handlers and validators.
 */
public class MockSmtpCommand extends SmtpCommand {
    private ValidationResult m_validationResult;
    private SmtpCommandResult m_executionResult;
    private SmtpCommandException m_executionException;

    /**
     * Creates a mock command with the specified type.
     *
     * @param commandType The type of SMTP command to mock.
     */
    public MockSmtpCommand(SmtpCommandType commandType) {
        super(commandType);
        m_validationResult = ValidationResultFactory.success();
    }

    /**
     * Creates a mock command with the specified type and parameters.
     *
     * @param commandType The type of SMTP command to mock.
     * @param parameters The command parameters.
     */
    public MockSmtpCommand(SmtpCommandType commandType, String[] parameters) {
        super(commandType, parameters);
        m_validationResult = ValidationResultFactory.success();
    }

    /**
     * Sets the result of the command execution.
     *
     * @param result The command execution result.
     * @return This MockSmtpCommand instance for method chaining.
     */
    public MockSmtpCommand setExecutionResult(SmtpCommandResult result) {
        m_executionResult = result;
        m_executionException = null;
        return this;
    }

    /**
     * Sets the exception thrown by the command execution.
     *
     * @param exception The exception thrown during execution.
     * @return This MockSmtpCommand instance for method chaining.
     */
    public MockSmtpCommand setExecutionException(SmtpCommandException exception) {
        m_executionException = exception;
        m_executionResult = null;
        return this;
    }

    /**
     * Sets the validation result to be returned by the validate method.
     *
     * @param result The validation result.
     * @return This MockSmtpCommand instance for method chaining.
     */
    public MockSmtpCommand setValidationResult(ValidationResult result) {
        m_validationResult = result != null ? result : ValidationResultFactory.success();
        return this;
    }

    /**
     * Execute the command with configurable mock behavior.
     * Returns the configured result or throws the configured exception.
     *
     * @return The configured execution result or a default success result.
     * @throws SmtpCommandException If a mock exception was configured.
     */
    @Override
    public SmtpCommandResult execute() throws SmtpCommandException {
        if (m_executionException != null) {
            throw m_executionException;
        }

        if (m_executionResult != null) {
            return m_executionResult;
        }

        // Default success result if none specified
        return new SmtpCommandResult(true, "Command executed successfully", 250);
    }

    /**
     * Validates this command.
     * Since this is a mock, it returns the preconfigured validation result.
     *
     * @return The mock validation result.
     */
    public ValidationResult validate() {
        return m_validationResult;
    }

    /**
     * Returns a string representation of this mock command.
     *
     * @return The formatted command string.
     */
    @Override
    public String toString() {
        return "MockSmtpCommand{" +
                "CommandType=" + getCommandType() +
                ", Parameters=" + Arrays.toString(getParameters()) +
                ", RawCommand=" + getRawCommand() + '\'' +
                '}';
    }
}