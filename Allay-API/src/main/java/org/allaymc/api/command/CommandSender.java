package org.allaymc.api.command;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TextReceiver;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.perm.Permissible;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandSender extends TextReceiver, Permissible {

    String getName();

    CommandOriginData getCommandOriginData();

    Location3fc getCmdExecuteLocation();

    default void handleResult(CommandResult result) {
        if (result.context() == null) return;
        if (getCmdExecuteLocation().dimension().getWorld().getWorldData().getGameRule(GameRule.SEND_COMMAND_FEEDBACK)) {
            if (result.isSuccess()) {
                Server.getInstance().broadcastCommandOutputs(this, result.status(), result.context().getOutputs().toArray(TrContainer[]::new));
            } else {
                // 如果报错了就只给自己发消息
                sendCommandOutputs(this, result.status(), result.context().getOutputs().toArray(TrContainer[]::new));
            }
        }
    }

    /**
     * @return 发送者是否为玩家<br>whether the sender is a player
     */
    default boolean isPlayer() {
        return false;
    }

    /**
     * 请使用这个方法来检查发送者是否是一个实体，而不是使用代码{@code "xxx instanceof Entity"}.<br>
     * 因为发送者可能不是{@code "Entity"}的一个实例，但实际上它是以一个实体的身份执行命令(例如：{@code "ExecutorCommandSender"})
     * <p>
     * please use this method to check whether the sender is an entity instead of using code {@code "xxx instanceof Entity"} <br>
     * because the sender may not an instance of {@code "Entity"} but in fact it is executing commands identity as an entity(eg: {@code "ExecutorCommandSender"})
     *
     * @return 发送者是否为实体<br>whether the sender is an entity
     */
    default boolean isEntity() {
        return false;
    }

    /**
     * 如果发送者是一个实体，返回执行该命令的实体.
     * <p>
     * return the entity who execute the command if the sender is a entity.
     *
     * @return 实体对象<br>Entity instance
     */
    default Entity asEntity() {
        return null;
    }

    /**
     * 如果发送者是一个玩家，返回执行该命令的玩家.
     * <p>
     * return the player who execute the command if the sender is a player.
     *
     * @return 玩家对象<br>Player instance
     */
    default EntityPlayer asPlayer() {
        return null;
    }
}
