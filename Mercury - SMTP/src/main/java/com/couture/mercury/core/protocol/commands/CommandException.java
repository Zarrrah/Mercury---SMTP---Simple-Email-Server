package com.couture.mercury.core.protocol.commands;

/**
 * Base exception for command-related errors in SMTP protocol processing.
 */
public class CommandException extends Exception{
    private final int m_responseCode;

    /**
     * Constructs a new SMTP command exception.
     * @param message Detailed error message.
     * @param responseCode Corresponding SMTP response code.
     */
    public CommandException(String message, int responseCode){
        super(message);
        m_responseCode = responseCode;
    }

    /**
     * Constructs a new SMTP command exception with a cause.
     *
     * @param message Detailed error message.
     * @param cause Original cause of the exception.
     * @param responseCode Corresponding SMTP response code.
     */
    public CommandException(String message, Throwable cause, int responseCode){
        super(message, cause);
        m_responseCode = responseCode;
    }

    /**
     * Retrieves the SMTP response code associated with this exception.
     *
     * @return SMTP response code.
     */
    public int getResponseCode(){
        return m_responseCode;
    }
}