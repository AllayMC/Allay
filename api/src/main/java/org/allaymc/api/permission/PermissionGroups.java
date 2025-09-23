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
            Set.of(Permissions.ABILITY_CHAT),
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
