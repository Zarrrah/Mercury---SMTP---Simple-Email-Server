package com.couture.mercury.core.commands.validation;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.SmtpCommandType;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for command validators that maps SmtpCommandType to appropriate validators.
 * Implements a simple factory pattern to retrieve the correct validator for each command type.
 */
public class CommandValidatorRegistry {
    private final Map<SmtpCommandType, SmtpCommandValidator<?>> m_validators;

    /**
     * Creates a new validator registry with an empty validator map.
     */
    public CommandValidatorRegistry(){
        m_validators = new HashMap<>();
    }

    /**
     * Registers a validator for a specific command type.
     * Overrides existing validator if one was already given for the command type.
     *
     * @param commandType The SMTP command type to register a validator for.
     * @param validator The validator to associate with the command type.
     * @throws IllegalArgumentException if commandType or validator is null.
     */
    public void registerValidator(SmtpCommandType commandType, SmtpCommandValidator<?> validator){
        if(commandType == null){
            throw new IllegalArgumentException("Command type cannot be null");
        }

        if(validator == null){
            throw new IllegalArgumentException("Validator cannot be null");
        }

        m_validators.put(commandType, validator);
    }

    /**
     * Retrieves the appropriate validator for a given command type.
     *
     * @param commandType The type of SMTP command.
     * @param <T> The type of SMTP command.
     * @return The validator for the specified command type.
     * @throws IllegalArgumentException If commandType is null.
     * @throws IllegalStateException If no validator is registered for the command type.
     */
    @SuppressWarnings("unchecked")
    public <T extends SmtpCommand> SmtpCommandValidator<T> getValidator(SmtpCommandType commandType){
        if(commandType == null){
            throw new IllegalArgumentException("Command type cannot be null");
        }

        SmtpCommandValidator<?> validator = m_validators.get(commandType);

        if(validator == null){
            throw new IllegalStateException("No validator registered for command type: " + commandType);
        }

        // This cast is safe because validators are registered with their corresponding command type.
        return (SmtpCommandValidator<T>) validator;
    }

    /**
     * Checks if a validator is registered for a specific command type.
     *
     * @param commandType The SMTP command type to check.
     * @return True if a validator is registered, false otherwise.
     * @throws IllegalArgumentException If commandType is null.
     */
    public boolean hasValidatorFor(SmtpCommandType commandType){
        if(commandType == null){
            throw new IllegalArgumentException("Command type cannot be null");
        }

        return m_validators.containsKey(commandType);
    }

    /**
     * Removes a validator for a specific command type.
     *
     * @param commandType The SMTP command type to unregister
     * @throws IllegalArgumentException if commandType is null
     */
    public void unregisterValidator(SmtpCommandType commandType){
        if(commandType == null){
            throw new IllegalArgumentException("Command type cannot be null");
        }

        m_validators.remove(commandType);
    }

    /**
     * Clears all registered validators.
     */
    public void clearValidators(){
        m_validators.clear();
    }
}