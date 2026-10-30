package org.allaymc.api.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code TextFormatTest} verifies formatting identities, lookup limits, translation, cleaning, and
 * terminal formatting reconstruction.
 *
 * @author daoge_cmd
 */
class TextFormatTest {

    /**
     * {@code EnumValuesTests} groups formatting code identity checks for colors, materials,
     * decorations, and reset.
     */
    @Nested
    class EnumValuesTests {

        /**
         * Checks the prefix character used by internal formatting strings. The public escape
         * constant must remain the section sign expected by the tested color and decoration
         * representations.
         */
        @Test
        void testEscapeCharacter() {
            assertEquals('§', TextFormat.ESCAPE);
        }

        /**
         * Checks each of the sixteen ordinary color code characters. Their enum constants must
         * retain the expected hexadecimal digit or lowercase letter used in chat formatting.
         */
        @Test
        void testColorValues() {
            assertEquals('0', TextFormat.BLACK.getChar());
            assertEquals('1', TextFormat.DARK_BLUE.getChar());
            assertEquals('2', TextFormat.DARK_GREEN.getChar());
            assertEquals('3', TextFormat.DARK_AQUA.getChar());
            assertEquals('4', TextFormat.DARK_RED.getChar());
            assertEquals('5', TextFormat.DARK_PURPLE.getChar());
            assertEquals('6', TextFormat.GOLD.getChar());
            assertEquals('7', TextFormat.GRAY.getChar());
            assertEquals('8', TextFormat.DARK_GRAY.getChar());
            assertEquals('9', TextFormat.BLUE.getChar());
            assertEquals('a', TextFormat.GREEN.getChar());
            assertEquals('b', TextFormat.AQUA.getChar());
            assertEquals('c', TextFormat.RED.getChar());
            assertEquals('d', TextFormat.LIGHT_PURPLE.getChar());
            assertEquals('e', TextFormat.YELLOW.getChar());
            assertEquals('f', TextFormat.WHITE.getChar());
        }

        /**
         * Checks the extra currency and material color code characters. Their enum constants must
         * retain the expected letters independently of the ordinary sixteen-color lookup range.
         */
        @Test
        void testMaterialColors() {
            assertEquals('g', TextFormat.MINECOIN_GOLD.getChar());
            assertEquals('h', TextFormat.MATERIAL_QUARTZ.getChar());
            assertEquals('i', TextFormat.MATERIAL_IRON.getChar());
            assertEquals('j', TextFormat.MATERIAL_NETHERITE.getChar());
            assertEquals('m', TextFormat.MATERIAL_REDSTONE.getChar());
            assertEquals('n', TextFormat.MATERIAL_COPPER.getChar());
            assertEquals('p', TextFormat.MATERIAL_GOLD.getChar());
            assertEquals('q', TextFormat.MATERIAL_EMERALD.getChar());
            assertEquals('s', TextFormat.MATERIAL_DIAMOND.getChar());
            assertEquals('t', TextFormat.MATERIAL_LAPIS.getChar());
            assertEquals('u', TextFormat.MATERIAL_AMETHYST.getChar());
            assertEquals('v', TextFormat.MATERIAL_RESIN.getChar());
        }

        /**
         * Checks the obfuscation, bold, italic, and reset code characters. These constants must
         * preserve the chat control letters used by formatting and reset strings.
         */
        @Test
        void testFormatValues() {
            assertEquals('k', TextFormat.OBFUSCATED.getChar());
            assertEquals('l', TextFormat.BOLD.getChar());
            assertEquals('o', TextFormat.ITALIC.getChar());
            assertEquals('r', TextFormat.RESET.getChar());
        }
    }

    /**
     * {@code TypeCheckTests} groups color and decoration classification scenarios for enum
     * constants.
     */
    @Nested
    class TypeCheckTests {

        /**
         * Checks decoration classification for obfuscation, bold, and italic. Ordinary colors and
         * reset must remain outside the decoration category despite also having formatting code
         * representations.
         */
        @Test
        void testIsFormat() {
            assertTrue(TextFormat.OBFUSCATED.isFormat());
            assertTrue(TextFormat.BOLD.isFormat());
            assertTrue(TextFormat.ITALIC.isFormat());

            assertFalse(TextFormat.RED.isFormat());
            assertFalse(TextFormat.RESET.isFormat());
        }

        /**
         * Checks color classification for ordinary and currency colors. Decoration constants and
         * reset must remain outside the color category used to stop terminal-format
         * reconstruction.
         */
        @Test
        void testIsColor() {
            assertTrue(TextFormat.RED.isColor());
            assertTrue(TextFormat.BLUE.isColor());
            assertTrue(TextFormat.GREEN.isColor());
            assertTrue(TextFormat.MINECOIN_GOLD.isColor());

            assertFalse(TextFormat.BOLD.isColor());
            assertFalse(TextFormat.ITALIC.isColor());
            assertFalse(TextFormat.RESET.isColor());
        }
    }

    /**
     * {@code LookupTests} groups numeric, character, and string formatting lookup scenarios.
     */
    @Nested
    class LookupTests {

        /**
         * Looks up recognized color, decoration, and reset characters alongside unknown
         * characters. Valid codes must resolve to their constants while unknown letters and spaces
         * return absence.
         */
        @Test
        void testGetByChar() {
            assertEquals(TextFormat.RED, TextFormat.getByChar('c'));
            assertEquals(TextFormat.BLUE, TextFormat.getByChar('9'));
            assertEquals(TextFormat.BOLD, TextFormat.getByChar('l'));
            assertEquals(TextFormat.RESET, TextFormat.getByChar('r'));
            assertNull(TextFormat.getByChar('x'));
            assertNull(TextFormat.getByChar(' '));
        }

        /**
         * Exercises the string lookup's minimum-length rule and first-character selection.
         * Two-character strings must resolve by their first character, while one-character, empty,
         * and {@code null} inputs return absence.
         */
        @Test
        void testGetByCharString() {
            assertEquals(TextFormat.RED, TextFormat.getByChar("c_"));
            assertEquals(TextFormat.GREEN, TextFormat.getByChar("a_"));
            assertNull(TextFormat.getByChar("c"));
            assertNull(TextFormat.getByChar(""));
            assertNull(TextFormat.getByChar((String) null));
        }

        /**
         * Looks up ordinary color and decoration numeric identities and an unknown ID. Recognized
         * IDs must resolve to their constants, while the absent numeric mapping returns {@code null}.
         */
        @Test
        void testGetById() {
            assertEquals(TextFormat.BLACK, TextFormat.getById(0x00));
            assertEquals(TextFormat.RED, TextFormat.getById(0xC));
            assertEquals(TextFormat.BOLD, TextFormat.getById(0x11));
            assertNull(TextFormat.getById(999));
        }
    }

    /**
     * {@code ToStringTests} groups formatting text rendering and message concatenation scenarios.
     */
    @Nested
    class ToStringTests {

        /**
         * Renders representative color, decoration, and reset constants as strings. Each result
         * must combine the section-sign prefix with its corresponding lowercase code character
         * exactly.
         */
        @Test
        void testToString() {
            assertEquals("§c", TextFormat.RED.toString());
            assertEquals("§a", TextFormat.GREEN.toString());
            assertEquals("§l", TextFormat.BOLD.toString());
            assertEquals("§r", TextFormat.RESET.toString());
        }

        /**
         * Concatenates enum constants with ordinary message text. String conversion must insert
         * the exact section-sign formatting pairs in the expected positions of the composed
         * message.
         */
        @Test
        void testConcatenation() {
            String text = TextFormat.RED + "Hello " + TextFormat.BOLD + "World";
            assertEquals("§cHello §lWorld", text);
        }
    }

    /**
     * {@code CleanTests} groups accepted-code removal, absent input, and recursive cleaning
     * scenarios.
     */
    @Nested
    class CleanTests {

        /**
         * Cleans ordinary color and decoration pairs from text that also includes a plain example.
         * Accepted formatting must disappear while unformatted text remains unchanged in the
         * result.
         */
        @Test
        void testCleanSimple() {
            assertEquals("Hello World", TextFormat.clean("§cHello §aWorld"));
            assertEquals("Test", TextFormat.clean("§lTest"));
            assertEquals("Plain text", TextFormat.clean("Plain text"));
        }

        /**
         * Passes {@code null} to the one-pass cleaning helper. The operation must preserve the
         * absent-input sentinel instead of constructing an empty string or raising a parse
         * failure.
         */
        @Test
        void testCleanNull() {
            assertNull(TextFormat.clean(null));
        }

        /**
         * Passes an empty string to the cleaning helper. The operation must preserve the empty
         * text result instead of adding formatting or changing the message contents.
         */
        @Test
        void testCleanEmpty() {
            assertEquals("", TextFormat.clean(""));
        }

        /**
         * Cleans adjacent color and decoration pairs before ordinary text. Every accepted pair in
         * the tested prefix must disappear while the message text remains intact afterward.
         */
        @Test
        void testCleanMultipleCodes() {
            assertEquals("Bold Red", TextFormat.clean("§l§cBold Red"));
            assertEquals("Hello", TextFormat.clean("§c§l§oHello"));
        }

        /**
         * Cleans the nested section-sign fixture using recursive mode. The resulting fixture text
         * must contain no remaining section sign after the helper repeats accepted-code removal.
         */
        @Test
        void testCleanRecursive() {
            String nested = "§c§§aText";
            String result = TextFormat.clean(nested, true);
            assertFalse(result.contains("§"));
        }
    }

    /**
     * {@code ColorizeTests} groups alternate-prefix translation and unchanged literal-text
     * scenarios.
     */
    @Nested
    class ColorizeTests {

        /**
         * Translates ampersand-prefixed color and decoration pairs. The returned text must contain
         * corresponding section-sign codes while retaining the surrounding ordinary message words
         * unchanged.
         */
        @Test
        void testColorizeWithAmpersand() {
            assertEquals("§cHello §aWorld", TextFormat.colorize("&cHello &aWorld"));
            assertEquals("§lBold", TextFormat.colorize("&lBold"));
        }

        /**
         * Translates formatting pairs using two caller-selected prefix characters. Each result
         * must use the same internal section-sign codes as the ordinary ampersand translation
         * path.
         */
        @Test
        void testColorizeWithCustomChar() {
            assertEquals("§cRed", TextFormat.colorize('#', "#cRed"));
            assertEquals("§l§cBold Red", TextFormat.colorize('%', "%l%cBold Red"));
        }

        /**
         * Translates a recognized color pair in text containing a literal ampersand. The literal
         * ampersand must remain while only the recognized formatting prefix is replaced.
         */
        @Test
        void testColorizePreservesOtherAmpersands() {
            assertEquals("Tom & Jerry §cRed", TextFormat.colorize("Tom & Jerry &cRed"));
        }

        /**
         * Translates uppercase color and decoration letters after the alternate prefix. The
         * resulting internal codes must use lowercase letters while retaining the original
         * surrounding message text.
         */
        @Test
        void testColorizeUpperCase() {
            assertEquals("§chello", TextFormat.colorize("&Chello"));
            assertEquals("§lbold", TextFormat.colorize("&Lbold"));
        }

        /**
         * Translates the ordinary sixteen-color ampersand sequence in one message. Every
         * recognized code must retain its order after conversion to section-sign-prefixed internal
         * formatting text.
         */
        @Test
        void testColorizeAllColors() {
            String input = "&0&1&2&3&4&5&6&7&8&9&a&b&c&d&e&f";
            String expected = "§0§1§2§3§4§5§6§7§8§9§a§b§c§d§e§f";
            assertEquals(expected, TextFormat.colorize(input));
        }

        /**
         * Translates the tested extra material and currency code letters. Their ampersand-prefixed
         * forms must become matching section-sign codes for the explicitly covered letter groups.
         */
        @Test
        void testColorizeMaterialColors() {
            assertEquals("§g§h§i§j", TextFormat.colorize("&g&h&i&j"));
            assertEquals("§m§n§p§q", TextFormat.colorize("&m&n&p&q"));
            assertEquals("§s§t§u", TextFormat.colorize("&s&t&u"));
        }
    }

    /**
     * {@code GetLastColorsTests} groups terminal color, decoration, and reset reconstruction
     * scenarios.
     */
    @Nested
    class GetLastColorsTests {

        /**
         * Finds the terminal color in messages containing one or several color changes. The most
         * recent recognized color must replace earlier colors in the returned continuation state.
         */
        @Test
        void testGetLastColorsSimple() {
            assertEquals("§c", TextFormat.getLastColors("Hello §cWorld"));
            assertEquals("§a", TextFormat.getLastColors("§cHello §aWorld"));
        }

        /**
         * Finds a color followed later by bold decoration. The continuation state must restore the
         * color before the decoration even though the implementation scans the message backward.
         */
        @Test
        void testGetLastColorsWithFormat() {
            assertEquals("§c§l", TextFormat.getLastColors("§cHello §lWorld"));
        }

        /**
         * Finds a reset following an earlier color in the message. The returned continuation state
         * must contain the reset alone rather than restoring the superseded color.
         */
        @Test
        void testGetLastColorsReset() {
            assertEquals("§r", TextFormat.getLastColors("§cHello §rWorld"));
        }

        /**
         * Checks messages with no formatting and an entirely empty message. Both must produce an
         * empty continuation sequence rather than inventing a default color or decoration.
         */
        @Test
        void testGetLastColorsEmpty() {
            assertEquals("", TextFormat.getLastColors("No colors here"));
            assertEquals("", TextFormat.getLastColors(""));
        }

        /**
         * Checks a later color after both a decoration and an earlier color. The later color must
         * bound reconstruction so the superseded decoration is not carried forward.
         */
        @Test
        void testGetLastColorsColorStopsSearch() {
            assertEquals("§a", TextFormat.getLastColors("§l§c§aText"));
        }
    }
}
