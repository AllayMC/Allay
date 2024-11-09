package org.allaymc.api.permission;

import org.allaymc.api.command.Command;
import org.allaymc.api.permission.tree.PermissionTree;

import static org.allaymc.api.permission.PermissionKeys.*;

/**
 * DefaultPermissions contains some default permission trees.
 *
 * @author daoge_cmd
 */
public final class DefaultPermissions {

    /**
     * The visitor permission tree.
     */
    public static final PermissionTree VISITOR = PermissionTree.create("Visitor");

    /**
     * The member permission tree.
     */
    public static final PermissionTree MEMBER =
            PermissionTree.create("Member")
                    .extendFrom(VISITOR)
                    .addPermission(BUILD).addPermission(MINE)
                    .addPermission(DOORS_AND_SWITCHES).addPermission(OPEN_CONTAINERS)
                    .addPermission(ATTACK_PLAYERS).addPermission(ATTACK_MOBS)
                    .addPermission(SUMMON_LIGHTNING).addPermission(PVM).addPermission(MVP)
                    .addPermission(Command.COMMAND_PERM_PREFIX + "me")
                    .addPermission(Command.COMMAND_PERM_PREFIX + "version");

    /**
     * The operator permission tree.
     */
    public static final PermissionTree OPERATOR = PermissionTree.create("Operator").extendFrom(MEMBER);

    /**
     * Gets a permission tree by name.
     *
     * @param name the name of the permission tree.
     *
     * @return the permission tree, or {@code null} if not found.
     */
    public static PermissionTree byName(String name) {
        return switch (name) {
            case "Visitor" -> VISITOR;
            case "Member" -> MEMBER;
            case "Operator" -> OPERATOR;
            default -> null;
        };
    }
}
