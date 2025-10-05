package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

import java.util.Locale;

import static org.allaymc.api.player.GameMode.*;

/**
 * @author daoge_cmd
 */
public class GameModeNode extends EnumNode {

    protected static final String[] GAME_MODE_ENUM = new String[]{
            "0", "survival", "s",
            "1", "creative", "c",
            "2", "adventure", "a",
            "3", "spectator", "spc"
    };

    public GameModeNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue, GAME_MODE_ENUM);
    }

    @Override
    protected Object argToResult(String arg) {
        return switch (arg.trim().toLowerCase(Locale.ROOT)) {
            case "0", "survival", "s" -> SURVIVAL;
            case "1", "creative", "c" -> CREATIVE;
            case "2", "adventure", "a" -> ADVENTURE;
            case "3", "spectator", "spc" -> SPECTATOR;
            default -> null;
        };
    }
}
