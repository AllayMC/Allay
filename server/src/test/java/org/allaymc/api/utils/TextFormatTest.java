package org.allaymc.api.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class TextFormatTest {

    @Nested
    class EnumValuesTests {

        @Test
        void testEscapeCharacter() {
            assertEquals('§', TextFormat.ESCAPE);
        }

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

        @Test
        void testFormatValues() {
            assertEquals('k', TextFormat.OBFUSCATED.getChar());
            assertEquals('l', TextFormat.BOLD.getChar());
            assertEquals('o', TextFormat.ITALIC.getChar());
            assertEquals('r', TextFormat.RESET.getChar());
        }
    }

    @Nested
    class TypeCheckTests {

        @Test
        void testIsFormat() {
            assertTrue(TextFormat.OBFUSCATED.isFormat());
            assertTrue(TextFormat.BOLD.isFormat());
            assertTrue(TextFormat.ITALIC.isFormat());

            assertFalse(TextFormat.RED.isFormat());
            assertFalse(TextFormat.RESET.isFormat());
        }

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

    @Nested
    class LookupTests {

        @Test
        void testGetByChar() {
            assertEquals(TextFormat.RED, TextFormat.getByChar('c'));
            assertEquals(TextFormat.BLUE, TextFormat.getByChar('9'));
            assertEquals(TextFormat.BOLD, TextFormat.getByChar('l'));
            assertEquals(TextFormat.RESET, TextFormat.getByChar('r'));
            assertNull(TextFormat.getByChar('x'));
            assertNull(TextFormat.getByChar(' '));
        }

        @Test
        void testGetByCharString() {
            assertEquals(TextFormat.RED, TextFormat.getByChar("c"));
            assertEquals(TextFormat.GREEN, TextFormat.getByChar("a"));
            assertNull(TextFormat.getByChar(""));
            assertNull(TextFormat.getByChar((String) null));
        }

        @Test
        void testGetById() {
            assertEquals(TextFormat.BLACK, TextFormat.getById(0x00));
            assertEquals(TextFormat.RED, TextFormat.getById(0xC));
            assertEquals(TextFormat.BOLD, TextFormat.getById(0x11));
            assertNull(TextFormat.getById(999));
        }
    }

    @Nested
    class ToStringTests {

        @Test
        void testToString() {
            assertEquals("§c", TextFormat.RED.toString());
            assertEquals("§a", TextFormat.GREEN.toString());
            assertEquals("§l", TextFormat.BOLD.toString());
            assertEquals("§r", TextFormat.RESET.toString());
        }

        @Test
        void testConcatenation() {
            String text = TextFormat.RED + "Hello " + TextFormat.BOLD + "World";
            assertEquals("§cHello §lWorld", text);
        }
    }

    @Nested
    class CleanTests {

        @Test
        void testCleanSimple() {
            assertEquals("Hello World", TextFormat.clean("§cHello §aWorld"));
            assertEquals("Test", TextFormat.clean("§lTest"));
            assertEquals("Plain text", TextFormat.clean("Plain text"));
        }

        @Test
        void testCleanNull() {
            assertNull(TextFormat.clean(null));
        }

        @Test
        void testCleanEmpty() {
            assertEquals("", TextFormat.clean(""));
        }

        @Test
        void testCleanMultipleCodes() {
            assertEquals("Bold Red", TextFormat.clean("§l§cBold Red"));
            assertEquals("Hello", TextFormat.clean("§c§l§oHello"));
        }

        @Test
        void testCleanRecursive() {
            String nested = "§c§§aText";
            String result = TextFormat.clean(nested, true);
            assertFalse(result.contains("§"));
        }
    }

    @Nested
    class ColorizeTests {

        @Test
        void testColorizeWithAmpersand() {
            assertEquals("§cHello §aWorld", TextFormat.colorize("&cHello &aWorld"));
            assertEquals("§lBold", TextFormat.colorize("&lBold"));
        }

        @Test
        void testColorizeWithCustomChar() {
            assertEquals("§cRed", TextFormat.colorize('#', "#cRed"));
            assertEquals("§l§cBold Red", TextFormat.colorize('%', "%l%cBold Red"));
        }

        @Test
        void testColorizePreservesOtherAmpersands() {
            assertEquals("Tom & Jerry §cRed", TextFormat.colorize("Tom & Jerry &cRed"));
        }

        @Test
        void testColorizeUpperCase() {
            assertEquals("§chello", TextFormat.colorize("&Chello"));
            assertEquals("§lbold", TextFormat.colorize("&Lbold"));
        }

        @Test
        void testColorizeAllColors() {
            String input = "&0&1&2&3&4&5&6&7&8&9&a&b&c&d&e&f";
            String expected = "§0§1§2§3§4§5§6§7§8§9§a§b§c§d§e§f";
            assertEquals(expected, TextFormat.colorize(input));
        }

        @Test
        void testColorizeMaterialColors() {
            assertEquals("§g§h§i§j", TextFormat.colorize("&g&h&i&j"));
            assertEquals("§m§n§p§q", TextFormat.colorize("&m&n&p&q"));
            assertEquals("§s§t§u", TextFormat.colorize("&s&t&u"));
        }
    }

    @Nested
    class GetLastColorsTests {

        @Test
        void testGetLastColorsSimple() {
            assertEquals("§c", TextFormat.getLastColors("Hello §cWorld"));
            assertEquals("§a", TextFormat.getLastColors("§cHello §aWorld"));
        }

        @Test
        void testGetLastColorsWithFormat() {
            assertEquals("§l§c", TextFormat.getLastColors("§cHello §lWorld"));
        }

        @Test
        void testGetLastColorsReset() {
            assertEquals("§r", TextFormat.getLastColors("§cHello §rWorld"));
        }

        @Test
        void testGetLastColorsEmpty() {
            assertEquals("", TextFormat.getLastColors("No colors here"));
            assertEquals("", TextFormat.getLastColors(""));
        }

        @Test
        void testGetLastColorsColorStopsSearch() {
            assertEquals("§a", TextFormat.getLastColors("§l§c§aText"));
        }
    }
}
