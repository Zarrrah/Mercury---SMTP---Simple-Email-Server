package com.couture.mercury.core.mocks;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.SmtpCommandException;
import com.couture.mercury.core.commands.SmtpCommandResult;
import com.couture.mercury.core.commands.SmtpCommandType;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.ValidationResultFactory;

import java.util.Arrays;
import java.util.Objects;

/**
 * Mock implementation of SmtpCommand for testing purposes.
 * Provides configurable behaviour to facilitate testing of command handlers and validators.
 */
public class MockSmtpCommand implements SmtpCommand {
    private final SmtpCommandType m_commandType;
    private String[] m_parameters;
    private String m_rawCommand;
    private SmtpCommandResult m_executionResult;
    private ValidationResult m_validationResult;
    private SmtpCommandException m_executionException;

    /**
     * Creates a mock command with the specified type.
     *
     * @param commandType The type of SMTP command to mock.
     */
    public MockSmtpCommand(SmtpCommandType commandType){
        m_commandType = Objects.requireNonNull(commandType, "Command type cannot be null");
        m_parameters = new String[0];
        m_rawCommand = commandType.toString();
        m_validationResult = ValidationResultFactory.success();
    }

    /**
     * Creates a mock command with the specified type and parameters.
     *
     * @param commandType The type of SMTP command to mock.
     * @param parameters The command parameters.
     */
    public MockSmtpCommand(SmtpCommandType commandType, String[] parameters){
        this(commandType);
        setParameters(parameters);
    }

    /**
     * Sets the parameters of this mock command.
     *
     * @param parameters The command parameters.
     * @return This MockSmtpCommand instance for method chaining.
     */
    public MockSmtpCommand setParameters(String[] parameters){
        m_parameters = parameters != null ? Arrays.copyOf(parameters,parameters.length) : new String[0];
        updateRawCommand();
        return this;
    }

    /**
     * Sets the raw command string.
     *
     * @param rawCommand The raw command string.
     * @return This MockSmtpCommand instance for method chaining.
     */
    public MockSmtpCommand setRawCommand(String rawCommand){
        m_rawCommand = rawCommand != null ? rawCommand : "";
        return this;
    }

    /**
     * Sets the result of the command execution.
     *
     * @param result The command execution result.
     * @return This MockSmtpCommand instance for method chaining.
     */
    public MockSmtpCommand setExecutionResult(SmtpCommandResult result){
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
    public MockSmtpCommand setExecutionException(SmtpCommandException exception){
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
    public MockSmtpCommand setValidationResult(ValidationResult result){
        m_validationResult = result != null ? result : ValidationResultFactory.success();
        return this;
    }

    /**
     * Gets the parameters of this mock command.
     *
     * @return The command parameters.
     */
    public String[] getParameters(){
        return Arrays.copyOf(m_parameters,m_parameters.length);
    }

    /**
     * Updates the raw command string based on the command type and parameters.
     */
    public void updateRawCommand(){
        StringBuilder rawCommand = new StringBuilder(m_commandType.toString());

        for(String parameter : m_parameters){
            rawCommand.append(" ").append(parameter);
        }

        m_rawCommand = rawCommand.toString();
    }

    @Override
    public SmtpCommandResult execute() throws SmtpCommandException{
        if(m_executionException != null){
            throw m_executionException;
        }

        if(m_executionResult != null){
            return m_executionResult;
        }

        // Default success result if none specified.
        return new SmtpCommandResult(true, "Command executed successfully", 250);
    }

    @Override
    public ValidationResult validate(){
        return m_validationResult;
    }

    @Override
    public SmtpCommandType getCommandType(){
        return m_commandType;
    }

    @Override
    public String toString(){
        return "MockSmtpCommand{" + "CommandType=" + m_commandType + ", Parameters=" + Arrays.toString(m_parameters) +
                ", RawCommand=" + m_rawCommand + '\'' + '}';
    }

}
