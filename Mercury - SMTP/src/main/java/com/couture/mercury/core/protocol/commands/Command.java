package com.couture.mercury.core.protocol.commands;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Abstract base class for all SMTP commands.
 * Provides common functionality and structure for command implementations.
 */
public abstract class Command implements Serializable {
    private final CommandType m_commandType;
    private String[] m_parameters;
    private String m_rawCommand;

    /**
     * Creates a new SMTP command with the specified type.
     *
     * @param commandType The SMTP command type.
     * @throws IllegalArgumentException If commandType is null.
     */
    protected Command(CommandType commandType) {
        m_commandType = Objects.requireNonNull(commandType, "Command type cannot be null");
        m_parameters = new String[0];
        m_rawCommand = commandType.toString();
    }

    /**
     * Creates a new SMTP command with the specified type and parameters.
     *
     * @param commandType The SMTP command type.
     * @param parameters The command parameters.
     * @throws IllegalArgumentException If commandType is null.
     */
    protected Command(CommandType commandType, String[] parameters) {
        this(commandType);
        setParameters(parameters);
    }

    /**
     * Sets the parameters for this command.
     * Validates that neither the parameter array nor any individual parameters are null.
     *
     * @param parameters The command parameters.
     * @throws IllegalArgumentException If any individual parameter is null.
     */
    protected void setParameters(String[] parameters) {
        // Handle null array
        if (parameters == null) {
            m_parameters = new String[0];
            updateRawCommand();
            return;
        }

        // Check each parameter for null
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] == null) {
                throw new IllegalArgumentException("Parameter at index " + i + " cannot be null");
            }
        }

        // Make defensive copy of the array
        m_parameters = Arrays.copyOf(parameters, parameters.length);
        updateRawCommand();
    }

    /**
     * Execute the command. Must be implemented by concrete command classes.
     * This method defines the behavior of the command once executed.
     *
     * @return The result of the command execution.
     * @throws CommandException If the command execution fails.
     */
    public abstract CommandResult execute() throws CommandException;

    /**
     * Updates the raw command string based on the command type and parameters.
     */
    protected void updateRawCommand() {
        StringBuilder rawCommand = new StringBuilder(m_commandType.toString());

        for (String parameter : m_parameters) {
            rawCommand.append(" ").append(parameter);
        }

        m_rawCommand = rawCommand.toString();
    }

    /**
     * Gets the parameters of this command.
     *
     * @return The command parameters as an array.
     */
    public String[] getParameters() {
        return Arrays.copyOf(m_parameters, m_parameters.length);
    }

    /**
     * Gets the type of this command.
     *
     * @return The command type.
     */
    public CommandType getCommandType() {
        return m_commandType;
    }

    /**
     * Gets the raw command string.
     *
     * @return The raw command string.
     */
    public String getRawCommand() {
        return m_rawCommand;
    }

    /**
     * Returns a string representation of this command.
     * Useful for logging and debugging.
     *
     * @return The raw command string.
     */
    @Override
    public String toString() {
        return m_rawCommand;
    }
}