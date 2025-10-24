package org.allaymc.server.entity.component.player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.SneakyThrows;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.EntityPlayerClientComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.container.impl.AbstractPlayerContainer;
import org.allaymc.server.container.impl.FakeContainerImpl;
import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.network.NetworkHelper;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.packet.*;

import java.util.*;

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
    protected EntityPlayerClientComponent clientComponent;
    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;

    @ComponentObject
    protected EntityPlayer thisPlayer;

    protected BiMap<Byte, Container> idToContainer;
    protected BiMap<ContainerType<?>, Container> typeToContainer;
    protected Map<ContainerSlotType, ContainerType<?>> slotTypeToFullType;

    public EntityPlayerContainerViewerComponentImpl() {
        this.idToContainer = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.typeToContainer = HashBiMap.create(new Object2ObjectOpenHashMap<>());
        this.slotTypeToFullType = new HashMap<>();
    }

    protected byte assignContainerId() {
        if (idCounter + 1 >= 100) {
            idCounter = 1;
        }

        return idCounter++;
    }

    @Override
    public void viewContents(Container container) {
        if (container instanceof AbstractPlayerContainer playerContainer) {
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
        // Client expects both zero if we do not use FullContainerName, and the id of
        // ContainerSlotType.ANVIL_INPUT is zero
        packet.setContainerNameData(new FullContainerName(ContainerSlotType.ANVIL_INPUT, null));
        packet.setContents(NetworkHelper.toNetwork(container.getItemStacks()));
        clientComponent.sendPacket(packet);
    }

    @Override
    public void viewSlot(Container container, int slot) {
        if (container instanceof AbstractPlayerContainer playerContainer) {
            if (playerContainer.getContainerType() == ContainerTypes.OFFHAND) {
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
        packet.setSlot(ContainerActionProcessor.toNetworkSlotIndex(container, slot));
        packet.setContainerNameData(new FullContainerName(ContainerActionProcessor.getSlotType(container, slot), null));
        packet.setItem(NetworkHelper.toNetwork(container.getItemStack(slot)));
        clientComponent.sendPacket(packet);
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
        // We should send the container's contents to the client if the container is not held by the entity
        if (containerHolderComponent.getContainer(containerType) == null) {
            viewContents(container);
        }

        return assignedId;
    }

    protected void registerOpenedContainer(byte assignedId, Container container) {
        this.idToContainer.put(assignedId, container);
        this.typeToContainer.put(container.getContainerType(), container);
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }

    @SneakyThrows
    protected void sendContainerOpenPacket(byte assignedId, Container container) {
        var packet = new ContainerOpenPacket();
        packet.setId(assignedId);
        packet.setType(ContainerNetworkInfo.getInfo(container.getContainerType()).toNetworkType());
        switch (container) {
            case BlockContainer blockContainer -> packet.setBlockPosition(NetworkHelper.toNetwork(blockContainer.getBlockPos()));
            case FakeContainerImpl fakeContainer -> packet.setBlockPosition(NetworkHelper.toNetwork(fakeContainer.getFakeBlockPos(thisPlayer)));
            default -> {
                var location = baseComponent.getLocation();
                packet.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
            }
        }
        Thread.sleep(500);
        this.clientComponent.sendPacket(packet);
    }

    @Override
    public void viewClose(Container container) {
        var assignedId = this.idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        }

        sendContainerClosePacket(assignedId, container);
        unregisterOpenedContainer(assignedId, container);
    }

    protected void unregisterOpenedContainer(byte assignedId, Container container) {
        this.typeToContainer.remove(Objects.requireNonNull(idToContainer.remove(assignedId)).getContainerType());
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.remove(slotType));
    }

    @SneakyThrows
    protected void sendContainerClosePacket(byte assignedId, Container container) {
        var packet = new ContainerClosePacket();
        packet.setId(assignedId);
        packet.setType(ContainerNetworkInfo.getInfo(container.getContainerType()).toNetworkType());
        Thread.sleep(500);
        clientComponent.sendPacket(packet);
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

        clientComponent.sendPacket(packet);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getOpenedContainer(ContainerType<T> type) {
        // Special case: If the player opens their inventory, they also implicitly open a series of other
        // containers, even if not registered
        Container container = null;
        if (isPlayerInventoryOpened()) {
            if (type == ContainerTypes.ARMOR || type == ContainerTypes.OFFHAND || type == ContainerTypes.CRAFTING_GRID) {
                container = containerHolderComponent.getContainer(type);
            }
        }

        if (container == null) {
            container = typeToContainer.get(type);
        }

        return (T) container;
    }

    @SuppressWarnings("unchecked")
    public <T extends Container> T getOpenedContainer(ContainerSlotType slotType) {
        // Similarly, special case handling needed
        ContainerType<?> type = null;
        if (isPlayerInventoryOpened()) {
            type = switch (slotType) {
                case ARMOR -> ContainerTypes.ARMOR;
                case OFFHAND -> ContainerTypes.OFFHAND;
                case CRAFTING_INPUT -> ContainerTypes.CRAFTING_GRID;
                default -> null;
            };
        }

        if (type == null) {
            type = slotTypeToFullType.get(slotType);
        }
        return (T) getOpenedContainer(type);
    }

    protected boolean isPlayerInventoryOpened() {
        return typeToContainer.get(ContainerTypes.INVENTORY) != null;
    }

    @Override
    public Container getOpenedContainer(byte id) {
        return idToContainer.get(id);
    }

    @Override
    public Set<Container> getOpenedContainers() {
        var containers = new HashSet<>(this.idToContainer.values());

        // Similarly, special case handling needed
        if (isPlayerInventoryOpened()) {
            containers.add(getOpenedContainer(ContainerTypes.ARMOR));
            containers.add(getOpenedContainer(ContainerTypes.OFFHAND));
            containers.add(getOpenedContainer(ContainerTypes.CRAFTING_GRID));
        }

        return containers;
    }

    @Override
    public void closeAllOpenedContainers() {
        for (var container : getOpenedContainers()) {
            container.removeViewer(this);
        }
    }
}
