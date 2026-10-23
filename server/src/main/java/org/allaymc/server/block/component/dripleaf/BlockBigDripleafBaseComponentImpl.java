package org.allaymc.server.block.component.dripleaf;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BigDripleafTilt;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockBigDripleafBaseComponentImpl} implements big-dripleaf column placement,
 * bone-meal extension, and head tilting. A column has supporting stems beneath a head, and its
 * cardinal direction is retained when another head is placed above it. Support checks are
 * deferred through scheduled updates after neighbor changes.
 *
 * <p>
 * Head state coordinates entity contact, projectile hits, redstone power, and delayed tilt
 * progression. Ordinary contact begins an unstable stage, projectiles jump to full tilt, and
 * power restores an untilted head. A fully tilted head remains tilted while a nonprojectile
 * entity intersects its shape and schedules another check. Column growth converts the old head
 * before placing the new one, using separate writes without rollback or a cancellable growth
 * proposal.
 *
 * @author daoge_cmd
 */
public class BlockBigDripleafBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the big dripleaf behavior component for the supplied block type.
     */
    public BlockBigDripleafBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a head on valid support, inheriting direction when it extends existing big
     * dripleaf. An existing lower dripleaf is converted to an untilted stem before the new
     * head is written. Other support uses ordinary property processing for the supplied state.
     *
     * <p>
     * Both paths initialize the new head with no tilt. The lower conversion is a separate
     * write and remains if the final placement fails; the boolean result comes only from the
     * final head write. This operation does not dispatch its own growth event.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var belowPos = BlockFace.DOWN.offsetPos(placeBlockPos);
        var belowState = dimension.getBlockState(belowPos);

        if (!isValidSupport(belowState)) {
            return false;
        }

        BlockState newState;
        if (belowState.getBlockType() == blockType) {
            var direction = belowState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
            newState = blockType.getDefaultState()
                    .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                    .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                    .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);

            var stemState = belowState
                    .setPropertyValue(BIG_DRIPLEAF_HEAD, false)
                    .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE);
            dimension.setBlockState(belowPos, stemState);
        } else {
            newState = processBlockProperties(blockState, placeBlockPos, placementInfo)
                    .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                    .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE);
        }

        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                newState, placementInfo
        );
    }

    /**
     * Responds to power by restoring a tilted head and otherwise schedules a one-tick update.
     * The delayed callback rechecks column support after this neighbor callback returns;
     * powered unstable heads reset without the upward-tilt sound.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var blockState = block.getBlockState();
        if (blockState.getPropertyValue(BIG_DRIPLEAF_HEAD) && block.isPowered()) {
            var tilt = blockState.getPropertyValue(BIG_DRIPLEAF_TILT);
            if (tilt != BigDripleafTilt.NONE) {
                if (tilt != BigDripleafTilt.UNSTABLE) {
                    block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_UP);
                }
                setTilt(block, BigDripleafTilt.NONE);
                return;
            }
        }
        // Defer support validation to a later tick rather than breaking a segment
        // inside the neighbor callback that reports a change to its column.
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 1);
    }

    /**
     * Validates dripleaf support before advancing head tilt. Unsupported blocks break, and
     * stems additionally require another big-dripleaf block above them. A powered head resets
     * its tilt and ends the callback.
     *
     * <p>
     * Unpowered heads advance from unstable to partial and then full tilt using ten-tick
     * delays. A fully tilted head waits one hundred ticks before checking again when an entity
     * remains on it; otherwise the head resets and emits the upward sound. The current block
     * state, power, and entity overlap jointly select the next state and scheduled delay.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        var blockState = block.getBlockState();
        var isHead = blockState.getPropertyValue(BIG_DRIPLEAF_HEAD);
        var tilt = blockState.getPropertyValue(BIG_DRIPLEAF_TILT);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        var belowPos = BlockFace.DOWN.offsetPos(pos);
        var belowState = dimension.getBlockState(belowPos);
        if (!isValidSupport(belowState)) {
            block.breakBlock();
            return;
        }

        if (!isHead) {
            var abovePos = new Vector3i(pos).add(0, 1, 0);
            var aboveState = dimension.getBlockState(abovePos);
            if (aboveState.getBlockType() != blockType) {
                block.breakBlock();
            }
            return;
        }

        if (block.isPowered()) {
            if (tilt != BigDripleafTilt.NONE) {
                setTilt(block, BigDripleafTilt.NONE);
            }
            return;
        }

        switch (tilt) {
            case UNSTABLE -> {
                setTilt(block, BigDripleafTilt.PARTIAL_TILT);
                block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
                // Ten ticks separate the normal head-tilt progression stages.
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 10);
            }
            case PARTIAL_TILT -> {
                setTilt(block, BigDripleafTilt.FULL_TILT);
                block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 10);
            }
            case FULL_TILT -> {
                if (hasEntityOnHead(block)) {
                    // Keep full tilt while an entity intersects the head and defer
                    // another overlap check instead of restoring a platform beneath that entity.
                    dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 100);
                } else {
                    setTilt(block, BigDripleafTilt.NONE);
                    block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_UP);
                }
            }
        }
    }

    /**
     * Starts an unpowered, untilted head's unstable phase for a nonprojectile collision. The
     * callback writes the unstable state, emits the downward sound, and schedules a ten-tick
     * progression; stems and already tilted heads remain unchanged.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        var blockState = block.getBlockState();
        if (!blockState.getPropertyValue(BIG_DRIPLEAF_HEAD)) {
            return;
        }

        if (blockState.getPropertyValue(BIG_DRIPLEAF_TILT) != BigDripleafTilt.NONE) {
            return;
        }

        if (entity instanceof EntityProjectile) {
            return;
        }

        if (block.isPowered()) {
            return;
        }

        setTilt(block, BigDripleafTilt.UNSTABLE);
        block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 10);
    }

    /**
     * Fully tilts an unpowered head after a projectile hit. The callback emits the downward
     * sound and schedules a one-hundred-tick check, while powered heads and stem segments
     * ignore the hit.
     */
    @Override
    public void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
        var blockState = block.getBlockState();
        if (!blockState.getPropertyValue(BIG_DRIPLEAF_HEAD)) {
            return;
        }

        if (block.isPowered()) {
            return;
        }

        setTilt(block, BigDripleafTilt.FULL_TILT);
        block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 100);
    }

    /**
     * Extends the highest contiguous big dripleaf after an unhandled bone-meal interaction.
     * The position above the top must be air or water; the new head inherits the old top's
     * cardinal direction.
     *
     * <p>
     * The old head becomes an untilted stem before the new untilted head is written. These
     * writes are independent and their results are ignored; the player then consumes bone meal
     * and receives particles. This callback has no growth-event cancellation and returns
     * {@code true} after reaching the write sequence.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();

        var topPos = new Vector3i(pos);
        while (true) {
            var abovePos = new Vector3i(topPos).add(0, 1, 0);
            var aboveState = dimension.getBlockState(abovePos);
            if (aboveState.getBlockType() == blockType) {
                topPos.set(abovePos);
            } else {
                break;
            }
        }

        var aboveTopPos = new Vector3i(topPos).add(0, 1, 0);
        var aboveTopState = dimension.getBlockState(aboveTopPos);
        var aboveTopType = aboveTopState.getBlockType();
        if (aboveTopType != BlockTypes.AIR && !aboveTopType.hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var topState = dimension.getBlockState(topPos);
        var direction = topState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);

        // Convert the old head before creating the extension so the resulting
        // column has its head at the top; these writes have no rollback on failure.
        var stemState = topState
                .setPropertyValue(BIG_DRIPLEAF_HEAD, false)
                .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE);
        dimension.setBlockState(topPos, stemState);

        var newHeadState = blockType.getDefaultState()
                .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);
        dimension.setBlockState(aboveTopPos, newHeadState);

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
        return true;
    }

    /**
     * Returns one small-dripleaf item for ordinary big-dripleaf block breaking here.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.SMALL_DRIPLEAF_BLOCK.createItemStack());
    }

    /**
     * Writes the supplied tilt property at the block's position and layer.
     */
    protected void setTilt(Block block, BigDripleafTilt tilt) {
        block.updateBlockProperty(BIG_DRIPLEAF_TILT, tilt);
    }

    /**
     * Returns whether a nonprojectile entity intersects the union bounds of the head's
     * translated selection and inside-detection shape. The scan reads current dimension
     * entities and does not require the entity to stand on a particular vertical surface.
     */
    protected boolean hasEntityOnHead(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var detectionAABB = block.getBlockStateData().computeOffsetShape(pos).unionAABB();
        for (var entity : dimension.getEntities().values()) {
            if (entity instanceof EntityProjectile) continue;
            if (entity.getOffsetAABB().intersectsAABB(detectionAABB)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the state belongs to this dripleaf type or the supported ground set.
     * Accepted ground includes grass, dirt, mycelium, podzol, farmland, rooted dirt, moss, and
     * clay.
     */
    protected boolean isValidSupport(BlockState belowState) {
        var belowType = belowState.getBlockType();
        return belowType == blockType ||
               belowType == BlockTypes.GRASS_BLOCK ||
               belowType == BlockTypes.DIRT ||
               belowType == BlockTypes.MYCELIUM ||
               belowType == BlockTypes.PODZOL ||
               belowType == BlockTypes.FARMLAND ||
               belowType == BlockTypes.DIRT_WITH_ROOTS ||
               belowType == BlockTypes.MOSS_BLOCK ||
               belowType == BlockTypes.CLAY;
    }
}
