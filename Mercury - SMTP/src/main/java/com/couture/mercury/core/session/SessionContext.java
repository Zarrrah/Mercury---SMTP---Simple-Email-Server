package com.couture.mercury.core.session;

/**
 * Represents the context of an SMTP session.
 * Provides access to session state and basic attribute storage.
 */
public interface SessionContext {

    /**
     * Gets the current state of this session.
     *
     * @return The current session state.
     */
    SessionState getState();

    /**
     * Sets the current state of this session.
     *
     * @param sessionState The new session state.
     */
    void setState(SessionState sessionState);

    /**
     * Get an attribute from this session.
     *
     * @param key The attribute key.
     * @return The attribute value, or null if not found.
     */
    Object getAttribute(String key);

    /**
     * Sets an attribute in this session.
     *
     * @param key The attribute key.
     * @param value The attribute value, or null to remove the attribute.
     */
    void setAttribute(String key, Object value);
}
