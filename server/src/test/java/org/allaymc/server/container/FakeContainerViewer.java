package org.allaymc.server.container;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerViewer;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class FakeContainerViewer implements ContainerViewer {
    @Override
    public void viewContainerContents(Container container) {
    }

    @Override
    public void viewContainerSlot(Container container, int slot) {
    }

    @Override
    public byte viewContainerOpen(Container container) {
        return 0;
    }

    @Override
    public void viewContainerClose(Container container) {
    }

    @Override
    public void viewContainerData(Container container, int property, int value) {
    }

    @Override
    public <T extends Container> T getOpenedContainer(ContainerType<T> type) {
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
