package cn.allay.api.entity.interfaces.player;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.component.container.EntityContainerHolderComponent;
import cn.allay.api.entity.component.container.EntityContainerViewerComponent;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.utils.MathUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventorySlotPacket;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */ //<editor-fold desc="EntityPlayerContainerViewerComponentImpl">
public class EntityPlayerContainerViewerComponentImpl implements EntityContainerViewerComponent {

    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_viewer_component");

    protected byte idCounter = 0;
    @Manager
    protected ComponentManager<EntityPlayer> manager;
    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;
    protected Client client;

    protected HashBiMap<Byte, Container> id2ContainerBiMap = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
    protected HashBiMap<FullContainerType<?>, Container> type2ContainerBiMap = HashBiMap.create(new Object2ObjectOpenHashMap<>());

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        client = manager.getComponentedObject().getClient();
    }

    @Override
    public byte assignInventoryId() {
        if (idCounter + 1 >= 100) {
            idCounter = 0;
        }
        return idCounter++;
    }

    @Override
    public void sendContents(Container container) {
        var id = id2ContainerBiMap.inverse().get(container);
        if (id == null)
            throw new IllegalArgumentException("This viewer did not open the container " + container.getContainerType());
        sendContentsWithSpecificContainerId(container, id);
    }

    @Override
    public void sendContentsWithSpecificContainerId(Container container, int containerId) {
        var inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId(containerId);
        inventoryContentPacket.setContents(container.toNetworkItemData());
        client.sendPacket(inventoryContentPacket);
    }

    @Override
    public void sendContentsWithSpecificContainerId(Container container, int containerId, int slot) {
        var inventorySlotPacket = new InventorySlotPacket();
        inventorySlotPacket.setContainerId(containerId);
        inventorySlotPacket.setSlot(slot);
        inventorySlotPacket.setItem(container.getItemStack(slot).toNetworkItemData());
        client.sendPacket(inventorySlotPacket);
    }

    @Override
    public void sendContent(Container container, int slot) {
        var id = id2ContainerBiMap.inverse().get(container);
        if (id == null)
            throw new IllegalArgumentException("This viewer did not open the container " + container.getContainerType());
        sendContentsWithSpecificContainerId(container, id, slot);
    }

    @Override
    public void onOpen(byte assignedId, Container container) {
        var containerOpenPacket = new ContainerOpenPacket();
        containerOpenPacket.setId(assignedId);
        var containerType = container.getContainerType();
        containerOpenPacket.setType(containerType.toNetworkType());
        if (container.hasBlockPos()) {
            containerOpenPacket.setBlockPosition(MathUtils.JOMLVecTocbVec(container.getBlockPos()));
        } else {
            var location = manager.getComponentedObject().getLocation();
            containerOpenPacket.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
        }
        client.sendPacket(containerOpenPacket);

        id2ContainerBiMap.put(assignedId, container);
        type2ContainerBiMap.put(container.getContainerType(), container);

        //We should send the container's contents to client if the container is not held by the entity
        if (containerHolderComponent.getContainer(containerType) == null) {
            sendContents(container);
        }
    }

    @Override
    public void onClose(byte assignedId, Container container) {
        if (!id2ContainerBiMap.containsKey(assignedId))
            throw new IllegalArgumentException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        var containerClosePacket = new ContainerClosePacket();
        containerClosePacket.setId(assignedId);
        client.sendPacket(containerClosePacket);

        type2ContainerBiMap.remove(id2ContainerBiMap.remove(assignedId).getContainerType());
    }

    @Override
    public void onSlotChange(Container container, int slot) {
        var id = id2ContainerBiMap.inverse().get(container);
        //"0" is player's inventory
        sendContentsWithSpecificContainerId(container, id != null ? id : 0, slot);
    }

    @Override
    @Nullable
    public <T extends Container> T getOpenedContainer(FullContainerType<T> type) {
        return (T) type2ContainerBiMap.get(type);
    }

    @Override
    @Nullable
    public Container getOpenedContainer(byte id) {
        return id2ContainerBiMap.get(id);
    }

    @Override
    public @UnmodifiableView BiMap<Byte, Container> getId2ContainerBiMap() {
        return id2ContainerBiMap;
    }

    @Override
    public @UnmodifiableView BiMap<FullContainerType<?>, Container> getType2ContainerBiMap() {
        return type2ContainerBiMap;
    }
}
