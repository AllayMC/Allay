package org.allaymc.server.command;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.joml.Vector3d;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class CommandTreeTest {

    static Command mockCmd;
    static MockCommandSender mockSender;

    @BeforeAll
    static void init() {
        mockCmd = Mockito.mock(Command.class);
        Mockito.when(mockCmd.getName()).thenReturn("test");
        CommandNodeFactory.FACTORY.set(new AllayCommandNodeFactory());
        mockSender = new MockCommandSender();
    }

    @Nested
    class BasicNodeParsingTests {

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
            assertEquals(3.14f, result.get(), 0.01f);
        }

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
            assertEquals(3.14159265359, result.get(), 0.0001);
        }

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
    }

    @Nested
    class PositionNodeTests {

        @BeforeEach
        void resetLocation() {
            mockSender.getFakeLocation().set(0, 0, 0);
        }

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

    @Nested
    class OptionalNodeTests {

        @Test
        void testOptionalParameter() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .str("value")
                    .optional()
                    .exec(context -> "".equals(context.getResult(0)) ? context.success() : context.fail());

            assertTrue(tree.parse(mockSender, new String[0]).isSuccess());
        }

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

        @Test
        void testOnlyOneOptionalInLeaves() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().str("opt1").optional();

            assertThrows(IllegalArgumentException.class, () ->
                    tree.getRoot().intNum("opt2").optional()
            );
        }

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

    @Nested
    class RedirectTests {

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

    @Nested
    class TreeStructureTests {

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

    @Nested
    class ParsingFailureTests {

        @Test
        void testInvalidBoolValue() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().bool("value").exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"notabool"}).isSuccess());
        }

        @Test
        void testInvalidIntValue() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().intNum("value").exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"notanumber"}).isSuccess());
        }

        @Test
        void testInvalidEnumValue() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot().enums("value", "a", "b", "c").exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"d"}).isSuccess());
        }

        @Test
        void testMissingRequiredArgument() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("cmd")
                    .intNum("required")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"cmd"}).isSuccess());
        }

        @Test
        void testTooManyArguments() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("cmd")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"cmd", "extra"}).isSuccess());
        }

        @Test
        void testUnknownSubcommand() {
            var tree = AllayCommandTree.create(mockCmd);
            tree.getRoot()
                    .key("known")
                    .exec(CommandContext::success);

            assertFalse(tree.parse(mockSender, new String[]{"unknown"}).isSuccess());
        }
    }

    @Nested
    class DefaultValueTests {

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
}
