package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.eventbus.event.block.ComposterEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockComposterBaseComponentImpl} converts compostable item interactions
 * into fill levels and bone meal. The item registry supplies each material's
 * success chance. A permitted attempt consumes the held item outside creative mode
 * even when the random roll fails, while a full composter yields bone meal.
 *
 * <p>
 * A successful addition reaching level seven schedules a later transition to the
 * harvestable level eight. {@code onScheduledUpdate} checks the current level
 * again before making that transition, so a changed composter state controls the
 * delayed result. The interaction event permits cancellation before consumption;
 * its mutable fill-level value currently does not replace the handler's captured
 * local level.
 *
 * @author daoge_cmd
 */
public class BlockComposterBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Returns the registered success chance for this item type. An unregistered
     * type returns zero, allowing the interaction handler to reject it without
     * consuming an item or dispatching an event.
     */
    public static float getCompostChance(ItemType<?> itemType) {
        var chance = Registries.COMPOSTABLE_ITEMS.get(itemType);
        // Unregistered materials have no compost chance, allowing callers to
        // reject them consistently before dispatching the composter event or
        // consuming the held item.
        return chance != null ? chance : 0.0f;
    }

    /**
     * Returns whether the compostable-item registry contains the supplied type.
     */
    public static boolean isCompostable(ItemType<?> itemType) {
        return Registries.COMPOSTABLE_ITEMS.get(itemType) != null;
    }

    /**
     * Creates the composter block component for the supplied registered block
     * type.
     */
    public BlockComposterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Harvests a full composter or attempts to add one compostable item. Missing
     * interaction data, empty input, unregistered chances, and event cancellation
     * return {@code false}. Accepted additions consume the item outside creative
     * mode before the random success roll. Success advances the captured fill
     * level and schedules maturation at level seven; failure still plays the fill
     * sound and returns {@code true} because the item was used.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var player = interactInfo.player();
        var fillLevel = block.getPropertyValue(BlockPropertyTypes.COMPOSTER_FILL_LEVEL);

        if (fillLevel == 8) {
            return harvestBoneMeal(block, player);
        }

        if (itemStack.isEmptyOrAir()) {
            return false;
        }

        var chance = getCompostChance(itemStack.getItemType());
        if (chance <= 0) {
            return false;
        }

        var event = new ComposterEvent(block, player, itemStack, fillLevel);
        event.call();
        if (event.isCancelled()) {
            return false;
        }

        if (player.getGameMode() != org.allaymc.api.player.GameMode.CREATIVE) {
            player.tryConsumeItemInHand();
        }

        // Registry chances are probabilities in the unit interval; accepted
        // attempts already consumed their item, so an unsuccessful roll still
        // spends that compost.
        var success = ThreadLocalRandom.current().nextFloat() < chance;

        if (success) {
            var newLevel = fillLevel + 1;
            dimension.updateBlockProperty(BlockPropertyTypes.COMPOSTER_FILL_LEVEL, newLevel, block.getPosition());
            block.addSound(SimpleSound.COMPOSTER_FILL_LAYER);

            if (newLevel == 7) {
                // Delay maturation by twenty ticks so level seven remains the
                // intermediate filled state; the scheduled handler rechecks that
                // state before making it harvestable.
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 20);
            }
        } else {
            block.addSound(SimpleSound.COMPOSTER_FILL);
        }

        return true;
    }

    /**
     * Matures a composter that still has fill level seven. The delayed callback
     * changes it to level eight and plays the ready sound; any other current level
     * is retained.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        var fillLevel = block.getPropertyValue(BlockPropertyTypes.COMPOSTER_FILL_LEVEL);
        if (fillLevel == 7) {
            block.getDimension().updateBlockProperty(BlockPropertyTypes.COMPOSTER_FILL_LEVEL, 8, block.getPosition());
            block.addSound(SimpleSound.COMPOSTER_READY);
        }
    }

    /**
     * Resets the composter and drops one bone meal above its center. The
     * fill-level write precedes the item drop and sound, and the method returns
     * {@code true} after those calls.
     */
    private boolean harvestBoneMeal(Block block, org.allaymc.api.entity.interfaces.EntityPlayer player) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        dimension.updateBlockProperty(BlockPropertyTypes.COMPOSTER_FILL_LEVEL, 0, pos);

        var boneMeal = ItemTypes.BONE_MEAL.createItemStack(1);
        dimension.dropItem(boneMeal, MathUtils.center(pos).add(0, 0.5, 0));

        block.addSound(SimpleSound.COMPOSTER_EMPTY);

        return true;
    }

    /**
     * Returns whether this composter supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the stored composter fill level as its comparator signal.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        return block.getPropertyValue(BlockPropertyTypes.COMPOSTER_FILL_LEVEL);
    }

    /**
     * Returns one composter item without converting stored compost into drops.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, org.allaymc.api.entity.Entity entity) {
        return Set.of(block.toItemStack());
    }
}
