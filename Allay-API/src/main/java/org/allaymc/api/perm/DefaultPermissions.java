package org.allaymc.api.perm;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.perm.tree.PermTree;

import java.util.HashMap;
import java.util.Map;

import static org.allaymc.api.perm.PermKeys.*;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public final class DefaultPermissions {

    public static final PermTree VISITOR = PermTree.create("Visitor");

    public static final PermTree MEMBER =
            PermTree.create("Member")
                    .extendFrom(VISITOR)
                    .addPerm(BUILD).addPerm(MINE)
                    .addPerm(DOORS_AND_SWITCHES).addPerm(OPEN_CONTAINERS)
                    .addPerm(ATTACK_PLAYERS).addPerm(ATTACK_MOBS)
                    .addPerm(SUMMON_LIGHTNING).addPerm(CHAT)
                    .addPerm(PVM).addPerm(MVP)
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "help")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "me");

    public static final PermTree OPERATOR =
            PermTree.create("Operator")
                    .extendFrom(MEMBER)
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gamemode")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gm")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gamerule")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gr")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gametest")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gt")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "stop");

    private static final Map<String, PermTree> NAME_LOOK_UP = new HashMap<>();

    static {
        NAME_LOOK_UP.put(VISITOR.getName(), VISITOR);
        NAME_LOOK_UP.put(MEMBER.getName(), MEMBER);
        NAME_LOOK_UP.put(OPERATOR.getName(), OPERATOR);
    }

    public static PermTree byName(String name) {
        return NAME_LOOK_UP.get(name);
    }
}
