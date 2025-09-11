package org.allaymc.server.entity.component.player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.BlockContainer;
import org.allaymc.api.container.impl.PlayerContainer;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.packet.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class EntityPlayerContainerViewerComponentImpl implements EntityContainerViewerComponent {

    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_viewer_component");

    protected byte idCounter = 1;
    @Dependency
    protected EntityPlayerBaseComponent baseComponent;
    @Dependency
    protected EntityPlayerNetworkComponent networkComponent;
    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;

    protected BiMap<Byte, Container> idToContainer = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
    protected BiMap<FullContainerType<?>, Container> typeToContainer = HashBiMap.create(new Object2ObjectOpenHashMap<>());
    protected Map<ContainerSlotType, FullContainerType<?>> slotTypeToFullType = new HashMap<>();

    protected byte assignContainerId() {
        if (idCounter + 1 >= 100) {
            idCounter = 1;
        }

        return idCounter++;
    }

    @Override
    public void viewContents(Container container) {
        if (container instanceof PlayerContainer playerContainer) {
            viewContentsWithSpecificContainerId(playerContainer, playerContainer.getUnopenedContainerId());
            return;
        }

        var id = idToContainer.inverse().get(container);
        if (id == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        viewContentsWithSpecificContainerId(container, id);
    }

    protected void viewContentsWithSpecificContainerId(Container container, int containerId) {
        var packet = new InventoryContentPacket();
        packet.setContainerId(containerId);
        // Client expects both zero if we do not use FullContainerName
        // And the id of ContainerSlotType.ANVIL_INPUT is zero
        packet.setContainerNameData(new FullContainerName(ContainerSlotType.ANVIL_INPUT, null));
        packet.setContents(container.toNetworkItemData());
        networkComponent.sendPacket(packet);
    }

    @Override
    public void viewSlot(Container container, int slot) {
        if (container instanceof PlayerContainer playerContainer) {
            if (playerContainer.getContainerType() == FullContainerType.OFFHAND) {
                // HACK: for unknown reason, we should send InventoryContentPacket instead of InventorySlotPacket
                // for offhand container, otherwise the client will not update the offhand item
                // TODO: replace this hack when we find the reason and have better solution
                viewContentsWithSpecificContainerId(playerContainer, playerContainer.getUnopenedContainerId());
            } else {
                viewSlotWithSpecificContainerId(playerContainer, slot, playerContainer.getUnopenedContainerId());
            }
            return;
        }

        var id = idToContainer.inverse().get(container);
        if (id == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        viewSlotWithSpecificContainerId(container, slot, id);
    }

    protected void viewSlotWithSpecificContainerId(Container container, int slot, int containerId) {
        var packet = new InventorySlotPacket();
        packet.setContainerId(containerId);
        packet.setSlot(container.toNetworkSlotIndex(slot));
        packet.setContainerNameData(new FullContainerName(container.getSlotType(slot), null));
        packet.setItem(container.getItemStack(slot).toNetworkItemData());
        networkComponent.sendPacket(packet);
    }

    @Override
    public byte viewOpen(Container container) {
        if (idToContainer.inverse().containsKey(container)) {
            throw new IllegalStateException("The container " + container.getContainerType() + " have been opened by this viewer");
        }

        var assignedId = assignContainerId();
        sendContainerOpenPacket(assignedId, container);
        registerOpenedContainer(assignedId, container);

        var containerType = container.getContainerType();
        // We should send the container's contents to client if the container is not held by the entity
        if (containerHolderComponent.getContainer(containerType) == null) {
            viewContents(container);
        }

        return assignedId;
    }

    protected void registerOpenedContainer(byte assignedId, Container container) {
        idToContainer.put(assignedId, container);
        typeToContainer.put(container.getContainerType(), container);
        container.getContainerType().heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }

    protected void sendContainerOpenPacket(byte assignedId, Container container) {
        var packet = new ContainerOpenPacket();
        packet.setId(assignedId);
        packet.setType(container.getContainerType().toNetworkType());
        if (container instanceof BlockContainer blockContainer) {
            packet.setBlockPosition(MathUtils.toCBVec(blockContainer.getBlockPos()));
        } else {
            var location = baseComponent.getLocation();
            packet.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
        }
        networkComponent.sendPacket(packet);
    }

    @Override
    public void viewClose(Container container) {
        var assignedId = idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        }

        sendContainerClosePacket(assignedId, container);
        unregisterOpenedContainer(assignedId, container);
    }

    protected void unregisterOpenedContainer(byte assignedId, Container container) {
        typeToContainer.remove(idToContainer.remove(assignedId).getContainerType());
        container.getContainerType().heldSlotTypes().forEach(slotType -> slotTypeToFullType.remove(slotType));
    }

    protected void sendContainerClosePacket(byte assignedId, Container container) {
        var packet = new ContainerClosePacket();
        packet.setId(assignedId);
        packet.setType(container.getContainerType().toNetworkType());
        networkComponent.sendPacket(packet);
    }

    @Override
    public void viewContainerData(Container container, int property, int value) {
        var assignedId = idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        var packet = new ContainerSetDataPacket();
        packet.setWindowId(assignedId);
        packet.setProperty(property);
        packet.setValue(value);

        networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Container> T getOpenedContainer(FullContainerType<T> type) {
        // Special case: If the player opens their inventory, they also implicitly open a series of other containers, even if not registered
        Container container = null;
        if (isPlayerInventoryOpened()) {
            if (type == FullContainerType.ARMOR || type == FullContainerType.OFFHAND || type == FullContainerType.CRAFTING_GRID) {
                container = containerHolderComponent.getContainer(type);
            }
        }

        if (container == null) {
            container = typeToContainer.get(type);
        }
        return (T) container;
    }

    @Override
    public <T extends Container> T getOpenedContainer(ContainerSlotType slotType) {
        // Similarly, special case handling needed
        FullContainerType<?> fullType = null;
        if (isPlayerInventoryOpened()) {
            fullType = switch (slotType) {
                case ARMOR -> FullContainerType.ARMOR;
                case OFFHAND -> FullContainerType.OFFHAND;
                case CRAFTING_INPUT -> FullContainerType.CRAFTING_GRID;
                default -> null;
            };
        }

        if (fullType == null) {
            fullType = slotTypeToFullType.get(slotType);
        }
        return (T) getOpenedContainer(fullType);
    }

    protected boolean isPlayerInventoryOpened() {
        return typeToContainer.get(FullContainerType.PLAYER_INVENTORY) != null;
    }

    @Override
    public Container getOpenedContainer(byte id) {
        return idToContainer.get(id);
    }

    @Override
    public Set<Container> getOpenedContainers() {
        return this.idToContainer.values();
    }

    @Override
    public void closeAllOpenedContainers() {
        for (var container : getOpenedContainers()) {
            container.removeViewer(this);
        }
    }
}
