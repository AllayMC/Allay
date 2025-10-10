package org.allaymc.server.registry.populator;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.Permission;
import org.allaymc.api.permission.PermissionListener;
import org.allaymc.api.permission.Permissions;

import java.util.function.BiConsumer;

/**
 * @author daoge_cmd
 */
public class PermissionRegistryPopulator implements Runnable {
    @Override
    public void run() {
        Permissions.ABILITY_FLY = Permission.create(
                "ability.fly",
                "The permission to fly in the sky",
                ifIsPlayer((player, value) -> {
                    if (!value && player.isFlying()) {
                        player.setFlying(false);
                    }
                })
        );
        Permissions.ABILITY_CHAT = Permission.create(
                "ability.chat",
                "The permission to chat",
                ifIsPlayer((player, value) -> player.viewPlayerPermission(player))
        );
        Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR = Permission.create(
                "ability.operator_command_quick_bar",
                "The permission to have operator command quick bar in chat screen",
                ifIsPlayer((player, value) -> player.viewPlayerPermission(player))
        );
    }

    private static PermissionListener ifIsPlayer(BiConsumer<EntityPlayer, Boolean> consumer) {
        return (permissible, value) -> {
            if (permissible instanceof EntityPlayer player) {
                consumer.accept(player, value);
            }
        };
    }
}
