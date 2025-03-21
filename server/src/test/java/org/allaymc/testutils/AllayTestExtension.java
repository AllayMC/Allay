package org.allaymc.testutils;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
import org.allaymc.server.Allay;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * AllayTestExtension is used to prepare the test environment.
 *
 * @author daoge_cmd
 */
public class AllayTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        if (!AllayAPI.getInstance().isImplemented()) {
            Thread.ofPlatform().name("Test Main Thread").start(() -> {
                try {
                    Allay.main(new String[]{});
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            while (Server.getInstance() == null || Server.getInstance().getState() == ServerState.STARTING) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
