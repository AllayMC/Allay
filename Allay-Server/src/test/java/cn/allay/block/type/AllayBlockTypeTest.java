package cn.allay.block.type;

import cn.allay.block.component.TestComponent;
import cn.allay.block.component.TestComponentImpl;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.property.type.BooleanPropertyType;
import cn.allay.block.property.type.EnumPropertyType;
import cn.allay.block.property.type.IntPropertyType;
import cn.allay.component.interfaces.ComponentProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/16 <br>
 * Allay Project <br>
 */
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
                .property(
                        TEST_BOOLEAN_PROPERTY_TYPE,
                        TEST_INT_PROPERTY_TYPE,
                        TEST_ENUM_PROPERTY_TYPE)
                .component(List.of(ComponentProvider.of(TestComponentImpl::new, TestComponentImpl.class)))
                .build();
    }

    @Test
    void testBlockType() {
        assertNotNull(testBlockType);
    }
}