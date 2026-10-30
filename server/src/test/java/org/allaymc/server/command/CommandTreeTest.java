package org.allaymc.server.command;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3d;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code CommandTreeTest} exercises parameter conversion, optional defaults,
 * redirects, structural navigation, and failed parsing through the server
 * command tree. Nested groups build independent trees while sharing the
 * installed node factory, mock command, and mutable sender fixture.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class CommandTreeTest {

    /**
     * {@code mockCmd} is the shared command fixture named {@code "test"}. Every
     * case builds a fresh tree around it rather than mutating a shared tree.
     */
    static Command mockCmd;
    /**
     * {@code mockSender} supplies shared permissions and the mutable fake
     * execution origin. Position tests reset its coordinates before each case
     * to isolate relative-coordinate expectations.
     */
    static MockCommandSender mockSender;

    /**
     * Creates shared command and sender fixtures and installs the server node
     * factory for fluent builders. The factory holder is global test state,
     * while each test constructs its own tree and result capture.
     */
    @BeforeAll
    static void init() {
        mockCmd = Mockito.mock(Command.class);
        Mockito.when(mockCmd.getName()).thenReturn("test");
        // Fluent builders read a shared API holder, so install the server factory
        // before any nested case creates parameter nodes from its fresh command tree.
        CommandNodeFactory.FACTORY.set(new AllayCommandNodeFactory());
        mockSender = new MockCommandSender();
    }

    /**
     * {@code BasicNodeParsingTests} covers primitive, enumeration, literal, and
     * greedy parameter conversion through terminal executors.
     */
    @Nested
    class BasicNodeParsingTests {

        /**
         * Parses the two accepted boolean spellings through an executor that
         * returns their truth value as command status. The assertions protect
         * both boolean conversion and executor-visible results.
         */
        @Test
        void testBoolNode() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .bool("value")
                    .exec(context -> {
                        boolean v = context.getResult(0);
                        return v ? context.success() : context.fail();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"true"}).isSuccess());
            assertFalse(tree.parse(mockSender, new String[]{"false"}).isSuccess());
        }

        /**
         * Parses one ordinary string token into a captured executor result. The
         * assertion protects unchanged token delivery through the string node
         * and result index zero.
         */
        @Test
        void testStringNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .str("value")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"hello"});
            assertEquals("hello", result.get());
        }

        /**
         * Parses a decimal integer token and reads it through an integer result
         * capture. The assertion protects boxed numeric conversion and
         * retrieval at the first result slot.
         */
        @Test
        void testIntNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicInteger result = new AtomicInteger();
            tree.getRoot()
                    .intNum("value")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"42"});
            assertEquals(42, result.get());
        }

        /**
         * Parses an integer token whose value exceeds the signed 32-bit range.
         * The assertion protects long conversion without accidental narrowing
         * before the executor reads its result.
         */
        @Test
        void testLongNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Long> result = new AtomicReference<>();
            tree.getRoot()
                    .longNum("value")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"9999999999"});
            assertEquals(9999999999L, result.get());
        }

        /**
         * Parses a decimal token into a captured single-precision result. The
         * assertion protects delivery of the parsed float value through the
         * executor's first result slot for an ordinary decimal input.
         */
        @Test
        void testFloatNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Float> result = new AtomicReference<>();
            tree.getRoot()
                    .floatNum("value")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"3.14"});
            // This tolerance still rejects rounding the example to an integer.
            assertEquals(3.14f, result.get(), 0.01f);
        }

        /**
         * Parses a decimal token containing more precision than the float case.
         * The assertion protects delivery of the parsed double value through
         * the executor's first result slot.
         */
        @Test
        void testDoubleNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Double> result = new AtomicReference<>();
            tree.getRoot()
                    .doubleNum("value")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"3.14159265359"});
            // This tolerance still rejects reducing the example to two decimal places.
            assertEquals(3.14159265359, result.get(), 0.0001);
        }

        /**
         * Parses a decimal token into a captured short result. The assertion
         * protects the node's boxed short representation rather than silently
         * delivering an integer value to the executor.
         */
        @Test
        void testShortNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Short> result = new AtomicReference<>();
            tree.getRoot()
                    .shortNum("value")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"100"});
            assertEquals((short) 100, result.get());
        }

        /**
         * Configures three accepted enumeration spellings and parses accepted
         * and unknown values. The assertions protect successful string results
         * for known spellings and rejection of a token outside the configured
         * set.
         */
        @Test
        void testEnumsNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .enums("value", "a", "b", "c")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"a"}).isSuccess());
            assertEquals("a", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"b"}).isSuccess());
            assertEquals("b", result.get());

            assertFalse(tree.parse(mockSender, new String[]{"d"}).isSuccess());
        }

        /**
         * Builds a literal subcommand followed by an integer parameter. The
         * assertions protect literal-key discrimination and the result-slot
         * offset caused by the key contributing its own parsed value.
         */
        @Test
        void testKeyNode() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("start")
                    .intNum("value")
                    .exec(context -> {
                        int v = context.getResult(1);
                        return v == 123 ? context.success() : context.fail();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"start", "123"}).isSuccess());
            assertFalse(tree.parse(mockSender, new String[]{"wrong", "123"}).isSuccess());
        }

        /**
         * Passes several tokens to a terminal message parameter and captures
         * its result. The assertion protects greedy consumption and joining
         * with one separating space between the supplied words.
         */
        @Test
        void testMsgNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .msg("message")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"hello", "world", "test"});
            assertEquals("hello world test", result.get());
        }

        /**
         * Places a greedy remaining-token parameter after a literal command
         * key. The assertion protects preservation of individual token values
         * and retrieval from the result slot following the key.
         */
        @Test
        void testRemainNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<List<String>> result = new AtomicReference<>();
            tree.getRoot()
                    .key("cmd")
                    .remain("args")
                    .exec(context -> {
                        result.set(context.getResult(1));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"cmd", "arg1", "arg2", "arg3"});
            assertEquals(List.of("arg1", "arg2", "arg3"), result.get());
        }

        /**
         * Parses differently cased forms of accepted enumeration values and one
         * unknown spelling. The assertions protect case-insensitive acceptance
         * while preserving the caller's original token casing in the executor
         * result.
         */
        @Test
        void testEnumsIgnoreCaseNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .enumsIgnoreCase("value", "", new String[]{"alpha", "beta", "gamma"})
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"alpha"}).isSuccess());
            assertEquals("alpha", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"ALPHA"}).isSuccess());
            assertEquals("ALPHA", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"AlPhA"}).isSuccess());
            assertEquals("AlPhA", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"Beta"}).isSuccess());
            assertEquals("Beta", result.get());

            assertFalse(tree.parse(mockSender, new String[]{"Delta"}).isSuccess());
        }

        /**
         * Configures a node from the test enum and supplies names with several
         * casing patterns. The assertions protect conversion back to actual
         * enum constants and rejection of an undeclared constant name.
         */
        @Test
        void testEnumClassNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<TestColor> result = new AtomicReference<>();
            tree.getRoot()
                    .enumClass("color", TestColor.class)
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"red"}).isSuccess());
            assertEquals(TestColor.RED, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"GREEN"}).isSuccess());
            assertEquals(TestColor.GREEN, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"Blue"}).isSuccess());
            assertEquals(TestColor.BLUE, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"yellow"}).isSuccess());
        }

        /**
         * Makes an enum-class node optional with a configured green default.
         * The assertions protect omission delivering that constant and explicit
         * input replacing it with the matching blue constant.
         */
        @Test
        void testEnumClassNodeWithDefault() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<TestColor> result = new AtomicReference<>();
            tree.getRoot()
                    .enumClass("color", TestColor.GREEN, TestColor.class)
                    .optional()
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[0]);
            assertEquals(TestColor.GREEN, result.get());

            tree.parse(mockSender, new String[]{"blue"});
            assertEquals(TestColor.BLUE, result.get());
        }

        /**
         * Parses selector-shaped, player-name, and wildcard text through a
         * wildcard-target node. The assertions protect unchanged text results
         * without requiring entity resolution or validating the supplied target
         * expression.
         */
        @Test
        void testWildcardTargetNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .wildcardTarget("target")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"@a"}).isSuccess());
            assertEquals("@a", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"@e[type=zombie]"}).isSuccess());
            assertEquals("@e[type=zombie]", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"PlayerName"}).isSuccess());
            assertEquals("PlayerName", result.get());

            assertTrue(tree.parse(mockSender, new String[]{"*"}).isSuccess());
            assertEquals("*", result.get());
        }

        /**
         * Supplies two ordinary command token sequences to a greedy
         * command-text node. The assertions protect reconstruction as
         * space-separated strings for executor use, without claiming coverage
         * of quoted-token round trips.
         */
        @Test
        void testCmdNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .cmd("command")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"say", "hello", "world"});
            assertEquals("say hello world", result.get());

            tree.parse(mockSender, new String[]{"gamemode", "creative"});
            assertEquals("gamemode creative", result.get());
        }

        /**
         * Parses every configured numeric, full-name, and abbreviated game-mode
         * alias. The assertions protect their mapped enum values, including the
         * command node's spectator aliases, and rejection of an unknown mode.
         */
        @Test
        void testGameModeNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<GameMode> result = new AtomicReference<>();
            tree.getRoot()
                    .gameMode("mode")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"survival"}).isSuccess());
            assertEquals(GameMode.SURVIVAL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"s"}).isSuccess());
            assertEquals(GameMode.SURVIVAL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"0"}).isSuccess());
            assertEquals(GameMode.SURVIVAL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"creative"}).isSuccess());
            assertEquals(GameMode.CREATIVE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"c"}).isSuccess());
            assertEquals(GameMode.CREATIVE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"1"}).isSuccess());
            assertEquals(GameMode.CREATIVE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"adventure"}).isSuccess());
            assertEquals(GameMode.ADVENTURE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"a"}).isSuccess());
            assertEquals(GameMode.ADVENTURE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"2"}).isSuccess());
            assertEquals(GameMode.ADVENTURE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"spectator"}).isSuccess());
            assertEquals(GameMode.SPECTATOR, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"spc"}).isSuccess());
            assertEquals(GameMode.SPECTATOR, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"3"}).isSuccess());
            assertEquals(GameMode.SPECTATOR, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid"}).isSuccess());
        }

        /**
         * Parses every configured numeric, full-name, and single-letter
         * difficulty alias. The assertions protect each mapped difficulty value
         * and ensure an unrelated token fails command parsing.
         */
        @Test
        void testDifficultyNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Difficulty> result = new AtomicReference<>();
            tree.getRoot()
                    .difficulty("diff")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"peaceful"}).isSuccess());
            assertEquals(Difficulty.PEACEFUL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"p"}).isSuccess());
            assertEquals(Difficulty.PEACEFUL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"0"}).isSuccess());
            assertEquals(Difficulty.PEACEFUL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"easy"}).isSuccess());
            assertEquals(Difficulty.EASY, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"e"}).isSuccess());
            assertEquals(Difficulty.EASY, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"1"}).isSuccess());
            assertEquals(Difficulty.EASY, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"normal"}).isSuccess());
            assertEquals(Difficulty.NORMAL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"n"}).isSuccess());
            assertEquals(Difficulty.NORMAL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"2"}).isSuccess());
            assertEquals(Difficulty.NORMAL, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"hard"}).isSuccess());
            assertEquals(Difficulty.HARD, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"h"}).isSuccess());
            assertEquals(Difficulty.HARD, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"3"}).isSuccess());
            assertEquals(Difficulty.HARD, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid"}).isSuccess());
        }

        /**
         * {@code TestColor} supplies distinct enum constants for name
         * conversion and omission-default tests.
         */
        enum TestColor {
            /**
             * Red conversion test value.
             */
            RED,
            /**
             * Green omission-default test value.
             */
            GREEN,
            /**
             * Blue conversion test value.
             */
            BLUE
        }
    }

    /**
     * {@code PositionNodeTests} covers absolute and relative coordinate triples
     * using a resettable sender-origin fixture.
     */
    @Nested
    class PositionNodeTests {

        /**
         * Resets the shared sender's coordinates to the origin before each
         * position case. The location object itself remains shared so each test
         * can set a distinct base for relative-coordinate parsing.
         */
        @BeforeEach
        void resetLocation() {
            mockSender.getFakeLocation().set(0, 0, 0);
        }

        /**
         * Parses a complete three-token position from the reset origin. The
         * assertion protects ordered coordinate assembly into one vector result
         * without applying a relative offset to absolute values.
         */
        @Test
        void testAbsolutePosition() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Vector3d> result = new AtomicReference<>();
            tree.getRoot()
                    .pos("pos")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"11", "45", "14"});
            assertEquals(new Vector3d(11, 45, 14), result.get());
        }

        /**
         * Sets a nonzero sender origin and supplies positive tilde offsets on
         * all axes. The assertion protects adding each offset to its
         * corresponding base coordinate rather than treating it as absolute
         * input.
         */
        @Test
        void testRelativePosition() {
            mockSender.getFakeLocation().set(10, 20, 30);

            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Vector3d> result = new AtomicReference<>();
            tree.getRoot()
                    .pos("pos")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"~1", "~2", "~3"});
            assertEquals(new Vector3d(11, 22, 33), result.get());
        }

        /**
         * Sets a nonzero sender origin and supplies negative tilde offsets on
         * all axes. The assertion protects subtraction from each corresponding
         * coordinate and preservation of coordinate order in the vector.
         */
        @Test
        void testNegativeRelativePosition() {
            mockSender.getFakeLocation().set(10, 20, 30);

            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Vector3d> result = new AtomicReference<>();
            tree.getRoot()
                    .pos("pos")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"~-1", "~-2", "~-3"});
            assertEquals(new Vector3d(9, 18, 27), result.get());
        }

        /**
         * Combines a nonzero sender origin with fractional tilde offsets. The
         * assertion protects decimal offset parsing and addition on each axis
         * before the executor receives the vector result.
         */
        @Test
        void testRelativePositionWithDecimals() {
            mockSender.getFakeLocation().set(10, 20, 30);

            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<Vector3d> result = new AtomicReference<>();
            tree.getRoot()
                    .pos("pos")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[]{"~0.5", "~1.5", "~2.5"});
            assertEquals(new Vector3d(10.5, 21.5, 32.5), result.get());
        }
    }

    /**
     * {@code OptionalNodeTests} covers omission defaults, optional-child
     * constraints, and coexistence with other argument branches.
     */
    @Nested
    class OptionalNodeTests {

        /**
         * Marks a string parameter optional and invokes its tree without
         * arguments. The assertion protects selection of the string node's
         * empty default rather than reporting missing required input.
         */
        @Test
        void testOptionalParameter() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .str("value")
                    .optional()
                    .exec(context -> "".equals(context.getResult(0)) ? context.success() : context.fail());

            assertTrue(tree.parse(mockSender, new String[0]).isSuccess());
        }

        /**
         * Configures an optional enumeration with a default outside its
         * accepted explicit spellings. The assertions protect omission
         * bypassing enumeration matching and explicit accepted input replacing
         * the stored default.
         */
        @Test
        void testOptionalWithDefaultValue() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .enums("value", "default", new String[]{"a", "b", "c"})
                    .optional()
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[0]);
            assertEquals("default", result.get());

            tree.parse(mockSender, new String[]{"a"});
            assertEquals("a", result.get());
        }

        /**
         * Registers one optional child and then attempts to mark a sibling
         * optional. The assertion protects the single optional-child constraint
         * required for an unambiguous end-of-input default path.
         */
        @Test
        void testOnlyOneOptionalInLeaves() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().str("opt1").optional();

            assertThrows(IllegalArgumentException.class, () ->
                    tree.getRoot().intNum("opt2").optional()
            );
        }

        /**
         * Attempts both permission addition after optionality and optionality
         * after permission addition. The assertions protect rejection in either
         * configuration order so an optional node cannot carry a permission
         * requirement.
         */
        @Test
        void testOptionalCannotHavePermission() {
            var tree = AllayCommandTree.create(mockCmd);

            assertThrows(IllegalArgumentException.class, () ->
                    tree.getRoot().intNum("opt").optional().permission("test.perm")
            );
            assertThrows(IllegalArgumentException.class, () ->
                    tree.getRoot().str("opt2").permission("test.perm").optional()
            );
        }

        /**
         * Builds optional-integer and required-boolean sibling paths with
         * different executor outcomes. The assertions protect selection from
         * actual input rather than letting optionality consume a token that
         * belongs to another matching type.
         */
        @Test
        void testNormalParamWithOptionalParam() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .intNum("optional")
                    .optional()
                    .exec(CommandContext::fail)
                    .root()
                    .bool("normal")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"1"}).isSuccess());
            assertTrue(tree.parse(mockSender, new String[]{"true"}).isSuccess());
        }

        /**
         * Builds two consecutive optional enumeration nodes with separate
         * defaults. The assertions protect successful explicit traversal and
         * complete omission producing a result slot for each node's configured
         * default.
         */
        @Test
        void testDoubleOptionalNode() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .enums("opt1", "d1", new String[]{"a", "b", "c"})
                    .optional()
                    .enums("opt2", "d2", new String[]{"d", "e", "f"})
                    .optional()
                    .exec(context -> {
                        var r1 = context.getResult(0);
                        var r2 = context.getResult(1);
                        if (r1.equals("a") && r2.equals("d")) return context.success();
                        if (r1.equals("d1") && r2.equals("d2")) return context.success();
                        return context.fail();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"a", "d"}).isSuccess());
            assertTrue(tree.parse(mockSender, new String[]{}).isSuccess());
        }

        /**
         * Builds optional and longer required literal paths sharing an initial
         * prefix. The assertions protect the accepted complete forms and
         * rejection of a shortened required form under argument-count branch
         * selection.
         */
        @Test
        void testComplexOptionalNode() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("a").optional()
                    .key("b").optional()
                    .exec(CommandContext::success)
                    .root()
                    .key("a").key("b").key("c").key("d")
                    .exec(CommandContext::success);

            assertTrue(tree.parse(mockSender, new String[]{"a", "b"}).isSuccess());
            assertTrue(tree.parse(mockSender, new String[]{"a", "b", "c", "d"}).isSuccess());
            assertFalse(tree.parse(mockSender, new String[]{"a", "b", "c"}).isSuccess());
        }
    }

    /**
     * {@code RedirectTests} covers remaining-input traversal and callback
     * ordering across terminal root redirects.
     */
    @Nested
    class RedirectTests {

        /**
         * Builds two root-redirecting key-and-value paths and supplies them in
         * both orders. The captured values protect continued argument
         * consumption and fresh result indexing each time parsing restarts at
         * the root.
         */
        @Test
        void testRedirect() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicInteger v1 = new AtomicInteger();
            AtomicInteger v2 = new AtomicInteger();

            tree.getRoot()
                    .key("v1")
                    .intNum("value")
                    .redirect(context -> v1.set(context.getResult(1)))
                    .key("v2")
                    .intNum("value")
                    .redirect(context -> v2.set(context.getResult(1)));

            tree.parse(mockSender, new String[]{"v1", "1", "v2", "2"});
            assertEquals(1, v1.get());
            assertEquals(2, v2.get());

            tree.parse(mockSender, new String[]{"v2", "1", "v1", "2"});
            assertEquals(2, v1.get());
            assertEquals(1, v2.get());
        }

        /**
         * Chains two redirect callbacks before a final executor on distinct
         * root keys. The assertion protects callback ordering and continued
         * traversal to the executor using the remaining input.
         */
        @Test
        void testMultipleRedirects() {
            var tree = AllayCommandTree.create(mockCmd);
            StringBuilder order = new StringBuilder();

            tree.getRoot()
                    .key("a")
                    .redirect(ctx -> order.append("A"))
                    .key("b")
                    .redirect(ctx -> order.append("B"))
                    .key("c")
                    .exec(ctx -> {
                        order.append("C");
                        return ctx.success();
                    });

            tree.parse(mockSender, new String[]{"a", "b", "c"});
            assertEquals("ABC", order.toString());
        }
    }

    /**
     * {@code TreeStructureTests} covers terminal discovery, root execution,
     * ancestry navigation, and separate command branches.
     */
    @Nested
    class TreeStructureTests {

        /**
         * Builds two separate parameter paths that each terminate in an
         * executor. The assertion protects discovery of terminal nodes rather
         * than counting intermediate structural nodes as completed command
         * overloads.
         */
        @Test
        void testGetLeaves() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("branch1")
                    .str("value")
                    .exec(CommandContext::success)
                    .root()
                    .key("branch2")
                    .intNum("value")
                    .exec(CommandContext::success);

            var leaves = tree.getLeaves();
            assertEquals(2, leaves.size());
        }

        /**
         * Installs an executor directly on an otherwise empty root and supplies
         * no arguments. The assertion protects execution of a root that is
         * itself the terminal node.
         */
        @Test
        void testRootExec() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicBoolean rootExecuted = new AtomicBoolean(false);

            tree.getRoot().exec(ctx -> {
                rootExecuted.set(true);
                return ctx.success();
            });

            tree.parse(mockSender, new String[0]);
            assertTrue(rootExecuted.get());
        }

        /**
         * Installs a root executor and then attaches a child before parsing
         * empty input. The assertion protects terminal-only executor dispatch,
         * since the root ceases to be a leaf after attachment.
         */
        @Test
        void testRootExecNotCalledWithChildren() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicBoolean rootExecuted = new AtomicBoolean(false);

            tree.getRoot().exec(ctx -> {
                rootExecuted.set(true);
                return ctx.success();
            });
            tree.getRoot().key("subcommand");

            tree.parse(mockSender, new String[0]);
            assertFalse(rootExecuted.get());
        }

        /**
         * Builds a three-level literal path beneath the root. The assertions
         * protect assigned depths, immediate parents, multi-level ancestry,
         * root lookup, and root identity checks on the resulting structure.
         */
        @Test
        void testNodeNavigation() {
            var tree = AllayCommandTree.create(mockCmd);
            var root = tree.getRoot();
            var child1 = root.key("level1");
            var child2 = child1.key("level2");
            var child3 = child2.key("level3");

            assertEquals(0, root.depth());
            assertEquals(1, child1.depth());
            assertEquals(2, child2.depth());
            assertEquals(3, child3.depth());

            assertSame(child2, child3.parent());
            assertSame(child1, child3.up(2));
            assertSame(root, child3.root());
            assertTrue(root.isRoot());
            assertFalse(child1.isRoot());
        }

        /**
         * Builds addition and subtraction paths with identical numeric
         * parameter shapes beneath different keys. The assertions protect
         * key-based branch discrimination and retrieval of operand results
         * after the literal-key result.
         */
        @Test
        void testMultipleBranches() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("add")
                    .intNum("a").intNum("b")
                    .exec(ctx -> {
                        int a = ctx.getResult(1);
                        int b = ctx.getResult(2);
                        return a + b == 3 ? ctx.success() : ctx.fail();
                    })
                    .root()
                    .key("sub")
                    .intNum("a").intNum("b")
                    .exec(ctx -> {
                        int a = ctx.getResult(1);
                        int b = ctx.getResult(2);
                        return a - b == 1 ? ctx.success() : ctx.fail();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"add", "1", "2"}).isSuccess());
            assertTrue(tree.parse(mockSender, new String[]{"sub", "2", "1"}).isSuccess());
        }
    }

    /**
     * {@code ParsingFailureTests} covers invalid tokens, missing required
     * input, extra arguments, and unknown subcommands.
     */
    @Nested
    class ParsingFailureTests {

        /**
         * Supplies an unrelated word to a boolean parameter with a success
         * executor. The assertion protects rejection before execution when the
         * token is neither accepted boolean spelling.
         */
        @Test
        void testInvalidBoolValue() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().bool("value").exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"notabool"}).isSuccess());
        }

        /**
         * Supplies nonnumeric text to an integer parameter with a success
         * executor. The assertion protects numeric mismatch becoming a failed
         * parse instead of reaching the configured executor.
         */
        @Test
        void testInvalidIntValue() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().intNum("value").exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"notanumber"}).isSuccess());
        }

        /**
         * Supplies a token outside a configured three-value enumeration. The
         * assertion protects rejection of unknown spellings even when the
         * terminal executor would otherwise report success.
         */
        @Test
        void testInvalidEnumValue() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().enums("value", "a", "b", "c").exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"d"}).isSuccess());
        }

        /**
         * Supplies only the literal key for a path requiring a following
         * integer. The assertion protects failure when the tree cannot select
         * the required child at end-of-input.
         */
        @Test
        void testMissingRequiredArgument() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("cmd")
                    .intNum("required")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"cmd"}).isSuccess());
        }

        /**
         * Supplies an extra token after a literal node with a terminal
         * executor. The assertion protects leftover-input rejection before the
         * executor can return its configured successful result.
         */
        @Test
        void testTooManyArguments() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("cmd")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"cmd", "extra"}).isSuccess());
        }

        /**
         * Supplies a root token different from the tree's only literal key. The
         * assertion protects an unmatched subcommand producing a failed parse
         * rather than invoking the known path's executor.
         */
        @Test
        void testUnknownSubcommand() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("known")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"unknown"}).isSuccess());
        }
    }

    /**
     * {@code DefaultValueTests} covers configured primitive and string defaults
     * when optional input is omitted.
     */
    @Nested
    class DefaultValueTests {

        /**
         * Configures an optional integer parameter with a nonzero default, then
         * parses omitted and explicit input. The assertions protect default
         * insertion and replacement by the explicitly parsed integer.
         */
        @Test
        void testIntDefaultValue() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicInteger result = new AtomicInteger();
            tree.getRoot()
                    .intNum("value", 42)
                    .optional()
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[0]);
            assertEquals(42, result.get());

            tree.parse(mockSender, new String[]{"100"});
            assertEquals(100, result.get());
        }

        /**
         * Configures an optional boolean parameter whose default is
         * {@code true} and supplies no arguments. The assertion protects
         * insertion of the configured value rather than the ordinary
         * {@code false} default.
         */
        @Test
        void testBoolDefaultValue() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicBoolean result = new AtomicBoolean();
            tree.getRoot()
                    .bool("value", true)
                    .optional()
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[0]);
            assertTrue(result.get());
        }

        /**
         * Configures an optional string parameter with a nonempty default and
         * supplies no arguments. The assertion protects delivery of that exact
         * default string to the terminal executor.
         */
        @Test
        void testStringDefaultValue() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<String> result = new AtomicReference<>();
            tree.getRoot()
                    .str("value", "default")
                    .optional()
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            tree.parse(mockSender, new String[0]);
            assertEquals("default", result.get());
        }
    }

    /**
     * {@code RegistryDependentNodeTests} covers registry-backed type conversion
     * and block-property argument parsing through real registrations.
     */
    @Nested
    class RegistryDependentNodeTests {

        /**
         * Parses several registered effect paths and one unknown path through
         * an effect node. The assertions protect conversion to registered
         * effect objects and rejection of an unadvertised effect spelling.
         */
        @Test
        void testEffectNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<EffectType> result = new AtomicReference<>();
            tree.getRoot()
                    .effect("effect")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"speed"}).isSuccess());
            assertEquals(EffectTypes.SPEED, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"regeneration"}).isSuccess());
            assertEquals(EffectTypes.REGENERATION, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"night_vision"}).isSuccess());
            assertEquals(EffectTypes.NIGHT_VISION, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid_effect"}).isSuccess());
        }

        /**
         * Parses bare and fully namespaced enchantment identifiers plus an
         * unknown spelling. The assertions protect default-namespace lookup,
         * explicit identifier lookup, and conversion into the corresponding
         * registered enchantment objects.
         */
        @Test
        void testEnchantmentNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<EnchantmentType> result = new AtomicReference<>();
            tree.getRoot()
                    .enchantment("enchant")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"sharpness"}).isSuccess());
            assertEquals(EnchantmentTypes.SHARPNESS, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"protection"}).isSuccess());
            assertEquals(EnchantmentTypes.PROTECTION, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"minecraft:efficiency"}).isSuccess());
            assertEquals(EnchantmentTypes.EFFICIENCY, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid_enchant"}).isSuccess());
        }

        /**
         * Parses bare and fully namespaced item identifiers plus an unknown
         * item. The assertions protect default-namespace expansion, direct
         * registry lookup, and rejection of a missing item type.
         */
        @Test
        void testItemTypeNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<ItemType<?>> result = new AtomicReference<>();
            tree.getRoot()
                    .itemType("item")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"diamond_sword"}).isSuccess());
            assertEquals(ItemTypes.DIAMOND_SWORD, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"minecraft:apple"}).isSuccess());
            assertEquals(ItemTypes.APPLE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"stone"}).isSuccess());
            assertEquals(ItemTypes.STONE, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid_item"}).isSuccess());
        }

        /**
         * Parses bare and fully namespaced block identifiers plus an unknown
         * block. The assertions protect default-namespace expansion, direct
         * registry lookup, and rejection of a missing block type.
         */
        @Test
        void testBlockTypeNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<BlockType<?>> result = new AtomicReference<>();
            tree.getRoot()
                    .blockType("block")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"stone"}).isSuccess());
            assertEquals(BlockTypes.STONE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"minecraft:dirt"}).isSuccess());
            assertEquals(BlockTypes.DIRT, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"oak_planks"}).isSuccess());
            assertEquals(BlockTypes.OAK_PLANKS, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid_block"}).isSuccess());
        }

        /**
         * Parses bare and fully namespaced entity identifiers plus an unknown
         * entity. The assertions protect current entity-registry resolution and
         * default-namespace expansion before the executor receives its type
         * result.
         */
        @Test
        void testEntityTypeNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<EntityType<?>> result = new AtomicReference<>();
            tree.getRoot()
                    .entityType("entity")
                    .exec(context -> {
                        result.set(context.getResult(0));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"zombie"}).isSuccess());
            assertEquals(EntityTypes.ZOMBIE, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"minecraft:creeper"}).isSuccess());
            assertEquals(EntityTypes.CREEPER, result.get());

            assertTrue(tree.parse(mockSender, new String[]{"pig"}).isSuccess());
            assertEquals(EntityTypes.PIG, result.get());

            assertFalse(tree.parse(mockSender, new String[]{"invalid_entity"}).isSuccess());
        }

        /**
         * Parses empty properties, a valid torch assignment, and an unbracketed
         * property token after a block type. The assertions protect the
         * empty-list convention, successful property conversion, and rejection
         * of missing bracket syntax.
         */
        @Test
        void testBlockPropertyValuesNode() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<BlockType<?>> blockResult = new AtomicReference<>();
            AtomicReference<List<BlockPropertyType.BlockPropertyValue<?, ?, ?>>> propertyResult = new AtomicReference<>();
            tree.getRoot()
                    .blockType("block")
                    .blockPropertyValues("properties")
                    .exec(context -> {
                        blockResult.set(context.getResult(0));
                        propertyResult.set(context.getResult(1));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"stone", "[]"}).isSuccess());
            assertEquals(BlockTypes.STONE, blockResult.get());
            assertTrue(propertyResult.get().isEmpty());

            assertTrue(tree.parse(mockSender, new String[]{"torch", "[torch_facing_direction=top]"}).isSuccess());
            assertEquals(BlockTypes.TORCH, blockResult.get());
            assertFalse(propertyResult.get().isEmpty());

            assertFalse(tree.parse(mockSender, new String[]{"stone", "invalid"}).isSuccess());
        }

        /**
         * Supplies a torch property assignment with quoted name and value
         * inside brackets. The assertions protect quote removal before property
         * lookup and conversion into a nonempty result list.
         */
        @Test
        void testBlockPropertyValuesNodeWithQuotedValues() {
            var tree = AllayCommandTree.create(mockCmd);
            AtomicReference<List<BlockPropertyType.BlockPropertyValue<?, ?, ?>>> propertyResult = new AtomicReference<>();
            tree.getRoot()
                    .blockType("block")
                    .blockPropertyValues("properties")
                    .exec(context -> {
                        propertyResult.set(context.getResult(1));
                        return context.success();
                    });

            assertTrue(tree.parse(mockSender, new String[]{"torch", "[\"torch_facing_direction\"=\"top\"]"}).isSuccess());
            assertFalse(propertyResult.get().isEmpty());
        }
    }
}
