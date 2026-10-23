package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.world.data.Difficulty;

/**
 * {@code DifficultyNode} converts accepted difficulty aliases into the
 * corresponding {@code Difficulty} value for executors. Inherited matching
 * requires an exact lowercase advertised token before invoking the conversion
 * hook. The supported aliases include {@code "0"} through {@code "3"}, full
 * lowercase names, and their one-letter abbreviations. The constructor stores
 * an omission default independently of optionality.
 *
 * @author IWareQ
 */
public class DifficultyNode extends EnumNode {
    /**
     * {@code VALUES} supplies numeric and textual aliases for matching and
     * client enumeration metadata. Nodes share this array, which the superclass
     * retains after lowercase normalization.
     */
    private static final String[] VALUES = new String[]{
            "0", "peaceful", "p",
            "1", "easy", "e",
            "2", "normal", "n",
            "3", "hard", "h"
    };

    /**
     * Creates an unattached difficulty node using the shared alias table. The
     * supplied default is retained for later optional omission, and callers
     * attach the node before using its matching behavior.
     */
    public DifficultyNode(String name, CommandNode parent, Difficulty defaultValue) {
        super(name, parent, defaultValue, VALUES);
    }

    /**
     * Returns the {@code Difficulty} corresponding to a supported difficulty
     * alias, or {@code null} for an unmatched token. Inherited matching
     * normally restricts calls to advertised lowercase aliases before
     * conversion occurs.
     */
    @Override
    protected Difficulty argToResult(String arg) {
        return switch (arg) {
            case "0", "peaceful", "p" -> Difficulty.PEACEFUL;
            case "1", "easy", "e" -> Difficulty.EASY;
            case "2", "normal", "n" -> Difficulty.NORMAL;
            case "3", "hard", "h" -> Difficulty.HARD;
            default -> null;
        };
    }
}
