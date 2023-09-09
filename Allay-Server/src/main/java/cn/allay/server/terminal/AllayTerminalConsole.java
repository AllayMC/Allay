package cn.allay.server.terminal;

import cn.allay.api.server.Server;
import lombok.extern.slf4j.Slf4j;
import net.minecrell.terminalconsole.SimpleTerminalConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

/**
 * Allay Project 2023/6/30
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayTerminalConsole extends SimpleTerminalConsole {

    protected Server server;

    public AllayTerminalConsole(Server server) {
        this.server = server;
    }

    @Override
    protected boolean isRunning() {
        return server.isRunning();
    }

    @Override
    protected void runCommand(String s) {
        if (s.equalsIgnoreCase("stop")) {
            shutdown();
        } else {
            //TODO
            log.info("§1TODO :)");
        }
    }

    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        //TODO: Completer
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
