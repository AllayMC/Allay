package org.allaymc.server.network.protocol;

import io.netty.buffer.Unpooled;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.protocol.v827.PacketEncoder_v827;
import org.allaymc.server.network.protocol.v844.PacketEncoder_v844;
import org.allaymc.testutils.AllayTestExtension;
import org.allaymc.testutils.TestRegistryFixtures;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ProtocolDataEncoderTest} checks cached protocol definitions, recipe
 * indexes, and packet copy boundaries.
 */
@ExtendWith(AllayTestExtension.class)
class ProtocolDataEncoderTest {
    /**
     * {@code registry} retains the extension-installed default registry after
     * setup. Every case reuses its initialized protocols and cached definitions
     * so repeated encodings compare the same source data.
     */
    private static ProtocolRegistry registry;

    /**
     * Captures the default registry after the Allay extension initializes
     * shared fixtures. This class reuses those protocol instances across tests
     * without installing another default registry or rebuilding their cached
     * data.
     */
    @BeforeAll
    static void setUpRegistry() {
        registry = ProtocolRegistry.getDefault();
    }

    /**
     * Compares recipe tables before and after modern furnace encoding. Indexed
     * wire recipes must resolve uniquely, legacy containers reject mutation,
     * and furnace payload representation follows the selected protocol.
     */
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

    /**
     * Encodes pack stacks on both sides of the v844 experiment change. The
     * v827-only experiment remains present across repeated encodes and
     * packet-list mutation without appearing in v844.
     */
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

    /**
     * Reads the same custom block material in legacy and modern definitions.
     * The tested material changes from a face-dimming field to the expected
     * packed boolean byte at v844.
     */
    @Test
    void packedMaterialBooleansStartAtV844() {
        var legacy = customBlockDefinition(protocol(827));
        var modern = customBlockDefinition(protocol(844));
        var legacyMaterial = material(legacy);
        var modernMaterial = material(modern);

        assertFalse(legacyMaterial.containsKey("packed_bools"));
        assertTrue(legacyMaterial.getBoolean("face_dimming"));
        assertEquals((byte) 3, modernMaterial.getByte("packed_bools"));
        assertFalse(modernMaterial.containsKey("face_dimming"));
    }

    /**
     * Compares collision data for a fixture containing two boxes. The older
     * encoding merges them into one full block, while v898 retains the two-box
     * representation.
     */
    @Test
    void multipleCollisionBoxesStartAtV898() {
        var legacy = firstPermutationComponents(customBlockDefinition(protocol(860)))
                .getCompound("minecraft:collision_box");
        var modern = firstPermutationComponents(customBlockDefinition(protocol(898)))
                .getCompound("minecraft:collision_box");

        assertFalse(legacy.containsKey("boxes"));
        assertTrue(legacy.getBoolean("enabled"));
        assertEquals(List.of(-8f, 0f, -8f), legacy.getList("origin", NbtType.FLOAT));
        assertEquals(List.of(16f, 16f, 16f), legacy.getList("size", NbtType.FLOAT));
        assertEquals(2, modern.getList("boxes", NbtType.COMPOUND).size());
    }

    /**
     * Inspects registered custom block and item fixtures after protocol
     * initialization. Callback counts, placement traits, custom properties, and
     * vanilla-item exclusion must match the structured definitions used to
     * build the payloads.
     */
    @Test
    void definitionsAreResolvedOnceAndEncodedFromStructuredModels() {
        var blockType = TestRegistryFixtures.customDefinitionBlockType();
        assertEquals(blockType.getAllStates().size(), TestRegistryFixtures.customBlockDefinitionInvocations());

        var blockDefinition = customBlockDefinition(protocol(898));
        var placementDirection = blockDefinition.getList("traits", NbtType.COMPOUND).getFirst();
        assertEquals(90f, placementDirection.getFloat("y_rotation_offset"));
        assertTrue(blockDefinition.getCompound("components").containsKey("allay_test:global"));

        var customItemType = TestRegistryFixtures.customDefinitionItemType();
        var customItem = protocol(898).getData().itemDefinitions().stream()
                .filter(definition -> definition.identifier().equals(customItemType.getIdentifier().toString()))
                .findFirst()
                .orElseThrow();
        var components = customItem.componentData().getCompound("components");
        assertTrue(customItem.componentBased());
        assertEquals("item.allay_test.custom_definition_item.name",
                components.getCompound("minecraft:display_name").getString("value"));
        assertTrue(components.containsKey("allay_test:component"));
        assertTrue(components.getCompound("item_properties").containsKey("allay_test:property"));

        var vanillaDiamond = assertInstanceOf(AllayItemType.class, ItemTypes.DIAMOND);
        assertFalse(vanillaDiamond.isCustomItem());
        assertNull(vanillaDiamond.getCustomItemDefinition());
    }

    /**
     * Examines cached creative entries, recipe outputs, air, and a custom block
     * item. Their item and block definitions must belong to the selected
     * protocol, with NBT present only for the tested item carrying extra data.
     */
    @Test
    void cachedItemPayloadsUseDefinitionsFromTheirProtocolRegistry() {
        var protocol = protocol(898);
        for (var creativeItem : protocol.getData().creativeItems()) {
            assertTrue(protocol.getItemDefinitionRegistry().isRegistered(creativeItem.item().getDefinition()));
            var blockDefinition = creativeItem.item().getBlockDefinition();
            if (blockDefinition != null) {
                assertTrue(protocol.getBlockDefinitionRegistry().isRegistered(blockDefinition));
            }
        }
        for (var recipe : protocol.getData().recipeTable().encodedRecipes()) {
            switch (recipe) {
                case CraftingRecipeData crafting -> crafting.getResults().forEach(output ->
                        assertTrue(protocol.getItemDefinitionRegistry().isRegistered(output.getDefinition())));
                case SmithingTransformRecipeData smithing -> assertTrue(
                        protocol.getItemDefinitionRegistry().isRegistered(smithing.getResult().getDefinition()));
                case FurnaceRecipeData furnace -> assertTrue(
                        protocol.getItemDefinitionRegistry().isRegistered(furnace.getResult().getDefinition()));
                default -> {
                }
            }
        }

        var air = NetworkHelper.toNetwork(
                ItemAirStack.AIR_STACK,
                protocol.getItemDefinitionRegistry(),
                protocol.getBlockDefinitionRegistry(),
                protocol.getData().blockPalette()
        );
        assertTrue(protocol.getItemDefinitionRegistry().isRegistered(air.getDefinition()));

        var blockItem = NetworkHelper.toNetwork(
                TestRegistryFixtures.customDefinitionBlockType().getItemType().createItemStack(1),
                protocol.getItemDefinitionRegistry(),
                protocol.getBlockDefinitionRegistry(),
                protocol.getData().blockPalette()
        );
        assertTrue(protocol.getItemDefinitionRegistry().isRegistered(blockItem.getDefinition()));
        assertTrue(protocol.getBlockDefinitionRegistry().isRegistered(blockItem.getBlockDefinition()));
        // This unnamed block item carries no extra data; the null tag checks
        // that its encoding does not introduce an empty compound solely because
        // it is a custom block item.
        assertNull(blockItem.getTag());

        var named = TestRegistryFixtures.customDefinitionBlockType().getItemType().createItemStack(1);
        named.setCustomName("Named");
        var namedItem = NetworkHelper.toNetwork(named, protocol.getItemDefinitionRegistry(), protocol.getBlockDefinitionRegistry(), protocol.getData().blockPalette());
        assertNotNull(namedItem.getTag());
        assertFalse(namedItem.getTag().isEmpty());
    }

    /**
     * Mutates item network IDs and recipe lists from one encoding. Repeated
     * creative and crafting packets must preserve their own values and leave
     * subsequent encodes unchanged.
     */
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
        // Change only one encoded item after both packets exist, exposing
        // aliasing with either the other packet or the cached creative
        // definition.
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
        // Mutating a result and clearing its packet list probes two copy
        // boundaries: item fields and the collection surrounding cached recipe
        // payloads.
        outputOne.setNetId(outputNetId + 10_000);
        craftingOne.getCraftingData().clear();
        assertEquals(outputNetId, outputTwo.getNetId());
        assertFalse(craftingTwo.getCraftingData().isEmpty());
        assertEquals(outputNetId, firstOutput(encoder.encodeCraftingData().getCraftingData().get(recipeIndex)).getNetId());
    }

    /**
     * Encodes crafting and pack-stack payloads at four selected version
     * boundaries. Each target codec must accept its own encoder's cached data
     * and produce readable bytes.
     */
    @Test
    void targetCodecsEncodeCachedDataPackets() {
        for (int version : List.of(827, 844, 944, 975)) {
            var protocol = protocol(version);
            assertPacketEncodes(protocol, protocol.getEncoder().encodeCraftingData());
            assertPacketEncodes(protocol, protocol.getEncoder().encodeResourcePackStack());
        }
    }

    /**
     * Returns the initialized international protocol for the requested test
     * version. A missing registry entry fails the test immediately, keeping
     * later assertions focused on payload behavior rather than {@code null}
     * handling.
     */
    private static Protocol protocol(int version) {
        var protocol = registry.resolve(ClientVariant.INTERNATIONAL, version);
        assertNotNull(protocol);
        return protocol;
    }

    /**
     * Returns the structured custom-block fixture from a protocol's cached
     * properties. The fixture must already be registered; a missing property
     * causes the stream lookup to fail instead of returning empty NBT.
     */
    private static NbtMap customBlockDefinition(Protocol protocol) {
        return protocol.getData().customBlockProperties().stream()
                .filter(property -> property.name().equals("allay_test:custom_definition_block"))
                .findFirst()
                .orElseThrow()
                .properties();
    }

    /**
     * Checks that every positive network recipe ID encountered is unique and
     * resolves in the reverse index. At least one such recipe must exist, while
     * nonnetwork and nonpositive entries are skipped by this helper.
     */
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

    /**
     * Returns whether the pack stack contains an experiment with the exact
     * name.
     */
    private static boolean hasExperiment(org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket packet, String name) {
        return packet.getExperiments().stream().anyMatch(experiment -> experiment.name().equals(name));
    }

    /**
     * Returns the wildcard material from the first block permutation.
     */
    private static NbtMap material(NbtMap definition) {
        return firstPermutationComponents(definition)
                .getCompound("minecraft:material_instances")
                .getCompound("materials")
                .getCompound("*");
    }

    /**
     * Returns component data from the definition's first block permutation.
     */
    private static NbtMap firstPermutationComponents(NbtMap definition) {
        return definition.getList("permutations", NbtType.COMPOUND)
                .getFirst()
                .getCompound("components");
    }

    /**
     * Returns the first index containing a supported recipe with an item
     * output. Empty crafting-result lists are skipped, smithing and furnace
     * outputs qualify directly, and absence of any qualifying recipe fails the
     * test.
     */
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

    /**
     * Returns the first output from a supported recipe representation. Crafting
     * result lists must be nonempty, and unsupported payload types fail the
     * test rather than returning an absent item.
     */
    private static ItemData firstOutput(RecipeData recipe) {
        return switch (recipe) {
            case CraftingRecipeData crafting -> crafting.getResults().getFirst();
            case SmithingTransformRecipeData smithing -> smithing.getResult();
            case FurnaceRecipeData furnace -> furnace.getResult();
            default -> fail("Recipe does not contain an item output: " + recipe);
        };
    }

    /**
     * Checks serialization with the selected protocol's own item and block
     * definitions. The helper requires readable output bytes, releases its
     * temporary encoding buffer on failure or success, and leaves the supplied
     * packet owned by the caller.
     */
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
            // The helper owns this temporary buffer rather than sending it, so
            // assertions and codec failures must both leave it released.
            buffer.release();
        }
    }
}
