package cn.allay.api.network;

import org.cloudburstmc.protocol.bedrock.BedrockPong;

/**
 * The network component of spray
 */
public interface Network {
    /**
     * Initialize the network component
     */
    void start();

    BedrockPong getPong();

    void updatePong();
}
