package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.world.Difficulty;

/**
 * @author IWareQ
 */
public class DifficultyNode extends EnumNode {
    private static final String[] VALUES = new String[]{
            "peaceful", "p", "0",
            "easy", "e", "1",
            "normal", "n", "2",
            "hard", "h", "3"
    };

    public DifficultyNode(String name, CommandNode parent, Difficulty defaultValue) {
        super(name, parent, defaultValue, VALUES);
    }

    @Override
    protected Difficulty argToResult(String arg) {
        try {
            return Difficulty.from(Integer.parseInt(arg));
        } catch (NumberFormatException ignored) {
            return Difficulty.from(arg);
        }
    }
}
