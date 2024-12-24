package org.allaymc.api.client.data;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.PermissionKeys;
import org.allaymc.api.permission.tree.PermissionTree;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.AbilityLayer;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermission;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAbilitiesPacket;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Consumer;

import static org.allaymc.api.permission.tree.PermissionTree.PermissionChangeType.ADD;

/**
 * @author daoge_cmd
 */
@Getter
public final class Abilities {

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLY_SPEED = 0.05f;

    private final Set<Ability> abilities = EnumSet.noneOf(Ability.class);

    private final EntityPlayer player;

    private float walkSpeed = DEFAULT_WALK_SPEED;
    private float flySpeed = DEFAULT_FLY_SPEED;

    private boolean dirty = false;

    public Abilities(EntityPlayer player) {
        this.player = player;
        var tree = player.getPermissionTree();
        tree.registerPermissionListener(PermissionKeys.BUILD, syncTo(Ability.BUILD));
        tree.registerPermissionListener(PermissionKeys.MINE, syncTo(Ability.MINE));
        tree.registerPermissionListener(PermissionKeys.DOORS_AND_SWITCHES, syncTo(Ability.DOORS_AND_SWITCHES));
        tree.registerPermissionListener(PermissionKeys.OPEN_CONTAINERS, syncTo(Ability.OPEN_CONTAINERS));
        tree.registerPermissionListener(PermissionKeys.ATTACK_PLAYERS, syncTo(Ability.ATTACK_PLAYERS));
        tree.registerPermissionListener(PermissionKeys.ATTACK_MOBS, syncTo(Ability.ATTACK_MOBS));
        tree.registerPermissionListener(PermissionKeys.MAY_FLY, syncTo(Ability.MAY_FLY));
        tree.registerPermissionListener(PermissionKeys.SUMMON_LIGHTNING, syncTo(Ability.LIGHTNING));
        tree.registerPermissionListener(PermissionKeys.MUTED, syncTo(Ability.MUTED));
    }

    public void applyGameType(GameType gameType) {
        var tree = player.getPermissionTree();
        // Set only necessary permissions
        tree.setPermission(PermissionKeys.BUILD, gameType != GameType.SPECTATOR);
        tree.setPermission(PermissionKeys.MINE, gameType != GameType.SPECTATOR);
        tree.setPermission(PermissionKeys.DOORS_AND_SWITCHES, gameType != GameType.SPECTATOR);
        tree.setPermission(PermissionKeys.OPEN_CONTAINERS, gameType != GameType.SPECTATOR);
        tree.setPermission(PermissionKeys.ATTACK_PLAYERS, gameType != GameType.SPECTATOR);
        tree.setPermission(PermissionKeys.ATTACK_MOBS, gameType != GameType.SPECTATOR);
        tree.setPermission(PermissionKeys.MAY_FLY, gameType != GameType.SURVIVAL && gameType != GameType.ADVENTURE);
        // Do not need to manage SUMMON_LIGHTNING and CHAT;
        // allow plugins to control without resetting after mode switch
        // The following abilities do not need to be integrated into the permission tree
        set(Ability.NO_CLIP, gameType == GameType.SPECTATOR);
        set(Ability.FLYING, gameType == GameType.SPECTATOR);
        set(Ability.INSTABUILD, gameType == GameType.CREATIVE);
        // The purpose of setting OPERATOR_COMMANDS here is solely to allow OP clients to display quick commands
        set(Ability.OPERATOR_COMMANDS, player.isOp());
        set(Ability.TELEPORT, true);
        dirty = true;
        sync();
    }

    public void set(Ability ability, boolean value) {
        if (value) abilities.add(ability);
        else abilities.remove(ability);
        dirty = true;
    }

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

    public void setFlying(boolean flying) {
        set(Ability.FLYING, flying);
    }

    public void sync() {
        if (!dirty) return;
        UpdateAbilitiesPacket updateAbilitiesPacket = createUpdateAbilitiesPacket();

        AbilityLayer abilityLayer = new AbilityLayer();
        abilityLayer.setLayerType(AbilityLayer.Type.BASE);
        abilityLayer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        abilityLayer.getAbilityValues().addAll(abilities);

        abilityLayer.getAbilityValues().add(Ability.WALK_SPEED);
        abilityLayer.getAbilityValues().add(Ability.FLY_SPEED);
        abilityLayer.setWalkSpeed(this.walkSpeed);
        abilityLayer.setFlySpeed(this.flySpeed);

        updateAbilitiesPacket.getAbilityLayers().add(abilityLayer);

        player.sendPacket(updateAbilitiesPacket);
        dirty = false;
    }

    private Consumer<PermissionTree.PermissionChangeType> syncTo(Ability ability) {
        return syncTo(ability, false);
    }

    private Consumer<PermissionTree.PermissionChangeType> syncTo(Ability ability, boolean reverse) {
        return type -> {
            if (type == ADD) {
                if (reverse) abilities.remove(ability);
                else abilities.add(ability);
            } else {
                if (reverse) abilities.add(ability);
                else abilities.remove(ability);
            }
            dirty = true;
        };
    }

    private UpdateAbilitiesPacket createUpdateAbilitiesPacket() {
        UpdateAbilitiesPacket updateAbilitiesPacket = new UpdateAbilitiesPacket();
        updateAbilitiesPacket.setUniqueEntityId(player.getRuntimeId());
        // The command permissions set here are actually not very useful;
        // their main function is to allow OPs to have quick command options
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        updateAbilitiesPacket.setCommandPermission(player.isOp() ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // TODO: Check reasons for the old writing style
        updateAbilitiesPacket.setPlayerPermission(player.isOp() /*&& player.getGameType() != GameType.SPECTATOR*/ ? PlayerPermission.OPERATOR : PlayerPermission.MEMBER);
        return updateAbilitiesPacket;
    }
}
