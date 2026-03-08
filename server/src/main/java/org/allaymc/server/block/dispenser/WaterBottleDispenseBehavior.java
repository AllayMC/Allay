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
 * Dispense behavior for water bottle (potion) items.
 * Converts dirt and coarse dirt to mud when dispensed onto them.
 *
 * @author daoge_cmd
 */
public class WaterBottleDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        // Only handle water bottles
        if (!(item instanceof ItemPotionComponent potionItem)) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        if (potionItem.getPotionType() != PotionType.WATER) {
            // For non-water potions, fall back to default behavior (drop the item)
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);
        var targetBlockType = targetBlock.getBlockType();

        // Convert dirt or coarse dirt to mud
        if (targetBlockType == BlockTypes.DIRT || targetBlockType == BlockTypes.COARSE_DIRT) {
            dimension.setBlockState(targetPos, BlockTypes.MUD.getDefaultState());
            spawnDispenseEffects(block, face);
            // Return empty glass bottle
            return DispenseResult.success(ItemTypes.GLASS_BOTTLE.createItemStack(1));
        }

        // Fall back to default behavior for other blocks
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
