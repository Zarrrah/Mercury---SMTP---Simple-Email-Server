package com.couture.mercury.core.commands.validation.steps;

import com.couture.mercury.core.commands.SmtpCommand;
import com.couture.mercury.core.commands.validation.SmtpValidationStep;
import com.couture.mercury.core.commands.validation.ValidationResult;
import com.couture.mercury.core.commands.validation.ValidationResultFactory;
import com.couture.mercury.core.session.SessionContext;
import com.couture.mercury.core.session.SessionState;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Validation step that checks if the command is valid for the current session state.
 */
public class StateValidationStep extends SmtpValidationStep {
    private final Set<SessionState> m_validStates;

    /**
     * Creates a new state validation step.
     *
     * @param validStates Set of valid session states for the command.
     */
    public StateValidationStep(SessionState... validStates){
        m_validStates = new HashSet<>(Arrays.asList(validStates));
    }

    @Override
    protected ValidationResult doValidation(SmtpCommand command, SessionContext context){
        if(context == null){
            return ValidationResultFactory.nullSession();
        }

        SessionState state = context.getState();
        if(!m_validStates.contains(state)){
            return ValidationResultFactory.invalidState(state.toString());
        }

        return ValidationResultFactory.success();
    }
}
