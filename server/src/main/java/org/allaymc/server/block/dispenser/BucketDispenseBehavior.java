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
 * {@code BucketDispenseBehavior} collects supported source liquids with empty
 * buckets and empties filled buckets into the adjacent output position. Powder
 * snow follows a separate block-replacement path, while other liquids delegate
 * layer selection to {@code Dimension.setLiquid}. Entity buckets can also request
 * addition of their contained entity.
 *
 * <p>
 * Collection requires the source-depth property for water or lava, and unsupported
 * targets fall back to ordinary item ejection. Successful specialized results
 * carry the exchanged bucket for the dispenser's inventory handling. Filled liquid
 * buckets report success after requesting the liquid write and optional entity
 * addition; those operations do not establish that liquid placement or deferred
 * entity admission succeeded.
 *
 * @author daoge_cmd
 */
public class BucketDispenseBehavior implements DispenseBehavior {

    /**
     * Selects empty- or filled-bucket behavior for the single-item input. An item
     * without {@code ItemBucketComponent} falls back to ordinary dispensing;
     * successful specialized transfers return the exchanged bucket as their
     * remainder.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        // Items lacking bucket capabilities still use ordinary ejection, so a
        // registry entry applied to an incompatible stack does not consume it
        // without an action.
        if (!(item instanceof ItemBucketComponent bucketComponent)) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        if (bucketComponent.isEmpty()) {
            return handleEmptyBucket(block, face);
        } else {
            return handleFilledBucket(block, face, bucketComponent);
        }
    }

    /**
     * Collects adjacent powder snow or a water or lava source and returns the
     * filled bucket. A source-depth lookup failure, non-source liquid, unsupported
     * liquid type, or other target instead ejects a fresh empty bucket through the
     * default behavior.
     */
    protected DispenseResult handleEmptyBucket(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);
        var targetBlockType = targetBlock.getBlockType();

        if (targetBlockType == BlockTypes.POWDER_SNOW) {
            dimension.setBlockState(targetPos, BlockTypes.AIR.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(ItemTypes.POWDER_SNOW_BUCKET.createItemStack(1));
        }

        if (targetBlock.getBehavior() instanceof BlockLiquidBehavior liquidBehavior) {
            try {
                // Only source-depth liquid can be exchanged for a filled bucket.
                // Eject an empty bucket for flowing targets instead of removing
                // their partial liquid.
                if (!BlockLiquidBaseComponent.isSource(targetBlock)) {
                    return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
                }
            // A liquid behavior can lack the source-depth property. Preserve the
            // empty-bucket action through ordinary ejection when that property
            // cannot be read.
            } catch (IllegalArgumentException ignore) {
                return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
            }

            ItemStack filledBucket;
            if (targetBlockType == BlockTypes.WATER || targetBlockType == BlockTypes.FLOWING_WATER) {
                filledBucket = ItemTypes.WATER_BUCKET.createItemStack(1);
            } else if (targetBlockType == BlockTypes.LAVA || targetBlockType == BlockTypes.FLOWING_LAVA) {
                filledBucket = ItemTypes.LAVA_BUCKET.createItemStack(1);
            // A source outside the supported water and lava types has no mapped
            // filled bucket, so ordinary ejection preserves an empty bucket for
            // the caller.
            } else {
                return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
            }

            dimension.setBlockState(targetPos, BlockTypes.AIR.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(filledBucket);
        }

        // No supported collectible target remains. Eject an empty bucket so the
        // successful default result corresponds to a real item leaving the
        // inventory.
        return DefaultDispenseBehavior.INSTANCE.dispense(block, face, ItemTypes.BUCKET.createItemStack(1));
    }

    /**
     * Places bucket contents at the adjacent output position and returns an empty
     * bucket. Powder snow requires air or a replaceable target; otherwise that
     * bucket is ejected intact. Other liquids call {@code setLiquid}, then an
     * optional contained entity is created and submitted for deferred addition.
     * The method reports success even when {@code setLiquid} declines its target
     * or the entity factory returns {@code null}, and earlier world edits are not
     * rolled back.
     */
    protected DispenseResult handleFilledBucket(Block block, BlockFace face, ItemBucketComponent bucketComponent) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);
        var targetBlockType = targetBlock.getBlockType();

        var liquidType = bucketComponent.getLiquidType();

        if (liquidType == BlockTypes.POWDER_SNOW) {
            // Powder snow replaces a block directly rather than using liquid
            // layers. An obstructed target therefore ejects the filled bucket
            // instead of overwriting the obstruction.
            if (targetBlockType != BlockTypes.AIR && !targetBlockType.hasBlockTag(BlockTags.REPLACEABLE)) {
                return DefaultDispenseBehavior.INSTANCE.dispense(block, face, (ItemStack) bucketComponent);
            }
            dimension.setBlockState(targetPos, BlockTypes.POWDER_SNOW.getDefaultState());
            spawnDispenseEffects(block, face);
            return DispenseResult.success(ItemTypes.BUCKET.createItemStack(1));
        }

        if (liquidType != BlockTypes.AIR) {
            // Delegate layer and replaceability decisions to setLiquid; its void
            // result cannot confirm placement before this handler returns the
            // empty-bucket remainder.
            dimension.setLiquid(targetPos, liquidType.getDefaultState());
        }

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
        return DispenseResult.success(ItemTypes.BUCKET.createItemStack(1));
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
