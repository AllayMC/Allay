package org.allaymc.server.entity.ai;

import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.server.Server;
import org.joml.Vector3dc;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Manages async route finding tasks using virtual threads.
 *
 * @author daoge_cmd
 */
public final class RouteFindingManager {

    private RouteFindingManager() {
    }

    /**
     * Submit a route finding task to the virtual thread pool.
     *
     * @param routeFinder the route finder to use
     * @param entity      the entity to find a route for
     * @param target      the target position
     * @return a {@link RouteFindingTask} that can be used to track or cancel the task
     */
    public static RouteFindingTask submit(RouteFinder routeFinder, EntityIntelligent entity, Vector3dc target) {
        var task = new RouteFindingTask(routeFinder, entity, target);
        task.future = Server.getInstance().getVirtualThreadPool().submit(task);
        return task;
    }

    public static class RouteFindingTask implements Runnable {
        private final RouteFinder routeFinder;
        private final EntityIntelligent entity;
        private final Vector3dc target;
        private final AtomicBoolean started = new AtomicBoolean(false);
        private final AtomicBoolean finished = new AtomicBoolean(false);
        private volatile Future<?> future;

        public RouteFindingTask(RouteFinder routeFinder, EntityIntelligent entity, Vector3dc target) {
            this.routeFinder = routeFinder;
            this.entity = entity;
            this.target = target;
        }

        @Override
        public void run() {
            started.set(true);
            try {
                routeFinder.search(entity, target);
            } finally {
                finished.set(true);
            }
        }

        public boolean isStarted() {
            return started.get();
        }

        public boolean isFinished() {
            return finished.get();
        }

        public void cancel() {
            if (future != null) {
                future.cancel(true);
            }
        }
    }
}
