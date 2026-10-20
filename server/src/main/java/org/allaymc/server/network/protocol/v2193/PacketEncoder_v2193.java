package org.allaymc.server.network.protocol.v2193;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2169.PacketEncoder_v2169;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.packet.DimensionDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.JigsawStructureDataPacket;

import java.util.List;

public class PacketEncoder_v2193 extends PacketEncoder_v2169 {
    /**
     * The jigsaw structure data that the client requires before {@code StartGamePacket}; without it the client
     * disconnects with "missing structure data". The rules only drive client-side jigsaw worldgen, which Allay does
     * not use, so empty rule lists are sent, as Geyser and gophertunnel do.
     */
    private static final NbtMap JIGSAW_STRUCTURE_DATA = NbtMap.builder()
            .putList("processors", NbtType.COMPOUND, List.of())
            .putList("template_pools", NbtType.COMPOUND, List.of())
            .putList("jigsaws", NbtType.COMPOUND, List.of())
            .putList("structure_sets", NbtType.COMPOUND, List.of())
            .build();

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

    @Override
    public JigsawStructureDataPacket encodeJigsawStructureData() {
        var packet = new JigsawStructureDataPacket();
        packet.setJigsawStructureDataTag(JIGSAW_STRUCTURE_DATA);
        return packet;
    }
}
