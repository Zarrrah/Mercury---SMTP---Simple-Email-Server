package com.couture.mercury.core.protocol.validation.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of command validation.
 */
public class ValidationResult {
    private final boolean m_valid;
    private final List<String> m_errors;

    /**
     * Creates a validation result.
     *
     * @param valid Overall validation status.
     */
    public ValidationResult(boolean valid){
        m_valid = valid;
        m_errors = new ArrayList<>();
    }

    /**
     * Creates a validation result with potential errors.
     *
     * @param valid Overall validation status.
     * @param errors List of validation error messages.
     */
    public ValidationResult(boolean valid, List<String> errors){
        m_valid = valid;
        m_errors = new ArrayList<>(errors);
    }

    /**
     *  Checks if validation passed.
     *
     * @return True is valid, otherwise false.
     */
    public boolean isValid(){
        return m_valid;
    }

    /**
     * Retrieves validation error messages.
     *
     * @return List of error messages.
     */
    public List<String> getErrors(){
        return new ArrayList<>(m_errors);
    }

    /**
     * Adds a validation error.
     *
     * @param error Error message to add.
     */
    public void addError(String error){
        m_errors.add(error);
    }
}
