package com.couture.mercury.core.protocol.validation.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.validation.ValidationChainBuilder;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;

/**
 * Unified validator for all SMTP commands.
 * Uses a command-type specific validation chain to validate each command
 * according to RFC 5321 requirements.
 *
 * <p>Validation includes command-specific checks:</p>
 * <ul>
 *   <li><strong>HELO/EHLO</strong>: Domain validation, proper session state</li>
 *   <li><strong>MAIL</strong>: FROM format, must be in HELO state</li>
 *   <li><strong>RCPT</strong>: TO format, must be in MAIL/RCPT state</li>
 *   <li><strong>DATA</strong>: No parameters, must be in RCPT state with recipients</li>
 *   <li><strong>RSET</strong>: No parameters, valid in most states</li>
 *   <li><strong>QUIT</strong>: No parameters, valid in any state</li>
 *   <li><strong>NOOP</strong>: No parameters, valid in any state</li>
 * </ul>
 */
public final class CommandValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandValidator.class);
    private static final Map<CommandType, ValidationStep> VALIDATION_CHAINS = new EnumMap<>(CommandType.class);

    // Initialise all validation chains once better performance
    static {
        VALIDATION_CHAINS.put(CommandType.HELO, ValidationChainBuilder.buildHELOValidationChain());
        VALIDATION_CHAINS.put(CommandType.EHLO, ValidationChainBuilder.buildEHLOValidationChain());
        VALIDATION_CHAINS.put(CommandType.MAIL, ValidationChainBuilder.buildMAILValidationChain());
        VALIDATION_CHAINS.put(CommandType.RCPT, ValidationChainBuilder.buildRCPTValidationChain());
        VALIDATION_CHAINS.put(CommandType.DATA, ValidationChainBuilder.buildDATAValidationChain());
        VALIDATION_CHAINS.put(CommandType.RSET, ValidationChainBuilder.buildRSETValidationChain());
        VALIDATION_CHAINS.put(CommandType.QUIT, ValidationChainBuilder.buildQUITValidationChain());
        VALIDATION_CHAINS.put(CommandType.NOOP, ValidationChainBuilder.buildNOOPValidationChain());

        // Ensure all command types have a validation chain
        LOGGER.info("Initialized validation chains for {} command types", VALIDATION_CHAINS.size());
    }

    /**
     * Validates an SMTP command against RFC 5321 requirements.
     * Uses the appropriate validation chain based on the command type.
     *
     * @param command The command to validate.
     * @param context The current session context.
     * @return Validation result indicating success or failure with error details.
     */
    public ValidationResult validate(Command command, SessionContext context) {
        if (command == null) {
            LOGGER.warn("Null command provided for validation");
            return ValidationResultFactory.nullCommand();
        }

        if (context == null) {
            LOGGER.warn("Null session context provided for validation");
            return ValidationResultFactory.nullSession();
        }

        CommandType commandType = command.getCommandType();
        LOGGER.debug("Validating {} command: {}", commandType, command);

        ValidationStep validationChain = VALIDATION_CHAINS.get(commandType);
        if (validationChain == null) {
            LOGGER.warn("No validation chain for command type: {}", commandType);
            return ValidationResultFactory.failure("Unsupported command type: " + commandType);
        }

        ValidationResult result = validationChain.validate(command, context);

        if (!result.isValid()) {
            LOGGER.debug("{} command validation failed: {}", commandType, result.getErrors());
        } else {
            LOGGER.debug("{} command validation successful", commandType);
        }

        return result;
    }
}