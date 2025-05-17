package com.couture.mercury.core.mocks;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandException;
import com.couture.mercury.core.protocol.commands.CommandResult;
import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;

import java.util.Arrays;

/**
 * Mock implementation of Command for testing purposes.
 * Provides configurable behaviour to facilitate testing of command handlers and validators.
 */
public class MockCommand extends Command {
    private ValidationResult m_validationResult;
    private CommandResult m_executionResult;
    private CommandException m_executionException;

    /**
     * Creates a mock command with the specified type.
     *
     * @param commandType The type of SMTP command to mock.
     */
    public MockCommand(CommandType commandType) {
        super(commandType);
        m_validationResult = ValidationResultFactory.success();
    }

    /**
     * Creates a mock command with the specified type and parameters.
     *
     * @param commandType The type of SMTP command to mock.
     * @param parameters The command parameters.
     */
    public MockCommand(CommandType commandType, String[] parameters) {
        super(commandType, parameters);
        m_validationResult = ValidationResultFactory.success();
    }

    /**
     * Sets the result of the command execution.
     *
     * @param result The command execution result.
     * @return This MockCommand instance for method chaining.
     */
    public MockCommand setExecutionResult(CommandResult result) {
        m_executionResult = result;
        m_executionException = null;
        return this;
    }

    /**
     * Sets the exception thrown by the command execution.
     *
     * @param exception The exception thrown during execution.
     * @return This MockCommand instance for method chaining.
     */
    public MockCommand setExecutionException(CommandException exception) {
        m_executionException = exception;
        m_executionResult = null;
        return this;
    }

    /**
     * Sets the validation result to be returned by the validate method.
     *
     * @param result The validation result.
     * @return This MockCommand instance for method chaining.
     */
    public MockCommand setValidationResult(ValidationResult result) {
        m_validationResult = result != null ? result : ValidationResultFactory.success();
        return this;
    }

    /**
     * Execute the command with configurable mock behavior.
     * Returns the configured result or throws the configured exception.
     *
     * @return The configured execution result or a default success result.
     * @throws CommandException If a mock exception was configured.
     */
    @Override
    public CommandResult execute() throws CommandException {
        if (m_executionException != null) {
            throw m_executionException;
        }

        if (m_executionResult != null) {
            return m_executionResult;
        }

        // Default success result if none specified
        return new CommandResult(true, "Command executed successfully", 250);
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
        return "MockCommand{" +
                "CommandType=" + getCommandType() +
                ", Parameters=" + Arrays.toString(getParameters()) +
                ", RawCommand=" + getRawCommand() + '\'' +
                '}';
    }
}