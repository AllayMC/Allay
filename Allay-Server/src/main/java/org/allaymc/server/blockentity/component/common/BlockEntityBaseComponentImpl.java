package org.allaymc.server.blockentity.component.common;

import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.common.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.event.BlockEntityLoadNBTEvent;
import org.allaymc.api.blockentity.component.event.BlockEntitySaveNBTEvent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityBaseComponentImpl<T extends BlockEntity> implements BlockEntityBaseComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_base_component");

    @Getter(AccessLevel.NONE)
    @Manager
    protected ComponentManager<T> manager;

    protected BlockEntityType<T> blockEntityType;
    protected Position3ic position;
    protected String customName = "";

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo<T> initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.dimension());
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        loadNBT(((BlockEntityInitInfo<?>) initInfo).nbt());
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putString("id", blockEntityType.getName())
                .putInt("x", position.x())
                .putInt("y", position.y())
                .putInt("z", position.z())
                .putString("CustomName", customName)
                .putBoolean("isMovable", true);
        var event = new BlockEntitySaveNBTEvent(builder);
        manager.callEvent(event);
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        nbt.listenForString("CustomName", customName -> this.customName = customName);

        var pos = new Position3i(position);
        pos.x = nbt.getInt("x", position.x());
        pos.y = nbt.getInt("y", position.y());
        pos.z = nbt.getInt("z", position.z());
        position = pos;

        var event = new BlockEntityLoadNBTEvent(nbt);
        manager.callEvent(event);
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
