package org.allaymc.server.container;

import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.server.container.impl.FakeChestContainerImpl;

/**
 * @author daoge_cmd
 */
public class AllayFakeContainerFactory implements FakeContainerFactory {
    @Override
    public FakeContainer createFakeChestContainer() {
        return new FakeChestContainerImpl();
    }
}
