package org.allaymc.api.perm;

import org.allaymc.api.command.Command;
import org.allaymc.api.perm.tree.PermTree;

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
                    .addPerm(SUMMON_LIGHTNING).addPerm(PVM).addPerm(MVP)
                    // .addPerm(Command.COMMAND_PERM_PREFIX + "help") // TODO
                    .addPerm(Command.COMMAND_PERM_PREFIX + "me")
                    .addPerm(Command.COMMAND_PERM_PREFIX + "version");

    public static final PermTree OPERATOR = PermTree.create("Operator").extendFrom(MEMBER);

    public static PermTree byName(String name) {
        return switch (name) {
            case "Visitor" -> VISITOR;
            case "Member" -> MEMBER;
            case "Operator" -> OPERATOR;
            default -> null;
        };
    }
}
