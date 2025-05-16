package com.couture.mercury.core.protocol.validation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;

/**
 * Abstract base class for command validation steps following the Chain of Responsibility design patten.
 * Each validation step can be linked to a next step, forming a validation chain.
 */
public abstract class ValidationStep {
    private ValidationStep m_nextStep;

    /**
     * Sets the next validation step in the chain.
     *
     * @param nextStep The next validation step to be executed if this step passes.
     * @return The next step, for method chaining.
     */
    public ValidationStep setNext(ValidationStep nextStep){
        m_nextStep = nextStep;
        return m_nextStep;
    }

    /**
     * Gets the next validation step in the chain.
     *
     * @return The next validation step or null if this is the last step.
     */
    protected ValidationStep getNext(){
        return m_nextStep;
    }

    /**
     * Executes this validation step, and if successful, continues to the next step in the chain.
     *
     * @param command The SMTP command to validate.
     * @param context The current session context.
     * @return ValidationResult with the outcome of the validation.
     */
    public ValidationResult validate(Command command, SessionContext context){
        ValidationResult result = doValidation(command,context);

        // If this step's validation fails, return the result
        if(!result.isValid()){
            return result;
        }

        // If this step passes and there's a next step, continue the chain.
        if(m_nextStep != null){
            return m_nextStep.validate(command,context);
        }

        // If this step passes and is the last step, return the validation result.
        return result;
    }

    /**
     * Abstract method to be implemented by concrete validation steps.
     * Each validation step should implement its specific validation logic.
     *
     * @param command The SMTP command to validate.
     * @param context The current session context.
     * @return ValidationResult with the outcome of the validation.
     */
    protected abstract ValidationResult doValidation(Command command, SessionContext context);
}