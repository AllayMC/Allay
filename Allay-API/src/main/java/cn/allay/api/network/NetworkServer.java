package cn.allay.api.network;

import org.cloudburstmc.protocol.bedrock.BedrockPong;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public interface NetworkServer {
    void start();

    BedrockPong getPong();
}
