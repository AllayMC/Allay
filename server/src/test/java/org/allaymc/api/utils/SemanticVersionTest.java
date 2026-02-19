package org.allaymc.api.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SemanticVersionTest {
    @Nested
    class ConstructorTests {
        @Test
        void testBasicConstructor() {
            var version = new SemanticVersion(1, 2, 3, "beta", "build-1");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals("beta", version.perRelease());
            assertEquals("build-1", version.build());
        }
    }

    @Nested
    class FromStringTests {
        @Test
        void simpleVersion() {
            var version = SemanticVersion.fromString("1.2.3");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertNull(version.perRelease());
            assertNull(version.build());
        }

        @Test
        void versionOverTen() {
            var version = SemanticVersion.fromString("12.34.56");

            assertEquals(12, version.major());
            assertEquals(34, version.minor());
            assertEquals(56, version.patch());
            assertNull(version.perRelease());
            assertNull(version.build());
        }

        @Test
        void versionWithPreReleaseAndBuild() {
            var version = SemanticVersion.fromString("1.2.3-beta+build-1");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals("beta", version.perRelease());
            assertEquals("build-1", version.build());
        }

        @Test
        void versionWithPreReleaseOnly() {
            var version = SemanticVersion.fromString("1.2.3-beta");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertEquals("beta", version.perRelease());
            assertNull(version.build());
        }

        @Test
        void versionWithBuildOnly() {
            var version = SemanticVersion.fromString("1.2.3+build-1");

            assertEquals(1, version.major());
            assertEquals(2, version.minor());
            assertEquals(3, version.patch());
            assertNull(version.perRelease());
            assertEquals("build-1", version.build());
        }
    }

    @Nested
    class InvalidStringTests {
        @Test
        void missingPatch() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("1.2")
            );
        }

        @Test
        void nonNumericMajor() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("a.2.3")
            );
        }

        @Test
        void nonNumericMinor() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("1.b.3")
            );
        }

        @Test
        void nonNumericPatch() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("1.2.c")
            );
        }

        @Test
        void onlyPreRelease() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("beta")
            );
        }

        @Test
        void onlyBuild() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("+build-1")
            );
        }

        @Test
        void invalidVersion() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("invalid-version")
            );
        }

        @Test
        void invalidPerRelease() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("1.2.3-")
            );
        }

        @Test
        void invalidBuild() {
            assertThrows(IllegalArgumentException.class, () ->
                    SemanticVersion.fromString("1.2.3+")
            );
        }
    }

    @Nested
    class CompareTests {
        @Test
        void compareVersions() {
            var version1 = SemanticVersion.fromString("1.2.3");
            var version2 = SemanticVersion.fromString("1.2.4");
            var version3 = SemanticVersion.fromString("1.3.0");
            var version4 = SemanticVersion.fromString("2.0.0");

            assertTrue(version1.compareTo(version2) < 0);
            assertTrue(version2.compareTo(version3) < 0);
            assertTrue(version3.compareTo(version4) < 0);
            assertTrue(version4.compareTo(version1) > 0);
        }

        @Test
        void comparePreRelease() {
            var version1 = SemanticVersion.fromString("1.2.3-alpha");
            var version2 = SemanticVersion.fromString("1.2.3-beta");
            var version3 = SemanticVersion.fromString("1.2.3");

            assertTrue(version1.compareTo(version2) < 0);
            assertTrue(version2.compareTo(version3) < 0);
            assertTrue(version3.compareTo(version1) > 0);
        }

        @Test
        void officialSemVerExamples() {
            // 1.0.0-alpha < 1.0.0-alpha.1 < 1.0.0-alpha.beta < 1.0.0-beta < 1.0.0-beta.2 < 1.0.0-beta.11 < 1.0.0-rc.1 < 1.0.0
            var version1 = SemanticVersion.fromString("1.0.0-alpha");
            var version2 = SemanticVersion.fromString("1.0.0-alpha.1");
            var version3 = SemanticVersion.fromString("1.0.0-alpha.beta");
            var version4 = SemanticVersion.fromString("1.0.0-beta");
            var version5 = SemanticVersion.fromString("1.0.0-beta.2");
            var version6 = SemanticVersion.fromString("1.0.0-beta.11");
            var version7 = SemanticVersion.fromString("1.0.0-rc.1");
            var version8 = SemanticVersion.fromString("1.0.0");

            assertTrue(version1.compareTo(version2) < 0);
            assertTrue(version2.compareTo(version3) < 0);
            assertTrue(version3.compareTo(version4) < 0);
            assertTrue(version4.compareTo(version5) < 0);
            assertTrue(version5.compareTo(version6) < 0);
            assertTrue(version6.compareTo(version7) < 0);
            assertTrue(version7.compareTo(version8) < 0);
        }
    }
}
