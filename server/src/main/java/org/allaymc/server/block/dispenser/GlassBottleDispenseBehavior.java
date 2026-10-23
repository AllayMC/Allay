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
 * {@code GlassBottleDispenseBehavior} fills glass bottles from adjacent water
 * source blocks while retaining the water in the world. A successful result
 * carries one water potion as the dispenser remainder, while unsupported targets
 * and unavailable depth properties fall back to ordinary item ejection.
 *
 * @author daoge_cmd
 */
public class GlassBottleDispenseBehavior implements DispenseBehavior {

    /**
     * Returns a water bottle remainder for adjacent water with source depth zero.
     * The source block is retained; non-source water, other targets, and
     * depth-property lookup failure instead use ordinary bottle ejection.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);

        if (targetBlock.getBehavior() instanceof BlockLiquidBehavior) {
            var blockType = targetBlock.getBlockType();
            if (blockType == BlockTypes.WATER || blockType == BlockTypes.FLOWING_WATER) {
                try {
                    // Depth zero identifies source water for bottle filling;
                    // flowing water falls through to ejection without producing a
                    // filled-bottle remainder.
                    if (targetBlock.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) == 0) {
                        var waterBottle = ItemTypes.POTION.createItemStack(1);
                        if (waterBottle instanceof ItemPotionComponent potionItem) {
                            potionItem.setPotionType(PotionType.WATER);
                        }

                        spawnDispenseEffects(block, face);
                        return DispenseResult.success(waterBottle);
                    }
                // A water-like behavior can lack the expected depth property.
                // Treat that mismatch as an unsupported fill target and preserve
                // ordinary ejection below.
                } catch (IllegalArgumentException ignore) {
                }
            }
        }

        // An unsuccessful source check must still produce an output action, so
        // default ejection spends the selected bottle as an item rather than a
        // water potion.
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
    }

    /**
     * Emits the dispenser click sound and directional shoot particle.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
