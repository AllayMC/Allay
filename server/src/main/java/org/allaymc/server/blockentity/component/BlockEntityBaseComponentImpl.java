package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.event.*;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class BlockEntityBaseComponentImpl implements BlockEntityBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_base_component");

    protected static final String TAG_ID = "id";
    protected static final String TAG_X = "x";
    protected static final String TAG_Y = "y";
    protected static final String TAG_Z = "z";
    protected static final String TAG_IS_MOVABLE = "isMovable";
    protected static final String TAG_CUSTOM_NAME = "CustomName";
    protected static final String TAG_PDC = "PDC";

    @Manager
    protected ComponentManager manager;

    @Getter
    protected BlockEntityType<?> blockEntityType;
    @Getter
    protected Position3ic position;
    @Getter
    @Setter
    protected String customName;
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.dimension());
    }

    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
    }

    public void tick(long currentTick) {}

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putString(TAG_ID, blockEntityType.getName())
                .putInt(TAG_X, position.x())
                .putInt(TAG_Y, position.y())
                .putInt(TAG_Z, position.z())
                .putBoolean(TAG_IS_MOVABLE, true);
        if (customName != null) {
            builder.putString(TAG_CUSTOM_NAME, customName);
        }
        if (!persistentDataContainer.isEmpty()) {
            builder.put(TAG_PDC, persistentDataContainer.toNbt());
        }
        var event = new CBlockEntitySaveNBTEvent(builder);
        manager.callEvent(event);
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        nbt.listenForString(TAG_CUSTOM_NAME, customName -> this.customName = customName);

        var pos = new Position3i(position);
        pos.x = nbt.getInt(TAG_X, position.x());
        pos.y = nbt.getInt(TAG_Y, position.y());
        pos.z = nbt.getInt(TAG_Z, position.z());
        position = pos;

        nbt.listenForCompound(TAG_PDC, customNbt -> {
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

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

    public void onPunch(CBlockOnPunchEvent event) {
        manager.callEvent(event);
    }
}
