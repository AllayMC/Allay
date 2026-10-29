package org.allaymc.testutils;

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
    private static boolean serverThreadStarted;

    @Override
    public void beforeAll(ExtensionContext context) {
        synchronized (AllayTestExtension.class) {
            Allay.initI18n();
            Allay.initAllay();
            TestRegistryFixtures.register();

            if (!serverThreadStarted && Server.getInstance().getState() == ServerState.STARTING) {
                serverThreadStarted = true;
                Thread.ofPlatform().name("Test Main Thread").start(() -> {
                    try {
                        Allay.main(new String[]{});
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        while (Server.getInstance().getState() == ServerState.STARTING) {
            Thread.onSpinWait();
        }
    }
}
