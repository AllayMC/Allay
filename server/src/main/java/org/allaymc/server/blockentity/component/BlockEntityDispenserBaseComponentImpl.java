package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.eventbus.event.block.BlockDispenseEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.block.dispenser.DefaultDispenseBehavior;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockEntityDispenserBaseComponentImpl} selects inventory items and
 * runs their registered dispensing behaviors. Each attempt selects uniformly
 * among nonempty slots rather than among individual items, and an event can
 * cancel the attempt or replace the behavior before execution.
 *
 * <p>
 * The behavior receives a copied stack with count one and performs its world
 * action before the original inventory is consumed. Only successful results
 * reduce that source and trigger remainder handling. The source slot is reused
 * when emptied; otherwise the remainder is inserted or its uninserted portion
 * is dropped. Behavior and listener failures have no compensating rollback, so
 * already completed world or inventory changes remain possible.
 *
 * @author daoge_cmd
 */
public class BlockEntityDispenserBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityDispenserBaseComponent {

    /**
     * {@code containerHolderComponent} supplies the live source inventory;
     * dispensing mutates its selected stack only after the chosen behavior
     * reports success.
     */
    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    /**
     * Creates dispenser base state using the supplied block-entity
     * initialization information.
     */
    public BlockEntityDispenserBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns the base save data without adding dispenser-specific NBT fields.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT();
    }

    /**
     * Applies the supplied NBT through the shared block-entity base loader.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
    }

    /**
     * Attempts one event-controlled action from a randomly selected nonempty
     * slot. Empty inventory or failed behavior plays the failure sound;
     * cancellation returns {@code false} without that sound or normal source
     * consumption.
     *
     * <p>
     * The selected behavior receives a one-item copy before the source stack is
     * reduced. A successful result updates the source slot and then handles any
     * nonair remainder; failure leaves normal consumption unapplied. Events and
     * behaviors can observe shared state, and exceptions after world effects or
     * inventory writes do not restore earlier state.
     */
    @Override
    public boolean tryDispense() {
        var container = containerHolderComponent.getContainer();
        var slot = selectRandomNonEmptySlot(container);
        if (slot == -1) {
            playFailSound();
            return false;
        }

        var item = container.getItemStack(slot);
        var block = new Block(position.dimension(), position);
        var facing = getFacing(block);

        var behavior = getBehavior(item);
        var event = new BlockDispenseEvent(block, item, facing, behavior);
        if (!event.call()) {
            return false;
        }

        behavior = event.getBehavior();
        var itemToDispense = item.copy();
        itemToDispense.setCount(1);

        // Execute the copied item's world action before consuming the source; a
        // failed result therefore skips normal consumption, while exceptions
        // cannot undo world effects.
        var result = behavior.dispense(block, facing, itemToDispense);

        if (result.succeeded()) {
            item.reduceCount(1);
            if (item.getCount() <= 0) {
                container.clearSlot(slot);
            } else {
                container.notifySlotChange(slot);
            }

            if (result.remainingItem() != ItemAirStack.AIR_STACK) {
                handleRemainingItem(container, slot, item, result.remainingItem());
            }
        } else {
            playFailSound();
        }

        return result.succeeded();
    }

    /**
     * Returns the registered behavior for the supplied item's type. An absent
     * registry entry selects {@code DefaultDispenseBehavior.INSTANCE}, allowing
     * unregistered item types to use the ordinary item-ejection behavior.
     */
    protected DispenseBehavior getBehavior(ItemStack item) {
        var behavior = Registries.DISPENSER_BEHAVIORS.get(item.getItemType());
        // Unregistered item types use ordinary item ejection instead of
        // preventing the selected inventory slot from dispensing altogether.
        return behavior != null ? behavior : DefaultDispenseBehavior.INSTANCE;
    }

    /**
     * Selects a uniformly random slot among nonnull, nonair inventory entries.
     * An inventory with no eligible entries returns {@code -1}; stack counts do
     * not weight selection, so each eligible slot has the same chance.
     */
    protected int selectRandomNonEmptySlot(Container container) {
        var stacks = container.getItemStackArray();
        var random = ThreadLocalRandom.current();

        int r = 1;
        int selectedSlot = -1;

        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i] != null && stacks[i] != ItemAirStack.AIR_STACK) {
                // Reservoir replacement gives each eligible slot equal
                // selection probability without allocating a separate list or
                // weighting slots by their stack counts.
                if (random.nextInt(r++) == 0) {
                    selectedSlot = i;
                }
            }
        }

        return selectedSlot;
    }

    /**
     * Reads the dispenser's face from its {@code FACING_DIRECTION} property.
     * The property uses {@code BlockFace} indices; an out-of-range index yields
     * {@code null} through {@code BlockFace.fromIndex} rather than selecting a
     * fallback.
     */
    protected BlockFace getFacing(Block block) {
        var facingIndex = block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION);
        return BlockFace.fromIndex(facingIndex);
    }

    /**
     * Places a successful behavior's remainder after source consumption. An
     * emptied source slot receives the remainder directly; otherwise
     * {@code tryAddItem} may merge it across the container before any leftover
     * is dropped at the block center.
     *
     * <p>
     * The insertion routine mutates the supplied remainder count, so a failed
     * full insertion still retains earlier successful merges and the drop
     * contains only its residual items. Neither a later insertion exception nor
     * a failed world drop reverses the source consumption or any completed
     * inventory merges.
     */
    protected void handleRemainingItem(Container container, int originalSlot, ItemStack originalItem, ItemStack remainingItem) {
        if (originalItem.getCount() <= 0) {
            container.setItemStack(originalSlot, remainingItem);
        } else {
            // Insertion can partially merge and reduce the remainder before
            // reporting failure; drop only the surviving portion rather than
            // duplicating those merged items.
            var slot = container.tryAddItem(remainingItem);
            if (slot == -1) {
                var dropPos = MathUtils.center(position);
                position.dimension().dropItem(remainingItem, dropPos);
            }
        }
    }

    /**
     * Plays the dispenser failure click centered on this block entity.
     */
    protected void playFailSound() {
        position.dimension().addSound(MathUtils.center(position), new CustomSound(SoundNames.RANDOM_CLICK, 1.0f, 1.2f));
    }
}
