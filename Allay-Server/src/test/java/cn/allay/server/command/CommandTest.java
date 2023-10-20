package cn.allay.server.command;

import cn.allay.api.command.annotation.Command;
import cn.allay.api.command.annotation.DefaultExecuteFor;
import cn.allay.api.command.annotation.Overload;
import cn.allay.api.command.impl.CommandHandlerImpl;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllayTestExtension.class)
class CommandTest {

    final CommandHandlerImpl handler = new CommandHandlerImpl() {

    };

    @Test
    void testRegisterCommand() {
        @Command("testcommand")
        class TestCommand {

            @DefaultExecuteFor("testcommand")
            public void defaultCommand() {}

            @Overload("testcommand")
            public void defaultCommandOverload() {}
        }

        handler.register(new TestCommand());

        assertEquals(1, handler.getCommands().size());
        handler.getCommands().forEach((path, data) -> {
            assertEquals("testcommand", data.getPath().getName());
            assertTrue(data.getPath().getAliases().isEmpty());
            assertTrue(data.getDescription().isEmpty());
            assertEquals(1, data.getMethods().size());
            assertEquals(1, data.getOverloads().size());
        });
    }
}
