package org.allaymc.server.container;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerViewer;

import java.util.Set;

/**
 * {@code FakeContainerViewer} supplies a passive viewer stub for container tests. Its
 * callbacks ignore updates and opening returns window ID zero, so individual tests can
 * override only the callback relevant to their membership or notification assertions.
 *
 * @author daoge_cmd
 */
public class FakeContainerViewer implements ContainerViewer {
    /**
     * Ignores content updates so tests can isolate container-side behavior.
     */
    @Override
    public void viewContainerContents(Container container) {
    }

    /**
     * Ignores slot updates unless a test overrides this callback.
     */
    @Override
    public void viewContainerSlot(Container container, int slot) {
    }

    /**
     * Returns window ID zero without tracking open container associations. Tests using
     * several viewers override this callback when distinct assigned IDs are required to
     * avoid the container's ID-collision removal path.
     */
    @Override
    public byte viewContainerOpen(Container container) {
        return 0;
    }

    /**
     * Ignores close callbacks so tests can observe container membership independently.
     */
    @Override
    public void viewContainerClose(Container container) {
    }

    /**
     * Ignores numeric property updates sent through the test viewer.
     */
    @Override
    public void viewContainerData(Container container, int property, int value) {
    }

    /**
     * Returns {@code null} for every typed lookup because this stub tracks no open
     * containers. Tests requiring lookup behavior must provide their own override rather
     * than rely on opening callbacks.
     */
    @Override
    public <T extends Container> T getOpenedContainer(ContainerType<T> type) {
        return null;
    }

    /**
     * Returns {@code null} for every assigned window ID because this stub records no
     * associations. Its opening callback supplies a fixed ID solely for container-side
     * membership tests.
     */
    @Override
    public Container getOpenedContainer(byte id) {
        return null;
    }

    /**
     * Returns {@code null} instead of an open-container set because this stub tracks no
     * viewer associations. Tests that enumerate open windows must override this
     * placeholder operation with suitable behavior.
     */
    @Override
    public Set<Container> getOpenedContainers() {
        return null;
    }

    /**
     * Ignores bulk-close requests because this stub tracks no viewer associations.
     */
    @Override
    public void closeAllOpenedContainers() {
    }
}
