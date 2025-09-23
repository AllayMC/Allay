package org.allaymc.server.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.AbilityLayer;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAbilitiesPacket;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author daoge_cmd
 */
@Getter
public final class Abilities {

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLY_SPEED = 0.05f;
    public static final float DEFAULT_VERTICAL_FLY_SPEED = 1.0f;

    private float flySpeed = DEFAULT_FLY_SPEED;
    private float verticalFlySpeed = DEFAULT_VERTICAL_FLY_SPEED;
    private final EntityPlayer player;
    private final Set<Ability> abilities;

    public Abilities(EntityPlayer player) {
        this.player = player;
        this.abilities = EnumSet.noneOf(Ability.class);
    }

    public void applyGameMode(GameMode gameMode) {
        this.player.setPermission(Permissions.ABILITY_FLY, gameMode != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE);
        setWithoutSend(Ability.BUILD, gameMode != GameMode.SPECTATOR);
        setWithoutSend(Ability.MINE, gameMode != GameMode.SPECTATOR);
        setWithoutSend(Ability.DOORS_AND_SWITCHES, gameMode != GameMode.SPECTATOR);
        setWithoutSend(Ability.OPEN_CONTAINERS, gameMode != GameMode.SPECTATOR);
        setWithoutSend(Ability.ATTACK_PLAYERS, gameMode != GameMode.SPECTATOR);
        setWithoutSend(Ability.ATTACK_MOBS, gameMode != GameMode.SPECTATOR);
        setWithoutSend(Ability.NO_CLIP, gameMode == GameMode.SPECTATOR);
        setWithoutSend(Ability.FLYING, gameMode == GameMode.SPECTATOR);
        setWithoutSend(Ability.INSTABUILD, gameMode == GameMode.CREATIVE);
        setWithoutSend(Ability.TELEPORT, true);
        sync();
    }

    public void set(Ability ability, boolean value) {
        if (value) {
            abilities.add(ability);
        } else {
            abilities.remove(ability);
        }
        sync();
    }

    private void setWithoutSend(Ability ability, boolean value) {
        if (value) {
            abilities.add(ability);
        } else {
            abilities.remove(ability);
        }
    }

    public boolean has(Ability ability) {
        return abilities.contains(ability);
    }

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
        sync();
    }

    public void setVerticalFlySpeed(float verticalFlySpeed) {
        this.verticalFlySpeed = verticalFlySpeed;
        sync();
    }

    public void setFlying(boolean flying) {
        set(Ability.FLYING, flying);
    }

    public void sync() {
        // Broadcast the packet to all players, so that players can see each other's permission level
        ((AllayPlayerManager) Server.getInstance().getPlayerManager()).broadcastPacket(encodePacket());
    }

    public UpdateAbilitiesPacket encodePacket() {
        UpdateAbilitiesPacket packet = new UpdateAbilitiesPacket();

        packet.setUniqueEntityId(player.getRuntimeId());
        // The command permissions set here are actually not very useful. Their main function is to allow OPs to have quick command options.
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        packet.setCommandPermission(player.hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR) ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // PlayerPermissions is the permission level of the player as it shows up in the player list built up using the PlayerList packet
        packet.setPlayerPermission(calculatePlayerPermission(player));

        var layer = new AbilityLayer();
        layer.setLayerType(AbilityLayer.Type.BASE);
        layer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        layer.getAbilityValues().addAll(abilities);
        layer.getAbilityValues().add(Ability.WALK_SPEED);
        layer.getAbilityValues().add(Ability.FLY_SPEED);
        // NOTICE: this shouldn't be changed
        layer.setWalkSpeed(DEFAULT_WALK_SPEED);
        layer.setFlySpeed(this.flySpeed);
        layer.setVerticalFlySpeed(this.verticalFlySpeed);
        packet.getAbilityLayers().add(layer);

        return packet;
    }

    private PlayerPermission calculatePlayerPermission(EntityPlayer player) {
        if (player.hasPermissions(PermissionGroups.OPERATOR, true)) {
            return PlayerPermission.OPERATOR;
        } else if (player.hasPermissions(PermissionGroups.MEMBER, true)) {
            return PlayerPermission.MEMBER;
        }
        return PlayerPermission.VISITOR;
    }
}
