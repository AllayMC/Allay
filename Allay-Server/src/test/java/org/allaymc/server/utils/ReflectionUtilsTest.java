package org.allaymc.server.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
class ReflectionUtilsTest {
    @Test
    void testGetAllFields() {
        var fields = ReflectionUtils.getAllFields(B.class);
        assertEquals(2, fields.size());
    }

    @Test
    @SneakyThrows
    void testGetAllMethods() {
        var methods = ReflectionUtils.getAllMethods(B.class);
        // NOTICE: Will have an extra jacoco method if jacoco is enabled
        // So both 14 and 15 are correct
        assertTrue(methods.size() == 14 || methods.size() == 15);
        var b = new B();
        for (var method : methods) {
            if (method.getName().equals("methodA")) assertEquals("override", method.invoke(b));
            else if (method.getName().equals("methodB")) assertEquals("methodB", method.invoke(b));
        }
    }

    static class A {
        int a;

        String methodA() {
            return "origin";
        }
    }

    static class B extends A {
        int b;

        @Override
        String methodA() {
            return "override";
        }

        String methodB() {
            return "methodB";
        }
    }
}