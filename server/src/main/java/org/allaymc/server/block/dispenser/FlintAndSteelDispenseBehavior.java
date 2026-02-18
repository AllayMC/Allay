package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockFireBaseComponentImpl;

/**
 * Dispense behavior for flint and steel items.
 * Primes TNT blocks, places fire blocks, or damages the item.
 *
 * @author daoge_cmd
 */
public class FlintAndSteelDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);

        // Priority 1: If target is TNT block, prime it
        if (targetBlock.getBlockType() == BlockTypes.TNT) {
            BlockTypes.TNT.getBlockBehavior().prime(new Position3i(targetPos, dimension));
            spawnDispenseEffects(block, face);
            return damageItem(item);
        }

        // Priority 2: If target is air, place fire
        if (targetBlock.getBlockType() == BlockTypes.AIR) {
            // Check if the block below can support fire
            var supportBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(targetPos));
            if (BlockFireBaseComponentImpl.canSupportFire(supportBlockState)) {
                // Check if the block that supports fire is a soul fire converter
                var fireBlockState = supportBlockState.getBlockType().hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER)
                        ? BlockTypes.SOUL_FIRE.getDefaultState()
                        : BlockTypes.FIRE.getDefaultState();
                dimension.setBlockState(targetPos, fireBlockState);
                dimension.addSound(MathUtils.center(targetPos), SimpleSound.IGNITE);
                spawnDispenseEffects(block, face);
                return damageItem(item);
            }
        }

        // Nether portal creation is now handled by fire block's onPlace()

        // Failed to do anything, fall back to default behavior
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
    }

    protected DispenseResult damageItem(ItemStack item) {
        item.tryIncreaseDamage(1);
        if (item.isBroken()) {
            return DispenseResult.success(ItemAirStack.AIR_STACK);
        }
        return DispenseResult.success(item);
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
