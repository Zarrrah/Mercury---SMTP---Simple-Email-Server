package com.couture.mercury.core.protocol.validation.steps;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.validation.ValidationStep;

import com.couture.mercury.core.protocol.util.ValidationConstants;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import com.couture.mercury.core.protocol.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class AllowedCharactersValidationStep extends ValidationStep {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllowedCharactersValidationStep.class);
    private final Set<Character> m_allowedChars;

    /**
     * Creates a new allowed characters validation step with the default set of allowed characters.
     */
    public AllowedCharactersValidationStep(){
        m_allowedChars = new HashSet<>();
        for(char character : ValidationConstants.ALLOWED_COMMAND_CHARS.toCharArray()){
            m_allowedChars.add(character);
        }
    }

    @Override
    protected ValidationResult doValidation(Command command, SessionContext context){
        String rawCommand = command.toString();

        // If the command representation doesn't include the raw command string, we can't validate.
        if(rawCommand == null){
            LOGGER.warn("Cannot validate allowed characters: raw string is null. This is likely a programming error.");
            return ValidationResultFactory.success();
        }

        StringBuilder illegalCharacters = new StringBuilder();
        for(char character : rawCommand.toCharArray()){
            if(!m_allowedChars.contains(character) && !illegalCharacters.toString().contains(String.valueOf(character))){
                illegalCharacters.append(character);
            }
        }

        if(!illegalCharacters.isEmpty()){
            return ValidationResultFactory.illegalCharacters(illegalCharacters.toString());
        }

        return ValidationResultFactory.success();
    }
}
