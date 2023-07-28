package cn.allay.api.entity.component.impl.container;

import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponent;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class EntityContainerViewerComponentImpl implements EntityContainerViewerComponent, EntityComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_viewer_component");

    protected byte idCounter = 0;
    @Dependency
    protected EntityPlayerControllerComponent playerControllerComponent;
    @Dependency
    protected EntityBaseComponent baseComponent;

    protected HashBiMap<Byte, Container> id2ContainerBiMap = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
    protected HashBiMap<FullContainerType, Container> type2ContainerBiMap = HashBiMap.create(new Object2ObjectOpenHashMap<>());

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public byte assignInventoryId() {
        if (idCounter + 1 >= 100) {
            idCounter = 0;
        }
        return idCounter++;
    }

    @Override
    @Impl
    public void sendContents(Container container) {
        if (playerControllerComponent.getClient() == null) return;

        var client = playerControllerComponent.getClient();
        var inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId(container.getContainerType().id());
        inventoryContentPacket.setContents(container.toNetworkItemData());
        client.sendPacket(inventoryContentPacket);
    }

    @Override
    @Impl
    public void sendContent(Container container, int slot) {
        if (playerControllerComponent.getClient() == null) return;

        var client = playerControllerComponent.getClient();
        var inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId(container.getContainerType().id());
        inventoryContentPacket.setContents(List.of(container.getItemStack(slot).toNetworkItemData()));
        client.sendPacket(inventoryContentPacket);
    }

    @Override
    @Impl
    public void onOpen(byte assignedId, Container container) {
        if (!container.getContainerType().canBeOpenedAlone())
            throw new IllegalArgumentException("Trying to open a container which cannot be opened alone! Type: " + container.getContainerType());
        if (playerControllerComponent.getClient() == null)
            return;

        var client = playerControllerComponent.getClient();
        var containerOpenPacket = new ContainerOpenPacket();
        containerOpenPacket.setId(assignedId);
        containerOpenPacket.setType(container.getContainerType().toNetworkType());
        var location = baseComponent.getLocation();
        containerOpenPacket.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
        client.sendPacket(containerOpenPacket);

        id2ContainerBiMap.put(assignedId, container);
        type2ContainerBiMap.put(container.getContainerType(), container);
    }

    @Override
    @Impl
    public void onClose(byte assignedId, Container container) {
        if (!id2ContainerBiMap.containsKey(assignedId))
            throw new IllegalArgumentException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        if (playerControllerComponent.getClient() == null) return;

        var client = playerControllerComponent.getClient();
        var containerClosePacket = new ContainerClosePacket();
        containerClosePacket.setId(assignedId);
        client.sendPacket(containerClosePacket);

        type2ContainerBiMap.remove(id2ContainerBiMap.remove(assignedId).getContainerType());
    }

    @Override
    @Impl
    public void onSlotChange(Container container, int slot, ItemStack current) {
        //TODO
    }

    @Override
    @Nullable
    @Impl
    public Container getOpenedContainer(FullContainerType type) {
        return id2ContainerBiMap.get(type);
    }

    @Override
    @Nullable
    @Impl
    public Container getOpenedContainer(byte id) {
        return id2ContainerBiMap.get(id);
    }

    @Override
    @Impl
    public @UnmodifiableView BiMap<Byte, Container> getId2ContainerBiMap() {
        return id2ContainerBiMap;
    }

    @Override
    @Impl
    public @UnmodifiableView BiMap<FullContainerType, Container> getType2ContainerBiMap() {
        return type2ContainerBiMap;
    }
}
