package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.util.ValidationErrorMessages;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Validation step that checks if at least X recipient(s) has been added to the session.
 * This step is primarily used for validating the DATA command, which requires at least
 * one recipient to be specified via prior RCPT commands.
 */
public class RecipientCountValidationStep extends ValidationStep {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipientCountValidationStep.class);

    private final String m_recipientsAttributeKey;
    private final int m_minRecipientCount;

    /**
     * Creates a new recipient count validation step with default attribution key and minimum count.
     */
    public RecipientCountValidationStep(){
        this("RECIPIENTS", 1);
    }


    /**
     * Creates a new recipient count validation step.
     *
     * @param recipientsAttributeKey The key used to store recipients in the session context.
     * @param minRecipientCount The minimum number of recipients required (At least 1).
     */
    public RecipientCountValidationStep(String recipientsAttributeKey, int minRecipientCount){
        if(recipientsAttributeKey == null || recipientsAttributeKey.isEmpty()){
            throw new IllegalArgumentException("Recipients attribute key cannot be null or empty");
        }

        if(minRecipientCount < 1){
            throw new IllegalArgumentException("Minimum recipient count must be at least 1");
        }

        m_recipientsAttributeKey = recipientsAttributeKey;
        m_minRecipientCount = minRecipientCount;
    }

    @Override
    protected ValidationResult doValidation(Command command, SessionContext context){
        if(context == null){
            return ValidationResultFactory.nullSession();
        }

        Object recipientsObj = context.getAttribute(m_recipientsAttributeKey);

        if(recipientsObj == null){
            return ValidationResultFactory.failure(ValidationErrorMessages.RCPT_NO_RECIPIENTS);
        }

        if(recipientsObj instanceof Collection){
            Collection<?> recipients = (Collection<?>) recipientsObj;

            if(recipients.size() < m_minRecipientCount){
                return ValidationResultFactory.failure(
                        String.format("At least %d recipient(s) required, but only %d found",
                                m_minRecipientCount, recipients.size()));
            }
        }
        else{
            LOGGER.warn("Recipients attribute is not a Collection. This is likely a programming error.");
            return ValidationResultFactory.failure(ValidationErrorMessages.RCPT_NO_RECIPIENTS);
        }

        return ValidationResultFactory.success();
    }
}
