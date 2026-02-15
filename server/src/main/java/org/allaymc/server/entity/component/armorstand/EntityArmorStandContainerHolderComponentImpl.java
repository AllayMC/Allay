package org.allaymc.server.entity.component.armorstand;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.container.impl.ArmorStandArmorContainerImpl;
import org.allaymc.server.container.impl.ArmorStandHandContainerImpl;
import org.allaymc.server.container.impl.ArmorStandOffhandContainerImpl;
import org.allaymc.server.entity.component.EntityContainerHolderComponentImpl;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.cloudburstmc.nbt.NbtType;

/**
 * Container holder component for armor stands.
 *
 * @author daoge_cmd
 */
public class EntityArmorStandContainerHolderComponentImpl extends EntityContainerHolderComponentImpl {

    protected static final String TAG_ARMOR = "Armor";
    protected static final String TAG_MAINHAND = "Mainhand";
    protected static final String TAG_OFFHAND = "Offhand";

    @ComponentObject
    protected EntityArmorStand thisArmorStand;

    public EntityArmorStandContainerHolderComponentImpl() {
        super();
        // Use suppliers so thisArmorStand will be resolved when actually needed
        addContainer(new ArmorStandArmorContainerImpl(() -> thisArmorStand));
        addContainer(new ArmorStandHandContainerImpl(() -> thisArmorStand));
        addContainer(new ArmorStandOffhandContainerImpl(() -> thisArmorStand));
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList(TAG_ARMOR, NbtType.COMPOUND, armorNbt ->
                getContainer(ContainerTypes.ARMOR).loadNBT(armorNbt));
        nbt.listenForList(TAG_MAINHAND, NbtType.COMPOUND, handNbt ->
                getContainer(ContainerTypes.ARMOR_STAND_HAND).loadNBT(handNbt));
        nbt.listenForList(TAG_OFFHAND, NbtType.COMPOUND, offhandNbt ->
                getContainer(ContainerTypes.OFFHAND).loadNBT(offhandNbt));
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt()
                .putList(TAG_ARMOR, NbtType.COMPOUND, getContainer(ContainerTypes.ARMOR).saveNBT())
                .putList(TAG_MAINHAND, NbtType.COMPOUND, getContainer(ContainerTypes.ARMOR_STAND_HAND).saveNBT())
                .putList(TAG_OFFHAND, NbtType.COMPOUND, getContainer(ContainerTypes.OFFHAND).saveNBT());
    }
}
