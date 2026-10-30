package org.allaymc.api.annotation;

import org.allaymc.api.server.Server;

import java.util.concurrent.ForkJoinPool;

/**
 * {@code ThreadType} identifies execution contexts documented by thread-related
 * API annotations.
 * These labels distinguish server, world, dimension, worker, and network work;
 * they do not select an executor or transfer execution. A context can describe
 * a logical role even when configuration changes the underlying thread type.
 *
 * @author daoge_cmd
 */
public enum ThreadType {
    /**
     * {@code SERVER} identifies the server instance's tick loop, normally on the
     * JVM main thread.
     * The loop advances the server scheduler and player manager; world tick
     * loops execute separately from this server-level work.
     */
    SERVER,
    /**
     * {@code WORLD} identifies a world's ticking thread. Each world owns its
     * ticking thread, which advances shared world time and weather.
     */
    WORLD,
    /**
     * {@code DIMENSION} identifies a dimension's tick, potentially alongside other dimensions when
     * the world's parallel-dimension setting is enabled.
     * <p>
     * The world updates shared time, weather, sleep, and scheduling before
     * starting dimension ticks, then waits for all dimension ticks before its
     * storage tick and next iteration. Sequential configuration can run dimension
     * work directly on the world's ticking thread.
     */
    DIMENSION,
    /**
     * {@code LIGHT} identifies sky and block light calculation workers in a
     * dimension. A dimension can use multiple workers for these calculations.
     */
    LIGHT,
    /**
     * Network packet processing context.
     */
    NETWORK,
    /**
     * {@code COMPUTE} identifies workers from {@link Server#getComputeThreadPool()}
     * or {@link ForkJoinPool#commonPool()}. World generation and entity physics
     * use this context for computational work.
     */
    COMPUTE,
    /**
     * JVM virtual-thread context.
     */
    VIRTUAL,
    /**
     * Unrestricted caller execution context.
     */
    ANY
}
