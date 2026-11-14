package org.allaymc.server.network.protocol;

import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.CraftingRecipeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class NetworkItemPaletteTest {

    @ParameterizedTest
    @ValueSource(strings = {"818", "819", "827", "844", "859", "898", "924", "944", "975", "1001", "2168"})
    void everyItemIsSentAsAnItemTheVersionKnows(String name) {
        var palette = NetworkItemPalette.of(name);
        var sentAsAir = new ArrayList<String>();
        for (var itemType : Registries.ITEMS.getContent().values()) {
            var networkType = palette.toNetwork(itemType);
            if (networkType == ItemTypes.AIR && itemType != ItemTypes.AIR) {
                sentAsAir.add(itemType.getIdentifier().toString());
                continue;
            }
            assertTrue(palette.isKnown(networkType), () -> itemType + " is sent as " + networkType + ", which " + name + " lacks");
        }
        assertEquals(List.of(), sentAsAir);
    }

    @ParameterizedTest
    @ValueSource(ints = {818, 819, 827, 844, 859, 860, 898, 924, 944, 975, 1001, 2168, 2169, 2193})
    void everyProtocolRegistersEachItemOnceUnderAUniqueName(int protocolVersion) {
        var definitions = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, protocolVersion).getData().itemDefinitions();
        var names = new HashSet<String>();
        var runtimeIds = new HashSet<Integer>();
        for (var definition : definitions) {
            assertTrue(names.add(definition.identifier()), definition.identifier());
            assertTrue(runtimeIds.add(definition.runtimeId()), definition.identifier());
        }
    }

    @Test
    void renamedItemsAreRegisteredUnderTheirOldName() {
        assertEquals("minecraft:chain", NetworkItemPalette.of("818").definition(ItemTypes.IRON_CHAIN).name());
        assertEquals("minecraft:iron_chain", NetworkItemPalette.of("844").definition(ItemTypes.IRON_CHAIN).name());
    }

    @Test
    void itemsTheVersionLacksFallBackToASimilarItem() {
        assertEquals(ItemTypes.STONE_SWORD, NetworkItemPalette.of("818").toNetwork(ItemTypes.COPPER_SWORD));
        assertEquals(ItemTypes.OAK_STAIRS, NetworkItemPalette.of("2168").toNetwork(ItemTypes.WHITE_WOOL_STAIRS));
        assertEquals(ItemTypes.BIRCH_PLANKS, NetworkItemPalette.of("1001").toNetwork(ItemTypes.POPLAR_PLANKS));
        assertEquals(ItemTypes.POPLAR_PLANKS, NetworkItemPalette.of("2168").toNetwork(ItemTypes.POPLAR_PLANKS));
        assertNull(NetworkItemPalette.of("2168").definition(ItemTypes.WHITE_WOOL_STAIRS));
    }

    @Test
    void olderProtocolsOnlySeeItemsTheyKnow() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2169);
        var data = protocol.getData();
        var palette = data.itemPalette();

        var registered = data.itemDefinitions().stream().map(ItemDefinition::identifier).collect(Collectors.toSet());
        assertFalse(registered.contains("minecraft:white_wool_stairs"));
        assertTrue(registered.contains("minecraft:oak_stairs"));

        var wool = ItemTypes.WHITE_WOOL_STAIRS.getRuntimeId();
        assertTrue(data.creativeItems().stream().noneMatch(item -> item.item().getDefinition().runtimeId() == wool));
        assertFalse(data.creativeItems().isEmpty());
        for (var recipe : data.recipeTable().encodedRecipes()) {
            if (recipe instanceof CraftingRecipeData crafting) {
                crafting.getResults().forEach(result -> assertTrue(registered.contains(result.getDefinition().identifier()), crafting.getId()));
            }
        }

        var item = NetworkHelper.toNetwork(
                ItemTypes.WHITE_WOOL_STAIRS.createItemStack(5),
                protocol.getItemDefinitionRegistry(),
                protocol.getBlockDefinitionRegistry(),
                data.blockPalette(),
                palette
        );
        assertEquals("minecraft:oak_stairs", item.getDefinition().identifier());
        assertEquals(5, item.getCount());
        assertEquals(data.blockPalette().toNetwork(ItemTypes.OAK_STAIRS.getBlockType().getDefaultState()), item.getBlockDefinition().runtimeId());
    }

    @Test
    void theCurrentPaletteSendsEveryItemAsItIs() {
        var palette = NetworkItemPalette.current();
        for (ItemType<?> itemType : List.of(ItemTypes.WHITE_WOOL_STAIRS, ItemTypes.COPPER_SWORD, ItemTypes.IRON_CHAIN)) {
            assertSame(itemType, palette.toNetwork(itemType));
        }
        assertTrue(ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193).getData().itemPalette().isCurrent());
        assertEquals(Registries.ITEMS.getContent().size(),
                ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193).getData().itemDefinitions().size());
    }
}
