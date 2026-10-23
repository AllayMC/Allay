package org.allaymc.server.entity.component.humanlike;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.container.impl.EntityArmorContainerImpl;
import org.allaymc.server.container.impl.EntityHandContainerImpl;
import org.allaymc.server.container.impl.EntityOffhandContainerImpl;
import org.allaymc.server.entity.component.EntityContainerHolderComponentImpl;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.cloudburstmc.nbt.NbtType;

/**
 * {@code EntityHumanLikeContainerHolderComponentImpl} supplies armor,
 * main-hand, and offhand containers for humanlike entities. Its load and save
 * handlers persist each equipment group under a separate compound-list tag.
 * Container callbacks resolve the inherited static {@code thisEntity} through
 * suppliers, so later owner injection can change the entity observed by those
 * callbacks.
 *
 * @author daoge_cmd
 */
public class EntityHumanLikeContainerHolderComponentImpl extends EntityContainerHolderComponentImpl {

    // TAG_ARMOR, TAG_MAINHAND, and TAG_OFFHAND keep the three equipment groups
    // separate so loading one list targets its matching container type.
    /**
     * NBT compound-list key for armor equipment.
     */
    protected static final String TAG_ARMOR = "Armor";
    /**
     * NBT compound-list key for main-hand equipment.
     */
    protected static final String TAG_MAINHAND = "Mainhand";
    /**
     * NBT compound-list key for offhand equipment.
     */
    protected static final String TAG_OFFHAND = "Offhand";

    /**
     * Creates the three equipment containers with deferred entity lookups. The
     * suppliers read the inherited static {@code thisEntity} when callbacks
     * run, so construction does not capture an owner before component injection
     * assigns it.
     */
    public EntityHumanLikeContainerHolderComponentImpl() {
        super();
        // Component injection follows construction. Defer owner lookup until a
        // container callback runs rather than retaining the pre-injection value.
        addContainer(new EntityArmorContainerImpl(() -> thisEntity));
        addContainer(new EntityHandContainerImpl(() -> thisEntity));
        addContainer(new EntityOffhandContainerImpl(() -> thisEntity));
    }

    /**
     * Loads equipment groups whose NBT entries are compound lists. Absent
     * entries or entries of another type leave the corresponding containers
     * unchanged; each accepted list is delegated to that container's own
     * item-loading behavior.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt ->
                getContainer(ContainerTypes.ARMOR).loadNBT(armorNbt));
        nbt.listenForList(TAG_MAINHAND, NbtType.COMPOUND, handNbt ->
                getContainer(ContainerTypes.ENTITY_HAND).loadNBT(handNbt));
        nbt.listenForList(TAG_OFFHAND, NbtType.COMPOUND, offhandNbt ->
                getContainer(ContainerTypes.OFFHAND).loadNBT(offhandNbt));
    }

    /**
     * Writes armor, main-hand, and offhand equipment lists into the NBT
     * builder.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt()
                .putList(TAG_ARMOR, NbtType.COMPOUND, getContainer(ContainerTypes.ARMOR).saveNBT())
                .putList(TAG_MAINHAND, NbtType.COMPOUND, getContainer(ContainerTypes.ENTITY_HAND).saveNBT())
                .putList(TAG_OFFHAND, NbtType.COMPOUND, getContainer(ContainerTypes.OFFHAND).saveNBT());
    }
}
