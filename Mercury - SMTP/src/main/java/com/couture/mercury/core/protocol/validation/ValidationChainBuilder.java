package com.couture.mercury.core.protocol.validation;

import com.couture.mercury.core.protocol.commands.CommandType;
import com.couture.mercury.core.protocol.util.CommandConstants;
import com.couture.mercury.core.protocol.util.ValidationConstants;
import com.couture.mercury.core.protocol.util.ValidationDescriptions;
import com.couture.mercury.core.protocol.validation.steps.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder for creating validation chains for SMTP commands.
 * Centralizes the construction of validation chains for all command validators.
 * The validation chains are based on the fixed RFC 5321 specifications.
 */
public final class ValidationChainBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationChainBuilder.class);

    private ValidationChainBuilder() {
        // Utility class should not be instantiated
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Builds a validation chain for the HELO command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildHELOValidationChain() {
        LOGGER.debug("Building HELO validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.HELO);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.HELO_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.HELO_PARAM_COUNT))
                .setNext(new DomainParameterValidationStep());

        return validationChain;
    }

    /**
     * Builds a validation chain for the EHLO command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildEHLOValidationChain() {
        LOGGER.debug("Building EHLO validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.EHLO);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.EHLO_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.EHLO_PARAM_COUNT))
                .setNext(new DomainParameterValidationStep());

        return validationChain;
    }

    /**
     * Builds a validation chain for the MAIL command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildMAILValidationChain() {
        LOGGER.debug("Building MAIL validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.MAIL);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.MAIL_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.MAIL_PARAM_COUNT))
                .setNext(new ParameterPatternValidationStep(
                        ValidationConstants.MAIL_FROM_PATTERN,
                        ValidationDescriptions.Mail.FROM_PATTERN));

        return validationChain;
    }

    /**
     * Builds a validation chain for the RCPT command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildRCPTValidationChain() {
        LOGGER.debug("Building RCPT validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.RCPT);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.RCPT_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.RCPT_PARAM_COUNT))
                .setNext(new ParameterPatternValidationStep(
                        ValidationConstants.RCPT_TO_PATTERN,
                        ValidationDescriptions.Rcpt.TO_PATTERN));

        return validationChain;
    }

    /**
     * Builds a validation chain for the DATA command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildDATAValidationChain() {
        LOGGER.debug("Building DATA validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.DATA);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.DATA_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.DATA_PARAM_COUNT))
                .setNext(new RecipientCountValidationStep(
                        CommandConstants.Attributes.RECIPIENTS_KEY,
                        CommandConstants.Attributes.MIN_RECIPIENT_COUNT));

        return validationChain;
    }

    /**
     * Builds a validation chain for the RSET command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildRSETValidationChain() {
        LOGGER.debug("Building RSET validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.RSET);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.RSET_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.RSET_PARAM_COUNT));

        return validationChain;
    }

    /**
     * Builds a validation chain for the QUIT command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildQUITValidationChain() {
        LOGGER.debug("Building QUIT validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.QUIT);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.QUIT_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.QUIT_PARAM_COUNT));

        return validationChain;
    }

    /**
     * Builds a validation chain for the NOOP command.
     *
     * @return The first step in the validation chain
     */
    public static ValidationStep buildNOOPValidationChain() {
        LOGGER.debug("Building NOOP validation chain");

        ValidationStep validationChain = new CommandTypeValidationStep(CommandType.NOOP);
        validationChain.setNext(new StateValidationStep(CommandConstants.States.NOOP_VALID_STATES))
                .setNext(new ParameterCountValidationStep(ValidationConstants.NOOP_PARAM_COUNT));

        return validationChain;
    }
}