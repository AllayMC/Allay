package org.allaymc.server.network.protocol;

import org.allaymc.api.block.property.enums.MinecraftCorner;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.testutils.AllayTestExtension;
import org.allaymc.testutils.TestRegistryFixtures;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class NetworkBlockPaletteTest {

    @ParameterizedTest
    @ValueSource(strings = {"818", "827", "844", "944", "975", "1001", "2168"})
    void everyStateIsSentAsAStateTheVersionKnows(String name) {
        var palette = NetworkBlockPalette.of(name);
        var known = networkHashes(palette);
        int unknown = palette.toNetwork(BlockTypes.UNKNOWN.getDefaultState());

        var sentAsUnknown = new ArrayList<String>();
        for (var blockType : Registries.BLOCKS.getContent().values()) {
            for (var blockState : blockType.getAllStates()) {
                int hash = palette.toNetwork(blockState);
                assertTrue(known.contains(hash), () -> blockState + " is sent as " + hash + ", which palette " + name + " lacks");
                if (hash == unknown && blockType != BlockTypes.UNKNOWN) {
                    sentAsUnknown.add(blockState.toString());
                }
            }
        }
        assertEquals(List.of(), sentAsUnknown);
    }

    @Test
    void unchangedStatesAreSentAsTheyAre() {
        var stone = BlockTypes.STONE.getDefaultState();
        assertEquals(stone.blockStateHash(), NetworkBlockPalette.of("818").toNetwork(stone));
        var poplar = BlockTypes.POPLAR_PLANKS.getDefaultState();
        assertEquals(poplar.blockStateHash(), NetworkBlockPalette.of("2168").toNetwork(poplar));
    }

    @Test
    void stairsAndFencesLoseTheirNewShapeProperties() {
        var palette = NetworkBlockPalette.of("2168");
        var corner = BlockTypes.OAK_STAIRS.getDefaultState()
                .setPropertyValue(WEIRDO_DIRECTION, 2)
                .setPropertyValue(MINECRAFT_CORNER, MinecraftCorner.INNER_LEFT);
        var straight = corner.setPropertyValue(MINECRAFT_CORNER, MinecraftCorner.NONE);
        assertEquals(palette.toNetwork(straight), palette.toNetwork(corner));
        assertNotEquals(straight.blockStateHash(), palette.toNetwork(straight));
        // Direction survives.
        assertNotEquals(palette.toNetwork(straight), palette.toNetwork(straight.setPropertyValue(WEIRDO_DIRECTION, 1)));

        var fence = BlockTypes.OAK_FENCE.getDefaultState();
        var connected = fence.setPropertyValue(MINECRAFT_CONNECTION_NORTH, true).setPropertyValue(MINECRAFT_CONNECTION_EAST, true);
        assertEquals(palette.toNetwork(fence), palette.toNetwork(connected));
        assertTrue(networkHashes(palette).contains(palette.toNetwork(fence)));
    }

    @Test
    void blocksTheVersionLacksFallBackToABlockOfTheSameShape() {
        var stairs = BlockTypes.WHITE_WOOL_STAIRS.getDefaultState().setPropertyValue(WEIRDO_DIRECTION, 3);
        var oakStairs = BlockTypes.OAK_STAIRS.getDefaultState().setPropertyValue(WEIRDO_DIRECTION, 3);
        var v2168 = NetworkBlockPalette.of("2168");
        assertEquals(v2168.toNetwork(oakStairs), v2168.toNetwork(stairs));

        var v1001 = NetworkBlockPalette.of("1001");
        assertEquals(v1001.toNetwork(BlockTypes.BIRCH_PLANKS.getDefaultState()), v1001.toNetwork(BlockTypes.POPLAR_PLANKS.getDefaultState()));

        var v818 = NetworkBlockPalette.of("818");
        assertEquals(v818.toNetwork(BlockTypes.CHEST.getDefaultState()), v818.toNetwork(BlockTypes.COPPER_CHEST.getDefaultState()));
    }

    @Test
    void renamedBlocksAreSentByTheirOldName() {
        // minecraft:chain became minecraft:iron_chain in 1.21.110 (844).
        var chain = BlockTypes.IRON_CHAIN.getDefaultState();
        var v818 = NetworkBlockPalette.of("818");
        assertNotEquals(chain.blockStateHash(), v818.toNetwork(chain));
        assertTrue(networkHashes(v818).contains(v818.toNetwork(chain)));
        assertEquals(chain.blockStateHash(), NetworkBlockPalette.of("844").toNetwork(chain));
    }

    @Test
    void customBlocksAreSentAsTheyAre() {
        var palette = NetworkBlockPalette.of("2168");
        for (BlockState state : TestRegistryFixtures.customDefinitionBlockType().getAllStates()) {
            assertEquals(state.blockStateHash(), palette.toNetwork(state));
            assertTrue(networkHashes(palette).contains(state.blockStateHash()));
        }
    }

    @Test
    void theCurrentPaletteSendsEveryStateAsItIs() {
        var palette = NetworkBlockPalette.current();
        var state = BlockTypes.WHITE_WOOL_STAIRS.getDefaultState();
        assertTrue(palette.isCurrent());
        assertEquals(state.blockStateHash(), palette.toNetwork(state));
    }

    @Test
    void protocolsUseThePaletteOfTheirVersion() {
        var registry = ProtocolRegistry.getDefault();
        assertEquals("2168", registry.resolve(ClientVariant.INTERNATIONAL, 2169).getData().blockPalette().name());
        assertEquals("818", registry.resolve(ClientVariant.INTERNATIONAL, 819).getData().blockPalette().name());
        assertTrue(registry.resolve(ClientVariant.INTERNATIONAL, 2193).getData().blockPalette().isCurrent());
    }

    @Test
    void encodersSendBlocksInTheClientsPalette() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2169);
        var palette = protocol.getData().blockPalette();
        var stairs = BlockTypes.WHITE_WOOL_STAIRS.getDefaultState();

        var update = protocol.getEncoder().encodeBlockUpdate(new Vector3i(1, 2, 3), 0, stairs);
        assertEquals(palette.toNetwork(stairs), update.getDefinition().runtimeId());
        assertNotEquals(stairs.blockStateHash(), update.getDefinition().runtimeId());

        var item = NetworkHelper.toNetwork(
                ItemTypes.WHITE_WOOL_STAIRS.createItemStack(1),
                protocol.getItemDefinitionRegistry(),
                protocol.getBlockDefinitionRegistry(),
                palette
        );
        assertEquals(palette.toNetwork(stairs), item.getBlockDefinition().runtimeId());
    }

    private static Set<Integer> networkHashes(NetworkBlockPalette palette) {
        return palette.definitions().stream().map(BlockDefinition::runtimeId).collect(Collectors.toSet());
    }
}
