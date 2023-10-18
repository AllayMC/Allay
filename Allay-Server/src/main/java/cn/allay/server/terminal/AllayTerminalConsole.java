package cn.allay.server.terminal;

import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.server.Server;
import cn.allay.api.utils.MathUtils;
import cn.allay.api.world.chunk.Chunk;
import lombok.extern.slf4j.Slf4j;
import net.minecrell.terminalconsole.SimpleTerminalConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.joml.Vector3i;

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
            log.info("Server ShutDown...");
            shutdown();
        } else if (s.startsWith("/t")) {
            EntityPlayer entityPlayer = Server.getInstance().getDefaultWorld().getPlayers().stream().findFirst().get();
            Vector3i floor = MathUtils.floor(entityPlayer.getLocation());
            Chunk chunk = Server.getInstance().getDefaultWorld().getChunkService().getChunk(
                    floor.x >> 4,
                    floor.z >> 4
            );
            System.out.println(entityPlayer.getLocation());
            System.out.println((floor.x >> 4) + ":" + (floor.z >> 4));
            System.out.println(chunk == null);
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
