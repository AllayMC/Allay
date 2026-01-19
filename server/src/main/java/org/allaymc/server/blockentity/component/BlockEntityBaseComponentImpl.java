package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveCleanNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

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
    @ComponentObject
    protected BlockEntity thisBlockEntity;

    @Getter
    protected BlockEntityType<?> blockEntityType;
    @Getter
    protected Position3ic position;
    @Getter
    @Setter
    protected String customName;
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer;

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = new Position3i(0, 0, 0, initInfo.dimension());
        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
    }

    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
    }

    public void tick(long currentTick) {
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        manager.callEvent(new CBlockEntitySaveNBTEvent(builder));
        builder.putString(TAG_ID, blockEntityType.getName())
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
        return builder.build();
    }

    @Override
    public NbtMap saveCleanNBT() {
        var builder = saveNBT().toBuilder();
        // Remove position
        builder.remove(TAG_X);
        builder.remove(TAG_Y);
        builder.remove(TAG_Z);
        // Let components remove their own position-related fields
        manager.callEvent(new CBlockEntitySaveCleanNBTEvent(builder));
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        manager.callEvent(new CBlockEntityLoadNBTEvent(nbt));
        nbt.listenForString(TAG_CUSTOM_NAME, customName -> this.customName = customName);

        var pos = new Position3i(this.position);
        nbt.listenForInt(TAG_X, value -> pos.x = value);
        nbt.listenForInt(TAG_Y, value -> pos.y = value);
        nbt.listenForInt(TAG_Z, value -> pos.z = value);
        this.position = pos;

        nbt.listenForCompound(TAG_PDC, customNbt -> {
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });
    }

    /**
     * Called when a player entity changes the block entity. This is mostly used in sign block where
     * the player can edit the content of the sign. Different from {@link #loadNBT(NbtMap)}, this method
     * may do more secure checks to protect the server from being hacked by malformed nbt data.
     *
     * @param player the player entity who changes the block entity
     * @param nbt    the new nbt of the block entity.
     */
    public void applyPlayerChange(EntityPlayer player, NbtMap nbt) {
        loadNBT(nbt);
    }

    /**
     * Checks whether the block entity should be sent to the client.
     */
    public boolean sendToClient() {
        return true;
    }

    /**
     * @see #sendBlockEntityToViewers(boolean)
     */
    public void sendBlockEntityToViewers() {
        sendBlockEntityToViewers(true);
    }

    /**
     * Sends the block entity to its viewers.
     *
     * @param immediately whether the block entity should be sent immediately. When {@code false}, the block
     *                    entity will be sent in the next tick of the chunk that the block entity is currently in.
     */
    public void sendBlockEntityToViewers(boolean immediately) {
        var pos = getPosition();
        var chunk = pos.dimension().getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        Objects.requireNonNull(chunk, "The chunk located at pos " + pos + " is not loaded!");
        if (immediately) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewBlockEntity(thisBlockEntity);
                }
            });
        } else {
            chunk.forEachChunkLoaderLater(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewBlockEntity(thisBlockEntity);
                }
            });
        }
    }
}
