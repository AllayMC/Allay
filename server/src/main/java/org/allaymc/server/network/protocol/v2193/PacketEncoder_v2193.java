package org.allaymc.server.network.protocol.v2193;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2169.PacketEncoder_v2169;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.definitions.DimensionDefinition;
import org.cloudburstmc.protocol.bedrock.packet.DimensionDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.JigsawStructureDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.VoxelShapesPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * {@code PacketEncoder_v2193} supplies dimension default-biome identifiers and
 * the jigsaw-structure payload for protocol v2193. Its inherited encoder
 * already supplies each dimension's identifier, height limits, generator, type,
 * and pack identity. Adding the biome field must preserve those values because
 * replacing a definition with defaults would change the client's interpretation
 * of the same dimension.
 *
 * <p> {@code encodeDimensionData} rebuilds each inherited definition with its
 * existing fields and chooses a biome from its dimension identifier. Nether and
 * End retain their matching biomes, while other identifiers use
 * {@code "minecraft:plains"}. {@code encodeJigsawStructureData} creates a
 * packet referencing the shared {@code JIGSAW_STRUCTURE_DATA} map; that map
 * supplies the four empty rule collections sent before {@code StartGamePacket}.
 */
public class PacketEncoder_v2193 extends PacketEncoder_v2169 {
    /**
     * {@code JIGSAW_STRUCTURE_DATA} contains the four empty jigsaw rule
     * collections sent before {@code StartGamePacket} by the login flow. Every
     * packet returned by {@code encodeJigsawStructureData} shares this map
     * rather than creating connection-specific generation rules.
     */
    private static final NbtMap JIGSAW_STRUCTURE_DATA = NbtMap.builder()
            .putList("processors", NbtType.COMPOUND, List.of())
            .putList("template_pools", NbtType.COMPOUND, List.of())
            .putList("jigsaws", NbtType.COMPOUND, List.of())
            .putList("structure_sets", NbtType.COMPOUND, List.of())
            .build();

    /**
     * Creates the v2193 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v2193(ProtocolData data) {
        super(data);
    }

    /**
     * Builds inherited dimension entries with the default-biome identifiers
     * required by v2193. The returned packet retains its original definition
     * order, and each replacement definition retains its identifier, height
     * limits, generator, type, and pack identity.
     *
     * <p> {@code "minecraft:nether"} selects {@code "minecraft:hell"}, and
     * {@code "minecraft:the_end"} selects the matching End biome. Every other
     * dimension identifier selects {@code "minecraft:plains"}, including custom
     * identifiers. Rebuilding each definition preserves the inherited dimension
     * configuration while supplying the additional field consumed by the v2193
     * serializer.
     */
    @Override
    public DimensionDataPacket encodeDimensionData() {
        var packet = super.encodeDimensionData();
        // Preserve existing dimension fields while supplying the new biome
        // field; custom identifiers without a special mapping use the plains
        // fallback.
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

    /**
     * Creates a fresh jigsaw-structure packet referencing the shared empty rule
     * data. The login flow sends this payload before {@code StartGamePacket};
     * the map contains the required collection names even though all four lists
     * are empty.
     */
    @Override
    public JigsawStructureDataPacket encodeJigsawStructureData() {
        var packet = new JigsawStructureDataPacket();
        packet.setJigsawStructureDataTag(JIGSAW_STRUCTURE_DATA);
        return packet;
    }

    @Override
    public Collection<VoxelShapesPacket> encodeVoxelShapes() {
        var packet = new VoxelShapesPacket();
        packet.setShapes(new ArrayList<>(VanillaData_v2193.VOXEL_SHAPES));
        packet.setNameMap(new HashMap<>(VanillaData_v2193.VOXEL_SHAPE_NAMES));
        packet.setCustomShapeCount(0);
        return List.of(packet);
    }
}
