package org.allaymc.api.permission;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface PermissionGroups {
    PermissionGroup VISITOR = PermissionGroup.create("VISITOR");

    PermissionGroup MEMBER = PermissionGroup.create(
            "MEMBER",
            Set.of(
                    Permissions.ABILITY_BUILD, Permissions.ABILITY_MINE,
                    Permissions.ABILITY_DOORS_AND_SWITCHES, Permissions.ABILITY_OPEN_CONTAINERS,
                    Permissions.ABILITY_ATTACK_MOBS, Permissions.ABILITY_ATTACK_PLAYERS,
                    Permissions.ABILITY_SUMMON_LIGHTNING, Permissions.ABILITY_CHAT,
                    Permissions.ADVENTURE_SETTING_PVM, Permissions.ADVENTURE_SETTING_MVP
            ),
            VISITOR
    );

    PermissionGroup OPERATOR = PermissionGroup.create(
            "OPERATOR",
            Set.of(
                    Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR
            ),
            MEMBER
    );
}
