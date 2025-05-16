package com.couture.mercury.core.protocol.validation.implementation;

import com.couture.mercury.core.protocol.commands.Command;
import com.couture.mercury.core.protocol.session.SessionContext;
import com.couture.mercury.core.protocol.validation.CommandValidator;
import com.couture.mercury.core.protocol.validation.ValidationChainBuilder;
import com.couture.mercury.core.protocol.validation.ValidationStep;
import com.couture.mercury.core.protocol.validation.result.ValidationResult;
import com.couture.mercury.core.protocol.validation.result.ValidationResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for SMTP HELO commands.
 * Implements RFC 5321 validation rules for the HELO command using Chain of Responsibility pattern.
 *
 * <p>Validation includes:
 * <ul>
 *   <li>Command type verification (must be HELO)</li>
 *   <li>Session state validation (must be in CONNECT or HELO state)</li>
 *   <li>Parameter count validation (exactly one parameter required)</li>
 *   <li>Domain format validation (parameter must be a valid domain)</li>
 * </ul>
 * </p>
 */
public final class HELOCommandValidator implements CommandValidator<Command> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HELOCommandValidator.class);

    private final ValidationStep m_validationChain;

    /**
     * Creates a new HELO command validator with the standard validation chain.
     */
    public HELOCommandValidator() {
        // Build the validation chain for HELO commands.
        m_validationChain = ValidationChainBuilder.buildHELOValidationChain();
    }

    @Override
    public ValidationResult validate(Command command, SessionContext context) {
        LOGGER.debug("Validating HELO command: {}", command);

        if (command == null) {
            LOGGER.warn("Null command provided for validation");
            return ValidationResultFactory.nullCommand();
        }

        if (context == null) {
            LOGGER.warn("Null session context provided for validation");
            return ValidationResultFactory.nullSession();
        }

        ValidationResult result = m_validationChain.validate(command, context);

        if (!result.isValid()) {
            LOGGER.debug("HELO command validation failed: {}", result.getErrors());
        } else {
            LOGGER.debug("HELO command validation successful");
        }

        return result;
    }
}
