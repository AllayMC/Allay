package org.allaymc.testutils;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.TestComponentImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.TestBlock;
import org.allaymc.server.block.type.TestBlockImpl;
import org.allaymc.server.block.type.TestEnum;

import java.util.List;
import java.util.Set;

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

    private static void ensureRegistered() {
        if (!registered) {
            throw new IllegalStateException("Test registry fixtures have not been registered");
        }
    }
}
