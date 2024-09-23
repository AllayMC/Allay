package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

import static org.allaymc.api.command.data.CommonEnums.GAMEMODE_ENUM;
import static org.allaymc.api.command.data.CommonEnums.getGameTypeFromString;

/**
 * @author daoge_cmd
 */
public class GameModeNode extends EnumNode {
    public GameModeNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue, GAMEMODE_ENUM);
    }

    @Override
    protected Object argToResult(String arg) {
        return getGameTypeFromString(arg);
    }
}
