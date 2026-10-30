package org.allaymc.server.blockentity.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityItemFrameBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.block.ItemFrameUseEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnPunchEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityItemFrameBaseComponentImpl} stores frame contents and
 * rotation and handles player placement, rotation, and removal interactions.
 * The exact {@code ItemAirStack.AIR_STACK} reference marks an empty frame.
 * Content setters synchronize the map-display block property, send the current
 * state to viewers, and refresh comparator output after broadcasting.
 *
 * <p>
 * Interaction handlers consult a cancellable {@code ItemFrameUseEvent} before
 * changing contents or rotation. Insertion copies one item into the frame
 * before attempting to consume the held item, while punching suppresses drops
 * for creative players but still clears accepted removals. These steps do not
 * roll back after a later failure. NBT loading assigns fields directly, so it
 * bypasses the map-property and comparator synchronization performed by
 * ordinary setters.
 *
 * @author daoge_cmd
 */
public class BlockEntityItemFrameBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityItemFrameBaseComponent {

    /**
     * {@code TAG_ITEM} names the retained stack compound. The frame tag
     * constants select contents, byte rotation, and the serialized drop
     * probability during saving.
     */
    protected static final String TAG_ITEM = "Item";
    /**
     * Persisted frame rotation byte.
     */
    protected static final String TAG_ITEM_ROTATION = "ItemRotation";
    /**
     * Persisted item drop probability.
     */
    protected static final String TAG_ITEM_DROP_CHANCE = "ItemDropChance";

    /**
     * {@code itemStack} retains the displayed mutable stack. The shared
     * {@code ItemAirStack.AIR_STACK} reference marks emptiness, and the
     * generated getter exposes the same stack used by saving and interactions.
     */
    @Getter
    protected ItemStack itemStack;
    /**
     * Current eight-position rotation index.
     */
    @Getter
    protected int itemRotation;

    /**
     * Creates common frame state with the shared air-stack empty sentinel. The
     * initial rotation remains zero, and the inherited initialization hook may
     * replace either value from supplied NBT after component wiring.
     */
    public BlockEntityItemFrameBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.itemStack = ItemAirStack.AIR_STACK;
    }

    /**
     * Rotates an occupied frame or places one copied held item into an empty
     * frame. A cancelled use event marks the interaction unsuccessful before
     * either mutation. Placement stores the copy before attempting hand
     * consumption and then plays its sound; rotation updates the index before
     * its sound. The handler marks success after those operations return, and
     * it does not restore earlier frame or inventory changes if delivery,
     * consumption, or sound processing fails.
     */
    @EventHandler
    protected void onBlockInteract(CBlockOnInteractEvent event) {
        if (itemStack != ItemAirStack.AIR_STACK) {
            var e = new ItemFrameUseEvent(event.getInteractInfo().getClickedBlock(), event.getInteractInfo().player(), ItemFrameUseEvent.Action.ROTATE);
            if (!e.call()) {
                event.setSuccess(false);
                return;
            }

            // Wrap the eight rotation indices back to the initial position.
            setItemRotation((itemRotation + 1) % 8);
            event.getDimension().addSound(MathUtils.center(event.getInteractInfo().clickedBlockPos()), SimpleSound.ITEM_FRAME_ROTATE);
        } else {
            var e = new ItemFrameUseEvent(event.getInteractInfo().getClickedBlock(), event.getInteractInfo().player(), ItemFrameUseEvent.Action.PUT);
            if (!e.call()) {
                event.setSuccess(false);
                return;
            }

            // Retain only one copied item before attempting hand consumption;
            // the frame must not alias the player's mutable held stack during
            // this transfer.
            var itemStack = event.getItemStack().copy();
            itemStack.setCount(1);
            setItemStack(itemStack);
            event.getInteractInfo().player().tryConsumeItemInHand();
            event.getDimension().addSound(MathUtils.center(event.getInteractInfo().clickedBlockPos()), SimpleSound.ITEM_ADD);
        }

        event.setSuccess(true);
    }

    /**
     * Removes displayed contents after an accepted drop-use event. Creative
     * players receive no item drop, while other entities produce a drop before
     * the frame is cleared and the removal sound is emitted.
     */
    @EventHandler
    protected void onBlockPunch(CBlockOnPunchEvent event) {
        if (itemStack == ItemAirStack.AIR_STACK) {
            return;
        }

        var e = new ItemFrameUseEvent(event.getCurrentBlock(), event.getEntity(), ItemFrameUseEvent.Action.DROP);
        if (!e.call()) {
            return;
        }

        var dimension = event.getCurrentBlock().getDimension();
        if (!(event.getEntity() instanceof EntityPlayer player) || player.getGameMode() != GameMode.CREATIVE) {
            // Creative players remove the displayed item without creating a
            // drop.
            dimension.dropItem(itemStack, MathUtils.center(event.getCurrentBlock().getPosition()));
        }
        clearItemStack();
        dimension.addSound(MathUtils.center(event.getCurrentBlock().getPosition()), SimpleSound.ITEM_FRAME_REMOVE);
    }

    /**
     * Drops displayed contents when the frame block is replaced. The air-stack
     * sentinel produces no drop; the handler resets contents directly after
     * dropping rather than using the normal setter's world-update sequence.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (this.itemStack != ItemAirStack.AIR_STACK) {
            event.getCurrentBlock().getDimension().dropItem(itemStack, MathUtils.center(event.getCurrentBlock().getPosition()));
            this.itemStack = ItemAirStack.AIR_STACK;
        }
    }

    /**
     * Builds common NBT with frame contents, byte rotation, and drop
     * probability. The stack must be usable for serialization, and this
     * implementation always writes a drop probability of {@code 1.0f}.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putCompound(TAG_ITEM, itemStack.saveNBT());
        builder.putByte(TAG_ITEM_ROTATION, (byte) itemRotation);
        // Persist a certain drop probability for the displayed item.
        builder.putFloat(TAG_ITEM_DROP_CHANCE, 1.0f);
        return builder.build();
    }

    /**
     * Loads common state and directly assigns supplied contents or rotation.
     * The loading path bypasses map-property and comparator synchronization,
     * and the signed rotation byte is retained without the setter's range
     * validation.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // Direct loading avoids the setter's broadcasts, but also bypasses its
        // ITEM_FRAME_MAP_BIT synchronization; loading a map does not repair
        // that block property.
        nbt.listenForCompound(TAG_ITEM, itemNbt -> this.itemStack = NBTIO.getAPI().fromItemStackNBT(itemNbt));
        nbt.listenForByte(TAG_ITEM_ROTATION, itemRotation -> this.itemRotation = itemRotation);
    }

    /**
     * Retains contents, updates the map-display property, then informs viewers
     * and comparators. The supplied stack remains shared and is not normalized
     * or copied. A {@code null} value is retained but cannot support later
     * saving. State and property updates occur before broadcasting, and
     * comparator notification occurs after broadcasting, so a failure leaves
     * whichever earlier changes completed. Callers clear a frame with
     * {@code ItemAirStack.AIR_STACK} to preserve the identity checks used by
     * interaction handlers.
     */
    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        if (this.itemStack instanceof ItemFilledMapStack) {
            this.position.dimension().updateBlockProperty(BlockPropertyTypes.ITEM_FRAME_MAP_BIT, true, this.position);
        } else {
            this.position.dimension().updateBlockProperty(BlockPropertyTypes.ITEM_FRAME_MAP_BIT, false, this.position);
        }
        sendBlockEntityToViewers();
        this.position.dimension().updateComparatorOutputLevel(this.position);
    }

    /**
     * Stores a validated rotation index and informs viewers before comparator
     * refresh. Values outside zero through seven fail before assignment;
     * failures during later delivery leave the new rotation retained.
     */
    @Override
    public void setItemRotation(int itemRotation) {
        Preconditions.checkArgument(itemRotation >= 0 && itemRotation <= 7, "Item rotation must be between 0 and 7");
        this.itemRotation = itemRotation;
        sendBlockEntityToViewers();
        this.position.dimension().updateComparatorOutputLevel(this.position);
    }
}
