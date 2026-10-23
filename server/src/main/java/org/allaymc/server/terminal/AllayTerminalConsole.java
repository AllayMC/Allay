package org.allaymc.server.terminal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minecrell.terminalconsole.SimpleTerminalConsole;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

/**
 * {@code AllayTerminalConsole} connects terminal input, command completion, and shutdown to a
 * server instance. Input removes one leading slash before command execution, and the configured
 * reader suppresses duplicate or space-prefixed history entries while the server is running.
 *
 * @author daoge_cmd
 */
@Slf4j
@AllArgsConstructor
public class AllayTerminalConsole extends SimpleTerminalConsole {
    /**
     * Server receiving terminal commands and shutdown.
     */
    private Server server;

    /**
     * Returns whether the associated server is currently in its running state.
     */
    @Override
    protected boolean isRunning() {
        return server.getState() == ServerState.RUNNING;
    }

    /**
     * Executes terminal input as the server command sender after removing one leading slash. This
     * operation does not trim whitespace or discard empty input, leaving remaining parsing behavior
     * to the command registry.
     */
    @Override
    protected void runCommand(String cmd) {
        // Terminal users can enter chat-style commands; remove their leading
        // slash because registry execution expects the command name without that prefix.
        if (cmd.startsWith("/")) {
            cmd = cmd.substring(1);
        }
        Registries.COMMANDS.execute(server, cmd);
    }

    /**
     * Builds the terminal reader with the Allay app name and command completer. History disables
     * beeps, duplicate entries, and space-prefixed entries before the superclass finishes
     * configuring the reader.
     */
    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        builder.appName("Allay");
        builder.completer(new AllayCommandCompleter(server));
        builder.option(LineReader.Option.HISTORY_BEEP, false);
        builder.option(LineReader.Option.HISTORY_IGNORE_DUPS, true);
        builder.option(LineReader.Option.HISTORY_IGNORE_SPACE, true);
        return super.buildReader(builder);
    }

    /**
     * Requests shutdown of the server associated with this terminal console.
     */
    @Override
    protected void shutdown() {
        server.shutdown();
    }
}
