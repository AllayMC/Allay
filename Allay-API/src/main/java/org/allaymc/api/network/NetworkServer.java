package org.allaymc.api.network;

import org.allaymc.api.client.data.SemVersion;
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

    default void setMaxPlayerCount(int maxPlayerCount) {
        setMaxPlayerCount(maxPlayerCount, true);
    }

    void setMaxPlayerCount(int maxPlayerCount, boolean update);

    SemVersion getSemVersion();

    BedrockCodec getCodec();
}
