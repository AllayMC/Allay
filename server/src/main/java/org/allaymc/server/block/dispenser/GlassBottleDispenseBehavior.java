package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * Dispense behavior for glass bottle items.
 * Collects water from water source blocks in front of the dispenser.
 *
 * @author daoge_cmd
 */
public class GlassBottleDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);

        // Check if target is a water source block
        if (targetBlock.getBehavior() instanceof BlockLiquidBehavior) {
            var blockType = targetBlock.getBlockType();
            if (blockType == BlockTypes.WATER || blockType == BlockTypes.FLOWING_WATER) {
                try {
                    // Only collect from source blocks (liquid depth == 0)
                    if (targetBlock.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) == 0) {
                        // Create water bottle
                        var waterBottle = ItemTypes.POTION.createItemStack(1);
                        if (waterBottle instanceof ItemPotionComponent potionItem) {
                            potionItem.setPotionType(PotionType.WATER);
                        }

                        spawnDispenseEffects(block, face);
                        return DispenseResult.success(waterBottle);
                    }
                } catch (IllegalArgumentException ignore) {
                    // Block doesn't have LIQUID_DEPTH property
                }
            }
        }

        // Fall back to default behavior if not water
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
