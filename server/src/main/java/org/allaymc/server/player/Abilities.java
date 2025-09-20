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

    private final Set<Ability> abilities = EnumSet.noneOf(Ability.class);

    private final EntityPlayer player;

    private float walkSpeed = DEFAULT_WALK_SPEED;
    private float flySpeed = DEFAULT_FLY_SPEED;
    private float verticalFlySpeed = DEFAULT_VERTICAL_FLY_SPEED;

    private boolean dirty = false;

    public Abilities(EntityPlayer player) {
        this.player = player;
    }

    public void applyGameMode(GameMode gameMode) {
        // Set only necessary permissions
        this.player.setPermission(Permissions.ABILITY_BUILD, gameMode != GameMode.SPECTATOR);
        this.player.setPermission(Permissions.ABILITY_MINE, gameMode != GameMode.SPECTATOR);
        this.player.setPermission(Permissions.ABILITY_DOORS_AND_SWITCHES, gameMode != GameMode.SPECTATOR);
        this.player.setPermission(Permissions.ABILITY_OPEN_CONTAINERS, gameMode != GameMode.SPECTATOR);
        this.player.setPermission(Permissions.ABILITY_ATTACK_PLAYERS, gameMode != GameMode.SPECTATOR);
        this.player.setPermission(Permissions.ABILITY_ATTACK_MOBS, gameMode != GameMode.SPECTATOR);
        this.player.setPermission(Permissions.ABILITY_MAY_FLY, gameMode != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE);
        // Do not need to manage SUMMON_LIGHTNING and CHAT;
        // allow plugins to control without resetting after mode switch
        // The following abilities do not need to be integrated into the permission tree
        set(Ability.NO_CLIP, gameMode == GameMode.SPECTATOR);
        set(Ability.FLYING, gameMode == GameMode.SPECTATOR);
        set(Ability.INSTABUILD, gameMode == GameMode.CREATIVE);
        set(Ability.TELEPORT, true);
        sync();
    }

    public void set(Ability ability, boolean value) {
        if (value) {
            abilities.add(ability);
        } else {
            abilities.remove(ability);
        }
        this.dirty = true;
    }

    public boolean has(Ability ability) {
        return abilities.contains(ability);
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
        this.dirty = true;
    }

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
        this.dirty = true;
    }

    public void setVerticalFlySpeed(float verticalFlySpeed) {
        this.verticalFlySpeed = verticalFlySpeed;
        this.dirty = true;
    }

    public void setFlying(boolean flying) {
        set(Ability.FLYING, flying);
    }

    public void sync() {
        if (this.dirty) {
            // Broadcast the packet to all players, so that players can see each other's permission level
            ((AllayPlayerManager) Server.getInstance().getPlayerManager()).broadcastPacket(encodeUpdateAbilitiesPacket());
            this.dirty = false;
        }
    }

    public UpdateAbilitiesPacket encodeUpdateAbilitiesPacket() {
        UpdateAbilitiesPacket updateAbilitiesPacket = new UpdateAbilitiesPacket();

        updateAbilitiesPacket.setUniqueEntityId(player.getRuntimeId());
        // The command permissions set here are actually not very useful. Their main function is to allow OPs to have quick command options.
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        updateAbilitiesPacket.setCommandPermission(player.hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR) ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // PlayerPermissions is the permission level of the player as it shows up in the player list built up using the PlayerList packet
        updateAbilitiesPacket.setPlayerPermission(calculatePlayerPermission(player));

        AbilityLayer abilityLayer = new AbilityLayer();
        abilityLayer.setLayerType(AbilityLayer.Type.BASE);
        abilityLayer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        abilityLayer.getAbilityValues().addAll(abilities);
        abilityLayer.getAbilityValues().add(Ability.WALK_SPEED);
        abilityLayer.getAbilityValues().add(Ability.FLY_SPEED);
        abilityLayer.setWalkSpeed(this.walkSpeed);
        abilityLayer.setFlySpeed(this.flySpeed);
        abilityLayer.setVerticalFlySpeed(this.verticalFlySpeed);
        updateAbilitiesPacket.getAbilityLayers().add(abilityLayer);

        return updateAbilitiesPacket;
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
