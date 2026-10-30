package org.allaymc.api.container;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.container.interfaces.FakeContainer;

/**
 * {@code FakeContainerFactory} creates synthetic chest containers for plugin-defined
 * inventory interfaces. The API holder supplies the installed server implementation, and
 * each creation operation returns a new container whose delayed opening and click
 * handling follow the {@link FakeContainer} contract.
 *
 * @author daoge_cmd
 */
public interface FakeContainerFactory {
    /**
     * {@code FACTORY} holds the server-installed factory implementation used by {@code
     * getFactory()} and by plugins that create synthetic container interfaces.
     */
    AllayAPI.APIInstanceHolder<FakeContainerFactory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Returns the currently installed fake-container factory from the API holder.
     */
    static FakeContainerFactory getFactory() {
        return FACTORY.get();
    }

    /**
     * Creates a new synthetic chest container with twenty-seven item slots.
     */
    FakeContainer createFakeChestContainer();

    /**
     * Creates a new synthetic double chest container with fifty-four slots.
     */
    FakeContainer createFakeDoubleChestContainer();
}
