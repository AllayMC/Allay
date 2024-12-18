package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnNeighborUpdateEvent;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class BlockEntityBaseComponentImpl implements BlockEntityBaseComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_base_component");

    @Manager
    protected ComponentManager manager;

    @Getter
    protected BlockEntityType<?> blockEntityType;
    @Getter
    protected Position3ic position;
    @Getter
    @Setter
    protected String customName;

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.dimension());
    }

    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putString("id", blockEntityType.getName())
                .putInt("x", position.x())
                .putInt("y", position.y())
                .putInt("z", position.z())
                .putBoolean("isMovable", true);
        if (customName != null) {
            builder.putString("CustomName", customName);
        }
        var event = new CBlockEntitySaveNBTEvent(builder);
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

        var event = new CBlockEntityLoadNBTEvent(nbt);
        manager.callEvent(event);
    }

    public void onNeighborUpdate(CBlockOnNeighborUpdateEvent event) {
        manager.callEvent(event);
    }

    public void onPlace(CBlockOnPlaceEvent event) {
        manager.callEvent(event);
    }

    public void onReplace(CBlockOnReplaceEvent event) {
        manager.callEvent(event);
    }

    public void onInteract(CBlockOnInteractEvent event) {
        manager.callEvent(event);
    }
}
