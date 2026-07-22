package org.allaymc.server.network.protocol;

import io.netty.buffer.Unpooled;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.server.network.protocol.v827.PacketEncoder_v827;
import org.allaymc.server.network.protocol.v844.PacketEncoder_v844;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class ProtocolDataEncoderTest {
    private static ProtocolRegistry registry;

    @BeforeAll
    static void setUpRegistry() {
        registry = ProtocolRegistry.getDefault();
    }

    @Test
    void recipeTableUsesOneStableReadOnlyNetworkIdIndex() {
        var legacyTable = protocol(944).getData().recipeTable();
        var modernTable = protocol(975).getData().recipeTable();

        assertRecipeIdsResolve(legacyTable);
        assertRecipeIdsResolve(modernTable);
        assertThrows(UnsupportedOperationException.class, () -> legacyTable.encodedRecipes().clear());
        assertThrows(UnsupportedOperationException.class, () -> legacyTable.potionMixes().clear());
        assertThrows(UnsupportedOperationException.class, () ->
                legacyTable.recipesByNetworkId().put(0, legacyTable.recipesByNetworkId().values().iterator().next()));

        assertTrue(legacyTable.encodedRecipes().stream().anyMatch(FurnaceRecipeData.class::isInstance));
        assertTrue(modernTable.encodedRecipes().stream()
                .filter(ShapelessRecipeData.class::isInstance)
                .map(ShapelessRecipeData.class::cast)
                .anyMatch(recipe -> modernTable.recipesByNetworkId().get(recipe.getNetId()) instanceof FurnaceRecipe));
    }

    @Test
    void v827ExperimentDoesNotLeakIntoV844() {
        var v827 = assertInstanceOf(PacketEncoder_v827.class, protocol(827).getEncoder());
        var v844 = assertInstanceOf(PacketEncoder_v844.class, protocol(844).getEncoder());

        var first827 = v827.encodeResourcePackStack();
        var second827 = v827.encodeResourcePackStack();
        var packet844 = v844.encodeResourcePackStack();

        assertNotSame(first827, second827);
        assertTrue(hasExperiment(first827, "y_2025_drop_3"));
        assertTrue(hasExperiment(second827, "y_2025_drop_3"));
        assertFalse(hasExperiment(packet844, "y_2025_drop_3"));

        first827.getExperiments().clear();
        assertTrue(hasExperiment(v827.encodeResourcePackStack(), "y_2025_drop_3"));
    }

    @Test
    void packedMaterialBooleansStartAtV844() {
        var definition = blockDefinition(materialInstances(), NbtMap.EMPTY);
        var legacy = encodeCustomBlockDefinition(protocol(827), definition);
        var modern = encodeCustomBlockDefinition(protocol(844), definition);
        var legacyMaterial = material(legacy);
        var modernMaterial = material(modern);

        assertFalse(legacyMaterial.containsKey("packed_bools"));
        assertTrue(legacyMaterial.getBoolean("face_dimming"));
        assertEquals((byte) 3, modernMaterial.getByte("packed_bools"));
        assertFalse(modernMaterial.containsKey("face_dimming"));
    }

    @Test
    void multipleCollisionBoxesStartAtV898() {
        var collision = NbtMap.builder()
                .putBoolean("enabled", true)
                .putList("boxes", NbtType.COMPOUND, List.of(
                        box(0, 0, 0, 4, 8, 4),
                        box(8, 2, 8, 16, 16, 16)
                ))
                .build();
        var definition = blockDefinition(NbtMap.EMPTY, collision);
        var legacy = encodeCustomBlockDefinition(protocol(860), definition)
                .getCompound("components")
                .getCompound("minecraft:collision_box");
        var modern = encodeCustomBlockDefinition(protocol(898), definition)
                .getCompound("components")
                .getCompound("minecraft:collision_box");

        assertFalse(legacy.containsKey("boxes"));
        assertEquals(List.of(-8f, 0f, -8f), legacy.getList("origin", NbtType.FLOAT));
        assertEquals(List.of(16f, 16f, 16f), legacy.getList("size", NbtType.FLOAT));
        assertEquals(2, modern.getList("boxes", NbtType.COMPOUND).size());
    }

    @Test
    void repeatedEncodingIsolatesPacketsAndMutableItemPayloads() {
        var encoder = protocol(975).getEncoder();

        var creativeOne = encoder.encodeCreativeContent();
        var creativeTwo = encoder.encodeCreativeContent();
        assertNotSame(creativeOne, creativeTwo);
        assertFalse(creativeOne.getContents().isEmpty());
        var creativeItemOne = creativeOne.getContents().getFirst().item();
        var creativeItemTwo = creativeTwo.getContents().getFirst().item();
        assertNotSame(creativeItemOne, creativeItemTwo);
        int creativeNetId = creativeItemTwo.getNetId();
        creativeItemOne.setNetId(creativeNetId + 10_000);
        assertEquals(creativeNetId, creativeItemTwo.getNetId());
        assertEquals(creativeNetId, encoder.encodeCreativeContent().getContents().getFirst().item().getNetId());

        var craftingOne = encoder.encodeCraftingData();
        var craftingTwo = encoder.encodeCraftingData();
        assertNotSame(craftingOne, craftingTwo);
        int recipeIndex = findRecipeWithOutput(craftingOne.getCraftingData());
        var outputOne = firstOutput(craftingOne.getCraftingData().get(recipeIndex));
        var outputTwo = firstOutput(craftingTwo.getCraftingData().get(recipeIndex));
        assertNotSame(outputOne, outputTwo);
        int outputNetId = outputTwo.getNetId();
        outputOne.setNetId(outputNetId + 10_000);
        craftingOne.getCraftingData().clear();
        assertEquals(outputNetId, outputTwo.getNetId());
        assertFalse(craftingTwo.getCraftingData().isEmpty());
        assertEquals(outputNetId, firstOutput(encoder.encodeCraftingData().getCraftingData().get(recipeIndex)).getNetId());
    }

    @Test
    void targetCodecsEncodeCachedDataPackets() {
        for (int version : List.of(827, 844, 944, 975)) {
            var protocol = protocol(version);
            assertPacketEncodes(protocol, protocol.getEncoder().encodeCraftingData());
            assertPacketEncodes(protocol, protocol.getEncoder().encodeResourcePackStack());
        }
    }

    private static Protocol protocol(int version) {
        var protocol = registry.resolve(ClientVariant.INTERNATIONAL, version);
        assertNotNull(protocol);
        return protocol;
    }

    private static NbtMap encodeCustomBlockDefinition(Protocol protocol, NbtMap definition) {
        return protocol.encodeCustomBlockDefinition(definition);
    }

    private static void assertRecipeIdsResolve(RecipeTable table) {
        var ids = new HashSet<Integer>();
        for (var recipe : table.encodedRecipes()) {
            if (!(recipe instanceof NetworkRecipeData networkRecipe) || networkRecipe.getNetId() <= 0) {
                continue;
            }
            assertTrue(ids.add(networkRecipe.getNetId()), "duplicate recipe network ID");
            assertNotNull(table.recipesByNetworkId().get(networkRecipe.getNetId()));
        }
        assertFalse(ids.isEmpty());
    }

    private static boolean hasExperiment(org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket packet, String name) {
        return packet.getExperiments().stream().anyMatch(experiment -> experiment.name().equals(name));
    }

    private static NbtMap blockDefinition(NbtMap materialInstances, NbtMap collisionBox) {
        var components = NbtMap.builder();
        if (!materialInstances.isEmpty()) {
            components.putCompound("minecraft:material_instances", materialInstances);
        }
        if (!collisionBox.isEmpty()) {
            components.putCompound("minecraft:collision_box", collisionBox);
        }
        return NbtMap.builder()
                .putCompound("components", components.build())
                .putList("permutations", NbtType.COMPOUND, List.of())
                .build();
    }

    private static NbtMap materialInstances() {
        return NbtMap.builder()
                .putCompound("materials", NbtMap.builder()
                        .putCompound("*", NbtMap.builder()
                                .putString("texture", "test")
                                .putByte("packed_bools", (byte) 3)
                                .build())
                        .build())
                .build();
    }

    private static NbtMap material(NbtMap definition) {
        return definition.getCompound("components")
                .getCompound("minecraft:material_instances")
                .getCompound("materials")
                .getCompound("*");
    }

    private static NbtMap box(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return NbtMap.builder()
                .putFloat("minX", minX)
                .putFloat("minY", minY)
                .putFloat("minZ", minZ)
                .putFloat("maxX", maxX)
                .putFloat("maxY", maxY)
                .putFloat("maxZ", maxZ)
                .build();
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

    private static void assertPacketEncodes(Protocol protocol, BedrockPacket packet) {
        var helper = protocol.getCodec().createHelper();
        helper.setItemDefinitions(SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(protocol.getData().itemDefinitions())
                .build());
        helper.setBlockDefinitions(SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(protocol.getData().blockDefinitions())
                .build());

        var buffer = Unpooled.buffer();
        try {
            assertDoesNotThrow(() -> protocol.getCodec().tryEncode(helper, buffer, packet));
            assertTrue(buffer.isReadable());
        } finally {
            buffer.release();
        }
    }
}
