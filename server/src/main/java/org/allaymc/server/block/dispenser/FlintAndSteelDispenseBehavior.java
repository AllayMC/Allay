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
import org.allaymc.server.block.component.fire.BlockFireBaseComponentImpl;

/**
 * {@code FlintAndSteelDispenseBehavior} uses flint and steel to prime adjacent TNT
 * or ignite supported air above a block. Successful actions return the damaged
 * tool as a remainder unless it breaks; unsupported targets eject the tool through
 * the ordinary fallback behavior.
 *
 * @author daoge_cmd
 */
public class FlintAndSteelDispenseBehavior implements DispenseBehavior {

    /**
     * Primes adjacent TNT first, otherwise attempts supported fire placement in
     * air. Successful actions emit effects and attempt tool damage; unsupported
     * targets use ordinary ejection without applying this handler's durability
     * step.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);

        if (targetBlock.getBlockType() == BlockTypes.TNT) {
            BlockTypes.TNT.getBlockBehavior().prime(new Position3i(targetPos, dimension));
            spawnDispenseEffects(block, face);
            return damageItem(item);
        }

        if (targetBlock.getBlockType() == BlockTypes.AIR) {
            var supportBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(targetPos));
            if (BlockFireBaseComponentImpl.canSupportFire(supportBlockState)) {
                var fireBlockState = supportBlockState.getBlockType().hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER)
                        ? BlockTypes.SOUL_FIRE.getDefaultState()
                        : BlockTypes.FIRE.getDefaultState();
                dimension.setBlockState(targetPos, fireBlockState);
                dimension.addSound(MathUtils.center(targetPos), SimpleSound.IGNITE);
                spawnDispenseEffects(block, face);
                return damageItem(item);
            }
        }


        // No TNT or supported fire target accepted the action. Eject the tool
        // instead so the caller's successful consumption corresponds to a dropped
        // item.
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
    }

    /**
     * Attempts one durability increase and returns the surviving tool reference as
     * a successful remainder. Durability rejection leaves the tool unchanged,
     * while a broken tool returns {@code ItemAirStack.AIR_STACK} as no
     * replacement.
     */
    protected DispenseResult damageItem(ItemStack item) {
        item.tryIncreaseDamage(1);
        if (item.isBroken()) {
            return DispenseResult.success(ItemAirStack.AIR_STACK);
        }
        return DispenseResult.success(item);
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
