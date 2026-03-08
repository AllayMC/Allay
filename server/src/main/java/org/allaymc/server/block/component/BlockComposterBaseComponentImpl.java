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
 * Implementation of Composter block behavior.
 * <p>
 * The composter converts organic materials into bone meal.
 * Fill level ranges from 0-8, where 8 means ready to harvest.
 *
 * @author daoge_cmd
 */
public class BlockComposterBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Get the compost chance for an item type.
     *
     * @param itemType the item type to check
     * @return the chance (0-1), or 0 if not compostable
     */
    public static float getCompostChance(ItemType<?> itemType) {
        var chance = Registries.COMPOSTABLE_ITEMS.get(itemType);
        return chance != null ? chance : 0.0f;
    }

    /**
     * Check if an item type is compostable.
     *
     * @param itemType the item type to check
     * @return true if the item can be composted
     */
    public static boolean isCompostable(ItemType<?> itemType) {
        return Registries.COMPOSTABLE_ITEMS.get(itemType) != null;
    }

    public BlockComposterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var player = interactInfo.player();
        var fillLevel = block.getPropertyValue(BlockPropertyTypes.COMPOSTER_FILL_LEVEL);

        // If full (level 8), harvest bone meal
        if (fillLevel == 8) {
            return harvestBoneMeal(block, player);
        }

        // Try to add compostable item
        if (itemStack.isEmptyOrAir()) {
            return false;
        }

        var chance = getCompostChance(itemStack.getItemType());
        if (chance <= 0) {
            return false;
        }

        // Call event
        var event = new ComposterEvent(block, player, itemStack, fillLevel);
        event.call();
        if (event.isCancelled()) {
            return false;
        }

        // Consume item
        if (player.getGameMode() != org.allaymc.api.player.GameMode.CREATIVE) {
            player.tryConsumeItemInHand();
        }

        // Calculate success
        var success = ThreadLocalRandom.current().nextFloat() < chance;

        if (success) {
            var newLevel = fillLevel + 1;
            dimension.updateBlockProperty(BlockPropertyTypes.COMPOSTER_FILL_LEVEL, newLevel, block.getPosition());
            block.addSound(SimpleSound.COMPOSTER_FILL_LAYER);

            if (newLevel == 7) {
                // Schedule update to convert to level 8 after 1 second (20 ticks)
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 20);
            }
        } else {
            // Failed to add layer
            block.addSound(SimpleSound.COMPOSTER_FILL);
        }

        return true;
    }

    @Override
    public void onScheduledUpdate(Block block) {
        var fillLevel = block.getPropertyValue(BlockPropertyTypes.COMPOSTER_FILL_LEVEL);
        if (fillLevel == 7) {
            // Convert to level 8 (ready to harvest)
            block.getDimension().updateBlockProperty(BlockPropertyTypes.COMPOSTER_FILL_LEVEL, 8, block.getPosition());
            block.addSound(SimpleSound.COMPOSTER_READY);
        }
    }

    private boolean harvestBoneMeal(Block block, org.allaymc.api.entity.interfaces.EntityPlayer player) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Reset fill level to 0
        dimension.updateBlockProperty(BlockPropertyTypes.COMPOSTER_FILL_LEVEL, 0, pos);

        // Drop bone meal
        var boneMeal = ItemTypes.BONE_MEAL.createItemStack(1);
        dimension.dropItem(boneMeal, MathUtils.center(pos).add(0, 0.5, 0));

        // Play sound
        block.addSound(SimpleSound.COMPOSTER_EMPTY);

        return true;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        return block.getPropertyValue(BlockPropertyTypes.COMPOSTER_FILL_LEVEL);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, org.allaymc.api.entity.Entity entity) {
        // Composter drops itself, not its contents
        return Set.of(block.toItemStack());
    }
}
