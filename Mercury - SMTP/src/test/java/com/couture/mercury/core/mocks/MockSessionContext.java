package com.couture.mercury.core.mocks;

import com.couture.mercury.core.session.SessionContext;
import com.couture.mercury.core.session.SessionState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Mock implementation of SessionContext for testing purposes.
 * Provides configurable state and attributes to facilitate testing of components that depend on session context.
 */
public class MockSessionContext implements SessionContext {
    private SessionState m_state;
    private final Map<String, Object> m_attributes;

    /**
     * Creates a mock session context in the CONNECT state.
     */
    public MockSessionContext(){
        m_state = SessionState.CONNECT;
        m_attributes = new HashMap<>();
    }

    /**
     * Creates a mock session context with the specified state.
     *
     * @param state The initial session state.
     */
    public MockSessionContext(SessionState state){
        this();
        setState(state);
    }

    /**
     * Gets the current state of this mock session.
     *
     * @return The current session state
     */
    @Override
    public SessionState getState(){
        return m_state;
    }

    /**
     * Sets the state of this mock session.
     *
     * @param _state The new session state
     */
    @Override
    public void setState(SessionState _state){
        m_state = Objects.requireNonNull(_state, "Session state cannot be null");
    }

    /**
     * Gets an attribute from this mock session.
     *
     * @param key The attribute key.
     * @return The attribute value, or null if not found.
     */
    @Override
    public Object getAttribute(String key){
        return key != null ? m_attributes.get(key) : null;
    }

    /**
     * Sets an attribute in this mock session.
     *
     * @param key The attribute key.
     * @param value The attribute value, or null to remove the attribute.
     */
    @Override
    public void setAttribute(String key, Object value){
        if (key != null){
            if (value != null){
                m_attributes.put(key, value);
            }
            else{
                m_attributes.remove(key);
            }
        }
    }

    /**
     * Returns a string representation of this mock session context.
     *
     * @return A string representation.
     */
    @Override
    public String toString() {
        return "MockSessionContext{" + "state=" + m_state + ", attributes=" + m_attributes + '}';
    }
}