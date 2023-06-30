package cn.allay.api.network;

import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public interface NetworkServer {
    void start();

    BedrockPong getPong();

    BedrockCodec getCodec();
}
