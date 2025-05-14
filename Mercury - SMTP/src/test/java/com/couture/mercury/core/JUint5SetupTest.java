package com.couture.mercury.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Simple test class to verify JUnit 5 Setup
 */
public class JUint5SetupTest {
    /**
     * Simple test to verify basic JUnit 5 functionality.
     */
    @Test
    public void testJUnit5Setup()
    {
        // Arrange
        int expected = 2;

        // Act
        int actual = 1 + 1;

        // Assert
        Assertions.assertEquals(expected,actual,"Basic addition should work");
    }

    /**
     * Another simple test to verify multiple assertions.
     */
    @Test
    public void testMultipleAssertions()
    {
        // Arrange
        String _testString = "JUnit5 is working";

        // Assert
        Assertions.assertNotNull(_testString,"String should not be null");
        Assertions.assertTrue(_testString.contains("JUnit5"),"Should should contain JUnit 5");
        Assertions.assertEquals(17,_testString.length(),"String length should be correct");
    }
}
