package org.allaymc.server.extension;

/**
 * @author daoge_cmd
 */
public abstract class Extension {
    public abstract void main(String[] args);

    public void afterServerStarted() {
        // noop
    }
}
