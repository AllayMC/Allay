package org.allaymc.api.annotation;

import org.allaymc.api.server.Server;

import java.util.concurrent.ForkJoinPool;

/**
 * Represents different types of threads used in Allay.
 *
 * @author daoge_cmd
 */
public enum ThreadType {
    /**
     * The ticking thread of the server instance, it is usually the `main` thread of the JVM.
     * Notes that in Allay, the main thread does not tick worlds since each world has its own
     * ticking thread. The main thread only handles tasks submitted to the server's scheduler
     * {@link Server#getScheduler()}.
     */
    SERVER,
    /**
     * The ticking thread of a world. Each world has its own ticking thread. World tick does some
     * simple things like updating the time and the weather of the whole world.
     */
    WORLD,
    /**
     * The ticking thread of a dimension. Ticks between different dimensions in the same world
     * are in parallel.
     * <p>
     * The world tick always completes before the dimension tick begins, and the world tick waits
     * for all dimensions in the current world to finish their ticks before proceeding to the next
     * tick. These two ticks never run concurrently.
     */
    DIMENSION,
    /**
     * The light thread which handles sky and block light calculation in a dimension. Each
     * dimension has a number of light threads.
     */
    LIGHT,
    /**
     * The network thread which handles incoming data packets from the players.
     */
    NETWORK,
    /**
     * The thread in the compute thread pool {@link Server#getComputeThreadPool()} or
     * {@link ForkJoinPool#commonPool()}. Compute threads are used in world generation and
     * entity physics calculation.
     */
    COMPUTE,
    /**
     * The virtual thread in the JVM. Virtual threads are mostly used in IO tasks such as
     * saving world data.
     */
    VIRTUAL,
    /**
     * Represents any thread type.
     */
    ANY
}