package org.allaymc.server.terminal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minecrell.terminalconsole.SimpleTerminalConsole;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

/**
 * Allay Project 2023/6/30
 *
 * @author daoge_cmd
 */
@Slf4j
@AllArgsConstructor
public class AllayTerminalConsole extends SimpleTerminalConsole {

    private Server server;

    @Override
    protected boolean isRunning() {
        return server.isRunning();
    }

    @Override
    protected void runCommand(String cmd) {
        if (cmd.startsWith("/")) cmd = cmd.substring(1);
        if (cmd.isEmpty()) return;
        Registries.COMMANDS.execute(server, cmd);
    }

    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        builder.appName("Allay");
        builder.option(LineReader.Option.HISTORY_BEEP, false);
        builder.option(LineReader.Option.HISTORY_IGNORE_DUPS, true);
        builder.option(LineReader.Option.HISTORY_IGNORE_SPACE, true);
        return super.buildReader(builder);
    }

    @Override
    protected void shutdown() {
        server.shutdown();
    }
}
