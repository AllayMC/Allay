package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

import java.util.Locale;

import static org.allaymc.api.player.GameMode.*;

/**
 * {@code GameModeNode} converts accepted game-mode aliases into the
 * corresponding {@code GameMode} value for executors. Inherited matching
 * requires an exact lowercase advertised token before invoking the conversion
 * hook. Spectator aliases are {@code "3"}, {@code "spectator"}, and
 * {@code "spc"}, unlike the separate selector game-mode parser's numeric
 * convention. The constructor stores an omission default independently of
 * optionality.
 *
 * @author daoge_cmd
 */
public class GameModeNode extends EnumNode {

    /**
     * {@code GAME_MODE_ENUM} supplies numeric and textual aliases for matching
     * and client enumeration metadata. Nodes share this array, which the
     * superclass retains after lowercase normalization.
     */
    protected static final String[] GAME_MODE_ENUM = new String[]{
            "0", "survival", "s",
            "1", "creative", "c",
            "2", "adventure", "a",
            "3", "spectator", "spc"
    };

    /**
     * Creates an unattached game-mode node using the shared alias table. The
     * supplied default is retained for later optional omission, and callers
     * attach the node before using its matching behavior.
     */
    public GameModeNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue, GAME_MODE_ENUM);
    }

    /**
     * Returns the {@code GameMode} corresponding to a supported game-mode
     * alias, or {@code null} for an unmatched token. Inherited matching
     * normally restricts calls to advertised lowercase aliases before
     * conversion occurs.
     */
    @Override
    protected Object argToResult(String arg) {
        // Direct conversion calls tolerate case and outer whitespace, but inherited
        // matching still checks the original token against the exact advertised aliases.
        return switch (arg.trim().toLowerCase(Locale.ROOT)) {
            case "0", "survival", "s" -> SURVIVAL;
            case "1", "creative", "c" -> CREATIVE;
            case "2", "adventure", "a" -> ADVENTURE;
            case "3", "spectator", "spc" -> SPECTATOR;
            default -> null;
        };
    }
}
