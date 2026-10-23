package org.allaymc.server.container;

import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.server.container.impl.FakeChestContainerImpl;
import org.allaymc.server.container.impl.FakeDoubleChestContainerImpl;

/**
 * {@code AllayFakeContainerFactory} creates the server's synthetic chest and double
 * chest containers for plugin interfaces. Each factory call allocates new container
 * storage; the returned implementation handles fake block publication, delayed window
 * opening, and click-listener dispatch when players interact.
 *
 * @author daoge_cmd
 */
public class AllayFakeContainerFactory implements FakeContainerFactory {
    /**
     * Creates a new synthetic chest container backed by twenty-seven slots.
     */
    @Override
    public FakeContainer createFakeChestContainer() {
        return new FakeChestContainerImpl();
    }

    /**
     * Creates a new synthetic double chest container backed by fifty-four slots.
     */
    @Override
    public FakeContainer createFakeDoubleChestContainer() {
        return new FakeDoubleChestContainerImpl();
    }
}
