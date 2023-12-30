package org.allaymc.api.command;

import org.allaymc.api.registry.MappedRegistry;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandRegistry extends MappedRegistry<String, Command, Map<String, Command>> {
    AvailableCommandsPacket getAvailableCommandsPacket();

    void registerDefaultCommands();

    /**
     * @param sender 命令发送者
     * @param cmd 命令，不包含斜杠
     * @return 命令执行结果
     */
    CommandResult execute(CommandSender sender, String cmd);
}
