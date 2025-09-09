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
        Permissions.ABILITY_BUILD = Permission.create(
                "ability.build",
                "The permission to build blocks",
                syncAbility(Ability.BUILD)
        );
        Permissions.ABILITY_MINE = Permission.create(
                "ability.mine",
                "The permission to mine blocks",
                syncAbility(Ability.MINE)
        );
        Permissions.ABILITY_DOORS_AND_SWITCHES = Permission.create(
                "ability.door_and_switches",
                "The permission to use doors and switches",
                syncAbility(Ability.DOORS_AND_SWITCHES)
        );
        Permissions.ABILITY_OPEN_CONTAINERS = Permission.create(
                "ability.open_containers",
                "The permission to open containers",
                syncAbility(Ability.OPEN_CONTAINERS)
        );
        Permissions.ABILITY_ATTACK_PLAYERS = Permission.create(
                "ability.attack_players",
                "The permission to attack players",
                syncAbility(Ability.ATTACK_PLAYERS)
        );
        Permissions.ABILITY_ATTACK_MOBS = Permission.create(
                "ability.attack_mobs",
                "The permission to attack mobs",
                syncAbility(Ability.ATTACK_MOBS)
        );
        Permissions.ABILITY_MAY_FLY = Permission.create(
                "ability.mayfly",
                "The permission to fly in the sky",
                syncAbility(Ability.MAY_FLY)
        );
        Permissions.ABILITY_SUMMON_LIGHTNING = Permission.create(
                "ability.summon_lightning",
                "The permission to summon lightning",
                syncAbility(Ability.LIGHTNING)
        );
        Permissions.ABILITY_CHAT = Permission.create(
                "ability.muted",
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
                getBaseComponent(player).getAbilities().set(ability, inverted != value);
            }
        };
    }

    private static EntityPlayerBaseComponentImpl getBaseComponent(EntityPlayer player) {
        return (EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent();
    }
}
