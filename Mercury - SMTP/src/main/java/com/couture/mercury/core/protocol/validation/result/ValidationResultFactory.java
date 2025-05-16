package com.couture.mercury.core.protocol.validation.result;

import com.couture.mercury.core.protocol.util.ValidationErrorMessages;

import java.util.List;

/**
 * Factory methods for creating ValidationResult instances.
 * Centralises the creation of success and error validation results.
 */
public class ValidationResultFactory{

    //region Public Static Methods

    /**
     * Creates a successful validation result.
     *
     * @return A ValidationResult indicating successful validation.
     */
    public static ValidationResult success(){
        return new ValidationResult(true);
    }

    /**
     * Creates a failed validation result with an error message.
     *
     * @param errorMessage The error message describing the validation failure.
     * @return A ValidationResults indicating failed validation with the given error message.
     * @throws IllegalArgumentException If errorMessage is null or empty.
     */
    public static ValidationResult failure(String errorMessage){
        validateErrorMessage(errorMessage);

        ValidationResult result = new ValidationResult(false);
        result.addError(errorMessage);
        return result;
    }

    /**
     * Creates a failed validation result with multiple error messages.
     *
     * @param errorMessages List of error messages describing the validation failures.
     * @return A ValidationResult indicating failed validation with the given error messages.
     * @throws IllegalArgumentException If errorMessages is null or Empty.
     */
    public static ValidationResult failure(List<String> errorMessages){
        if(errorMessages == null || errorMessages.isEmpty()){
            throw new IllegalArgumentException("Error messages list cannot be null or empty");
        }

        ValidationResult result = new ValidationResult(false);

        for(String errorMessage : errorMessages){
            validateErrorMessage(errorMessage);
            result.addError(errorMessage);
        }

        return result;
    }

    /**
     * Creates a validation result for invalid syntax.
     *
     * @param details Details about the syntax error.
     * @return A ValidationResult indicating invalid syntax.
     * @throws IllegalArgumentException If details is null or empty.
     */
    public static ValidationResult invalidSyntax(String details){
        validateErrorMessage(details);
        return failure(String.format(ValidationErrorMessages.INVALID_COMMAND_SYNTAX, details));
    }

    /**
     * Creates a failed validation result for an invalid state.
     *
     * @param currentState Description of the current state.
     * @return A ValidationResult indicating invalid state.
     * @throws IllegalArgumentException if currentState is null or empty.
     */
    public static ValidationResult invalidState(String currentState){
        validateErrorMessage(currentState);
        return failure(String.format(ValidationErrorMessages.INVALID_STATE, currentState));
    }

    /**
     * Creates a failed validation result for a missing parameter.
     *
     * @param parameterName Name of the missing parameter.
     * @return A ValidationResult indicating a missing parameter.
     * @throws IllegalArgumentException if parameterName is null or empty.
     */
    public static ValidationResult missingParameter(String parameterName){
        validateErrorMessage(parameterName);
        return failure(String.format(ValidationErrorMessages.MISSING_REQUIRED_PARAMETER, parameterName));
    }

    /**
     * Creates a failed validation result for an invalid parameter format.
     *
     * @param details Details about the parameter format error.
     * @return A ValidationResult indicating invalid parameter format.
     * @throws IllegalArgumentException if details is null or empty.
     */
    public static ValidationResult invalidParameterFormat(String details){
        validateErrorMessage(details);
        return failure(String.format(ValidationErrorMessages.INVALID_PARAMETER_FORMAT, details));
    }

    /**
     * Creates a failed validation result for an invalid email address.
     *
     * @param address The invalid email address.
     * @return A ValidationResult indicating an invalid email address.
     * @throws IllegalArgumentException if address is null or empty.
     */
    public static ValidationResult invalidEmailAddress(String address){
        validateErrorMessage(address);
        return failure(String.format(ValidationErrorMessages.INVALID_EMAIL_ADDRESS, address));
    }

    /**
     * Creates a failed validation result for an invalid domain format.
     *
     * @param domain The invalid domain.
     * @return A ValidationResult indicating an invalid domain format.
     * @throws IllegalArgumentException if domain is null or empty.
     */
    public static ValidationResult invalidDomain(String domain){
        validateErrorMessage(domain);
        return failure(String.format(ValidationErrorMessages.INVALID_DOMAIN_FORMAT, domain));
    }

    /**
     * Creates a failed validation result for a command that's too long.
     *
     * @param maxLength The maximum allowed length.
     * @return A ValidationResult indicating a command that's too long.
     */
    public static ValidationResult commandTooLong(int maxLength)
    {
        return failure(String.format(ValidationErrorMessages.COMMAND_TOO_LONG, maxLength));
    }

    /**
     * Creates a failed validation result for too many parameters.
     *
     * @return A ValidationResult indicating too many parameters.
     */
    public static ValidationResult tooManyParameters(){
        return failure(ValidationErrorMessages.TOO_MANY_PARAMETERS);
    }

    /**
     * Creates a failed validation result for too few parameters.
     *
     * @return A ValidationResult indicating too few parameters.
     */
    public static ValidationResult tooFewParameters(){
        return failure(ValidationErrorMessages.TOO_FEW_PARAMETERS);
    }

    /**
     * Creates a failed validation result for a sequence error.
     *
     * @return A ValidationResult indicating a command sequence error.
     */
    public static ValidationResult sequenceError(){
        return failure(ValidationErrorMessages.SEQUENCE_ERROR);
    }

    /**
     * Creates a failed validation result for illegal characters.
     *
     * @param illegalCharacters The illegal characters found.
     * @return A ValidationResult indicating illegal characters were found.
     * @throws IllegalArgumentException if illegalCharacters is null or empty.
     */
    public static ValidationResult illegalCharacters(String illegalCharacters){
        validateErrorMessage(illegalCharacters);
        return failure(String.format(ValidationErrorMessages.CONTAINS_ILLEGAL_CHARACTERS, illegalCharacters));
    }

    /**
     * Creates a failed validation result for a null command.
     *
     * @return A ValidationResult indicating a null command.
     */
    public static ValidationResult nullCommand(){
        return failure(ValidationErrorMessages.NULL_COMMAND);
    }

    /**
     * Creates a failed validation result for a null session.
     *
     * @return A ValidationResult indicating a null session.
     */
    public static ValidationResult nullSession(){
        return failure(ValidationErrorMessages.NULL_SESSION);
    }

    //endregion

    //region Private Static methods

    /**
     * Validates an error message is not null or empty.
     *
     * @param errorMessage The error message to validate.
     * @throws IllegalArgumentException If the error message is null or empty.
     */
    private static void validateErrorMessage(String errorMessage){
        if(errorMessage == null || errorMessage.trim().isEmpty()){
            throw new IllegalArgumentException("Error message cannot be null or empty");
        }
    }

    //endregion

    //region Constructors

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ValidationResultFactory(){
        // This class should not be instantiated.
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    //endregion
}