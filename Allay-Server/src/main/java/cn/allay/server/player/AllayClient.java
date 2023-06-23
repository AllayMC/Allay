package cn.allay.server.player;

import cn.allay.api.network.Client;
import cn.allay.api.network.NetworkProcessor;
import cn.allay.api.server.Server;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public class AllayClient implements Client {

    private final BedrockServerSession session;
    @Getter
    private final NetworkProcessor networkProcessor;

    private AllayClient(BedrockServerSession session, NetworkProcessor networkProcessor) {
        this.session = session;
        this.networkProcessor = networkProcessor;
        session.setPacketHandler(this);
    }

    public static AllayClient hold(BedrockServerSession session, NetworkProcessor networkProcessor) {
        return new AllayClient(session, networkProcessor);
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        session.sendPacket(packet);
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        session.sendPacketImmediately(packet);
    }

    @Override
    public void disconnect(String reason) {
        disconnect(reason, false);
    }

    @Override
    public void disconnect(String reason, boolean hideReason) {
        networkProcessor.onClientDisconnect(this);
        session.disconnect(reason, hideReason);
    }

    @Override
    public PacketSignal handle(RequestNetworkSettingsPacket packet) {
        var protocolVersion = packet.getProtocolVersion();
        var supportedProtocolVersion = Server.getInstance().getNetworkServer().getCodec().getProtocolVersion();
        if (protocolVersion != supportedProtocolVersion) {
            String message;
            if (protocolVersion < supportedProtocolVersion) {
                message = "disconnectionScreen.outdatedClient";
            } else {
                message = "disconnectionScreen.outdatedServer";
            }
            disconnect(message, false);
        }
        var settingsPacket = new NetworkSettingsPacket();
        //TODO: Support other compression algorithms
        settingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.ZLIB);
        settingsPacket.setCompressionThreshold(1);
        sendPacketImmediately(settingsPacket);
        return PacketSignal.HANDLED;
    }



    @Override
    public PacketSignal handle(LoginPacket packet) {

    }
}
