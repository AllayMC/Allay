package org.allaymc.server.command;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.joml.Vector3f;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class CommandTreeTest {

    static Command mockCmd = Mockito.mock(Command.class);
    static CommandSender mockSender = Mockito.mock(CommandSender.class);
    static Location3f cmdExecLoc = new Location3f(0, 0, 0, null);

    @BeforeAll
    static void init() {
        Mockito.when(mockCmd.getName()).thenReturn("test");
        Mockito.when(mockSender.getCmdExecuteLocation()).thenReturn(cmdExecLoc);
        CommandNodeFactory.FACTORY.set(new AllayCommandNodeFactory());
    }

    @Test
    void testSimpleParse() {
        var tree = AllayCommandTree.create(mockCmd);
        tree.getRoot()
                .key("test_bool")
                .bool("bool")
                .exec(context -> {
                    boolean bool = context.getResult(1);
                    return bool ? context.success() : context.fail();
                })
                .root()
                .key("test_str")
                .str("str")
                .exec(CommandContext::success)
                .root()
                .key("test_enums")
                .enums("enums", "a", "b", "c", "d")
                .exec(CommandContext::success)
                .root()
                .key("test_int")
                .intNum("int")
                .exec(context -> {
                    int i = context.getResult(1);
                    return i == 1 ? context.success() : context.fail();
                });

        var res = tree.parse(mockSender, new String[]{"test_bool", "true"});
        assertTrue(res.isSuccess());
        res = tree.parse(mockSender, new String[]{"test_str", "a"});
        assertTrue(res.isSuccess());
        res = tree.parse(mockSender, new String[]{"test_enums", "b"});
        assertTrue(res.isSuccess());
        res = tree.parse(mockSender, new String[]{"test_int", "1"});
        assertTrue(res.isSuccess());
    }

    @Test
    void testOptionalParamParse() {
        var tree = AllayCommandTree.create(mockCmd);
        tree.getRoot()
                .str("test_optional")
                .optional()
                .exec(context -> "".equals(context.getResult(0)) ? context.success() : context.fail());
        var res = tree.parse(mockSender, Utils.EMPTY_STRING_ARRAY);
        assertTrue(res.isSuccess());
    }

    @Test
    void testOnlyOneOptionalParamInLeaves() {
        var tree = AllayCommandTree.create(mockCmd);
        tree.getRoot()
                .str("test_optional1")
                .optional();
        assertThrows(IllegalArgumentException.class, () -> tree.getRoot().intNum("test_optional2").optional());
    }

    @Test
    void testNormalParamWithOptionalParamParse() {
        var tree = AllayCommandTree.create(mockCmd);
        tree.getRoot()
                .intNum("test_optional")
                .optional()
                .exec(CommandContext::fail)
                .root()
                .bool("test_normal")
                .exec(CommandContext::success);
        var res = tree.parse(mockSender, new String[]{"1"});
        assertFalse(res.isSuccess());
        res = tree.parse(mockSender, new String[]{"true"});
        assertTrue(res.isSuccess());
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
                    if (r1.equals("a") && r2.equals("d")) {
                        return context.success();
                    }
                    if (r1.equals("d1") && r2.equals("d2")) {
                        return context.success();
                    }
                    return context.fail();
                });
        var res = tree.parse(mockSender, new String[]{"a", "d"});
        assertTrue(res.isSuccess());
        res = tree.parse(mockSender, new String[]{});
        assertTrue(res.isSuccess());
    }

    @Test
    void testComplexOptionalNode() {
        var tree = AllayCommandTree.create(mockCmd);
        // tree description:
        // /test [a] [b]
        // /test <a> <b> <c> <d>
        tree.getRoot()
                .key("a")
                .optional()
                .key("b")
                .optional()
                .exec(CommandContext::success)
                .root()
                .key("a")
                .key("b")
                .key("c")
                .key("d")
                .exec(CommandContext::success);
        var res = tree.parse(mockSender, new String[]{"a", "b"});
        assertTrue(res.isSuccess());
        res = tree.parse(mockSender, new String[]{"a", "b", "c", "d"});
        assertTrue(res.isSuccess());
        res = tree.parse(mockSender, new String[]{"a", "b", "c"});
        assertFalse(res.isSuccess());
    }

    @Test
    void testPosNode() {
        var tree = AllayCommandTree.create(mockCmd);
        Vector3f dest = new Vector3f();
        tree.getRoot()
                .pos("pos")
                .exec(context -> {
                    dest.set((Vector3f)context.getFirstResult());
                    return context.success();
                });
        tree.parse(mockSender, new String[]{"11", "45", "14"});
        assertEquals(new Vector3f(11, 45, 14), dest);
        cmdExecLoc.set(19, 19, 810);
        tree.parse(mockSender, new String[]{"~1", "~2", "~3"});
        assertEquals(new Vector3f(19 + 1, 19 + 2, 810 + 3), dest);
        tree.parse(mockSender, new String[]{"~-1", "~-2", "~-3"});
        assertEquals(new Vector3f(19 - 1, 19 - 2, 810 - 3), dest);
        tree.parse(mockSender, new String[]{"~1.5", "~2.5", "~3.5"});
        assertEquals(new Vector3f(19 + 1.5f, 19 + 2.5f, 810 + 3.5f), dest);
        tree.parse(mockSender, new String[]{"19", "19", "810"});
        assertEquals(new Vector3f(19, 19, 810), dest);
    }
}
