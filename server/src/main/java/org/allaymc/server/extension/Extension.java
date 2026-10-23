package org.allaymc.server.extension;

/**
 * {@code Extension} defines an entry point for extensions loaded before normal
 * server startup.
 * Extensions are discovered separately from plugins and receive the launch
 * arguments directly. The current extension manager invokes successful entries
 * during discovery and again after the discovery walk completes.
 *
 * @author daoge_cmd
 */
public abstract class Extension {
    /**
     * Runs this extension with the original server launch arguments.
     * The manager passes the shared array and may invoke this method more than
     * once, so implementations must account for repeated entry and shared input.
     */
    public abstract void main(String[] args);
}
