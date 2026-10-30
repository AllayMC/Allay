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
 * {@code EntityThrownTridentBaseComponentImpl} represents the item carried by
 * a thrown trident, its preferred return slot, and its return flag. New
 * instances carry a fresh trident item, while persistence restores the item
 * and slot independently of the transient returning state.
 *
 * @author daoge_cmd
 */
public class EntityThrownTridentBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityThrownTridentBaseComponent {

    /**
     * {@code TAG_TRIDENT} names the item compound restored and written for the
     * retained thrown trident stack.
     */
    protected static final String TAG_TRIDENT = "Trident";
    /**
     * {@code TAG_FAVORED_SLOT} names the integer inventory preference restored
     * and written for loyalty return handling.
     */
    protected static final String TAG_FAVORED_SLOT = "favoredSlot";

    /**
     * {@code tridentItem} retains the mutable carried item or {@code null}.
     * Generated accessors expose the same stack, which impact durability and
     * return handling can modify.
     */
    @Getter
    @Setter
    protected ItemTridentStack tridentItem;
    /**
     * {@code favoredSlot} stores the preferred shooter inventory index. The
     * value {@code -1} disables the slot preference without changing the
     * carried item enchantments.
     */
    @Getter
    @Setter
    protected int favoredSlot = -1;
    /**
     * Current loyalty return presentation flag.
     */
    @Getter
    protected boolean returning;

    /**
     * Creates the trident base with a fresh one-item trident stack. The return
     * preference starts at {@code -1}, and returning remains {@code false}
     * until callers or impact physics enable it.
     */
    public EntityThrownTridentBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.tridentItem = ItemTypes.TRIDENT.createItemStack(1);
    }

    /**
     * Replaces the returning flag and broadcasts the updated trident state.
     * The flag selects homing and collision-bypass behavior in trident
     * physics, and this setter does not change item ownership.
     */
    @Override
    public void setReturning(boolean returning) {
        this.returning = returning;
        broadcastState();
    }

    /**
     * Returns the thin trident local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.025, 0.0, -0.025, 0.025, 0.25, 0.025);
    }

    /**
     * Loads inherited data and decodes a present item compound as {@code
     * ItemTridentStack}. Incompatible decoded item types fail the cast, while
     * a missing compound retains the existing item and missing slot data
     * retains its preference.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound(TAG_TRIDENT, tridentNbt -> {
            this.tridentItem = (ItemTridentStack) NBTIO.getAPI().fromItemStackNBT(tridentNbt);
        });
        nbt.listenForInt(TAG_FAVORED_SLOT, slot -> this.favoredSlot = slot);
    }

    /**
     * Returns inherited entity data with the preferred slot and any retained
     * trident item. A {@code null} item omits its compound, and this override
     * does not persist the transient returning flag.
     */
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
