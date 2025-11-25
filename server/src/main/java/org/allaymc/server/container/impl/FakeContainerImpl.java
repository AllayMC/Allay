package org.allaymc.server.container.impl;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.joml.RoundingMode;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.*;
import java.util.function.Consumer;

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
        throw new UnsupportedOperationException("Calling addViewer() in FakeContainer is unsupported! Please use addPlayer() method!");
    }

    @Override
    public boolean removeViewer(ContainerViewer viewer) {
        if (!(viewer instanceof Player player)) {
            return false;
        }

        if (super.removeViewer(player)) {
            removeFakeBlocks(player);
            return true;
        }

        return false;
    }

    @Override
    public void addPlayer(Player player, Consumer<Boolean> callback) {
        sendFakeBlocks(player);
        runDelayed(() -> {
            if (!super.addViewer(player)) {
                runDelayed(() -> removeFakeBlocks(player));
                callback.accept(false);
            }

            callback.accept(true);
        });
    }

    public Vector3ic getFakeBlockPos(ContainerViewer viewer) {
        var array = this.fakeBlockPositions.get(viewer);
        if (array != null) {
            return array[0];
        }

        return null;
    }

    public void onClick(int slot) {
        this.clickListeners.getOrDefault(slot, Collections.emptySet()).forEach(Runnable::run);
    }

    protected abstract void sendFakeBlocks(Player player);

    protected void removeFakeBlocks(Player player) {
        var dimension = player.getControlledEntity().getDimension();
        for (var pos : this.fakeBlockPositions.getOrDefault(player, new Vector3ic[0])) {
            player.viewBlockUpdate(pos, 0, dimension.getBlockState(pos, 0));
        }
    }

    protected void runDelayed(Runnable runnable) {
        var server = Server.getInstance();
        server.getScheduler().scheduleDelayed(server, () -> {
            runnable.run();
            return true;
        }, 8);
    }

    protected Vector3ic computeFakeBlockPos(Player player) {
        var entity = player.getControlledEntity();
        var location = entity.getLocation();
        var pos = MathUtils.getDirectionVector(location);
        var aabb = entity.getAABB();
        pos.x *= -(1 + aabb.lengthX());
        pos.y *= -(1 + aabb.lengthY());
        pos.z *= -(1 + aabb.lengthZ());
        return location.add(pos, new Vector3d()).get(RoundingMode.FLOOR, new Vector3i());
    }
}
