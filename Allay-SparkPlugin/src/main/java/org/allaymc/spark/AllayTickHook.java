package org.allaymc.spark;

import lombok.AllArgsConstructor;
import me.lucko.spark.common.tick.AbstractTickHook;
import me.lucko.spark.common.tick.TickHook;
import org.allaymc.api.scheduler.Task;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Allay Project 10/02/2024
 *
 * @author IWareQ
 */
@AllArgsConstructor
public class AllayTickHook extends AbstractTickHook implements TickHook, Task {

    private final AtomicBoolean isEnabled = new AtomicBoolean(true);

    private final AllaySparkPlugin plugin;

    @Override
    public boolean onRun() {
        this.onTick();
        return this.isEnabled.get();
    }

    @Override
    public void start() {
        this.plugin.getServerScheduler().scheduleRepeating(this.plugin, this, 1);
    }

    @Override
    public void close() {
        this.isEnabled.compareAndSet(true, false);
    }
}
