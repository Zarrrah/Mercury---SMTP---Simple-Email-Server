package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.session.SessionState;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Validation step that checks if the command is valid for the current session state.
 */
public class StateValidationStep extends ValidationStep {
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
    protected ValidationResult doValidation(Command command, SessionContext context){
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
