package org.allaymc.api.client.data;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.perm.PermKeys;
import org.allaymc.api.perm.tree.PermTree;
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

import static org.allaymc.api.perm.tree.PermTree.PermChangeType.ADD;

/**
 * Allay Project 2024/1/6
 *
 * @author daoge_cmd
 */
public final class Abilities {

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLY_SPEED = 0.05f;

    private final Set<Ability> abilities = EnumSet.noneOf(Ability.class);

    private final EntityPlayer player;

    @Getter
    private float walkSpeed = DEFAULT_WALK_SPEED;
    @Getter
    private float flySpeed = DEFAULT_FLY_SPEED;

    @Getter
    private boolean dirty = false;

    public Abilities(EntityPlayer player) {
        this.player = player;
        var tree = player.getPermTree();
        tree.registerPermListener(PermKeys.BUILD, syncTo(Ability.BUILD));
        tree.registerPermListener(PermKeys.MINE, syncTo(Ability.MINE));
        tree.registerPermListener(PermKeys.DOORS_AND_SWITCHES, syncTo(Ability.DOORS_AND_SWITCHES));
        tree.registerPermListener(PermKeys.OPEN_CONTAINERS, syncTo(Ability.OPEN_CONTAINERS));
        tree.registerPermListener(PermKeys.ATTACK_PLAYERS, syncTo(Ability.ATTACK_PLAYERS));
        tree.registerPermListener(PermKeys.ATTACK_MOBS, syncTo(Ability.ATTACK_MOBS));
        tree.registerPermListener(PermKeys.MAY_FLY, syncTo(Ability.MAY_FLY));
        tree.registerPermListener(PermKeys.SUMMON_LIGHTNING, syncTo(Ability.LIGHTNING));
        tree.registerPermListener(PermKeys.MUTED, syncTo(Ability.MUTED));
    }

    public void applyGameType(GameType gameType) {
        var tree = player.getPermTree();
        // 只设置必须设置的权限
        tree.setPerm(PermKeys.BUILD, gameType != GameType.SPECTATOR);
        tree.setPerm(PermKeys.MINE, gameType != GameType.SPECTATOR);
        tree.setPerm(PermKeys.DOORS_AND_SWITCHES, gameType != GameType.SPECTATOR);
        tree.setPerm(PermKeys.OPEN_CONTAINERS, gameType != GameType.SPECTATOR);
        tree.setPerm(PermKeys.ATTACK_PLAYERS, gameType != GameType.SPECTATOR);
        tree.setPerm(PermKeys.ATTACK_MOBS, gameType != GameType.SPECTATOR);
        tree.setPerm(PermKeys.MAY_FLY, gameType != GameType.SURVIVAL && gameType != GameType.ADVENTURE);
        // 不需要管SUMMON_LIGHTNING和CHAT，让插件可以控制而不会在切换模式后重置
        // 以下的几个能力不需要集成到权限树里面
        set(Ability.NO_CLIP, gameType == GameType.SPECTATOR);
        set(Ability.FLYING, gameType == GameType.SPECTATOR);
        set(Ability.INSTABUILD, gameType == GameType.CREATIVE);
        // 这边设置这个OPERATOR_COMMANDS的目的仅仅是让OP客户端能显示快捷指令
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

    private Consumer<PermTree.PermChangeType> syncTo(Ability ability) {
        return syncTo(ability, false);
    }

    private Consumer<PermTree.PermChangeType> syncTo(Ability ability, boolean reverse) {
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
        // 这边设置的命令权限其实没啥用，主要作用是让op能拥有快捷指令选项
        // 若此玩家没有指定命令的权限，命令描述压根就不会发到客户端
        updateAbilitiesPacket.setCommandPermission(player.isOp() ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // TODO: 检查旧的写法原因
        updateAbilitiesPacket.setPlayerPermission(player.isOp() /*&& player.getGameType() != GameType.SPECTATOR*/ ? PlayerPermission.OPERATOR : PlayerPermission.MEMBER);
        return updateAbilitiesPacket;
    }
}
