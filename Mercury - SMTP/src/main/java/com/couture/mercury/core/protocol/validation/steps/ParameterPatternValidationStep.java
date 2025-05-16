package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.session.SessionContext;

import java.util.regex.Pattern;

/**
 * Validation step that checks if a parameter matches a specific REGEX pattern.
 * Particularly useful for MAIL FROM: and RCPT TO: parameters which have specific enforced formats.
 */
public class ParameterPatternValidationStep extends ValidationStep {
    private final Pattern m_pattern;
    // Pattern description is for human-readable error messages.
    private final String m_patternDescription;
    private final int m_parameterIndex;

    /**
     * Creates a new parameter pattern validation step.
     *
     * @param patternString The REGEX pattern to match against.
     * @param patternDescription Human-readable description of the pattern for error messages (e.g., "'FROM:' format").
     * @param parameterIndex The index of the parameter to validate.
     */
    public ParameterPatternValidationStep(String patternString, String patternDescription, int parameterIndex){
        if(patternString == null || patternString.isEmpty()){
            throw new IllegalArgumentException("Pattern string cannot be null or empty.");
        }

        if(parameterIndex < 0){
            throw new IllegalArgumentException("Parameter index cannot be negative.");
        }

        m_pattern = Pattern.compile(patternString);
        m_patternDescription = patternDescription != null ? patternDescription : "parameter format";
        m_parameterIndex = parameterIndex;
    }

    /**
     * Creates a parameter pattern validation step for the first parameter.
     *
     * @param patternString The REGEX pattern to match against.
     * @param patternDescription Human-readable description of the pattern for error messages.
     */
    public ParameterPatternValidationStep(String patternString, String patternDescription){
        this(patternString, patternDescription, 0);
    }

    @Override
    protected ValidationResult doValidation(Command command, SessionContext context){
        String[] parameters = command.getParameters();

        // Check if parameter index is valid
        if(parameters == null || parameters.length <= m_parameterIndex){
            return ValidationResultFactory.missingParameter("Parameter at index " + m_parameterIndex);
        }

        String parameter = parameters[m_parameterIndex];

        // Check for null or empty parameter
        if(parameter == null || parameter.isEmpty()){
            return ValidationResultFactory.missingParameter("Parameter at index " + m_parameterIndex);
        }

        if(!m_pattern.matcher(parameter).matches()){
            return ValidationResultFactory.invalidParameterFormat(m_patternDescription);
        }

        return ValidationResultFactory.success();
    }
}