package org.allaymc.api.permission;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.Ability;

import java.util.function.BiConsumer;

/**
 * Contains all the permissions that are used in allay.
 *
 * @author daoge_cmd
 */
public interface Permissions {
    // Vanilla abilities
    Permission ABILITY_BUILD = Permission.create(
            "ability.build",
            "The permission to build blocks",
            syncAbility(Ability.BUILD)
    );
    Permission ABILITY_MINE = Permission.create(
            "ability.mine",
            "The permission to mine blocks",
            syncAbility(Ability.MINE)
    );
    Permission ABILITY_DOORS_AND_SWITCHES = Permission.create(
            "ability.door_and_switches",
            "The permission to use doors and switches",
            syncAbility(Ability.DOORS_AND_SWITCHES)
    );
    Permission ABILITY_OPEN_CONTAINERS = Permission.create(
            "ability.open_containers",
            "The permission to open containers",
            syncAbility(Ability.OPEN_CONTAINERS)
    );
    Permission ABILITY_ATTACK_PLAYERS = Permission.create(
            "ability.attack_players",
            "The permission to attack players",
            syncAbility(Ability.ATTACK_PLAYERS)
    );
    Permission ABILITY_ATTACK_MOBS = Permission.create(
            "ability.attack_mobs",
            "The permission to attack mobs",
            syncAbility(Ability.ATTACK_MOBS)
    );
    Permission ABILITY_MAY_FLY = Permission.create(
            "ability.mayfly",
            "The permission to fly in the sky",
            syncAbility(Ability.MAY_FLY)
    );
    Permission ABILITY_SUMMON_LIGHTNING = Permission.create(
            "ability.summon_lightning",
            "The permission to summon lightning",
            syncAbility(Ability.LIGHTNING)
    );
    Permission ABILITY_CHAT = Permission.create(
            "ability.muted",
            "The permission to chat",
            syncAbility(Ability.MUTED, true)
    );
    Permission ABILITY_OPERATOR_COMMAND_QUICK_BAR = Permission.create(
            "ability.operator_command_quick_bar",
            "The permission to have operator command quick bar in chat screen",
            syncAbility(Ability.OPERATOR_COMMANDS)
    );

    // Vanilla adventure settings
    Permission ADVENTURE_SETTING_PVM = Permission.create(
            "adventure_setting.pvm",
            "",
            ifIsPlayer((player, value) -> {
                player.getAdventureSettings().setNoPVM(!value);
            })
    );
    Permission ADVENTURE_SETTING_MVP = Permission.create(
            "adventure_setting.mvp",
            "",
            ifIsPlayer((player, value) -> {
                player.getAdventureSettings().setNoMVP(!value);
            })
    );
    Permission ADVENTURE_SETTING_AUTO_JUMP = Permission.create(
            "adventure_setting.auto_jump",
            "The permission to enable auto jump",
            ifIsPlayer((player, value) -> {
                player.getAdventureSettings().setAutoJump(value);
            })
    );
    Permission ADVENTURE_SETTING_SHOW_NAME_TAGS = Permission.create(
            "adventure_setting.view_name_tags",
            "The permission to show own's name tags",
            ifIsPlayer((player, value) -> {
                player.getAdventureSettings().setShowNameTags(value);
            })
    );

    private static PermissionListener syncAbility(Ability ability) {
        return syncAbility(ability, false);
    }

    private static PermissionListener syncAbility(Ability ability, boolean inverted) {
        return (permissible, value) -> {
            if (permissible instanceof EntityPlayer player) {
                player.getAbilities().set(ability, inverted != value);
            }
        };
    }

    private static PermissionListener ifIsPlayer(BiConsumer<EntityPlayer, Boolean> consumer) {
        return (permissible, value) -> {
            if (permissible instanceof EntityPlayer player) {
                consumer.accept(player, value);
            }
        };
    }
}
