package org.allaymc.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * {@code TextFormat} represents the chat color, decoration, and reset codes recognized by
 * the server. Each constant supplies a numeric identity, a lowercase code character, and its
 * section-sign-prefixed text representation. Lookup is case-sensitive, while translation and
 * cleaning apply their own explicit accepted-character sets.
 *
 * <p>
 * Continuing formatting across message fragments requires the most recent color or reset
 * and the decorations that follow it. {@code getLastColors} scans backward, prepends recognized
 * codes to restore their original order, and stops at that boundary because earlier colors
 * and decorations are superseded. Cleaning has a separate compatibility limit: its pattern
 * omits the material codes {@code m} and {@code n} and the resin code {@code v}; alternate
 * translation also omits {@code v}.
 *
 * @author daoge_cmd | Nukkit
 */
public enum TextFormat {
    /**
     * Black text color.
     */
    BLACK('0', 0x00),
    /**
     * Dark blue text color.
     */
    DARK_BLUE('1', 0x1),
    /**
     * Dark green text color.
     */
    DARK_GREEN('2', 0x2),
    /**
     * Dark aqua text color.
     */
    DARK_AQUA('3', 0x3),
    /**
     * Dark red text color.
     */
    DARK_RED('4', 0x4),
    /**
     * Dark purple text color.
     */
    DARK_PURPLE('5', 0x5),
    /**
     * Gold text color.
     */
    GOLD('6', 0x6),
    /**
     * Gray text color.
     */
    GRAY('7', 0x7),
    /**
     * Dark gray text color.
     */
    DARK_GRAY('8', 0x8),
    /**
     * Blue text color.
     */
    BLUE('9', 0x9),
    /**
     * Green text color.
     */
    GREEN('a', 0xA),
    /**
     * Aqua text color.
     */
    AQUA('b', 0xB),
    /**
     * Red text color.
     */
    RED('c', 0xC),
    /**
     * Light purple text color.
     */
    LIGHT_PURPLE('d', 0xD),
    /**
     * Yellow text color.
     */
    YELLOW('e', 0xE),
    /**
     * White text color.
     */
    WHITE('f', 0xF),
    /**
     * Minecoin gold text color.
     */
    MINECOIN_GOLD('g', 0x16),
    /**
     * Material quartz text color.
     */
    MATERIAL_QUARTZ('h', 0x17),
    /**
     * Material iron text color.
     */
    MATERIAL_IRON('i', 0x18),
    /**
     * Material netherite text color.
     */
    MATERIAL_NETHERITE('j', 0x19),
    /**
     * Material redstone text color.
     */
    MATERIAL_REDSTONE('m', 0x20),
    /**
     * Material copper text color.
     */
    MATERIAL_COPPER('n', 0x21),
    /**
     * Material gold text color.
     */
    MATERIAL_GOLD('p', 0x22),
    /**
     * Material emerald text color.
     */
    MATERIAL_EMERALD('q', 0x23),
    /**
     * Material diamond text color.
     */
    MATERIAL_DIAMOND('s', 0x24),
    /**
     * Material lapis text color.
     */
    MATERIAL_LAPIS('t', 0x25),
    /**
     * Material amethyst text color.
     */
    MATERIAL_AMETHYST('u', 0x26),
    /**
     * Material resin text color.
     */
    MATERIAL_RESIN('v', 0x27),
    /**
     * Obfuscated text decoration.
     */
    OBFUSCATED('k', 0x10, true),
    /**
     * Bold text decoration.
     */
    BOLD('l', 0x11, true),
    /**
     * Italic text decoration.
     */
    ITALIC('o', 0x14, true),
    /**
     * Reset formatting reset.
     */
    RESET('r', 0x15);

    /**
     * {@code ESCAPE} prefixes internal chat formatting codes with the section sign. Alternate
     * format translation replaces a selected caller-supplied prefix with this character for
     * recognized code letters.
     */
    public static final char ESCAPE = '§';

    /**
     * {@code CLEAN_PATTERN} matches removable section-sign codes case-insensitively. Its ranges
     * exclude material redstone, material copper, and resin codes even though those enum constants
     * exist in this type.
     */
    private static final Pattern CLEAN_PATTERN = Pattern.compile("(?i)" + ESCAPE + "[0-9A-LO-U]");

    // BY_ID and BY_CHAR index the same enum constants by numeric identity and code character.
    // The static initializer builds both tables once before public lookup methods are used.
    /**
     * Numeric formatting identity index.
     */
    private final static Map<Integer, TextFormat> BY_ID = new TreeMap<>();
    /**
     * Lowercase formatting character index.
     */
    private final static Map<Character, TextFormat> BY_CHAR = new HashMap<>();

    // Builds both lookup tables from the declared formatting constants during class initialization.
    static {
        for (TextFormat color : values()) {
            BY_ID.put(color.intCode, color);
            BY_CHAR.put(color.code, color);
        }
    }

    /**
     * Numeric formatting code identity.
     */
    private final int intCode;
    /**
     * Lowercase formatting code character.
     */
    private final char code;
    /**
     * Decoration rather than color flag.
     */
    private final boolean isFormat;
    /**
     * Cached section-sign code string.
     */
    private final String toString;

    /**
     * Creates a code whose decoration flag defaults to {@code false}. Color classification then treats
     * it as a color unless the enum constant is {@code RESET}, which remains a separate
     * formatting-state boundary.
     */
    TextFormat(char code, int intCode) {
        this(code, intCode, false);
    }

    /**
     * Creates a formatting code and caches its section-sign-prefixed text representation.
     */
    TextFormat(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{ESCAPE, code});
    }

    /**
     * Returns the constant registered for the numeric formatting identity. Unknown IDs produce
     * {@code null}, including gaps between declared IDs; lookup does not reinterpret the numeric
     * value as a formatting character.
     */
    public static TextFormat getById(int intCode) {
        return BY_ID.get(intCode);
    }

    /**
     * Returns the constant registered for the exact code character. Lowercase recognized letters
     * and numeric characters resolve, while uppercase or unknown characters return {@code null}
     * without automatic normalization.
     */
    public static TextFormat getByChar(char code) {
        return BY_CHAR.get(code);
    }

    /**
     * Looks up the first character only when {@code code} has at least two characters. {@code
     * null},
     * empty strings, and one-character strings return {@code null}; the remaining suffix is
     * ignored
     * and the first character is not normalized.
     */
    public static TextFormat getByChar(String code) {
        if (code == null || code.length() <= 1) return null;
        return BY_CHAR.get(code.charAt(0));
    }

    /**
     * Removes one pass of codes accepted by {@code CLEAN_PATTERN}, returning {@code null} for
     * {@code null} input. Material redstone, material copper, and resin codes remain because the
     * pattern does not include their code letters.
     */
    public static String clean(String input) {
        return clean(input, false);
    }

    /**
     * Removes codes accepted by {@code CLEAN_PATTERN} and optionally repeats until none remain.
     * Repeating also removes matching codes exposed when an earlier match disappears; {@code null}
     * input returns {@code null}, and unsupported letters remain untouched.
     */
    public static String clean(String input, boolean recursive) {
        if (input == null) return null;

        var result = CLEAN_PATTERN.matcher(input).replaceAll("");
        // Removing one code can expose another section-sign pair across its old boundary.
        // Recursive cleaning repeats until no accepted pair remains in the resulting text.
        if (recursive && CLEAN_PATTERN.matcher(result).find()) {
            return clean(result, true);
        }

        return result;
    }

    /**
     * Replaces recognized alternate-prefix codes with section-sign codes and lowercase letters.
     * The accepted table includes the declared material colors through amethyst but omits resin's
     * {@code v}; unknown pairs remain unchanged and {@code null} input is rejected by
     * character-array
     * conversion.
     */
    public static String colorize(char altFormatChar, String textToTranslate) {
        var text = textToTranslate.toCharArray();
        for (int i = 0; i < text.length - 1; i++) {
            if (text[i] == altFormatChar && "0123456789AaBbCcDdEeFfGgHhIiJjMmNnPpQqSsTtUuKkLlOoRr".indexOf(text[i + 1]) > -1) {
                text[i] = TextFormat.ESCAPE;
                text[i + 1] = Character.toLowerCase(text[i + 1]);
            }
        }

        return new String(text);
    }

    /**
     * Translates recognized ampersand-prefixed codes through the alternate-prefix overload.
     * Unknown pairs and the resin code remain unchanged, while recognized letters are lowercased
     * in the returned string; {@code null} input is not accepted.
     */
    public static String colorize(String textToTranslate) {
        return colorize('&', textToTranslate);
    }

    /**
     * Returns the recognized terminal formatting sequence needed to continue text into another
     * fragment. The method scans backward and prepends codes so their original order is retained
     * in the returned sequence.
     *
     * <p>
     * The most recent color or reset ends the search because it supersedes earlier color and
     * decoration state. Decorations after that boundary remain, while unknown characters and
     * uppercase code letters contribute no recognized format; a string without recognized codes
     * returns the empty string.
     */
    public static String getLastColors(String input) {
        StringBuilder result = new StringBuilder();
        int length = input.length();

        // Later colors supersede earlier state. Scan from the end so the first color or
        // reset bounds the decorations that must be carried into the next message fragment.
        for (int index = length - 1; index > -1; index--) {
            char section = input.charAt(index);
            if (section == ESCAPE && index < length - 1) {
                char c = input.charAt(index + 1);
                TextFormat color = getByChar(c);

                if (color == null) continue;
                result.insert(0, color);

                // A color or reset replaces all earlier formatting state. Keep the codes
                // already collected after it and omit the superseded prefix of the message.
                if (color.isColor() || color.equals(RESET)) break;
            }
        }

        return result.toString();
    }

    /**
     * Returns the character following the section sign for this format.
     */
    public char getChar() {
        return code;
    }

    /**
     * Returns the cached section-sign-prefixed representation of this formatting code.
     */
    @Override
    public String toString() {
        return toString;
    }

    /**
     * Returns whether this constant applies a decoration rather than a color.
     */
    public boolean isFormat() {
        return isFormat;
    }

    /**
     * Returns whether this constant is a color rather than decoration or reset.
     */
    public boolean isColor() {
        return !isFormat && this != RESET;
    }
}
