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
 * Container holder component for human like entities such as zombie and armor stand
 *
 * @author daoge_cmd
 */
public class EntityHumanLikeContainerHolderComponentImpl extends EntityContainerHolderComponentImpl {

    protected static final String TAG_ARMOR = "Armor";
    protected static final String TAG_MAINHAND = "Mainhand";
    protected static final String TAG_OFFHAND = "Offhand";

    public EntityHumanLikeContainerHolderComponentImpl() {
        super();
        // Use suppliers so thisArmorStand will be resolved when actually needed
        addContainer(new EntityArmorContainerImpl(() -> thisEntity));
        addContainer(new EntityHandContainerImpl(() -> thisEntity));
        addContainer(new EntityOffhandContainerImpl(() -> thisEntity));
    }

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

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt()
                .putList(TAG_ARMOR, NbtType.COMPOUND, getContainer(ContainerTypes.ARMOR).saveNBT())
                .putList(TAG_MAINHAND, NbtType.COMPOUND, getContainer(ContainerTypes.ENTITY_HAND).saveNBT())
                .putList(TAG_OFFHAND, NbtType.COMPOUND, getContainer(ContainerTypes.OFFHAND).saveNBT());
    }
}
