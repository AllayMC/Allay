package org.allaymc.api.permission;

import java.util.Set;

/**
 * PermissionGroups contains all available permission groups that are in pure allay.
 *
 * @author daoge_cmd
 */
public interface PermissionGroups {
    /**
     * Permission group that contains no permission. Suitable for player that only view the server.
     */
    PermissionGroup VISITOR = PermissionGroup.create("VISITOR");

    /**
     * Permission group that contains permissions to do normal activities.
     */
    PermissionGroup MEMBER = PermissionGroup.create(
            "MEMBER",
            Set.of(
                    Permissions.ABILITY_BUILD, Permissions.ABILITY_MINE,
                    Permissions.ABILITY_DOORS_AND_SWITCHES, Permissions.ABILITY_OPEN_CONTAINERS,
                    Permissions.ABILITY_ATTACK_MOBS, Permissions.ABILITY_ATTACK_PLAYERS,
                    Permissions.ABILITY_SUMMON_LIGHTNING, Permissions.ABILITY_CHAT,
                    Permissions.ADVENTURE_SETTING_PVM, Permissions.ADVENTURE_SETTING_MVP
            ),
            Set.of(VISITOR)
    );

    /**
     * Permission group that contains advanced permissions, including using op-only commands.
     */
    PermissionGroup OPERATOR = PermissionGroup.create(
            "OPERATOR",
            Set.of(
                    Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR
            ),
            Set.of(MEMBER)
    );
}
