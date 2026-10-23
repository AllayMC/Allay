package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * {@code WaterBottleDispenseBehavior} converts dirt or coarse dirt immediately in front of a
 * dispenser into mud when the selected item is a water potion. Other items, other potion
 * types, and other target blocks retain default dispensing behavior instead of entering the
 * mud conversion path.
 *
 * <p>
 * An eligible conversion requests the block write before emitting effects and returning a
 * successful result with a glass bottle. The dispenser caller then consumes its original
 * potion and handles the bottle, so this behavior does not directly update inventory. The
 * dimension's write result is ignored; consequently the returned success describes reaching
 * the conversion calls rather than a coordinated guarantee that the target and inventory
 * changed together.
 *
 * @author daoge_cmd
 */
public class WaterBottleDispenseBehavior implements DispenseBehavior {

    /**
     * Converts the facing dirt or coarse dirt target to mud for a water potion, then returns a
     * glass bottle. Other inputs and targets use default dispensing, which drops a single item
     * for these potion cases.
     *
     * <p>
     * The mud path emits effects and reports success after the dimension write call without
     * checking its boolean result. The dispenser caller owns consumption of the original
     * potion and handling of the returned bottle; this behavior does not directly mutate the
     * dispenser inventory.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        // Preserve ordinary item dispensing when this handler receives an item without
        // potion behavior, rather than attempting to interpret an unrelated stack as water.
        if (!(item instanceof ItemPotionComponent potionItem)) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        if (potionItem.getPotionType() != PotionType.WATER) {
            // Nonwater potions keep their item form through default dispensing, so
            // this conversion path never consumes them in exchange for an empty bottle.
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);
        var targetBlockType = targetBlock.getBlockType();

        if (targetBlockType == BlockTypes.DIRT || targetBlockType == BlockTypes.COARSE_DIRT) {
            dimension.setBlockState(targetPos, BlockTypes.MUD.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(ItemTypes.GLASS_BOTTLE.createItemStack(1));
        }

        // An ineligible target keeps the water potion intact as a dropped item
        // through default dispensing instead of returning a bottle without a mud conversion.
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
    }

    /**
     * Emits a block-click sound and directional shoot particle at the dispenser center.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
