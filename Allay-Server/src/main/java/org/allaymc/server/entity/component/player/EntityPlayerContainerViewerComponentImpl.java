package org.allaymc.server.entity.component.player;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.common.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.MathUtils;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.HashMap;
import java.util.Map;

import static org.allaymc.api.container.FullContainerType.CRAFTING_GRID;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class EntityPlayerContainerViewerComponentImpl implements EntityContainerViewerComponent {

    @ComponentIdentifier
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

    @Override
    public byte assignInventoryId() {
        if (idCounter + 1 >= 100) idCounter = 1;
        return idCounter++;
    }

    @Override
    public void sendContents(Container container) {
        var id = idToContainer.inverse().get(container);
        if (id == null)
            throw new IllegalArgumentException("This viewer did not open the container " + container.getContainerType());
        sendContentsWithSpecificContainerId(container, id);
    }

    @Override
    public void sendContentsWithSpecificContainerId(Container container, int containerId) {
        var inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId(containerId);
        inventoryContentPacket.setContents(container.toNetworkItemData());
        networkComponent.sendPacket(inventoryContentPacket);
    }

    @Override
    public void sendContentsWithSpecificContainerId(Container container, int containerId, int slot) {
        var inventorySlotPacket = new InventorySlotPacket();
        inventorySlotPacket.setContainerId(containerId);
        inventorySlotPacket.setSlot(container.toNetworkSlotIndex(slot));
        inventorySlotPacket.setItem(container.getItemStack(slot).toNetworkItemData());
        networkComponent.sendPacket(inventorySlotPacket);
    }

    @Override
    public void sendContent(Container container, int slot) {
        var id = idToContainer.inverse().get(container);
        if (id == null)
            throw new IllegalArgumentException("This viewer did not open the container " + container.getContainerType());
        sendContentsWithSpecificContainerId(container, id, slot);
    }

    @Override
    public void onOpen(byte assignedId, Container container) {
        sendContainerOpenPacket(assignedId, container);
        registerOpenedContainer(assignedId, container);

        var containerType = container.getContainerType();
        // We should send the container's contents to client if the container is not held by the entity
        if (containerHolderComponent.getContainer(containerType) == null) {
            sendContents(container);
        }
    }

    protected void registerOpenedContainer(byte assignedId, Container container) {
        idToContainer.put(assignedId, container);
        typeToContainer.put(container.getContainerType(), container);
        container.getContainerType().heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }

    private void sendContainerOpenPacket(byte assignedId, Container container) {
        var containerOpenPacket = new ContainerOpenPacket();
        containerOpenPacket.setId(assignedId);
        containerOpenPacket.setType(container.getContainerType().toNetworkType());
        if (container.hasBlockPos()) {
            containerOpenPacket.setBlockPosition(MathUtils.JOMLVecToCBVec(container.getBlockPos()));
        } else {
            var location = baseComponent.getLocation();
            containerOpenPacket.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
        }
        networkComponent.sendPacket(containerOpenPacket);
    }

    @Override
    public void onClose(byte assignedId, Container container) {
        if (!idToContainer.containsKey(assignedId))
            throw new IllegalArgumentException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        sendContainerClosePacket(assignedId, container);
        unregisterOpenedContainer(assignedId, container);
    }

    protected void unregisterOpenedContainer(byte assignedId, Container container) {
        typeToContainer.remove(idToContainer.remove(assignedId).getContainerType());
        container.getContainerType().heldSlotTypes().forEach(slotType -> slotTypeToFullType.remove(slotType));
    }

    protected void sendContainerClosePacket(byte assignedId, Container container) {
        var containerClosePacket = new ContainerClosePacket();
        containerClosePacket.setId(assignedId);
        containerClosePacket.setType(container.getContainerType().toNetworkType());
        networkComponent.sendPacket(containerClosePacket);
    }

    @Override
    public void onSlotChange(Container container, int slot) {
        var id = idToContainer.inverse().get(container);
        sendContentsWithSpecificContainerId(container, id != null ? id : FixedContainerId.PLAYER_INVENTORY, slot);
    }

    @Override
    public <T extends Container> T getOpenedContainer(FullContainerType<T> type) {
        // Special case: If the player opens their inventory, they also implicitly open a series of other containers, even if not registered
        Container container = null;
        if (isPlayerInventoryOpened()) {
            if (
                    type == FullContainerType.ARMOR ||
                    type == FullContainerType.OFFHAND ||
                    type == CRAFTING_GRID
            ) {
                container = containerHolderComponent.getContainer(type);
            }
        }

        if (container == null) container = typeToContainer.get(type);
        return (T) container;
    }

    @Override
    public <T extends Container> T getOpenedContainerBySlotType(ContainerSlotType slotType) {
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

        if (fullType == null) fullType = slotTypeToFullType.get(slotType);
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
    public @UnmodifiableView BiMap<Byte, Container> getIdToContainer() {
        return idToContainer;
    }

    @Override
    public @UnmodifiableView BiMap<FullContainerType<?>, Container> getTypeToContainer() {
        return typeToContainer;
    }
}
