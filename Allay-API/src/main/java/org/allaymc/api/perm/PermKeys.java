package org.allaymc.api.perm;

import org.allaymc.api.command.Command;

/**
 * Allay Project 2024/1/6
 *
 * @author daoge_cmd
 */
public interface PermKeys {
    // Vanilla abilities
    String BUILD = "ability.build";
    String MINE = "ability.mine";
    String DOORS_AND_SWITCHES = "ablilty.door_and_switches";
    String OPEN_CONTAINERS = "ability.open_containers";
    String ATTACK_PLAYERS = "ability.attack_players";
    String ATTACK_MOBS = "ability.attack_mobs";
    String OPERATOR_COMMANDS = Command.COMMAND_PERM_PREFIX + "*";
    String TELEPORT = "ability.teleport";
    String MAY_FLY = "ability.mayfly";
    String SUMMON_LIGHTNING = "ability.summon_lightning";
}
