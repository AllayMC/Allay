package org.allaymc.server.container.impl;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.joml.Vector3ic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public abstract class FakeContainerImpl extends BaseContainer implements FakeContainer {

    @Getter
    @Setter
    protected String customName;
    protected Int2ObjectMap<Set<Runnable>> clickListeners;
    protected Map<ContainerViewer, Vector3ic[]> fakeBlockPositions;

    public FakeContainerImpl(ContainerType<? extends Container> containerType) {
        super(containerType);
        this.clickListeners = new Int2ObjectOpenHashMap<>();
        this.fakeBlockPositions = new HashMap<>();
    }

    @Override
    public void addClickListener(int slot, Runnable listener) {
        this.clickListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
    }

    @Override
    public void removeClickListener(int slot, Runnable listener) {
        this.clickListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
    }

    @Override
    public void removeAllClickListeners() {
        this.clickListeners.clear();
    }

    @Override
    public boolean addViewer(ContainerViewer viewer) {
        sendFakeBlock(viewer);
        if (!super.addViewer(viewer)) {
            removeFakeBlock(viewer);
            return false;
        }

        return true;
    }

    @Override
    public boolean removeViewer(ContainerViewer viewer) {
        if (super.removeViewer(viewer)) {
            removeFakeBlock(viewer);
            return true;
        }

        return false;
    }

    public void onClick(int slot) {
        this.clickListeners.get(slot).forEach(Runnable::run);
    }

    protected abstract void sendFakeBlock(ContainerViewer viewer);

    protected abstract void removeFakeBlock(ContainerViewer viewer);
}
