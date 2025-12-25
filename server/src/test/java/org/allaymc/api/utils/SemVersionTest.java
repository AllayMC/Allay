package org.allaymc.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class SemVersionTest {

    @Nested
    class ConstructorTests {

        @Test
        void testBasicConstructor() {
            var version = new SemVersion(1, 2, 3, 4, 5);

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(5, version.build());
        }

        @Test
        void testZeroVersion() {
            var version = new SemVersion(0, 0, 0, 0, 0);

            assertEquals(0, version.major());
            assertEquals(0, version.minor());
            assertEquals(0, version.patch());
        }
    }

    @Nested
    class FromArrayTests {

        @Test
        void testFromArrayWithThreeElements() {
            var version = SemVersion.from(new int[]{1, 2, 3});

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        @Test
        void testFromArrayWithFourElements() {
            var version = SemVersion.from(new int[]{1, 2, 3, 4});

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(0, version.build());
        }

        @Test
        void testFromArrayWithFiveElements() {
            var version = SemVersion.from(new int[]{1, 2, 3, 4, 5});

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(5, version.build());
        }

        @Test
        void testFromEmptyArray() {
            var version = SemVersion.from(new int[]{});

            assertEquals(0, version.major());
            assertEquals(0, version.minor());
            assertEquals(0, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        @Test
        void testFromArrayWithTwoElementsThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from(new int[]{1, 2})
            );
        }

        @Test
        void testFromArrayWithOneElementThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from(new int[]{1})
            );
        }
    }

    @Nested
    class FromStringTests {

        @Test
        void testFromStringWithThreeParts() {
            var version = SemVersion.from("1.2.3");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

        @Test
        void testFromStringWithFourParts() {
            var version = SemVersion.from("1.2.3.4");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(0, version.build());
        }

        @Test
        void testFromStringWithFiveParts() {
            var version = SemVersion.from("1.2.3.4.5");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(4, version.revision());
            assertEquals(5, version.build());
        }

        @Test
        void testFromStringWithLargeNumbers() {
            var version = SemVersion.from("100.200.300");

            assertEquals(100, version.major());
            assertEquals(200, version.minor());
            assertEquals(300, version.patch());
        }

        @Test
        void testFromStringWithTwoPartsThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from("1.2")
            );
        }

        @Test
        void testFromStringWithOnePartThrows() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemVersion.from("1")
            );
        }

        @Test
        void testFromStringWithInvalidNumberThrows() {
            assertThrows(NumberFormatException.class, () ->
                    SemVersion.from("1.2.abc")
            );
        }
    }

    @Nested
    class ToArrayTests {

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

        @Test
        void testRoundTrip() {
            var original = new SemVersion(10, 20, 30, 40, 50);
            var restored = SemVersion.from(original.toArray());

            assertEquals(original, restored);
        }
    }

    @Nested
    class RecordTests {

        @Test
        void testEquals() {
            var v1 = new SemVersion(1, 2, 3, 4, 5);
            var v2 = new SemVersion(1, 2, 3, 4, 5);
            var v3 = new SemVersion(1, 2, 3, 0, 0);

            assertEquals(v1, v2);
            assertNotEquals(v1, v3);
        }

        @Test
        void testHashCode() {
            var v1 = new SemVersion(1, 2, 3, 4, 5);
            var v2 = new SemVersion(1, 2, 3, 4, 5);

            assertEquals(v1.hashCode(), v2.hashCode());
        }

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

    @Nested
    class JsonSerializationTests {

        private final Gson gson = new GsonBuilder()
                .registerTypeAdapter(SemVersion.class, new SemVersion.Serializer())
                .registerTypeAdapter(SemVersion.class, new SemVersion.Deserializer())
                .create();

        @Test
        void testSerialize() {
            var version = new SemVersion(1, 2, 3, 4, 5);
            String json = gson.toJson(version);

            assertEquals("[1,2,3]", json);
        }

        @Test
        void testDeserialize() {
            var version = gson.fromJson("[1,2,3]", SemVersion.class);

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals(0, version.revision());
            assertEquals(0, version.build());
        }

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
