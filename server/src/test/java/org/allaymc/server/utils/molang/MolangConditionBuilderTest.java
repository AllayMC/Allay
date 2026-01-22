package org.allaymc.server.utils.molang;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.TestComponentImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.TestBlock;
import org.allaymc.server.block.type.TestBlockImpl;
import org.allaymc.server.block.type.TestEnum;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class MolangConditionBuilderTest {

    static BlockPropertyType<Boolean> BOOL_PROP;
    static BlockPropertyType<Integer> INT_PROP;
    static BlockPropertyType<TestEnum> ENUM_PROP;
    static BlockType<TestBlock> blockTypeWithAllProps;
    static BlockType<TestBlock> blockTypeWithBoolOnly;

    @BeforeAll
    static void init() {
        BOOL_PROP = BooleanPropertyType.of("test_bool", false);
        INT_PROP = IntPropertyType.of("test_int", 0, 3, 0);
        ENUM_PROP = EnumPropertyType.of("test_enum", TestEnum.class, TestEnum.A);

        blockTypeWithAllProps = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("minecraft:molang_test_block")
                .setProperties(BOOL_PROP, INT_PROP, ENUM_PROP)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();

        blockTypeWithBoolOnly = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("minecraft:molang_test_bool_block")
                .setProperties(BOOL_PROP)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
    }

    @Test
    void testSingleStateBooleanCondition() {
        var state = blockTypeWithBoolOnly.getDefaultState();
        var states = Set.of(state);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithBoolOnly);

        assertEquals("q.block_state('test_bool') == 0", condition);
    }

    @Test
    void testSingleStateBooleanTrueCondition() {
        var state = blockTypeWithBoolOnly.getDefaultState().setPropertyValue(BOOL_PROP, true);
        var states = Set.of(state);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithBoolOnly);

        assertEquals("q.block_state('test_bool') == 1", condition);
    }

    @Test
    void testMultiplePropertiesCondition() {
        var state = blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 2)
                .setPropertyValue(ENUM_PROP, TestEnum.B);
        var states = Set.of(state);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        // Condition should contain all three property checks
        assertTrue(condition.contains("q.block_state('test_bool') == 1"));
        assertTrue(condition.contains("q.block_state('test_int') == 2"));
        assertTrue(condition.contains("q.block_state('test_enum') == 'b'"));
        assertTrue(condition.contains("&&"));
    }

    @Test
    void testMultipleStatesWithSameProperty() {
        var state1 = blockTypeWithBoolOnly.getDefaultState().setPropertyValue(BOOL_PROP, false);
        var state2 = blockTypeWithBoolOnly.getDefaultState().setPropertyValue(BOOL_PROP, true);
        var states = Set.of(state1, state2);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithBoolOnly);

        // Should produce an OR condition
        assertTrue(condition.contains("||"));
        assertTrue(condition.contains("q.block_state('test_bool') == 0"));
        assertTrue(condition.contains("q.block_state('test_bool') == 1"));
    }

    @Test
    void testEmptyStatesReturnsAlwaysTrue() {
        var states = Set.<BlockState>of();

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithBoolOnly);

        assertEquals("1", condition);
    }

    @Test
    void testOptimizationWithConstantProperty() {
        // Create states where INT_PROP varies but BOOL_PROP is constant
        var states = new HashSet<BlockState>();
        for (int i = 0; i <= 3; i++) {
            states.add(blockTypeWithAllProps.getDefaultState()
                    .setPropertyValue(BOOL_PROP, true)  // constant
                    .setPropertyValue(INT_PROP, i)      // varies
                    .setPropertyValue(ENUM_PROP, TestEnum.A));  // constant
        }

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        // The constant properties should be factored out
        // and the varying property should use OR
        assertTrue(condition.contains("q.block_state('test_bool') == 1"));
        assertTrue(condition.contains("q.block_state('test_enum') == 'a'"));
    }

    @Test
    void testSingleStateIntCondition() {
        var state = blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(INT_PROP, 2);
        var states = Set.of(state);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_int') == 2"));
    }

    @Test
    void testSingleStateEnumCondition() {
        var state = blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(ENUM_PROP, TestEnum.C);
        var states = Set.of(state);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        // Enum values should be lowercase
        assertTrue(condition.contains("q.block_state('test_enum') == 'c'"));
    }

    @Test
    void testBlockWithNoProperties() {
        var blockTypeNoProps = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("minecraft:molang_no_props")
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();

        var state = blockTypeNoProps.getDefaultState();
        var states = Set.of(state);

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeNoProps);

        // Block with no properties should return "1"
        assertEquals("1", condition);
    }

    @Test
    void testAllPropertiesVaryingNoOptimization() {
        // Create states where all 3 properties vary - no optimization possible
        var states = new HashSet<BlockState>();
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, false)
                .setPropertyValue(INT_PROP, 0)
                .setPropertyValue(ENUM_PROP, TestEnum.A));
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 1)
                .setPropertyValue(ENUM_PROP, TestEnum.B));

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        // Should contain OR since all properties vary
        assertTrue(condition.contains("||"));
    }

    @Test
    void testMultipleEnumValues() {
        var states = new HashSet<BlockState>();
        for (TestEnum e : TestEnum.values()) {
            states.add(blockTypeWithAllProps.getDefaultState()
                    .setPropertyValue(BOOL_PROP, true)  // constant
                    .setPropertyValue(INT_PROP, 0)      // constant
                    .setPropertyValue(ENUM_PROP, e));   // varies
        }

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        // Should have constant bool and int, with OR for enum values
        assertTrue(condition.contains("q.block_state('test_bool') == 1"));
        assertTrue(condition.contains("q.block_state('test_int') == 0"));
        assertTrue(condition.contains("'a'") || condition.contains("'b'") || condition.contains("'c'"));
    }

    @Test
    void testTwoVariableProperties() {
        // Both INT_PROP and ENUM_PROP vary, BOOL_PROP is constant
        var states = new HashSet<BlockState>();
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 0)
                .setPropertyValue(ENUM_PROP, TestEnum.A));
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 1)
                .setPropertyValue(ENUM_PROP, TestEnum.B));

        var condition = MolangConditionBuilder.buildCondition(states, blockTypeWithAllProps);

        // BOOL_PROP should be factored out as constant
        assertTrue(condition.contains("q.block_state('test_bool') == 1"));
        // Should contain OR for the variable properties
        assertTrue(condition.contains("||"));
    }
}
