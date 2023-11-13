package org.allaymc.api.blockentity.component.base;

import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class BlockEntityBaseComponentImpl<T extends BlockEntity> implements BlockEntityBaseComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_base_component");

    @Manager
    protected ComponentManager<T> manager;

    protected BlockEntityType<T> blockEntityType;
    protected Position3ic position;
    protected String customName = "";

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo<T> initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.dimension());
        loadNBT(initInfo.nbt());
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getBlockEntityType() {
        return blockEntityType;
    }

    @Override
    public Position3ic getPosition() {
        return position;
    }

    @Override
    public NbtMap saveNBT() {
        return NbtMap.builder()
                .putString("id", blockEntityType.getBlockEntityId())
                .putInt("x", position.x())
                .putInt("y", position.y())
                .putInt("z", position.z())
                .putString("CustomName", customName)
                .putBoolean("isMovable", true)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        if (nbt.containsKey("CustomName")) {
            this.customName = nbt.getString("CustomName");
        }
        var pos = new Position3i(position);
        pos.x = nbt.getInt("x", position.x());
        pos.y = nbt.getInt("y", position.y());
        pos.z = nbt.getInt("z", position.z());
        position = pos;
    }

    @Override
    public void onNeighborUpdate(BlockOnNeighborUpdateEvent event) {
        manager.callEvent(event);
    }

    @Override
    public void onPlace(BlockOnPlaceEvent event) {
        manager.callEvent(event);
    }

    @Override
    public void onReplace(BlockOnReplaceEvent event) {
        manager.callEvent(event);
    }

    @Override
    public void onInteract(BlockOnInteractEvent event) {
        manager.callEvent(event);
    }
}
