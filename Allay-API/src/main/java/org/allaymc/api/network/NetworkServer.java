package org.allaymc.api.network;

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

    void updatePong();

    default void setMotd(String motd) {
        setMotd(motd, true);
    }

    void setMotd(String motd, boolean update);

    default void setSubMotd(String subMotd) {
        setSubMotd(subMotd, true);
    }

    void setSubMotd(String subMotd, boolean update);

    default void setPlayerCount(int count) {
        setPlayerCount(count, true);
    }

    void setPlayerCount(int count, boolean update);

    void setMaxPlayerCount(int maxPlayerCount, boolean update);

    default int getMaxPlayerCount() {
        return getPong().maximumPlayerCount();
    }

    default void setMaxPlayerCount(int maxPlayerCount) {
        setMaxPlayerCount(maxPlayerCount, true);
    }

    BedrockCodec getCodec();
}
