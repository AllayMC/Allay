package cn.allay.api.blockentity.component.base;

import cn.allay.api.block.component.event.BlockOnInteractEvent;
import cn.allay.api.block.component.event.BlockOnNeighborChangedEvent;
import cn.allay.api.block.component.event.BlockOnPlaceEvent;
import cn.allay.api.block.component.event.BlockOnReplaceEvent;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.math.position.Position3ic;
import lombok.Builder;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

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

    protected Consumer<BlockOnNeighborChangedEvent> onNeighborChanged = event -> {
    };
    protected Consumer<BlockOnInteractEvent> onInteract = event -> {
    };
    protected Consumer<BlockOnPlaceEvent> onPlace = event -> {
    };
    protected Consumer<BlockOnReplaceEvent> onReplace = event -> {
    };

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo<T> initInfo) {
        this(initInfo, null, null, null, null);
    }

    @Builder
    public BlockEntityBaseComponentImpl(
            BlockEntityInitInfo<T> initInfo,
            @Nullable Consumer<BlockOnNeighborChangedEvent> onNeighborChanged,
            @Nullable Consumer<BlockOnInteractEvent> onInteract,
            @Nullable Consumer<BlockOnPlaceEvent> onPlace,
            @Nullable Consumer<BlockOnReplaceEvent> onReplace
    ) {
        if (onNeighborChanged != null) this.onNeighborChanged = onNeighborChanged;
        if (onInteract != null) this.onInteract = onInteract;
        if (onPlace != null) this.onPlace = onPlace;
        if (onReplace != null) this.onReplace = onReplace;
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.world());
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
    public void onNeighborChanged(BlockOnNeighborChangedEvent event) {
        manager.callEvent(event);
        onNeighborChanged.accept(event);
    }

    @Override
    public void onPlace(BlockOnPlaceEvent event) {
        manager.callEvent(event);
        onPlace.accept(event);
    }

    @Override
    public void onReplace(BlockOnReplaceEvent event) {
        manager.callEvent(event);
        onReplace.accept(event);
    }

    @Override
    public void onInteract(BlockOnInteractEvent event) {
        manager.callEvent(event);
        onInteract.accept(event);
    }
}
