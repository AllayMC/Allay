package org.allaymc.server.registry.populator;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.Permission;
import org.allaymc.api.permission.PermissionListener;
import org.allaymc.api.permission.Permissions;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.protocol.bedrock.data.Ability;

/**
 * @author daoge_cmd
 */
public class PermissionRegistryPopulator implements Runnable {
    @Override
    public void run() {
        Permissions.ABILITY_FLY = Permission.create(
                "ability.fly",
                "The permission to fly in the sky",
                syncAbility(Ability.MAY_FLY)
        );
        Permissions.ABILITY_CHAT = Permission.create(
                "ability.chat",
                "The permission to chat",
                syncAbility(Ability.MUTED, true)
        );
        Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR = Permission.create(
                "ability.operator_command_quick_bar",
                "The permission to have operator command quick bar in chat screen",
                syncAbility(Ability.OPERATOR_COMMANDS)
        );
    }

    private static PermissionListener syncAbility(Ability ability) {
        return syncAbility(ability, false);
    }

    private static PermissionListener syncAbility(Ability ability, boolean inverted) {
        return (permissible, value) -> {
            if (permissible instanceof EntityPlayer player) {
                getBaseComponent(player).getAbilities().setWithoutSend(ability, inverted != value);
            }
        };
    }

    private static EntityPlayerBaseComponentImpl getBaseComponent(EntityPlayer player) {
        return (EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent();
    }
}
