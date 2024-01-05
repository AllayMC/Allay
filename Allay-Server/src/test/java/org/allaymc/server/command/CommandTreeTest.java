package org.allaymc.server.command;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class CommandTreeTest {

    static Command mockCmd = Mockito.mock(Command.class);

    @BeforeAll
    static void init() {
        Mockito.when(mockCmd.getName()).thenReturn("test");
    }

    @Test
    void testSimpleParse() {
        var tree = AllayCommandTree.create(mockCmd);
        tree.getRoot()
                .key("test_bool")
                .bool("bool")
                .exec(context -> {
                    boolean bool = context.getResult(1);
                    return bool ? context.success() : context.failed();
                })
                .root()
                .key("test_str")
                .str("str")
                .exec(context -> {
                    System.out.println((String) context.getResult(1));
                    return context.success();
                })
                .root()
                .key("test_enums")
                .enums("enums", "a", "b", "c", "d")
                .exec(context -> {
                    System.out.println((String) context.getResult(1));
                    return context.success();
                })
                .root()
                .key("test_int")
                .intNum("int")
                .exec(context -> {
                    int i = context.getResult(1);
                    return i == 1 ? context.success() : context.failed();
                });

        var res = tree.parse(null, new String[]{"test_bool", "true"});
        assertTrue(res.isSuccess());
        res = tree.parse(null, new String[]{"test_str", "a"});
        assertTrue(res.isSuccess());
        res = tree.parse(null, new String[]{"test_enums", "b"});
        assertTrue(res.isSuccess());
        res = tree.parse(null, new String[]{"test_int", "1"});
        assertTrue(res.isSuccess());
    }

    @Test
    void testOptionalParamParse() {
        var tree = AllayCommandTree.create(mockCmd);
        tree.getRoot()
                .str("test_optional")
                .optional()
                .exec(context -> "".equals(context.getResult(0)) ? context.success() : context.failed());
        var res = tree.parse(null, new String[0]);
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
                .exec(CommandContext::failed)
                .root()
                .bool("test_normal")
                .exec(CommandContext::success);
        var res = tree.parse(null, new String[]{"1"});
        assertFalse(res.isSuccess());
        res = tree.parse(null, new String[]{"true"});
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
                    return context.failed();
                });
        var res = tree.parse(null, new String[]{"a", "d"});
        assertTrue(res.isSuccess());
        res = tree.parse(null, new String[]{});
        assertTrue(res.isSuccess());
    }

    @Test
    void testComplexOptionalNode() {
        var tree = AllayCommandTree.create(mockCmd);
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
        var res = tree.parse(null, new String[]{"a", "b"});
        assertTrue(res.isSuccess());
        res = tree.parse(null, new String[]{"a", "b", "c", "d"});
        assertTrue(res.isSuccess());
        res = tree.parse(null, new String[]{"a", "b", "c"});
        assertFalse(res.isSuccess());
    }
}
