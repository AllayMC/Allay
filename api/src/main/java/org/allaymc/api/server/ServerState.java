package org.allaymc.api.server;

/**
 * {@code ServerState} identifies the server lifecycle phase exposed to service
 * consumers.
 * Startup publishes the server before it enters {@code RUNNING}; a shutdown request
 * selects {@code STOPPING} before cleanup. {@code STOPPED} means the shutdown sequence reached
 * its end, without promising that submitted executor work has terminated.
 *
 * @author IWareQ
 */
public enum ServerState {
    /** Server construction and startup. */
    STARTING,
    /** Main server tick loop. */
    RUNNING,
    /** Requested or ongoing shutdown. */
    STOPPING,
    /** Completed synchronous shutdown sequence. */
    STOPPED
}
