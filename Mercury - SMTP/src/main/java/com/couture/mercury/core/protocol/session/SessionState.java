package com.couture.mercury.core.protocol.session;

/**
 * Represents the current state of an SMTP session.
 */
public enum SessionState {
    CONNECT,
    HELO,
    MAIL,
    RCPT,
    DATA,
    QUIT
}