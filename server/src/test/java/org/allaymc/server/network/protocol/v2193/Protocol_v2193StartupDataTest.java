package org.allaymc.server.network.protocol.v2193;

import io.netty.buffer.Unpooled;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class Protocol_v2193StartupDataTest {

    @Test
    void v2193SendsTheVanillaVoxelShapeRegistry() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193);
        var packet = protocol.getEncoder().encodeVoxelShapes().iterator().next();

        // Data-driven blocks resolve their shapes by name, e.g. "minecraft:empty"; an empty registry gets the session
        // rejected with a bad packet.
        assertEquals(287, packet.getShapes().size());
        assertEquals(220, packet.getNameMap().size());
        assertEquals(0, packet.getNameMap().get("minecraft:empty"));
        assertEquals(1, packet.getNameMap().get("minecraft:unit_cube"));
        var codec = protocol.getCodec();
        var buffer = Unpooled.buffer();
        try {
            assertDoesNotThrow(() -> codec.tryEncode(codec.createHelper(), buffer, packet));
        } finally {
            buffer.release();
        }
    }

    @Test
    void v2193StartGameDescribesTheDataDrivenVanillaBlocks() {
        var properties = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193).getData().customBlockProperties();
        var names = properties.stream().map(BlockPropertyData::name).toList();

        assertEquals(98, VanillaData_v2193.DATA_DRIVEN_BLOCK_PROPERTIES.size());
        assertTrue(names.containsAll(VanillaData_v2193.DATA_DRIVEN_BLOCK_PROPERTIES.stream().map(BlockPropertyData::name).toList()));
        assertTrue(names.contains("minecraft:white_wool_stairs"));
        for (var name : names) {
            assertNotNull(Registries.BLOCKS.get(new Identifier(name)), name);
        }
    }

    @Test
    void olderProtocolsDoNotDescribeTheDataDrivenVanillaBlocks() {
        var properties = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2169).getData().customBlockProperties();

        assertTrue(properties.stream().noneMatch(property -> property.name().equals("minecraft:white_wool_stairs")));
    }
}
