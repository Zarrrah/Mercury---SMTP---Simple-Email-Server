package com.couture.mercury.core.commands.validation;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.session.SessionContext;

/**
 * Abstract base class for command validation steps following the Chain of Responsibility design patten.
 * Each validation step can be linked to a next step, forming a validation chain.
 */
public abstract class SmtpValidationStep {
    private SmtpValidationStep m_nextStep;

    /**
     * Sets the next validation step in the chain.
     *
     * @param nextStep The next validation step to be executed if this step passes.
     * @return The next step, for method chaining.
     */
    public SmtpValidationStep setNext(SmtpValidationStep nextStep){
        m_nextStep = nextStep;
        return m_nextStep;
    }

    /**
     * Gets the next validation step in the chain.
     *
     * @return The next validation step or null if this is the last step.
     */
    protected SmtpValidationStep getNext(){
        return m_nextStep;
    }

    /**
     * Executes this validation step, and if successful, continues to the next step in the chain.
     *
     * @param command The SMTP command to validate.
     * @param context The current session context.
     * @return ValidationResult with the outcome of the validation.
     */
    public ValidationResult validate(SmtpCommand command, SessionContext context){
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
    protected abstract ValidationResult doValidation(SmtpCommand command, SessionContext context);
}