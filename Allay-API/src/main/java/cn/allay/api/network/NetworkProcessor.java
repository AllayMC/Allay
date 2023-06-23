package cn.allay.api.network;

import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public interface NetworkProcessor {
    //TODO
    void onClientConnect(BedrockServerSession session);

    NetworkSettings getNetworkSetting();
}
