package org.allaymc.testutils;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.TestComponentImpl;
import org.allaymc.server.block.type.*;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.item.type.CustomItemDefinition;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Registers block types used by tests before the server freezes its registries.
 */
public final class TestRegistryFixtures {
    private static final BlockPropertyType<Boolean> BLOCK_BOOLEAN_PROPERTY =
            BooleanPropertyType.of("test_boolean", false);
    private static final BlockPropertyType<Integer> BLOCK_INT_PROPERTY =
            IntPropertyType.of("test_int", 0, 10, 0);
    private static final BlockPropertyType<TestEnum> BLOCK_ENUM_PROPERTY =
            EnumPropertyType.of("test_enum", TestEnum.class, TestEnum.A);
    private static final BlockPropertyType<Boolean> MOLANG_BOOLEAN_PROPERTY =
            BooleanPropertyType.of("test_bool", false);
    private static final BlockPropertyType<Integer> MOLANG_INT_PROPERTY =
            IntPropertyType.of("test_int", 0, 3, 0);
    private static final BlockPropertyType<TestEnum> MOLANG_ENUM_PROPERTY =
            EnumPropertyType.of("test_enum", TestEnum.class, TestEnum.A);
    private static final ItemTag CUSTOMIZED_ITEM_TAG = new ItemTag("minecraft:test_auto_generated_block_item");

    private static BlockType<TestBlock> blockType1;
    private static BlockType<TestBlock> blockType2;
    private static BlockType<TestBlock> customizedItemBlockType;
    private static BlockType<TestBlock> molangBlockTypeWithAllProperties;
    private static BlockType<TestBlock> molangBlockTypeWithBooleanOnly;
    private static BlockType<TestBlock> molangBlockTypeWithoutProperties;
    private static BlockType<TestBlock> customDefinitionBlockType;
    private static ItemType<?> customDefinitionItemType;
    private static final AtomicInteger CUSTOM_BLOCK_DEFINITION_INVOCATIONS = new AtomicInteger();
    private static boolean registered;

    private TestRegistryFixtures() {
    }

    public static synchronized void register() {
        if (registered) {
            return;
        }

        blockType1 = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:test_block")
                .setProperties(BLOCK_BOOLEAN_PROPERTY, BLOCK_INT_PROPERTY, BLOCK_ENUM_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder().burnOdds(2).build())))
                .build();
        blockType2 = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:test_block2")
                .setProperties(BLOCK_BOOLEAN_PROPERTY, BLOCK_INT_PROPERTY, BLOCK_ENUM_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDirectDynamic(blockState -> BlockStateData.builder().burnOdds(3).build())))
                .build();
        customizedItemBlockType = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:test_block_with_customized_item")
                .setComponents(List.of(new TestComponentImpl()))
                .customizeItemType(itemBuilder -> itemBuilder.setItemTags(Set.of(CUSTOMIZED_ITEM_TAG)))
                .customBlockDefinition(CustomBlockStateDefinition.ofTexture("allay_test_constant_block"))
                .build();
        molangBlockTypeWithAllProperties = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:molang_test_block")
                .setProperties(MOLANG_BOOLEAN_PROPERTY, MOLANG_INT_PROPERTY, MOLANG_ENUM_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
        molangBlockTypeWithBooleanOnly = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:molang_test_bool_block")
                .setProperties(MOLANG_BOOLEAN_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
        molangBlockTypeWithoutProperties = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:molang_no_props")
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
        customDefinitionBlockType = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:custom_definition_block")
                .setProperties(BLOCK_BOOLEAN_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder()
                                .collisionShape(VoxelShape.builder()
                                        .solid(0, 0, 0, 0.25, 0.5, 0.25)
                                        .solid(0.5, 0.125, 0.5, 1, 1, 1)
                                        .build())
                                .build())))
                .customBlockDefinition(state -> {
                    CUSTOM_BLOCK_DEFINITION_INVOCATIONS.incrementAndGet();
                    return CustomBlockStateDefinition.builder()
                            .geometry("geometry.allay_test.custom_block")
                            .materials(CustomBlockStateDefinition.Materials.builder()
                                    .any(CustomBlockStateDefinition.MaterialInstance.builder()
                                            .texture("allay_test_custom_block")
                                            .faceDimming(true)
                                            .randomUVRotation(true)
                                            .ambientOcclusionIntensity(1f)
                                            .build())
                                    .build())
                            .displayName("tile.allay_test.custom_definition_block.name")
                            .rawComponents(Map.of(
                                    "minecraft:collision_box",
                                    NbtMap.builder().putBoolean("enabled", false).build()
                            ))
                            .build();
                })
                .customBlockRotationOffset(90)
                .customBlockRawComponent("allay_test:global", NbtMap.EMPTY)
                .build();
        customDefinitionItemType = AllayItemType
                .builder(ItemStackImpl.class)
                .identifier("allay_test:custom_definition_item")
                .customItemDefinition(CustomItemDefinition.builder()
                        .texture("allay_test_custom_item")
                        .displayName("item.allay_test.custom_definition_item.name")
                        .renderOffsets(CustomItemDefinition.RenderOffsets.builder()
                                .mainHand(CustomItemDefinition.RenderOffsets.Hand.builder()
                                        .firstPerson(CustomItemDefinition.RenderOffsets.Offset.builder()
                                                .scale(new Vector3f(0.25f))
                                                .build())
                                        .build())
                                .build())
                        .foil(true)
                        .rawProperties(Map.of("allay_test:property", NbtMap.EMPTY))
                        .rawComponents(Map.of("allay_test:component", NbtMap.EMPTY))
                        .build())
                .build();

        registered = true;
    }

    public static BlockPropertyType<Boolean> blockBooleanProperty() {
        return BLOCK_BOOLEAN_PROPERTY;
    }

    public static BlockPropertyType<Integer> blockIntProperty() {
        return BLOCK_INT_PROPERTY;
    }

    public static BlockPropertyType<TestEnum> blockEnumProperty() {
        return BLOCK_ENUM_PROPERTY;
    }

    public static BlockPropertyType<Boolean> molangBooleanProperty() {
        return MOLANG_BOOLEAN_PROPERTY;
    }

    public static BlockPropertyType<Integer> molangIntProperty() {
        return MOLANG_INT_PROPERTY;
    }

    public static BlockPropertyType<TestEnum> molangEnumProperty() {
        return MOLANG_ENUM_PROPERTY;
    }

    public static ItemTag customizedItemTag() {
        return CUSTOMIZED_ITEM_TAG;
    }

    public static BlockType<TestBlock> blockType1() {
        ensureRegistered();
        return blockType1;
    }

    public static BlockType<TestBlock> blockType2() {
        ensureRegistered();
        return blockType2;
    }

    public static BlockType<TestBlock> customizedItemBlockType() {
        ensureRegistered();
        return customizedItemBlockType;
    }

    public static BlockType<TestBlock> molangBlockTypeWithAllProperties() {
        ensureRegistered();
        return molangBlockTypeWithAllProperties;
    }

    public static BlockType<TestBlock> molangBlockTypeWithBooleanOnly() {
        ensureRegistered();
        return molangBlockTypeWithBooleanOnly;
    }

    public static BlockType<TestBlock> molangBlockTypeWithoutProperties() {
        ensureRegistered();
        return molangBlockTypeWithoutProperties;
    }

    public static BlockType<TestBlock> customDefinitionBlockType() {
        ensureRegistered();
        return customDefinitionBlockType;
    }

    public static ItemType<?> customDefinitionItemType() {
        ensureRegistered();
        return customDefinitionItemType;
    }

    public static int customBlockDefinitionInvocations() {
        ensureRegistered();
        return CUSTOM_BLOCK_DEFINITION_INVOCATIONS.get();
    }

    private static void ensureRegistered() {
        if (!registered) {
            throw new IllegalStateException("Test registry fixtures have not been registered");
        }
    }
}
