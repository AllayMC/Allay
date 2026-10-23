package org.allaymc.server.utils;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.type.TestBlock;
import org.allaymc.server.block.type.TestEnum;
import org.allaymc.testutils.AllayTestExtension;
import org.allaymc.testutils.TestRegistryFixtures;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code MolangUtilsTest} verifies Molang property formatting and state-group condition
 * construction against registered fixture blocks.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class MolangUtilsTest {

    // BOOL_PROP, INT_PROP, ENUM_PROP, blockTypeWithAllProps, and blockTypeWithBoolOnly
    // retain the registered fixture definitions assigned by init before any condition-building test executes.
    /**
     * Boolean fixture state property.
     */
    static BlockPropertyType<Boolean> BOOL_PROP;
    /**
     * Integer fixture state property.
     */
    static BlockPropertyType<Integer> INT_PROP;
    /**
     * Enum fixture state property.
     */
    static BlockPropertyType<TestEnum> ENUM_PROP;
    /**
     * Three-property fixture block type.
     */
    static BlockType<TestBlock> blockTypeWithAllProps;
    /**
     * Boolean-only fixture block type.
     */
    static BlockType<TestBlock> blockTypeWithBoolOnly;

    /**
     * Retains registered property and block fixtures before constructing test state groups.
     */
    @BeforeAll
    static void init() {
        BOOL_PROP = TestRegistryFixtures.molangBooleanProperty();
        INT_PROP = TestRegistryFixtures.molangIntProperty();
        ENUM_PROP = TestRegistryFixtures.molangEnumProperty();
        blockTypeWithAllProps = TestRegistryFixtures.molangBlockTypeWithAllProperties();
        blockTypeWithBoolOnly = TestRegistryFixtures.molangBlockTypeWithBooleanOnly();
    }

    /**
     * Builds a condition for a default {@code false} boolean state. The expression must use a negated
     * block-state query rather than comparing the boolean to numeric or quoted text.
     */
    @Test
    void testSingleStateBooleanCondition() {
        var state = blockTypeWithBoolOnly.getDefaultState();
        var states = Set.of(state);

        var condition = MolangUtils.buildCondition(states, blockTypeWithBoolOnly);

        assertEquals("!q.block_state('test_bool')", condition);
    }

    /**
     * Builds a condition after changing the fixture boolean to {@code true}. The expression must use the
     * direct block-state query without the {@code false}-state negation prefix.
     */
    @Test
    void testSingleStateBooleanTrueCondition() {
        var state = blockTypeWithBoolOnly.getDefaultState().setPropertyValue(BOOL_PROP, true);
        var states = Set.of(state);

        var condition = MolangUtils.buildCondition(states, blockTypeWithBoolOnly);

        assertEquals("q.block_state('test_bool')", condition);
    }

    /**
     * Builds a condition from boolean, integer, and enum values in one state. The result must
     * include each property check joined as a conjunction, with lowercase enum text.
     */
    @Test
    void testMultiplePropertiesCondition() {
        var state = blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 2)
                .setPropertyValue(ENUM_PROP, TestEnum.B);
        var states = Set.of(state);

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_bool')"));
        assertTrue(condition.contains("q.block_state('test_int') == 2"));
        assertTrue(condition.contains("q.block_state('test_enum') == 'b'"));
        assertTrue(condition.contains("&&"));
    }

    /**
     * Builds a condition for both values of one boolean property. The resulting text must include
     * a disjunction containing the direct and negated boolean query forms.
     */
    @Test
    void testMultipleStatesWithSameProperty() {
        var state1 = blockTypeWithBoolOnly.getDefaultState().setPropertyValue(BOOL_PROP, false);
        var state2 = blockTypeWithBoolOnly.getDefaultState().setPropertyValue(BOOL_PROP, true);
        var states = Set.of(state1, state2);

        var condition = MolangUtils.buildCondition(states, blockTypeWithBoolOnly);

        assertTrue(condition.contains("||"));
        assertTrue(condition.contains("!q.block_state('test_bool')"));
        assertTrue(condition.contains("q.block_state('test_bool')"));
    }

    /**
     * Builds a condition from an empty state set. The helper must return the unconditional
     * expression rather than an empty disjunction or an expression rejecting all states.
     */
    @Test
    void testEmptyStatesReturnsAlwaysTrue() {
        var states = Set.<BlockState>of();

        var condition = MolangUtils.buildCondition(states, blockTypeWithBoolOnly);

        assertEquals("1", condition);
    }

    /**
     * Builds a group whose integer varies while boolean and enum values stay fixed. The generated
     * text must retain both fixed property checks in the resulting condition.
     */
    @Test
    void testOptimizationWithConstantProperty() {
        var states = new HashSet<BlockState>();
        for (int i = 0; i <= 3; i++) {
            states.add(blockTypeWithAllProps.getDefaultState()
                    .setPropertyValue(BOOL_PROP, true)
                    .setPropertyValue(INT_PROP, i)
                    .setPropertyValue(ENUM_PROP, TestEnum.A));
        }

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_bool')"));
        assertTrue(condition.contains("q.block_state('test_enum') == 'a'"));
    }

    /**
     * Builds a condition for a state with integer value two. The resulting text must include that
     * numeric comparison without quoting the integer as an enum string.
     */
    @Test
    void testSingleStateIntCondition() {
        var state = blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(INT_PROP, 2);
        var states = Set.of(state);

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_int') == 2"));
    }

    /**
     * Builds a condition for a state using the third enum value. The resulting comparison must
     * render the enum literal in lowercase for the client expression.
     */
    @Test
    void testSingleStateEnumCondition() {
        var state = blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(ENUM_PROP, TestEnum.C);
        var states = Set.of(state);

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_enum') == 'c'"));
    }

    /**
     * Builds a condition for the fixture type with no state properties. The result must be the
     * unconditional expression because no property check can distinguish that state.
     */
    @Test
    void testBlockWithNoProperties() {
        var blockTypeNoProps = TestRegistryFixtures.molangBlockTypeWithoutProperties();

        var state = blockTypeNoProps.getDefaultState();
        var states = Set.of(state);

        var condition = MolangUtils.buildCondition(states, blockTypeNoProps);

        assertEquals("1", condition);
    }

    /**
     * Builds a group where boolean, integer, and enum properties all vary. The generated text must
     * retain a disjunction representing the alternatives even without common property checks.
     */
    @Test
    void testAllPropertiesVaryingNoOptimization() {
        var states = new HashSet<BlockState>();
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, false)
                .setPropertyValue(INT_PROP, 0)
                .setPropertyValue(ENUM_PROP, TestEnum.A));
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 1)
                .setPropertyValue(ENUM_PROP, TestEnum.B));

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("||"));
    }

    /**
     * Builds a group spanning enum values with fixed boolean and integer properties. The text must
     * retain the fixed checks and at least one observed enum literal.
     */
    @Test
    void testMultipleEnumValues() {
        var states = new HashSet<BlockState>();
        for (TestEnum e : TestEnum.values()) {
            states.add(blockTypeWithAllProps.getDefaultState()
                    .setPropertyValue(BOOL_PROP, true)
                    .setPropertyValue(INT_PROP, 0)
                    .setPropertyValue(ENUM_PROP, e));
        }

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_bool')"));
        assertTrue(condition.contains("q.block_state('test_int') == 0"));
        assertTrue(condition.contains("'a'") || condition.contains("'b'") || condition.contains("'c'"));
    }

    /**
     * Builds a group with fixed boolean state and varying integer/enum pairs. The generated text
     * must include both the fixed boolean check and a disjunction for the variable alternatives.
     */
    @Test
    void testTwoVariableProperties() {
        var states = new HashSet<BlockState>();
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 0)
                .setPropertyValue(ENUM_PROP, TestEnum.A));
        states.add(blockTypeWithAllProps.getDefaultState()
                .setPropertyValue(BOOL_PROP, true)
                .setPropertyValue(INT_PROP, 1)
                .setPropertyValue(ENUM_PROP, TestEnum.B));

        var condition = MolangUtils.buildCondition(states, blockTypeWithAllProps);

        assertTrue(condition.contains("q.block_state('test_bool')"));
        assertTrue(condition.contains("||"));
    }
}
