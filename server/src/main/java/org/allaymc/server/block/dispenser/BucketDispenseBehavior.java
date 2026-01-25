package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * Dispense behavior for bucket items.
 * Empty buckets collect liquids, filled buckets place liquids and spawn entities if applicable.
 *
 * @author daoge_cmd
 */
public class BucketDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        if (!(item instanceof ItemBucketComponent bucketComponent)) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        if (bucketComponent.isEmpty()) {
            return handleEmptyBucket(block, face);
        } else {
            return handleFilledBucket(block, face, bucketComponent);
        }
    }

    protected DispenseResult handleEmptyBucket(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);
        var targetBlockType = targetBlock.getBlockType();

        // Check for powder snow
        if (targetBlockType == BlockTypes.POWDER_SNOW) {
            dimension.setBlockState(targetPos, BlockTypes.AIR.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(ItemTypes.POWDER_SNOW_BUCKET.createItemStack(1));
        }

        // Check for liquid source blocks
        if (targetBlock.getBehavior() instanceof BlockLiquidBehavior liquidBehavior) {
            try {
                // Only collect from source blocks
                if (!BlockLiquidBaseComponent.isSource(targetBlock)) {
                    return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
                }
            } catch (IllegalArgumentException ignore) {
                // Block doesn't have LIQUID_DEPTH property
                return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
            }

            ItemStack filledBucket;
            if (targetBlockType == BlockTypes.WATER || targetBlockType == BlockTypes.FLOWING_WATER) {
                filledBucket = ItemTypes.WATER_BUCKET.createItemStack(1);
            } else if (targetBlockType == BlockTypes.LAVA || targetBlockType == BlockTypes.FLOWING_LAVA) {
                filledBucket = ItemTypes.LAVA_BUCKET.createItemStack(1);
            } else {
                return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
            }

            dimension.setBlockState(targetPos, BlockTypes.AIR.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(filledBucket);
        }

        // Cannot collect anything
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
    }

    protected DispenseResult handleFilledBucket(Block block, BlockFace face, ItemBucketComponent bucketComponent) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);
        var targetBlockType = targetBlock.getBlockType();

        var liquidType = bucketComponent.getLiquidType();

        // Handle powder snow bucket separately (it's not a liquid)
        if (liquidType == BlockTypes.POWDER_SNOW) {
            if (targetBlockType != BlockTypes.AIR && !targetBlockType.hasBlockTag(BlockTags.REPLACEABLE)) {
                return DefaultDispenseBehavior.INSTANCE.dispense(block, face, (ItemStack) bucketComponent);
            }
            dimension.setBlockState(targetPos, BlockTypes.POWDER_SNOW.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(ItemTypes.BUCKET.createItemStack(1));
        }

        // Handle liquid buckets (water, lava, fish buckets, etc.)
        if (liquidType != BlockTypes.AIR) {
            // Use setLiquid which handles layer logic automatically
            dimension.setLiquid(targetPos, liquidType.getDefaultState());
        }

        // Spawn entity if bucket contains one (fish, axolotl, tadpole)
        var entityType = bucketComponent.getEntityType();
        if (entityType != null) {
            var entity = entityType.createEntity(
                    EntityInitInfo.builder()
                            .dimension(dimension)
                            .pos(MathUtils.center(targetPos))
                            .build()
            );
            if (entity != null) {
                dimension.getEntityManager().addEntity(entity);
            }
        }

        spawnDispenseEffects(block, face);
        // Return empty bucket
        return DispenseResult.success(ItemTypes.BUCKET.createItemStack(1));
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
