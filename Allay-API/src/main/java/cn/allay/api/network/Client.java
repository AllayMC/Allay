package cn.allay.api.network;

import cn.allay.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 * <br>
 * 描述一个玩家客户端，玩家客户端可以持有一个EntityHuman并操控它
 */
public interface Client {
    Server getServer();

    void sendPacket(BedrockPacket packet);

    void sendPacketImmediately(BedrockPacket packet);

    void disconnect(String reason);

    void disconnect(String reason, boolean hideReason);

    void initializePlayer();

    String getName();
}
