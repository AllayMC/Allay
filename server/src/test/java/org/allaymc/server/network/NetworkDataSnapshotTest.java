package org.allaymc.server.network;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.entity.property.type.EnumPropertyType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.PotionRecipe;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.recipe.ShapelessRecipe;
import org.allaymc.api.item.recipe.SmithingTransformRecipe;
import org.allaymc.api.item.recipe.SmithingTrimRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeTags;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.item.recipe.DecoratedPotRecipe;
import org.allaymc.server.item.recipe.FireworkRecipe;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.PacketEncoderV766;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NetworkDataSnapshotTest {

    @Test
    void packSnapshotDoesNotRetainMutableManifestMetadata() {
        var registeredId = UUID.fromString("00000000-0000-0000-0000-000000000010");
        var manifest = PackManifest.fromJson(new StringReader("""
                {
                  "format_version": 2,
                  "header": {
                    "name": "test",
                    "description": "test",
                    "uuid": "00000000-0000-0000-0000-000000000001",
                    "version": [1, 2, 3]
                  },
                  "modules": [],
                  "capabilities": ["raytraced"]
                }
                """));
        var otherManifest = PackManifest.fromJson(new StringReader("""
                {
                  "format_version": 2,
                  "header": {
                    "name": "other",
                    "description": "other",
                    "uuid": "00000000-0000-0000-0000-000000000002",
                    "version": [9, 8, 7]
                  },
                  "modules": []
                }
                """));
        var hash = new byte[]{1, 2, 3, 4};
        var pack = mock(Pack.class);
        when(pack.getId()).thenAnswer(ignored -> manifest.getHeader().getUuid());
        when(pack.getStringVersion()).thenAnswer(ignored -> manifest.getHeader().getVersion().getVersion());
        when(pack.getSize()).thenReturn(123);
        when(pack.getContentKey()).thenReturn("content-key");
        when(pack.getType()).thenReturn(Pack.Type.RESOURCES);
        when(pack.getManifest()).thenReturn(manifest);
        when(pack.getHash()).thenReturn(hash);

        var snapshot = NetworkData.PackSnapshot.from(registeredId, pack);
        manifest.getHeader().setUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        manifest.getHeader().setVersion(otherManifest.getHeader().getVersion());
        manifest.getCapabilities().clear();
        hash[0] = 99;

        assertEquals(registeredId, snapshot.id());
        assertEquals("1.2.3", snapshot.version());
        assertEquals(123, snapshot.size());
        assertEquals("content-key", snapshot.contentKey());
        assertEquals(Pack.Type.RESOURCES, snapshot.type());
        assertTrue(snapshot.raytraced());
        assertArrayEquals(new byte[]{1, 2, 3, 4}, snapshot.hash());

        var returnedHash = snapshot.hash();
        returnedHash[1] = 88;
        assertArrayEquals(new byte[]{1, 2, 3, 4}, snapshot.hash());
    }

    @Test
    void resourcePackPacketsOwnTheirMutablePayloads() {
        var id = UUID.fromString("00000000-0000-0000-0000-000000000020");
        var content = new byte[]{10, 20, 30, 40, 50};
        var hash = new byte[]{1, 2, 3, 4};
        var manifest = PackManifest.fromJson(new StringReader("""
                {
                  "format_version": 2,
                  "header": {
                    "name": "packet-test",
                    "description": "packet-test",
                    "uuid": "00000000-0000-0000-0000-000000000021",
                    "version": [1, 0, 0]
                  },
                  "modules": []
                }
                """));
        var pack = mock(Pack.class);
        when(pack.getStringVersion()).thenReturn("1.0.0");
        when(pack.getSize()).thenReturn(content.length);
        when(pack.getContentKey()).thenReturn("");
        when(pack.getType()).thenReturn(Pack.Type.RESOURCES);
        when(pack.getManifest()).thenReturn(manifest);
        when(pack.getHash()).thenReturn(hash);
        when(pack.getChunk(anyInt(), anyInt())).thenAnswer(invocation -> {
            int offset = invocation.getArgument(0);
            int length = invocation.getArgument(1);
            return Arrays.copyOfRange(content, offset, offset + length);
        });

        var snapshot = NetworkData.PackSnapshot.from(id, pack);
        var encoder = new PacketEncoderV766(mock(ProtocolData.class));

        var firstInfo = encoder.encodeResourcePackDataInfo(snapshot, 3);
        firstInfo.getHash()[0] = 99;
        var secondInfo = encoder.encodeResourcePackDataInfo(snapshot, 3);
        assertArrayEquals(hash, secondInfo.getHash());
        assertEquals(2, secondInfo.getChunkCount());

        var firstChunk = encoder.encodeResourcePackChunkData(snapshot, 0, 3);
        var secondChunk = encoder.encodeResourcePackChunkData(snapshot, 0, 3);
        var finalChunk = encoder.encodeResourcePackChunkData(snapshot, 1, 3);
        try {
            assertNotSame(firstChunk.getData(), secondChunk.getData());
            firstChunk.getData().setByte(0, 99);
            assertEquals(10, secondChunk.getData().getByte(0));
            assertEquals(0, firstChunk.getProgress());
            assertEquals(2, finalChunk.getData().readableBytes());
            assertEquals(3, finalChunk.getProgress());
            assertEquals(40, finalChunk.getData().getByte(0));
            assertThrows(
                    IllegalArgumentException.class,
                    () -> encoder.encodeResourcePackChunkData(snapshot, 2, 3)
            );
        } finally {
            firstChunk.getData().release();
            secondChunk.getData().release();
            finalChunk.getData().release();
        }
    }

    @Test
    void entitySnapshotDoesNotRetainMutablePropertyDefinitions() {
        var enumProperty = EnumPropertyType.of(
                "test:mode",
                TestEntityPropertyValue.class,
                TestEntityPropertyValue.FIRST
        );
        var properties = new LinkedHashMap<String, EntityPropertyType<?>>();
        properties.put(enumProperty.getName(), enumProperty);
        EntityType<?> entityType = mock(EntityType.class);
        when(entityType.getIdentifier()).thenReturn(new Identifier("test:entity"));
        when(entityType.getProperties()).thenReturn(properties);

        var snapshot = NetworkData.EntitySnapshot.from(entityType);
        properties.clear();
        enumProperty.getValidValues().set(0, TestEntityPropertyValue.SECOND);

        assertEquals(new Identifier("test:entity"), snapshot.identifier());
        assertEquals(1, snapshot.properties().size());
        var property = assertInstanceOf(
                NetworkData.EnumEntityPropertySnapshot.class,
                snapshot.properties().getFirst()
        );
        assertEquals(List.of("first", "second"), property.serializedValues());
        assertThrows(
                UnsupportedOperationException.class,
                () -> property.serializedValues().add("third")
        );
    }

    @Test
    void protocolDataDoesNotRetainMutableBlockOrDimensionMetadata() {
        var blockRuntimeId = new AtomicInteger(101);
        var blockState = mock(BlockState.class);
        when(blockState.blockStateHash()).thenAnswer(ignored -> blockRuntimeId.get());

        var dimensionId = new AtomicInteger(7);
        var dimensionIdentifier = new AtomicReference<>(new Identifier("test:mutable_dimension"));
        var minHeight = new AtomicInteger(0);
        var maxHeight = new AtomicInteger(31);
        var hasSkyLight = new AtomicBoolean(true);
        var bedWorks = new AtomicBoolean(true);
        var respawnAnchorWorks = new AtomicBoolean(false);
        var waterEvaporates = new AtomicBoolean(false);
        var dimensionType = mock(DimensionType.class);
        when(dimensionType.getId()).thenAnswer(ignored -> dimensionId.get());
        when(dimensionType.getIdentifier()).thenAnswer(ignored -> dimensionIdentifier.get());
        when(dimensionType.getMinHeight()).thenAnswer(ignored -> minHeight.get());
        when(dimensionType.getMaxHeight()).thenAnswer(ignored -> maxHeight.get());
        when(dimensionType.hasSkyLight()).thenAnswer(ignored -> hasSkyLight.get());
        when(dimensionType.bedWorks()).thenAnswer(ignored -> bedWorks.get());
        when(dimensionType.respawnAnchorWorks()).thenAnswer(ignored -> respawnAnchorWorks.get());
        when(dimensionType.waterEvaporates()).thenAnswer(ignored -> waterEvaporates.get());

        var source = new NetworkData(
                List.of(),
                List.of(),
                List.of(blockState),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(dimensionType),
                List.of(),
                List.of(),
                List.of()
        );

        blockRuntimeId.set(202);
        dimensionId.set(8);
        dimensionIdentifier.set(new Identifier("test:changed_dimension"));
        minHeight.set(16);
        maxHeight.set(63);
        hasSkyLight.set(false);
        bedWorks.set(false);
        respawnAnchorWorks.set(true);
        waterEvaporates.set(true);

        var data = ProtocolData.builder(source, 766, ClientVariant.INTERNATIONAL).build();
        var encoder = new PacketEncoderV766(data);
        var capturedDimension = source.dimensionTypes().getFirst();
        var firstDefinition = encoder.encodeDimensionData().getDefinitions().getFirst();

        assertEquals(101, data.blockDefinitions().getFirst().runtimeId());
        assertNotSame(dimensionType, capturedDimension);
        assertEquals(7, capturedDimension.getId());
        assertEquals(new Identifier("test:mutable_dimension"), capturedDimension.getIdentifier());
        assertEquals(0, capturedDimension.getMinHeight());
        assertEquals(31, capturedDimension.getMaxHeight());
        assertTrue(capturedDimension.hasSkyLight());
        assertTrue(capturedDimension.bedWorks());
        assertFalse(capturedDimension.respawnAnchorWorks());
        assertFalse(capturedDimension.waterEvaporates());
        assertEquals("test:mutable_dimension", firstDefinition.id());
        assertEquals(32, firstDefinition.maximumHeight());
        assertEquals(0, firstDefinition.minimumHeight());
        assertEquals(7, firstDefinition.dimensionType());

        blockRuntimeId.set(303);
        dimensionId.set(9);
        maxHeight.set(95);

        assertEquals(101, data.blockDefinitions().getFirst().runtimeId());
        assertEquals(firstDefinition, encoder.encodeDimensionData().getDefinitions().getFirst());
    }

    @Test
    void shapedRecipeSnapshotDefensivelyCopiesMutableData() {
        var itemType = itemType("test:ingredient");
        var output = mutableItemStack(itemType, 2, 1);
        var descriptor = new MutableItemTypeDescriptor(itemType, 4);
        var pattern = new char[][]{{'A'}};
        var keys = new LinkedHashMap<Character, ItemDescriptor>();
        keys.put('A', descriptor);
        var outputs = new ItemStack[]{output};
        var source = new ShapedRecipe(
                new Identifier("test:shaped"),
                outputs,
                3,
                pattern,
                keys
        );

        var snapshot = assertInstanceOf(ShapedRecipe.class, NetworkData.snapshotRecipe(source));
        pattern[0][0] = 'B';
        keys.clear();
        outputs[0] = mutableItemStack(itemType, 11, 1);
        descriptor.setMeta(8);
        output.setMeta(9);

        assertEquals('A', snapshot.getPattern()[0][0]);
        assertEquals(4, assertInstanceOf(ItemTypeDescriptor.class, snapshot.getKeys().get('A')).getMeta());
        assertEquals(2, snapshot.getOutput().getMeta());
        assertEquals(3, snapshot.getPriority());

        var exposedPattern = snapshot.getPattern();
        exposedPattern[0][0] = 'Z';
        var exposedOutputs = snapshot.getOutputs();
        exposedOutputs[0].setMeta(10);
        exposedOutputs[0] = null;
        var exposedKeys = snapshot.getKeys();
        assertThrows(UnsupportedOperationException.class, exposedKeys::clear);

        assertEquals('A', snapshot.getPattern()[0][0]);
        assertEquals(2, snapshot.getOutput().getMeta());
        assertNotSame(exposedKeys.get('A'), snapshot.getKeys().get('A'));
    }

    @Test
    void recipeSnapshotsPreserveProcessorVisibleKindsAndInputs() {
        var ingredientType = itemType("test:ingredient");
        var templateType = itemType("test:template");
        var baseType = itemType("test:base");
        var additionType = itemType("test:addition");
        var reagentType = itemType("test:reagent");
        var outputType = itemType("test:output");

        var shapelessDescriptor = new MutableItemTypeDescriptor(ingredientType, 1);
        var shapelessIngredients = new ItemDescriptor[]{shapelessDescriptor};
        var shapelessOutput = mutableItemStack(outputType, 2, 1);
        var shapelessSource = new ShapelessRecipe(
                new Identifier("test:shapeless"),
                new ItemStack[]{shapelessOutput},
                4,
                shapelessIngredients,
                ShapelessRecipe.Type.STONECUTTER
        );
        var shapeless = assertInstanceOf(
                ShapelessRecipe.class,
                NetworkData.snapshotRecipe(shapelessSource)
        );
        shapelessDescriptor.setMeta(9);
        shapelessIngredients[0] = new ItemTypeDescriptor(outputType, 7);
        shapelessOutput.setMeta(8);
        assertEquals(1, assertInstanceOf(ItemTypeDescriptor.class, shapeless.getIngredients()[0]).getMeta());
        assertEquals(2, shapeless.getOutput().getMeta());
        assertEquals(ShapelessRecipe.Type.STONECUTTER, shapeless.getType());
        var exposedIngredients = shapeless.getIngredients();
        exposedIngredients[0] = null;
        assertNotNull(shapeless.getIngredients()[0]);

        var template = new MutableItemTypeDescriptor(templateType, 1);
        var base = new MutableItemTypeDescriptor(baseType, 2);
        var addition = new MutableItemTypeDescriptor(additionType, 3);
        var smithingOutput = mutableItemStack(outputType, 4, 1);
        var transformSource = new SmithingTransformRecipe(
                new Identifier("test:smithing_transform"),
                new ItemStack[]{smithingOutput},
                5,
                template,
                base,
                addition
        );
        var transform = assertInstanceOf(
                SmithingTransformRecipe.class,
                NetworkData.snapshotRecipe(transformSource)
        );
        template.setMeta(7);
        base.setMeta(8);
        addition.setMeta(9);
        smithingOutput.setMeta(10);
        assertEquals(1, assertInstanceOf(ItemTypeDescriptor.class, transform.getTemplate()).getMeta());
        assertEquals(2, assertInstanceOf(ItemTypeDescriptor.class, transform.getBase()).getMeta());
        assertEquals(3, assertInstanceOf(ItemTypeDescriptor.class, transform.getAddition()).getMeta());
        assertEquals(4, transform.getOutput().getMeta());

        var trimSource = new SmithingTrimRecipe(
                new Identifier("test:smithing_trim"),
                null,
                0,
                new ItemTypeDescriptor(templateType),
                new ItemTypeDescriptor(baseType),
                new ItemTypeDescriptor(additionType)
        );
        var trim = assertInstanceOf(SmithingTrimRecipe.class, NetworkData.snapshotRecipe(trimSource));
        assertNull(trim.getOutputs());

        var furnaceDescriptor = new MutableItemTypeDescriptor(ingredientType, 5);
        var furnaceOutput = mutableItemStack(outputType, 6, 1);
        var furnaceSource = new FurnaceRecipe(
                furnaceDescriptor,
                furnaceOutput,
                2,
                FurnaceRecipe.Type.BLAST_FURNACE
        );
        var furnace = assertInstanceOf(FurnaceRecipe.class, NetworkData.snapshotRecipe(furnaceSource));
        furnaceDescriptor.setMeta(11);
        furnaceOutput.setMeta(12);
        assertEquals(5, assertInstanceOf(ItemTypeDescriptor.class, furnace.getIngredient()).getMeta());
        assertEquals(6, furnace.getOutput().getMeta());
        assertEquals(FurnaceRecipe.Type.BLAST_FURNACE, furnace.getType());

        var potionIngredient = mutableItemStack(ingredientType, 7, 1);
        var potionReagent = mutableItemStack(reagentType, 8, 1);
        var potionOutput = mutableItemStack(outputType, 9, 1);
        var potionSource = new PotionRecipe(potionIngredient, potionOutput, potionReagent);
        var potion = assertInstanceOf(PotionRecipe.class, NetworkData.snapshotRecipe(potionSource));
        potionIngredient.setMeta(17);
        potionReagent.setMeta(18);
        potionOutput.setMeta(19);
        assertEquals(7, potion.getIngredient().getMeta());
        assertEquals(8, potion.getReagent().getMeta());
        assertEquals(9, potion.getOutput().getMeta());
        var exposedPotionIngredient = potion.getIngredient();
        exposedPotionIngredient.setMeta(20);
        assertEquals(7, potion.getIngredient().getMeta());

        var firework = assertInstanceOf(
                ComplexRecipe.class,
                NetworkData.snapshotRecipe(FireworkRecipe.INSTANCE)
        );
        var decoratedPot = assertInstanceOf(
                ComplexRecipe.class,
                NetworkData.snapshotRecipe(DecoratedPotRecipe.INSTANCE)
        );
        assertNotSame(FireworkRecipe.INSTANCE, firework);
        assertNotSame(DecoratedPotRecipe.INSTANCE, decoratedPot);
        assertEquals(FireworkRecipe.INSTANCE.getUuid(), firework.getUuid());
        assertEquals(DecoratedPotRecipe.INSTANCE.getUuid(), decoratedPot.getUuid());
    }

    @Test
    void recipeSnapshotRejectsUnsupportedRecipeAndDescriptorTypes() {
        var itemType = itemType("test:item");
        var output = mutableItemStack(itemType, 0, 1);
        var unsupportedRecipe = new Recipe(
                new Identifier("test:unsupported"),
                new ItemStack[]{output},
                0
        ) {
            @Override
            public boolean match(RecipeInput input) {
                return false;
            }
        };
        var recipeError = assertThrows(
                IllegalArgumentException.class,
                () -> NetworkData.snapshotRecipe(unsupportedRecipe)
        );
        assertTrue(recipeError.getMessage().contains("Unsupported recipe type"));

        ItemDescriptor unsupportedDescriptor = itemStack -> true;
        var unsupportedDescriptorRecipe = new ShapelessRecipe(
                new Identifier("test:unsupported_descriptor"),
                new ItemStack[]{output},
                0,
                new ItemDescriptor[]{unsupportedDescriptor},
                ShapelessRecipe.Type.CRAFTING
        );
        var descriptorError = assertThrows(
                IllegalArgumentException.class,
                () -> NetworkData.snapshotRecipe(unsupportedDescriptorRecipe)
        );
        assertTrue(descriptorError.getMessage().contains("Unsupported item descriptor type"));

        var unsupportedComplex = new ComplexRecipe(
                UUID.fromString("00000000-0000-0000-0000-000000000030")
        ) {
            @Override
            public boolean match(RecipeInput input) {
                return false;
            }

            @Override
            public ItemStack[] getOutputs(RecipeInput input) {
                return null;
            }
        };
        var complexError = assertThrows(
                IllegalArgumentException.class,
                () -> NetworkData.snapshotRecipe(unsupportedComplex)
        );
        assertTrue(complexError.getMessage().contains("Unsupported complex recipe type"));
    }

    @Test
    void biomeSnapshotDoesNotRetainMutableTags() {
        var tags = new ArrayList<>(List.of(BiomeTags.PLAINS));
        var biomeData = new BiomeData(
                0.8f, 0.4f,
                0, 0, 0, 0,
                0, 0.1f, 0.2f,
                Color.BLUE, true, tags
        );
        var biomeType = mock(BiomeType.class);
        when(biomeType.getIdentifier()).thenReturn(new Identifier("test:biome"));
        when(biomeType.getId()).thenReturn(42);
        when(biomeType.getBiomeData()).thenReturn(biomeData);

        var snapshot = NetworkData.BiomeSnapshot.from(biomeType);
        tags.clear();
        tags.add(BiomeTags.DESERT);

        assertEquals(new Identifier("test:biome"), snapshot.getIdentifier());
        assertEquals(42, snapshot.getId());
        assertEquals(List.of(BiomeTags.PLAINS), snapshot.getBiomeData().tags());
        assertThrows(
                UnsupportedOperationException.class,
                () -> snapshot.getBiomeData().tags().add(BiomeTags.DESERT)
        );
        assertDoesNotThrow(() -> NetworkHelper.toNetwork(snapshot));
    }

    @SuppressWarnings("unchecked")
    private static ItemType<? extends ItemStack> itemType(String identifier) {
        var itemType = (ItemType<? extends ItemStack>) mock(ItemType.class);
        when(itemType.getIdentifier()).thenReturn(new Identifier(identifier));
        return itemType;
    }

    private static ItemStack mutableItemStack(
            ItemType<? extends ItemStack> itemType,
            int initialMeta,
            int initialCount
    ) {
        var meta = new AtomicInteger(initialMeta);
        var count = new AtomicInteger(initialCount);
        var itemStack = mock(ItemStack.class);
        doReturn(itemType).when(itemStack).getItemType();
        when(itemStack.getMeta()).thenAnswer(ignored -> meta.get());
        when(itemStack.getCount()).thenAnswer(ignored -> count.get());
        doAnswer(invocation -> {
            meta.set(invocation.getArgument(0));
            return null;
        }).when(itemStack).setMeta(anyInt());
        doAnswer(invocation -> {
            count.set(invocation.getArgument(0));
            return null;
        }).when(itemStack).setCount(anyInt());
        when(itemStack.copy(false)).thenAnswer(ignored ->
                mutableItemStack(itemType, meta.get(), count.get())
        );
        return itemStack;
    }

    private static final class MutableItemTypeDescriptor extends ItemTypeDescriptor {
        private MutableItemTypeDescriptor(ItemType<?> itemType, int meta) {
            super(itemType, meta);
        }

        private void setMeta(int meta) {
            this.meta = meta;
        }
    }

    private enum TestEntityPropertyValue {
        FIRST,
        SECOND
    }
}
