package org.allaymc.server.registry.populator;

import org.allaymc.api.permission.Permission;
import org.allaymc.api.permission.PermissionListener;
import org.allaymc.api.permission.Permissions;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;

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
                ifIsPlayer((player, value) -> getBaseComponent(player).sendAbilities(player))
        );
        Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR = Permission.create(
                "ability.operator_command_quick_bar",
                "The permission to have operator command quick bar in chat screen",
                ifIsPlayer((player, value) -> getBaseComponent(player).sendAbilities(player))
        );
    }

    private static PermissionListener ifIsPlayer(BiConsumer<EntityPlayerImpl, Boolean> consumer) {
        return (permissible, value) -> {
            if (permissible instanceof EntityPlayerImpl player) {
                consumer.accept(player, value);
            }
        };
    }

    private static EntityPlayerBaseComponentImpl getBaseComponent(EntityPlayerImpl player) {
        return (EntityPlayerBaseComponentImpl) player.getBaseComponent();
    }
}
