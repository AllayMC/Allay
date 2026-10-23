package org.allaymc.server.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ReflectionUtilsTest} verifies inheritance traversal, method selection, private-interface
 * identity, and name-based constant mapping.
 *
 * @author daoge_cmd
 */
class ReflectionUtilsTest {
    /**
     * Collects fields from a subclass containing its own field and one superclass field. Discovery
     * must return both declarations rather than stopping at the requested class.
     */
    @Test
    void testGetAllFields() {
        var fields = ReflectionUtils.getAllFields(B.class);
        assertEquals(2, fields.size());
    }

    /**
     * Collects subclass and inherited methods and invokes the selected overrides. The expected
     * method count permits coverage instrumentation, while invocation must use the subclass
     * override and its distinct helper.
     */
    @Test
    @SneakyThrows
    void testGetAllMethods() {
        var methods = ReflectionUtils.getAllMethods(B.class);
        // Coverage instrumentation can add a synthetic method to this fixture. Accept
        // either count while the invocation assertions still protect override selection behavior.
        assertTrue(methods.size() == 14 || methods.size() == 15);
        var b = new B();
        for (var method : methods) {
            if (method.getName().equals("methodA")) assertEquals("override", method.invoke(b));
            else if (method.getName().equals("methodB")) assertEquals("methodB", method.invoke(b));
        }
    }

    /**
     * Collects methods from a class relying on an interface default. Discovery must include that
     * default declaration even though the implementing class declares no corresponding method
     * itself.
     */
    @Test
    void testGetAllMethodsIncludesInterfaceDefaultMethods() {
        var methods = ReflectionUtils.getAllMethods(C.class);
        assertTrue(methods.stream().anyMatch(m -> m.getName().equals("defaultMethod")));
    }

    /**
     * Collects methods from a class implementing two unrelated interfaces with same-named private
     * helpers. Both helper declarations must survive instead of being collapsed by their shared
     * local signature.
     */
    @Test
    void testGetAllMethodsDoesNotDeduplicatePrivateInterfaceMethodsAcrossInterfaces() {
        var methods = ReflectionUtils.getAllMethods(F.class);
        assertEquals(2, methods.stream().filter(m -> m.getName().equals("helper")).count());
    }

    /**
     * Maps two enum constant sets by their declared field names. Shared names must resolve in both
     * directions, while the unmatched source constant must have no association.
     */
    @Test
    void testMapStaticFields() {
        var map = ReflectionUtils.mapStaticFields(EnumA.class, EnumB.class);
        assertEquals(EnumB.A, map.get(EnumA.A));
        assertEquals(EnumB.B, map.get(EnumA.B));
        assertNull(map.get(EnumA.C));

        var inverse = map.inverse();
        assertEquals(EnumA.A, inverse.get(EnumB.A));
        assertEquals(EnumA.B, inverse.get(EnumB.B));
    }

    /**
     * {@code A} supplies the superclass field and original method used by inheritance discovery
     * tests.
     */
    static class A {
        /**
         * Superclass field discovery fixture.
         */
        int a;

        /**
         * Returns the superclass marker used to verify subclass override selection.
         */
        String methodA() {
            return "origin";
        }
    }

    /**
     * {@code B} adds a field, overrides the inherited method, and supplies a distinct test helper.
     */
    static class B extends A {
        /**
         * Subclass field discovery fixture.
         */
        int b;

        /**
         * Returns the subclass marker selected instead of the inherited implementation.
         */
        @Override
        String methodA() {
            return "override";
        }

        /**
         * Returns the distinct subclass helper marker used during reflective invocation.
         */
        String methodB() {
            return "methodB";
        }
    }

    /**
     * {@code WithDefaultMethod} supplies an interface default method for inherited-method
     * discovery.
     */
    interface WithDefaultMethod {
        /**
         * Returns the interface marker used to verify inherited default discovery.
         */
        default String defaultMethod() {
            return "default";
        }
    }

    /**
     * {@code PrivateMethodA} owns one private helper whose name also appears in an unrelated
     * interface.
     */
    interface PrivateMethodA {
        /**
         * Supplies an empty private declaration whose identity must remain interface-specific.
         */
        private void helper() {}
    }

    /**
     * {@code PrivateMethodB} owns a separate private helper with the same local signature as
     * another interface.
     */
    interface PrivateMethodB {
        /**
         * Supplies an empty private declaration whose identity must remain interface-specific.
         */
        private void helper() {}
    }

    /**
     * {@code C} inherits an interface default method without declaring its own implementation.
     */
    static class C implements WithDefaultMethod {}

    /**
     * {@code F} combines two unrelated private-interface helper declarations for
     * identity-preservation tests.
     */
    static class F implements PrivateMethodA, PrivateMethodB {}

    /**
     * {@code EnumA} supplies three source constants for name-based bidirectional mapping tests.
     */
    enum EnumA {
        /** Shared first source constant. */
        A,
        /** Shared second source constant. */
        B,
        /** Unmatched third source constant. */
        C
    }

    /**
     * {@code EnumB} supplies two target constants whose names match the source enum.
     */
    enum EnumB {
        /** Shared first target constant. */
        A,
        /** Shared second target constant. */
        B
    }
}
