package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDiagnosticsPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ServerboundDiagnosticsPacketProcessor extends PacketProcessor<ServerboundDiagnosticsPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, ServerboundDiagnosticsPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DIAGNOSTICS;
    }
}
