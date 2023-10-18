package cn.allay.api.command;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.command.resolver.ValueResolver;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import org.jetbrains.annotations.NotNull;

public interface CommandHandler {

    ApiInstanceHolder<CommandHandler> INSTANCE = ApiInstanceHolder.of();

    static CommandHandler getInstance() {
        return INSTANCE.get();
    }

    <T> void registerValueResolver(@NotNull Class<T> type, @NotNull ValueResolver<T> resolver);

    void register(@NotNull Object... commands);

    void dispatch(@NotNull CommandSender sender, @NotNull String command);

    @NotNull AvailableCommandsPacket createPacketFor(@NotNull EntityPlayer player);
}
