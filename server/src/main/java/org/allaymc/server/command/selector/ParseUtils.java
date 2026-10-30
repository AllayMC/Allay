package org.allaymc.server.command.selector;

import lombok.experimental.UtilityClass;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.server.AllayServer;

/**
 * {@code ParseUtils} interprets numeric offsets and common value constraints
 * used by entity selector arguments. Tilde-prefixed numbers add to a supplied
 * origin, while absolute values ignore it. Helpers also enforce single-value
 * and reversal rules and map supported game-mode tokens to selector-facing
 * numbers.
 */
@UtilityClass
public class ParseUtils {

    /**
     * Returns an absolute integer value or a tilde offset added to
     * {@code base}. A bare {@code "~"} returns {@code base} unchanged;
     * malformed numeric text raises {@code SelectorSyntaxException} with the
     * numeric parse failure as its cause. Relative addition uses ordinary
     * integer arithmetic and can overflow without a range check.
     */
    public static int parseOffsetInt(String value, int base) throws SelectorSyntaxException {
        try {
            // The selector's tilde prefix selects relative coordinates; only that
            // form adds the supplied origin, while absolute input remains independent.
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
     * Returns an absolute single-precision value or a tilde offset added to
     * {@code base}. A bare {@code "~"} returns {@code base} unchanged;
     * malformed numeric text raises {@code SelectorSyntaxException} with the
     * numeric parse failure as its cause.
     */
    public static float parseOffsetFloat(String value, float base) throws SelectorSyntaxException {
        try {
            // The selector's tilde prefix selects relative coordinates; only that
            // form adds the supplied origin, while absolute input remains independent.
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
     * Returns an absolute double-precision value or a tilde offset added to
     * {@code base}. A bare {@code "~"} returns {@code base} unchanged;
     * malformed numeric text raises {@code SelectorSyntaxException} with the
     * numeric parse failure as its cause.
     */
    public static double parseOffsetDouble(String value, double base) throws SelectorSyntaxException {
        try {
            // The selector's tilde prefix selects relative coordinates; only that
            // form adds the supplied origin, while absolute input remains independent.
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
     * Returns whether the supplied selector value begins with the reversal
     * marker {@code !}.
     */
    public static boolean checkReversed(String value) {
        return value.startsWith("!");
    }

    /**
     * Rejects a value whose first character is the reversal marker {@code !}.
     * Other values pass unchanged; this check does not parse or otherwise
     * validate the remaining value text.
     */
    public static void cannotReversed(String value) throws SelectorSyntaxException {
        if (checkReversed(value)) {
            throw new SelectorSyntaxException("Argument cannot be reversed!");
        }
    }

    /**
     * Rejects more than one occurrence of a selector key with an
     * argument-specific syntax error. An empty {@code args} array passes this
     * guard, so handlers accessing its first element still require at least one
     * supplied value.
     */
    public static void singleArgument(String[] args, String keyName) throws SelectorSyntaxException {
        if (args.length > 1) {
            throw new SelectorSyntaxException("Multiple arguments is not allow in arg " + keyName);
        }
    }

    /**
     * Returns whether the value lies between the two bounds, including both
     * endpoints. Bound order is irrelevant; ordinary comparisons reject
     * {@code NaN} inputs rather than providing a special numeric fallback.
     */
    public static boolean checkBetween(double bound1, double bound2, double value) {
        return bound1 < bound2 ?
                (value >= bound1 && value <= bound2) :
                (value >= bound2 && value <= bound1);
    }

    /**
     * Returns the selector-facing number for an exact supported game-mode
     * token. Survival, creative, and adventure map to {@code 0}, {@code 1}, and
     * {@code 2}; spectator maps to {@code 6}. {@code "d"} and {@code "default"}
     * read the configured default mode's ordinal, and unknown tokens raise
     * {@code SelectorSyntaxException}.
     */
    public static int parseGameMode(String token) throws SelectorSyntaxException {
        return switch (token) {
            case "s", "survival", "0" -> 0;
            case "c", "creative", "1" -> 1;
            case "a", "adventure", "2" -> 2;
            case "spectator", "6" -> 6;
            case "d", "default" -> AllayServer.getSettings().genericSettings().defaultGameMode().ordinal();
            default -> throw new SelectorSyntaxException("Unknown GameMode token: " + token);
        };
    }
}
