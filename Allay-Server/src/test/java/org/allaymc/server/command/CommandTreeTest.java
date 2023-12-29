package org.allaymc.server.command;

import org.allaymc.api.cmdv2.CommandResult;
import org.allaymc.server.cmdv2.tree.AllayCommandTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class CommandTreeTest {

    @Test
    void testParse() {
        var tree = AllayCommandTree.create();
        tree.getRoot()
                .key("test_bool")
                    .bool("bool")
                    .exec(helper -> {
                        boolean bool = helper.getResult(1);
                        return bool ? CommandResult.SUCCESS : CommandResult.FAILED;
                    })
            .root()
                .key("test_str")
                    .str("str")
                    .exec(helper -> {
                        System.out.println((String) helper.getResult(1));
                        return CommandResult.SUCCESS;
                    });
        var res = tree.parse(null, new String[]{"test_bool", "true"});
        assertEquals(CommandResult.SUCCESS, res);
        res = tree.parse(null, new String[]{"test_str", "a"});
        assertEquals(CommandResult.SUCCESS, res);
    }
}
