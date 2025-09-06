package org.allaymc.server.container;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class FakeContainerViewer implements ContainerViewer {
    @Override
    public void viewContents(Container container) {
    }

    @Override
    public void viewSlot(Container container, int slot) {
    }

    @Override
    public byte viewOpen(Container container) {
        return 0;
    }

    @Override
    public void viewClose(Container container) {
    }

    @Override
    public void viewContainerData(Container container, int property, int value) {
    }

    @Override
    public <T extends Container> T getOpenedContainer(FullContainerType<T> type) {
        return null;
    }

    @Override
    public <T extends Container> T getOpenedContainer(ContainerSlotType slotType) {
        return null;
    }

    @Override
    public Container getOpenedContainer(byte id) {
        return null;
    }

    @Override
    public Set<Container> getOpenedContainers() {
        return null;
    }

    @Override
    public void closeAllOpenedContainers() {
    }
}
