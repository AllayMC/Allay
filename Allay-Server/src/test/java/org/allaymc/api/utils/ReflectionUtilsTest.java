package org.allaymc.api.utils;

import lombok.SneakyThrows;
import org.allaymc.api.common.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/8/4
 *
 * @author daoge_cmd
 */
class ReflectionUtilsTest {

    class A {
        int a;

        String methodA() {
            return "origin";
        }
    }

    class B extends A {
        int b;

        @Override
        String methodA() {
            return "override";
        }

        String methodB() {
            return "methodB";
        }
    }

    @Test
    void testGetAllFields() {
        var fields = ReflectionUtils.getAllFields(B.class);
        assertEquals(2, fields.size());
    }

    @Test
    @SneakyThrows
    void testGetAllMethods() {
        var methods = ReflectionUtils.getAllMethods(B.class);
        assertEquals(14, methods.size());
        var b = new B();
        for (var method : methods) {
            if (method.getName().equals("methodA")) assertEquals("override", method.invoke(b));
            else if (method.getName().equals("methodB")) assertEquals("methodB", method.invoke(b));
        }
    }
}