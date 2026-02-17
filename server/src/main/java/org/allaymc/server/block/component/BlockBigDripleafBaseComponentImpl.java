package org.allaymc.server.block.component;

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
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockBigDripleafBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockBigDripleafBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var belowPos = BlockFace.DOWN.offsetPos(placeBlockPos);
        var belowState = dimension.getBlockState(belowPos);

        if (!isValidSupport(belowState)) {
            return false;
        }

        BlockState newState;
        if (belowState.getBlockType() == blockType) {
            // Placed on top of another big dripleaf: inherit direction, convert below to stem
            var direction = belowState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
            newState = blockType.getDefaultState()
                    .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                    .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                    .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);

            // Convert block below to stem
            var stemState = belowState
                    .setPropertyValue(BIG_DRIPLEAF_HEAD, false)
                    .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE);
            dimension.setBlockState(belowPos, stemState);
        } else {
            // Placed on natural block: process direction normally
            newState = processBlockProperties(blockState, placeBlockPos, placementInfo)
                    .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                    .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE);
        }

        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                newState, placementInfo
        );
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var blockState = block.getBlockState();
        // Redstone signal immediately resets tilt
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
        // Schedule update in 1 tick to defer survival check
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 1);
    }

    @Override
    public void onScheduledUpdate(Block block) {
        var blockState = block.getBlockState();
        var isHead = blockState.getPropertyValue(BIG_DRIPLEAF_HEAD);
        var tilt = blockState.getPropertyValue(BIG_DRIPLEAF_TILT);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        // Survival check
        var belowPos = BlockFace.DOWN.offsetPos(pos);
        var belowState = dimension.getBlockState(belowPos);
        if (!isValidSupport(belowState)) {
            block.breakBlock();
            return;
        }

        if (!isHead) {
            // Stem: check that block above is also big dripleaf
            var abovePos = new Vector3i(pos).add(0, 1, 0);
            var aboveState = dimension.getBlockState(abovePos);
            if (aboveState.getBlockType() != blockType) {
                block.breakBlock();
            }
            return;
        }

        // Redstone power resets tilt
        if (block.isPowered()) {
            if (tilt != BigDripleafTilt.NONE) {
                setTilt(block, BigDripleafTilt.NONE);
            }
            return;
        }

        // Tilt progression (only for head blocks)
        switch (tilt) {
            case UNSTABLE -> {
                setTilt(block, BigDripleafTilt.PARTIAL_TILT);
                block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 10);
            }
            case PARTIAL_TILT -> {
                setTilt(block, BigDripleafTilt.FULL_TILT);
                block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 10);
            }
            case FULL_TILT -> {
                if (hasEntityOnHead(block)) {
                    // Entity still on the dripleaf, stay tilted and recheck later
                    dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, 100);
                } else {
                    setTilt(block, BigDripleafTilt.NONE);
                    block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_UP);
                }
            }
        }
    }

    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        var blockState = block.getBlockState();
        if (!blockState.getPropertyValue(BIG_DRIPLEAF_HEAD)) {
            return;
        }

        if (blockState.getPropertyValue(BIG_DRIPLEAF_TILT) != BigDripleafTilt.NONE) {
            return;
        }

        // Skip projectiles (they use onProjectileHit)
        if (entity instanceof EntityProjectile) {
            return;
        }

        // Redstone power prevents tilt
        if (block.isPowered()) {
            return;
        }

        setTilt(block, BigDripleafTilt.UNSTABLE);
        block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 10);
    }

    @Override
    public void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
        var blockState = block.getBlockState();
        if (!blockState.getPropertyValue(BIG_DRIPLEAF_HEAD)) {
            return;
        }

        // Redstone power prevents tilt
        if (block.isPowered()) {
            return;
        }

        setTilt(block, BigDripleafTilt.FULL_TILT);
        block.addSound(SimpleSound.BIG_DRIPLEAF_TILT_DOWN);
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), 100);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();

        // Find topmost big dripleaf in column
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

        // Check if block above the top is AIR or water
        var aboveTopPos = new Vector3i(topPos).add(0, 1, 0);
        var aboveTopState = dimension.getBlockState(aboveTopPos);
        var aboveTopType = aboveTopState.getBlockType();
        if (aboveTopType != BlockTypes.AIR && !aboveTopType.hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var topState = dimension.getBlockState(topPos);
        var direction = topState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);

        // Convert current top to stem
        var stemState = topState
                .setPropertyValue(BIG_DRIPLEAF_HEAD, false)
                .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE);
        dimension.setBlockState(topPos, stemState);

        // Place new head above
        var newHeadState = blockType.getDefaultState()
                .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);
        dimension.setBlockState(aboveTopPos, newHeadState);

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
        return true;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.SMALL_DRIPLEAF_BLOCK.createItemStack());
    }

    protected void setTilt(Block block, BigDripleafTilt tilt) {
        block.updateBlockProperty(BIG_DRIPLEAF_TILT, tilt);
    }

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
