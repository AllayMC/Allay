package org.allaymc.testutils;

import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
import org.allaymc.server.Allay;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * {@code AllayTestExtension} prepares shared server services and registered fixtures before
 * JUnit test classes run. Its class monitor serializes setup and the decision to launch a
 * test server thread; fixture registration precedes that launch so the server's later
 * registry freeze does not prevent test-type construction.
 *
 * <p>
 * The startup claim is set before the platform thread is created and is never reset here.
 * Other classes reuse that claim and spin only while the shared server reports
 * {@code ServerState.STARTING}. Leaving that state ends the wait even if startup did not
 * reach a usable running state, and a failed worker does not automatically notify or restart
 * the waiting callback.
 *
 * @author daoge_cmd
 */
public class AllayTestExtension implements BeforeAllCallback {
    /**
     * {@code serverThreadStarted} claims the single server-thread launch under the class monitor.
     * The flag is set before starting that thread and remains set after a startup failure.
     */
    private static boolean serverThreadStarted;

    /**
     * Initializes services and shared fixtures, then launches a server thread if startup still
     * needs one. Setup and the launch claim are serialized through the extension class monitor,
     * while waiting occurs after that monitor is released so other test classes can finish their
     * own setup callbacks.
     *
     * <p>
     * The callback waits only while the server state is {@code STARTING}, without a timeout or
     * worker join. Any other state ends the wait; thread-start failure leaves the claim set,
     * and an interrupted server worker throws on that worker rather than reporting through a
     * returned future to this callback.
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        // Serialize fixture construction before the startup thread can freeze registries.
        // The same monitor also prevents test classes from launching competing server threads.
        synchronized (AllayTestExtension.class) {
            Allay.initI18n();
            Allay.initAllay();
            TestRegistryFixtures.register();

            if (!serverThreadStarted && Server.getInstance().getState() == ServerState.STARTING) {
                // Publish the launch claim before creating the worker so another callback
                // cannot start a second server thread, even if the first launch later fails.
                serverThreadStarted = true;
                Thread.ofPlatform().name("Test Main Thread").start(() -> {
                    try {
                        Allay.main(new String[]{});
                    } catch (InterruptedException e) {
                        // This worker cannot return a checked startup failure to JUnit's
                        // callback. Re-throw it on the worker; beforeAll has no completion future.
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        // Release the setup monitor before waiting so other test classes can initialize.
        // The state check observes departure from STARTING, not necessarily successful startup.
        while (Server.getInstance().getState() == ServerState.STARTING) {
            Thread.onSpinWait();
        }
    }
}
