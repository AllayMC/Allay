package org.allaymc.server.network.protocol.v2193;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2169.PacketEncoder_v2169;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.packet.DimensionDataPacket;

public class PacketEncoder_v2193 extends PacketEncoder_v2169 {
    public PacketEncoder_v2193(ProtocolData data) {
        super(data);
    }

    @Override
    public DimensionDataPacket encodeDimensionData() {
        var packet = super.encodeDimensionData();
        packet.getDefinitions().replaceAll(definition -> new DimensionDefinition(
                definition.id(),
                definition.maximumHeight(),
                definition.minimumHeight(),
                definition.generatorType(),
                definition.dimensionType(),
                definition.packId(),
                switch (definition.id()) {
                    case "minecraft:nether" -> "minecraft:hell";
                    case "minecraft:the_end" -> "minecraft:the_end";
                    default -> "minecraft:plains";
                }
        ));
        return packet;
    }
}
