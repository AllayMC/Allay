package cn.allay.server.player;

import cn.allay.api.network.Client;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public class AllayClient implements Client {

    @Getter
    private final BedrockServerSession session;

    private AllayClient(BedrockServerSession session) {
        this.session = session;
        session.setPacketHandler(this);
    }

    public static AllayClient hold(BedrockServerSession session) {
        return new AllayClient(session);
    }
}
