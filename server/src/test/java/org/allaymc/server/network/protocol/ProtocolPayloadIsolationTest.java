package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class ProtocolPayloadIsolationTest {

    @Test
    void creativeAccessorsCannotPolluteLaterPackets() {
        var protocol = protocol(975);
        var data = protocol.getData();
        var expectedItemNetId = data.creativeItems().getFirst().item().getNetId();
        var exposedItem = data.creativeItems().getFirst().item();

        exposedItem.setNetId(expectedItemNetId + 10_000);

        assertEquals(expectedItemNetId, data.creativeItems().getFirst().item().getNetId());
        assertEquals(
                expectedItemNetId,
                protocol.getEncoder().encodeCreativeContent().getContents().getFirst().item().getNetId()
        );

        var expectedIconNetId = data.creativeGroups().getFirst().icon().getNetId();
        data.creativeGroups().getFirst().icon().setNetId(expectedIconNetId + 10_000);
        assertEquals(expectedIconNetId, data.creativeGroups().getFirst().icon().getNetId());
    }

    @Test
    void recipeAccessorsCannotPolluteLaterPackets() {
        var protocol = protocol(975);
        var table = protocol.getData().recipeTable();
        var firstRead = table.encodedRecipes();
        int recipeIndex = findRecipeWithOutput(firstRead);
        int expectedNetId = firstOutput(firstRead.get(recipeIndex)).getNetId();

        firstOutput(firstRead.get(recipeIndex)).setNetId(expectedNetId + 10_000);

        assertEquals(expectedNetId, firstOutput(table.encodedRecipes().get(recipeIndex)).getNetId());
        assertEquals(
                expectedNetId,
                firstOutput(protocol.getEncoder().encodeCraftingData().getCraftingData().get(recipeIndex)).getNetId()
        );
    }

    @Test
    void itemCopiesRecursivelyIsolateNbtArrays() {
        var componentData = NbtMap.builder()
                .putIntArray("component_values", new int[]{3, 4})
                .build();
        var tag = NbtMap.builder()
                .putCompound("nested", NbtMap.builder()
                        .putIntArray("tag_values", new int[]{1, 2})
                        .build())
                .build();
        var item = ItemData.builder()
                .definition(new SimpleItemDefinition("test:item", 1, ItemVersion.LEGACY, true, componentData))
                .count(1)
                .tag(tag)
                .build();
        var recipe = FurnaceRecipeData.of(1, item, "furnace");
        var table = new RecipeTable(
                List.of(recipe),
                List.of(),
                new Int2ObjectOpenHashMap<Recipe>()
        );

        var exposedItem = firstOutput(table.encodedRecipes().getFirst());
        nbtIntArray(exposedItem.getTag().getCompound("nested"))[0] = 99;
        nbtIntArray(exposedItem.getDefinition().componentData())[0] = 99;

        var isolatedItem = firstOutput(table.encodedRecipes().getFirst());
        assertArrayEquals(new int[]{1, 2}, isolatedItem.getTag().getCompound("nested").getIntArray("tag_values"));
        assertArrayEquals(new int[]{3, 4}, isolatedItem.getDefinition().componentData().getIntArray("component_values"));
    }

    @Test
    void itemCopiesHandleNullConstantsAndCustomDefinitions() {
        var itemWithoutDefinition = ItemData.builder()
                .definition(null)
                .count(1)
                .build();
        assertNull(ProtocolPayloadCopier.copyItemData(itemWithoutDefinition).getDefinition());

        var air = ItemData.builder()
                .definition(ItemDefinition.AIR)
                .count(0)
                .build();
        assertSame(ItemDefinition.AIR, ProtocolPayloadCopier.copyItemData(air).getDefinition());

        var components = NbtMap.builder()
                .putIntArray("custom_values", new int[]{5, 6})
                .build();
        ItemDefinition customDefinition = new ItemDefinition() {
            @Override
            public boolean componentBased() {
                return true;
            }

            @Override
            public NbtMap componentData() {
                return components;
            }

            @Override
            public String identifier() {
                return "test:custom_definition";
            }

            @Override
            public int runtimeId() {
                return 42;
            }
        };
        var customItem = ItemData.builder()
                .definition(customDefinition)
                .count(1)
                .build();

        var copiedDefinition = ProtocolPayloadCopier.copyItemData(customItem).getDefinition();
        assertNotSame(customDefinition, copiedDefinition);
        assertEquals(customDefinition.identifier(), copiedDefinition.identifier());
        assertEquals(customDefinition.runtimeId(), copiedDefinition.runtimeId());
        assertEquals(customDefinition.version(), copiedDefinition.version());
        assertTrue(copiedDefinition.componentBased());
        nbtIntArray(copiedDefinition.componentData())[0] = 99;
        assertArrayEquals(new int[]{5, 6}, customDefinition.componentData().getIntArray("custom_values"));

        var mutableBlockRuntimeId = new int[]{7};
        var blockItem = ItemData.builder()
                .definition(ItemDefinition.AIR)
                .blockDefinition(() -> mutableBlockRuntimeId[0])
                .count(1)
                .build();
        var copiedBlockItem = ProtocolPayloadCopier.copyItemData(blockItem);
        mutableBlockRuntimeId[0] = 8;
        assertEquals(7, copiedBlockItem.getBlockDefinition().runtimeId());
    }

    private static Protocol protocol(int version) {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, version);
        assertNotNull(protocol);
        return protocol;
    }

    private static int findRecipeWithOutput(List<RecipeData> recipes) {
        for (int index = 0; index < recipes.size(); index++) {
            if (recipes.get(index) instanceof CraftingRecipeData recipe && !recipe.getResults().isEmpty()) {
                return index;
            }
            if (recipes.get(index) instanceof SmithingTransformRecipeData) {
                return index;
            }
            if (recipes.get(index) instanceof FurnaceRecipeData) {
                return index;
            }
        }
        return fail("No recipe with an item output was encoded");
    }

    private static ItemData firstOutput(RecipeData recipe) {
        return switch (recipe) {
            case CraftingRecipeData crafting -> crafting.getResults().getFirst();
            case SmithingTransformRecipeData smithing -> smithing.getResult();
            case FurnaceRecipeData furnace -> furnace.getResult();
            default -> fail("Recipe does not contain an item output: " + recipe);
        };
    }

    private static int[] nbtIntArray(NbtMap map) {
        return map.values().stream()
                .filter(int[].class::isInstance)
                .map(int[].class::cast)
                .findFirst()
                .orElseThrow();
    }
}
