package org.allaymc.api.permission;

import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;

import java.util.HashSet;
import java.util.Set;

/**
 * The default permission calculator used in Allay, where the operator player has all permissions
 * and the non-operator player has a limited list of permissions.
 *
 * @param player the player
 * @author daoge_cmd
 */
public record OpPermissionCalculator(Player player) implements PermissionCalculator {

    /**
     * A list of permissions that are granted by default to non-operator players. Plugin can
     * add more permissions to this list.
     */
    public static final Set<String> NON_OP_PERMISSIONS = new HashSet<>(Set.of(
            Permissions.ABILITY_CHAT,
            Permissions.COMMAND_ME,
            Permissions.COMMAND_VERSION
    ));

    @Override
    public Tristate calculatePermission(String permission) {
        if (Server.getInstance().getPlayerManager().isOperator(player)) {
            return Tristate.TRUE;
        }

        if (NON_OP_PERMISSIONS.contains(permission)) {
            return Tristate.TRUE;
        }

        return Tristate.UNDEFINED;
    }
}
