package org.allaymc.api.permission;

import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;

import java.util.HashSet;
import java.util.Set;

/**
 * {@code OpPermissionCalculator} evaluates a player's permissions using current
 * operator membership and the shared non-operator permission set. Operators
 * receive every permission; other players receive only listed permissions, with
 * all remaining queries producing {@link Tristate#UNDEFINED} rather than an
 * explicit denial.
 *
 * @param player the player whose operator membership is queried on each check
 * @author daoge_cmd
 */
public record OpPermissionCalculator(Player player) implements PermissionCalculator {

    /**
     * {@code NON_OP_PERMISSIONS} contains names granted to non-operator players.
     * Plugins can modify this shared set to affect subsequent checks; modifying
     * it does not itself invoke {@link Permissible#onPermissionChange()}.
     */
    public static final Set<String> NON_OP_PERMISSIONS = new HashSet<>(Set.of(
            Permissions.ABILITY_CHAT,
            Permissions.COMMAND_ME,
            Permissions.COMMAND_VERSION
    ));

    /**
     * Returns a grant for operators or names in {@link #NON_OP_PERMISSIONS}.
     * The current server's player manager supplies operator membership on each
     * call; unmatched non-operator queries return {@link Tristate#UNDEFINED}.
     */
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
