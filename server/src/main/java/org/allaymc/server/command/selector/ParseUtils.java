package org.allaymc.server.command.selector;

import lombok.experimental.UtilityClass;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.server.Server;

/**
 * ParseUtils provides some commonly used static functions about target selector parsing.
 */
@UtilityClass
public class ParseUtils {

    /**
     * Parse the offset int value
     *
     * @param value Text
     * @param base  Base value
     *
     * @return Offset value
     */
    public static int parseOffsetInt(String value, int base) throws SelectorSyntaxException {
        try {
            if (value.startsWith("~")) {
                return value.length() != 1 ? base + Integer.parseInt(value.substring(1)) : base;
            } else {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            throw new SelectorSyntaxException("Error parsing target selector", e);
        }
    }

    /**
     * Parse the offset double value
     *
     * @param value Text
     * @param base  Base value
     *
     * @return Offset value
     */
    public static float parseOffsetFloat(String value, float base) throws SelectorSyntaxException {
        try {
            if (value.startsWith("~")) {
                return value.length() != 1 ? base + Float.parseFloat(value.substring(1)) : base;
            } else {
                return Float.parseFloat(value);
            }
        } catch (NumberFormatException e) {
            throw new SelectorSyntaxException("Error parsing target selector", e);
        }
    }

    /**
     * Parse the offset double value
     *
     * @param value Text
     * @param base  Base value
     *
     * @return Offset value
     */
    public static double parseOffsetDouble(String value, double base) throws SelectorSyntaxException {
        try {
            if (value.startsWith("~")) {
                return value.length() != 1 ? base + Double.parseDouble(value.substring(1)) : base;
            } else {
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException e) {
            throw new SelectorSyntaxException("Error parsing target selector", e);
        }
    }

    /**
     * Check if the parameter is reversed
     *
     * @param value Given string
     *
     * @return Whether it is reversed
     */
    public static boolean checkReversed(String value) {
        return value.startsWith("!");
    }

    /**
     * Require the parameter not to be reversed. If reversed, throw {@link SelectorSyntaxException}
     *
     * @param value Given string
     */
    public static void cannotReversed(String value) throws SelectorSyntaxException {
        if (checkReversed(value))
            throw new SelectorSyntaxException("Argument cannot be reversed!");
    }

    /**
     * Require the parameter not to be more than 1
     *
     * @param args    Parameter list
     * @param keyName Parameter key name
     */
    public static void singleArgument(String[] args, String keyName) throws SelectorSyntaxException {
        if (args.length > 1)
            throw new SelectorSyntaxException("Multiple arguments is not allow in arg " + keyName);
    }

    /**
     * Check if the given value is between the given two numbers
     *
     * @param bound1 Boundary 1
     * @param bound2 Boundary 2
     * @param value  The value
     *
     * @return Whether the given value is between the given two numbers
     */
    public static boolean checkBetween(double bound1, double bound2, double value) {
        return bound1 < bound2 ?
                (value >= bound1 && value <= bound2) :
                (value >= bound2 && value <= bound1);
    }

    /**
     * Parse the game mode number from the given game mode string
     * <p>
     * This method matches the parameter with the predefined value of the original selector parameter m
     *
     * @param token String
     *
     * @return Game mode number
     */
    public static int parseGameMode(String token) throws SelectorSyntaxException {
        return switch (token) {
            case "s", "survival", "0" -> 0;
            case "c", "creative", "1" -> 1;
            case "a", "adventure", "2" -> 2;
            case "spectator", "6" -> 6;
            case "d", "default" -> Server.SETTINGS.genericSettings().defaultGameType().ordinal();
            default -> throw new SelectorSyntaxException("Unknown GameMode token: " + token);
        };
    }
}
