package cn.allay.block.type;

import cn.allay.block.component.TestComponentImpl;
import cn.allay.block.component.TestComponentImplV2;
import cn.allay.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.block.component.impl.attribute.BlockAttributes;
import cn.allay.block.impl.BlockBlueCandle;
import cn.allay.block.impl.BlockCobbledDeepslateWall;
import cn.allay.block.impl.BlockCoralFan;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.property.type.BooleanPropertyType;
import cn.allay.block.property.type.EnumPropertyType;
import cn.allay.block.property.type.IntPropertyType;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.property.vanilla.enums.*;
import cn.allay.math.position.Pos;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static cn.allay.component.interfaces.ComponentProvider.of;
import static cn.allay.component.interfaces.ComponentProvider.ofSingleton;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/16 <br>
 * Allay Project <br>
 */
@ExtendWith(AllayTestExtension.class)
class AllayBlockTypeTest {

    static BlockType<TestBlock> testBlockType;
    static BlockPropertyType<Boolean> TEST_BOOLEAN_PROPERTY_TYPE;
    static BlockPropertyType<Integer> TEST_INT_PROPERTY_TYPE;
    static BlockPropertyType<TestEnum> TEST_ENUM_PROPERTY_TYPE;

    @BeforeAll
    static void init() {
        TEST_BOOLEAN_PROPERTY_TYPE = BooleanPropertyType.createType("test_boolean", false);
        TEST_INT_PROPERTY_TYPE = IntPropertyType.createType("test_int", 0, 10, 0);
        TEST_ENUM_PROPERTY_TYPE = EnumPropertyType.createType("test_enum", TestEnum.class, TestEnum.A);
        testBlockType = AllayBlockType
                .builder(TestBlock.class)
                .namespaceId("minecraft:test_block")
                .withProperties(
                        TEST_BOOLEAN_PROPERTY_TYPE,
                        TEST_INT_PROPERTY_TYPE,
                        TEST_ENUM_PROPERTY_TYPE)
                .setComponents(List.of(
                        of(TestComponentImpl::new, TestComponentImpl.class),
                        ofSingleton(BlockAttributeComponentImpl.ofGlobalStatic(BlockAttributes.DEFAULT))
                ))
                .addBasicComponents()
                .build();
        assertThrows(BlockTypeBuildException.class,
                () -> {
                    AllayBlockType
                            .builder(TestBlock.class)
                            .namespaceId("minecraft:test_block_v2")
                            .withProperties(
                                    TEST_BOOLEAN_PROPERTY_TYPE,
                                    TEST_INT_PROPERTY_TYPE,
                                    TEST_ENUM_PROPERTY_TYPE)
                            .setComponents(List.of(
                                    of(TestComponentImplV2::new, TestComponentImplV2.class),
                                    ofSingleton(BlockAttributeComponentImpl.ofGlobalStatic(BlockAttributes.DEFAULT))
                            ))
                            .addBasicComponents()
                            .build();
                }
        );
    }

    @Test
    void testBlockType() {
        assertNotNull(testBlockType);
    }

    @Test
    void testCommon() {
        var block = testBlockType.createBlock(new BlockInitInfo.Simple(Pos.of(1, 2, 3, null)));
        assertEquals(1, block.getPosition().getX());
        assertEquals(2, block.getPosition().getY());
        assertEquals(3, block.getPosition().getZ());
        assertEquals(testBlockType, block.getBlockType());
        //Test block properties
        assertFalse(block.getProperty(TEST_BOOLEAN_PROPERTY_TYPE));
        block.setProperty(TEST_BOOLEAN_PROPERTY_TYPE, true);
        assertTrue(block.getProperty(TEST_BOOLEAN_PROPERTY_TYPE));
        assertEquals(0, block.getProperty(TEST_INT_PROPERTY_TYPE));
        block.setProperty(TEST_INT_PROPERTY_TYPE, 5);
        assertEquals(5, block.getProperty(TEST_INT_PROPERTY_TYPE));
        assertEquals(TestEnum.A, block.getProperty(TEST_ENUM_PROPERTY_TYPE));
        block.setProperty(TEST_ENUM_PROPERTY_TYPE, TestEnum.B);
        assertEquals(TestEnum.B, block.getProperty(TEST_ENUM_PROPERTY_TYPE));
    }

    @Test
    void testRequirePropertyAnnotation() {
        assertThrows(
                BlockTypeBuildException.class,
                () -> AllayBlockType
                        .builder(TestBlock.class)
                        .namespaceId("minecraft:test_block")
                        .withProperties(
                                TEST_BOOLEAN_PROPERTY_TYPE,
//                                TEST_INT_PROPERTY_TYPE,
                                TEST_ENUM_PROPERTY_TYPE)
                        .setComponents(List.of(
                                of(TestComponentImpl::new, TestComponentImpl.class),
                                ofSingleton(BlockAttributeComponentImpl.ofGlobalStatic(BlockAttributes.DEFAULT))
                        ))
                        .addBasicComponents()
                        .build()
        );
    }

    @Test
    void testBlockStateHash() {
        var b1 = BlockCobbledDeepslateWall.TYPE.createBlock(new BlockInitInfo.Simple(Pos.of(1, 2, 3, null)));
        b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, WallConnectionTypeEast.NONE);
        b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, WallConnectionTypeNorth.TALL);
        b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, WallConnectionTypeSouth.SHORT);
        b1.setProperty(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, WallConnectionTypeWest.NONE);
        b1.setProperty(VanillaBlockPropertyTypes.WALL_POST_BIT, true);
        assertEquals(1789459903, b1.getCurrentState().getUnsignedBlockStateHash());

        var b2 = BlockBlueCandle.TYPE.createBlock(new BlockInitInfo.Simple(Pos.of(1, 2, 3, null)));
        b2.setProperty(VanillaBlockPropertyTypes.CANDLES, 2);
        b2.setProperty(VanillaBlockPropertyTypes.LIT, false);
        assertEquals(4220034033L, b2.getCurrentState().getUnsignedBlockStateHash());

        var b3 = BlockCoralFan.TYPE.createBlock(new BlockInitInfo.Simple(Pos.of(0, 1, 2, null)));
        b3.setProperty(VanillaBlockPropertyTypes.CORAL_COLOR, CoralColor.BLUE);
        b3.setProperty(VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION, 0);
        assertEquals(781710940, b3.getCurrentState().getUnsignedBlockStateHash());
    }
}