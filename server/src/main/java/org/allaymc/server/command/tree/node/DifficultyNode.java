package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.world.data.Difficulty;

/**
 * @author IWareQ
 */
public class DifficultyNode extends EnumNode {
    private static final String[] VALUES = new String[]{
            "0", "peaceful", "p",
            "1", "easy", "e",
            "2", "normal", "n",
            "3", "hard", "h"
    };

    public DifficultyNode(String name, CommandNode parent, Difficulty defaultValue) {
        super(name, parent, defaultValue, VALUES);
    }

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
