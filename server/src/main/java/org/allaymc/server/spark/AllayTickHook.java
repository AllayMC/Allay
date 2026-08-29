package org.allaymc.server.spark;

import me.lucko.spark.common.tick.TickHook;
import org.allaymc.api.server.Server;

import java.util.ArrayList;
import java.util.List;

public final class AllayTickHook implements TickHook {

    private final List<Callback> callbacks = new ArrayList<>();
    private volatile boolean running;

    @Override
    public void start() {
        if (running) {
            return;
        }
        running = true;
        Server.getInstance().getScheduler().scheduleRepeating(Server.getInstance(), () -> {
            if (!running) {
                return false;
            }
            int tick = getCurrentTick();
            callbacks.forEach(callback -> callback.onTick(tick));
            return true;
        }, 1, false);
    }

    @Override
    public void close() {
        running = false;
    }

    @Override
    public int getCurrentTick() {
        return (int) Server.getInstance().getTick();
    }

    @Override
    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeCallback(Callback callback) {
        callbacks.remove(callback);
    }
}