package org.allaymc.api.player.data;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.AbilityLayer;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAbilitiesPacket;
import org.jetbrains.annotations.ApiStatus;

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

    public void applyGameType(GameType gameType) {
        // Set only necessary permissions
        player.setPermission(Permissions.ABILITY_BUILD, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ABILITY_MINE, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ABILITY_DOORS_AND_SWITCHES, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ABILITY_OPEN_CONTAINERS, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ABILITY_ATTACK_PLAYERS, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ABILITY_ATTACK_MOBS, gameType != GameType.SPECTATOR);
        player.setPermission(Permissions.ABILITY_MAY_FLY, gameType != GameType.SURVIVAL && gameType != GameType.ADVENTURE);
        // Do not need to manage SUMMON_LIGHTNING and CHAT;
        // allow plugins to control without resetting after mode switch
        // The following abilities do not need to be integrated into the permission tree
        set(Ability.NO_CLIP, gameType == GameType.SPECTATOR);
        set(Ability.FLYING, gameType == GameType.SPECTATOR);
        set(Ability.INSTABUILD, gameType == GameType.CREATIVE);
        set(Ability.TELEPORT, true);
        dirty = true;
        sync();
    }

    @ApiStatus.Internal
    public void set(Ability ability, boolean value) {
        if (value) {
            abilities.add(ability);
        } else {
            abilities.remove(ability);
        }
        dirty = true;
    }

    @ApiStatus.Internal
    public boolean has(Ability ability) {
        return abilities.contains(ability);
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
        dirty = true;
    }

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
        dirty = true;
    }

    public void setVerticalFlySpeed(float verticalFlySpeed) {
        this.verticalFlySpeed = verticalFlySpeed;
        dirty = true;
    }

    public void setFlying(boolean flying) {
        set(Ability.FLYING, flying);
    }

    @ApiStatus.Internal
    public void sync() {
        if (!dirty) {
            return;
        }

        // Broadcast the packet to all players, so that players can see each other's permission level
        Server.getInstance().getPlayerManager().broadcastPacket(encodeUpdateAbilitiesPacket());

        dirty = false;
    }

    @ApiStatus.Internal
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
