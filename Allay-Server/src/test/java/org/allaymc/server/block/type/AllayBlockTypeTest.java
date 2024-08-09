package org.allaymc.server.block.type;

import org.allaymc.api.block.component.common.BlockStateData;
import org.allaymc.api.block.property.enums.WallConnectionTypeEast;
import org.allaymc.api.block.property.enums.WallConnectionTypeNorth;
import org.allaymc.api.block.property.enums.WallConnectionTypeSouth;
import org.allaymc.api.block.property.enums.WallConnectionTypeWest;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.TestComponentImpl;
import org.allaymc.server.block.component.TestComponentImplV2;
import org.allaymc.server.block.component.common.BlockStateDataComponentImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.allaymc.api.block.type.BlockTypes.BLUE_CANDLE;
import static org.allaymc.api.block.type.BlockTypes.COBBLED_DEEPSLATE_WALL;
import static org.allaymc.server.block.type.AllayBlockType.computeSpecialValue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayBlockTypeTest {
    static BlockType<TestBlock> testBlockType1;
    static BlockType<TestBlock> testBlockType2;
    static BlockPropertyType<Boolean> TEST_BOOLEAN_PROPERTY_TYPE;
    static BlockPropertyType<Integer> TEST_INT_PROPERTY_TYPE;
    static BlockPropertyType<TestEnum> TEST_ENUM_PROPERTY_TYPE;

    @BeforeAll
    static void init() {
        TEST_BOOLEAN_PROPERTY_TYPE = BooleanPropertyType.of("test_boolean", false);
        TEST_INT_PROPERTY_TYPE = IntPropertyType.of("test_int", 0, 10, 0);
        TEST_ENUM_PROPERTY_TYPE = EnumPropertyType.of("test_enum", TestEnum.class, TestEnum.A);
        testBlockType1 = AllayBlockType
                .builder(TestBlock.class)
                .identifier("minecraft:test_block")
                .setProperties(
                        TEST_BOOLEAN_PROPERTY_TYPE,
                        TEST_INT_PROPERTY_TYPE,
                        TEST_ENUM_PROPERTY_TYPE)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder().burnOdds(2).build())))
                .build();
        testBlockType2 = AllayBlockType
                .builder(TestBlock.class)
                .identifier("minecraft:test_block2")
                .setProperties(
                        TEST_BOOLEAN_PROPERTY_TYPE,
                        TEST_INT_PROPERTY_TYPE,
                        TEST_ENUM_PROPERTY_TYPE)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDirectDynamic(blockState -> BlockStateData.builder().burnOdds(3).build())))
                .build();
    }

    @Test
    public void testBlockTypeBuildException() {
        assertThrows(BlockTypeBuildException.class,
                () -> AllayBlockType
                        .builder(TestBlock.class)
                        .identifier("minecraft:test_block_v2")
                        .setProperties(
                                TEST_BOOLEAN_PROPERTY_TYPE,
                                TEST_INT_PROPERTY_TYPE,
                                TEST_ENUM_PROPERTY_TYPE)
                        .setComponents(List.of(
                                new TestComponentImplV2(),
                                BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.DEFAULT)
                        ))
                        .build()
        );
    }

    @Test
    void testDefaultItemType() {
        assertNotNull(testBlockType1.getItemType());
        assertNotNull(testBlockType2.getItemType());
    }

    @Test
    void testBlockType() {
        assertNotNull(testBlockType1);
    }

    @Test
    void testCommon() {
        var block = testBlockType1.getBlockBehavior();
        assertEquals(testBlockType1, block.getBlockType());
        // Test block properties
        var state = block.getBlockType().getDefaultState();
        assertFalse(state.getPropertyValue(TEST_BOOLEAN_PROPERTY_TYPE));
        state = state.setProperty(TEST_BOOLEAN_PROPERTY_TYPE, true);
        assertTrue(state.getPropertyValue(TEST_BOOLEAN_PROPERTY_TYPE));
        assertEquals(0, state.getPropertyValue(TEST_INT_PROPERTY_TYPE));
        state = state.setProperty(TEST_INT_PROPERTY_TYPE, 5);
        assertEquals(5, state.getPropertyValue(TEST_INT_PROPERTY_TYPE));
        assertEquals(TestEnum.A, state.getPropertyValue(TEST_ENUM_PROPERTY_TYPE));
        state = state.setProperty(TEST_ENUM_PROPERTY_TYPE, TestEnum.B);
        assertEquals(TestEnum.B, state.getPropertyValue(TEST_ENUM_PROPERTY_TYPE));
    }

    @Test
    void testRequirePropertyAnnotation() {
        assertThrows(
                BlockTypeBuildException.class,
                () -> AllayBlockType
                        .builder(TestBlock.class)
                        .identifier("minecraft:test_block")
                        .setProperties(
                                TEST_BOOLEAN_PROPERTY_TYPE,
                                // TEST_INT_PROPERTY_TYPE,
                                TEST_ENUM_PROPERTY_TYPE)
                        .setComponents(List.of(
                                new TestComponentImpl(),
                                BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.DEFAULT)
                        ))
                        .build()
        );
    }

    @Test
    void testBlockStateHash() {
        var b1 = COBBLED_DEEPSLATE_WALL.getDefaultState();
        b1 = b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, WallConnectionTypeEast.NONE);
        b1 = b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, WallConnectionTypeNorth.TALL);
        b1 = b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, WallConnectionTypeSouth.SHORT);
        b1 = b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, WallConnectionTypeWest.NONE);
        b1 = b1.setProperty(VanillaBlockPropertyTypes.WALL_POST_BIT, true);
        assertEquals(1789459903, b1.unsignedBlockStateHash());

        var b2 = BLUE_CANDLE.getDefaultState();
        b2 = b2.setProperty(VanillaBlockPropertyTypes.CANDLES, 2);
        b2 = b2.setProperty(VanillaBlockPropertyTypes.LIT, false);
        assertEquals(4220034033L, b2.unsignedBlockStateHash());
    }

    @Test
    void testBlockAttributes() {
        assertEquals(2, testBlockType1.getBlockBehavior().getBlockStateData(testBlockType1.getDefaultState()).burnOdds());
        assertEquals(3, testBlockType2.getBlockBehavior().getBlockStateData(testBlockType2.getDefaultState()).burnOdds());
    }

    @Test
    void testSpecialValue() {
        var values = new BlockPropertyType.BlockPropertyValue<?, ?, ?>[3];
        values[0] = (TEST_BOOLEAN_PROPERTY_TYPE.createValue(true));// 1 bit
        values[1] = (TEST_INT_PROPERTY_TYPE.createValue(5));// 4 bit
        values[2] = (TEST_ENUM_PROPERTY_TYPE.createValue(TestEnum.B));// 2 bit
        byte offset = 0;
        for (var value : values) {
            offset += value.getPropertyType().getBitSize();
        }
        // 1 0101 01
        assertEquals(0b1010101, computeSpecialValue(offset, values));
        // 1 0101 00
        values[2] = TEST_ENUM_PROPERTY_TYPE.createValue(TestEnum.A);
        assertEquals(0b1010100, computeSpecialValue(offset, values));
        // 1 0000 00
        values[1] = TEST_INT_PROPERTY_TYPE.createValue(0);
        assertEquals(0b1000000, computeSpecialValue(offset, values));
    }
}