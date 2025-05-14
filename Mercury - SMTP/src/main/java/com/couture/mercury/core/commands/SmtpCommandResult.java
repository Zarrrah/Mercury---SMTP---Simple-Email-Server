package com.couture.mercury.core.commands;

/**
 * Represents the result of an SMTP command execution.
 */
public class SmtpCommandResult {
    private final boolean m_success;
    private final String m_message;
    private final int m_responseCode;

    /**
     * Constructs an SMTP command result.
     *
     * @param success Indicates whether the command was successful.
     * @param message Descriptive message about command execution.
     * @param responseCode SMTP response code.
     */
    public SmtpCommandResult(boolean success, String message, int responseCode){
        m_success = success;
        m_message = message;
        m_responseCode = responseCode;
    }

    /**
     * Checks whether command execution was successful.
     *
     * @return True if successful, otherwise false.
     */
    public boolean isSuccess() {
        return m_success;
    }

    /**
     *  Returns the descriptive message.
     *
     * @return Execution result message.
     */
    public String getMessage(){
        return m_message;
    }

    /**
     * Retrieves the SMTP response code.
     *
     * @return Response code.
     */
    public int getResponseCode(){
        return m_responseCode;
    }
}
