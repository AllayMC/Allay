package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityThrownTridentBaseComponent;
import org.allaymc.api.item.interfaces.ItemTridentStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
public class EntityThrownTridentBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityThrownTridentBaseComponent {

    protected static final String TAG_TRIDENT = "Trident";
    protected static final String TAG_FAVORED_SLOT = "favoredSlot";

    @Getter
    @Setter
    protected ItemTridentStack tridentItem;
    @Getter
    @Setter
    protected int favoredSlot = -1;
    @Getter
    protected boolean returning;

    public EntityThrownTridentBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.tridentItem = ItemTypes.TRIDENT.createItemStack(1);
    }

    @Override
    public void setReturning(boolean returning) {
        this.returning = returning;
        broadcastState();
    }

    @Override
    public AABBdc getBaseAABB() {
        // Trident hitbox is thin and long
        return new AABBd(-0.025, 0.0, -0.025, 0.025, 0.25, 0.025);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound(TAG_TRIDENT, tridentNbt -> {
            this.tridentItem = (ItemTridentStack) NBTIO.getAPI().fromItemStackNBT(tridentNbt);
        });
        nbt.listenForInt(TAG_FAVORED_SLOT, slot -> this.favoredSlot = slot);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        if (tridentItem != null) {
            builder.putCompound(TAG_TRIDENT, tridentItem.saveNBT());
        }
        builder.putInt(TAG_FAVORED_SLOT, favoredSlot);

        return builder.build();
    }
}
