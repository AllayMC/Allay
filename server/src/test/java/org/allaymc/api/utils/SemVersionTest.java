package org.allaymc.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code SemVersionTest} verifies version component construction, conversion defaults, record
 * equality, and the three-component JSON representation.
 *
 * @author daoge_cmd
 */
class SemVersionTest {

    /**
     * {@code ConstructorTests} groups direct construction scenarios for all version components and
     * zero values.
     */
    @Nested
    class ConstructorTests {

        /**
         * Constructs a version with five distinct components. Each generated accessor must expose
         * the corresponding major, minor, patch, revision, or build value without changing its
         * position.
         */
        @Test
        void testBasicConstructor() {
            var version = new SemVersion(1, 2, 3, 4, 5);

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(5, version.build());
        }

        /**
         * Constructs a version whose components are all zero. The major, minor, and patch
         * accessors must retain those zero values as ordinary version components.
         */
        @Test
        void testZeroVersion() {
            var version = new SemVersion(0, 0, 0, 0, 0);

            assertEquals(0, version.major());
            assertEquals(0, version.minor());
            assertEquals(0, version.patch());
        }
    }

    /**
     * {@code FromArrayTests} groups array parsing scenarios for required components, optional
     * suffixes, and rejected short inputs.
     */
    @Nested
    class FromArrayTests {

        /**
         * Parses an array containing only the three required version components. Revision and
         * build must default to zero while the supplied leading components remain unchanged.
         */
        @Test
        void testFromArrayWithThreeElements() {
            var version = SemVersion.from(new int[]{1, 2, 3});

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        /**
         * Parses an array containing a revision but no build component. The supplied revision must
         * remain available while only the absent build component defaults to zero.
         */
        @Test
        void testFromArrayWithFourElements() {
            var version = SemVersion.from(new int[]{1, 2, 3, 4});

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(0, version.build());
        }

        /**
         * Parses an array containing all five version components. Each resulting accessor must
         * preserve the source element at the matching version position without applying a default.
         */
        @Test
        void testFromArrayWithFiveElements() {
            var version = SemVersion.from(new int[]{1, 2, 3, 4, 5});

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(5, version.build());
        }

        /**
         * Parses an empty component array through the special empty-version path. All five
         * resulting components must be zero rather than triggering the short-array validation
         * error.
         */
        @Test
        void testFromEmptyArray() {
            var version = SemVersion.from(new int[]{});

            assertEquals(0, version.major());
            assertEquals(0, version.minor());
            assertEquals(0, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        /**
         * Parses a nonempty array containing only two version components. The operation must
         * reject that incomplete representation instead of supplying a missing patch value.
         */
        @Test
        void testFromArrayWithTwoElementsThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from(new int[]{1, 2})
            );
        }

        /**
         * Parses a nonempty array containing only one version component. The operation must reject
         * that incomplete representation instead of treating it as the empty-version default.
         */
        @Test
        void testFromArrayWithOneElementThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from(new int[]{1})
            );
        }
    }

    /**
     * {@code FromStringTests} groups dotted-string parsing scenarios for optional components and
     * malformed input.
     */
    @Nested
    class FromStringTests {

        /**
         * Parses a dotted string containing the three required components. The supplied values
         * must remain unchanged, with revision and build both defaulting to zero.
         */
        @Test
        void testFromStringWithThreeParts() {
            var version = SemVersion.from("1.2.3");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        /**
         * Parses a dotted version string with an explicit revision. The revision must remain
         * available while only the missing build component receives the zero default.
         */
        @Test
        void testFromStringWithFourParts() {
            var version = SemVersion.from("1.2.3.4");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(0, version.build());
        }

        /**
         * Parses a dotted string containing all five version components. Each resulting component
         * must retain its source value rather than being truncated to the core version.
         */
        @Test
        void testFromStringWithFiveParts() {
            var version = SemVersion.from("1.2.3.4.5");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(5, version.build());
        }

        /**
         * Parses three dotted components containing multiple decimal digits. Numeric parsing must
         * retain each complete component instead of interpreting one character or digit per
         * position.
         */
        @Test
        void testFromStringWithLargeNumbers() {
            var version = SemVersion.from("100.200.300");

            assertEquals(100, version.major());
            assertEquals(200, version.minor());
            assertEquals(300, version.patch());
        }

        /**
         * Parses a dotted string containing only two version components. The operation must reject
         * that incomplete input rather than silently defaulting the missing patch component.
         */
        @Test
        void testFromStringWithTwoPartsThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from("1.2")
            );
        }

        /**
         * Parses a string containing only one version component. The operation must reject that
         * incomplete input rather than treating it as a complete three-component version.
         */
        @Test
        void testFromStringWithOnePartThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from("1")
            );
        }

        /**
         * Parses a three-part string whose patch component is alphabetic. Integer parsing must
         * reject the malformed consumed component with a number-format failure instead of a
         * default.
         */
        @Test
        void testFromStringWithInvalidNumberThrows() {
            assertThrows(NumberFormatException.class, () ->
                    SemVersion.from("1.2.abc")
            );
        }
    }

    /**
     * {@code ToArrayTests} groups version-array conversion and full five-component round-trip
     * scenarios.
     */
    @Nested
    class ToArrayTests {

        /**
         * Converts a five-component version to a primitive array. The array must contain five
         * entries in the same major-through-build order as the original record.
         */
        @Test
        void testToArray() {
            var version = new SemVersion(1, 2, 3, 4, 5);
            int[] array = version.toArray();

            assertEquals(5, array.length);
            assertEquals(1, array[0]);
            assertEquals(2, array[1]);
            assertEquals(3, array[2]);
            assertEquals(4, array[3]);
            assertEquals(5, array[4]);
        }

        /**
         * Converts a five-component version to a boxed integer array. The result must contain all
         * five entries in the same major-through-build order without truncating components.
         */
        @Test
        void testToBoxedArray() {
            var version = new SemVersion(1, 2, 3, 4, 5);
            Integer[] array = version.toBoxedArray();

            assertEquals(5, array.length);
            assertEquals(1, array[0]);
            assertEquals(2, array[1]);
            assertEquals(3, array[2]);
            assertEquals(4, array[3]);
            assertEquals(5, array[4]);
        }

        /**
         * Converts a version with nonzero revision and build to an array and back. Record equality
         * must confirm that this array representation retains all five components.
         */
        @Test
        void testRoundTrip() {
            var original = new SemVersion(10, 20, 30, 40, 50);
            var restored = SemVersion.from(original.toArray());

            assertEquals(original, restored);
        }
    }

    /**
     * {@code RecordTests} groups equality, hash-code, and generated text scenarios for version
     * records.
     */
    @Nested
    class RecordTests {

        /**
         * Compares records with identical components and another with different trailing
         * components. Equality must include revision and build rather than comparing only the
         * three core version values.
         */
        @Test
        void testEquals() {
            var v1 = new SemVersion(1, 2, 3, 4, 5);
            var v2 = new SemVersion(1, 2, 3, 4, 5);
            var v3 = new SemVersion(1, 2, 3, 0, 0);

            assertEquals(v1, v2);
            assertNotEquals(v1, v3);
        }

        /**
         * Constructs two records with the same five version components. Their hash codes must
         * agree with record equality so equal versions remain compatible as map keys.
         */
        @Test
        void testHashCode() {
            var v1 = new SemVersion(1, 2, 3, 4, 5);
            var v2 = new SemVersion(1, 2, 3, 4, 5);

            assertEquals(v1.hashCode(), v2.hashCode());
        }

        /**
         * Renders a version containing five distinct component values. The generated text must
         * include every component value, including the revision and build beyond the core version.
         */
        @Test
        void testToString() {
            var version = new SemVersion(1, 2, 3, 4, 5);
            String str = version.toString();

            assertTrue(str.contains("1"));
            assertTrue(str.contains("2"));
            assertTrue(str.contains("3"));
            assertTrue(str.contains("4"));
            assertTrue(str.contains("5"));
        }
    }

    /**
     * {@code JsonSerializationTests} groups custom JSON adapter scenarios for the three-component
     * version representation.
     */
    @Nested
    class JsonSerializationTests {

        /**
         * {@code gson} holds the serializer and deserializer used by this nested group's tests.
         * Both adapters use the three-component JSON representation, independently of array
         * conversion's
         * five-component representation.
         */
        private final Gson gson = new GsonBuilder()
                .registerTypeAdapter(SemVersion.class, new SemVersion.Serializer())
                .registerTypeAdapter(SemVersion.class, new SemVersion.Deserializer())
                .create();

        /**
         * Serializes a version with nonzero revision and build through the custom adapter. The
         * JSON array must contain only the three core components, explicitly demonstrating its
         * truncation.
         */
        @Test
        void testSerialize() {
            var version = new SemVersion(1, 2, 3, 4, 5);
            String json = gson.toJson(version);

            assertEquals("[1,2,3]", json);
        }

        /**
         * Decodes a three-element JSON version array through the custom adapter. The core values
         * must be restored while revision and build receive their zero defaults.
         */
        @Test
        void testDeserialize() {
            var version = gson.fromJson("[1,2,3]", SemVersion.class);

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        /**
         * Round-trips a core version through the configured JSON adapters. The restored major,
         * minor, and patch values must match the original values after serialization and decoding.
         */
        @Test
        void testSerializeAndDeserialize() {
            var original = new SemVersion(10, 20, 30, 0, 0);
            String json = gson.toJson(original);
            var restored = gson.fromJson(json, SemVersion.class);

            assertEquals(original.major(), restored.major());
            assertEquals(original.minor(), restored.minor());
            assertEquals(original.patch(), restored.patch());
        }
    }
}
