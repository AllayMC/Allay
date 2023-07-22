package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponent;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.inventory.Inventory;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.bedrock.packet.InventoryContentPacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class EntityInventoryViewerComponentImpl implements EntityInventoryViewerComponent, EntityComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_viewer_component");

    protected byte idCounter = 0;
    @Dependency
    protected EntityPlayerControllerComponent playerControllerComponent;
    @Dependency
    protected EntityBaseComponent baseComponent;

    protected Inventory openedInventory;
    protected byte openedInventoryId = -1;

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
    public void sendContents(Inventory inventory) {
        if (playerControllerComponent.getClient() == null) return;
        var client = playerControllerComponent.getClient();
        var inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId(inventory.getType().getId());
        inventoryContentPacket.setContents(inventory.toNetworkItemData());
        client.sendPacket(inventoryContentPacket);
    }

    @Override
    @Impl
    public void sendContent(Inventory inventory, int slot) {
        if (playerControllerComponent.getClient() == null) return;
        var client = playerControllerComponent.getClient();
        var inventoryContentPacket = new InventoryContentPacket();
        inventoryContentPacket.setContainerId(inventory.getType().getId());
        inventoryContentPacket.setContents(List.of(inventory.getItemStack(slot).toNetworkItemData()));
        client.sendPacket(inventoryContentPacket);
    }

    @Override
    @Impl
    public void onOpen(byte assignedId, Inventory inventory) {
        if (openedInventoryId != -1)
            return;
        if (playerControllerComponent.getClient() == null)
            return;
        var client = playerControllerComponent.getClient();
        var containerOpenPacket = new ContainerOpenPacket();
        containerOpenPacket.setId(assignedId);
        containerOpenPacket.setType(inventory.getType());
        var location = baseComponent.getLocation();
        containerOpenPacket.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
        client.sendPacket(containerOpenPacket);
        openedInventory = inventory;
        openedInventoryId = assignedId;
    }

    @Override
    @Impl
    public void onClose(byte assignedId, Inventory inventory) {
        if (assignedId != openedInventoryId)
            throw new IllegalArgumentException("Inventory id mismatch");
        if (playerControllerComponent.getClient() == null) return;
        var client = playerControllerComponent.getClient();
        var containerClosePacket = new ContainerClosePacket();
        containerClosePacket.setId(assignedId);
        client.sendPacket(containerClosePacket);
        openedInventory = null;
        openedInventoryId = -1;
    }

    @Override
    @Impl
    @Nullable
    public Inventory getOpenedInventory() {
        return openedInventory;
    }
}
