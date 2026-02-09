package org.allaymc.server.block.component;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.dto.NeighborUpdateContext;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntitySleepableComponent;
import org.allaymc.api.eventbus.event.block.BlockExplodeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * @author harry-xi
 */
public class BlockBedBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityBed> blockEntityHolderComponent;

    public BlockBedBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    public static Block getPairBlock(Block block) {
        var otherPos = posOfOtherPart(block);
        return new Block(block.getDimension(), otherPos);
    }

    private static Vector3ic posOfOtherPart(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return (head ? face.opposite() : face).offsetPos(block.getPosition());
    }

    private static boolean posEqVec3ic(Position3ic pos, Vector3ic other) {
        return pos.x() == other.x() && pos.y() == other.y() && pos.z() == other.z();
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var playerFace = placementInfo.player().getHorizontalFace();
        var nextPos = playerFace.offsetPos(placeBlockPos);
        var nextBlockState = dimension.getBlockState(nextPos);
        if (!nextBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        blockState = blockState
                .setPropertyValue(BlockPropertyTypes.DIRECTION_4, playerFace.getHorizontalIndex())
                .setPropertyValue(BlockPropertyTypes.OCCUPIED_BIT, false);

        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                processBlockProperties(blockState.setPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT, false), placeBlockPos, placementInfo),
                placementInfo
        ) && dimension.setBlockState(
                nextPos.x(), nextPos.y(), nextPos.z(),
                processBlockProperties(blockState.setPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT, true), nextPos, placementInfo),
                placementInfo
        );
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();

        if (dimension.getDimensionInfo() != DimensionInfo.OVERWORLD) {
            if (!dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.RESPAWN_BLOCKS_EXPLODE)) {
                return true;
            }

            var explosion = new Explosion(5, true);
            explosion.setSourceBlockType(this.blockType);
            var event = new BlockExplodeEvent(block, explosion);
            if (!event.call()) {
                return false;
            }

            explosion.explode(dimension, block.getPosition());
            return true;
        }

        var player = interactInfo.player();

        // Find the head block of the bed
        var headBlock = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT)
                ? block : getPairBlock(block);
        var headPos = headBlock.getPosition();

        // Always set spawn point to the head position
        var spawnPoint = player.validateAndGetSpawnPoint();
        if (spawnPoint == null || spawnPoint.x() != headPos.x() || spawnPoint.y() != headPos.y() || spawnPoint.z() != headPos.z()
            || spawnPoint.dimension() != dimension) {
            player.setSpawnPoint(headBlock.getLocation());
            player.sendTranslatable(TrKeys.MC_TILE_BED_RESPAWNSET);
        }

        // Check time - can only sleep at night or during rain/thunder
        var world = dimension.getWorld();
        var time = world.getWorldData().getTimeOfDay() % WorldData.TIME_FULL;
        var weather = world.getWeather();

        if (weather != Weather.THUNDER) {
            if (weather != Weather.RAIN && (time <= WorldData.TIME_SLEEP || time >= WorldData.TIME_WAKE)) {
                player.sendTranslatable(TrKeys.MC_TILE_BED_NOSLEEP);
                return true;
            }
            if (time <= WorldData.TIME_SLEEP_WITH_RAIN || time >= WorldData.TIME_WAKE_WITH_RAIN) {
                player.sendTranslatable(TrKeys.MC_TILE_BED_NOSLEEP);
                return true;
            }
        }

        // Check if bed is occupied
        if (headBlock.getPropertyValue(BlockPropertyTypes.OCCUPIED_BIT)) {
            player.sendTranslatable(TrKeys.MC_TILE_BED_OCCUPIED);
            return true;
        }

        // Check if player is already sleeping
        if (player.isSleeping()) {
            return true;
        }

        player.sleep(headPos);
        return true;
    }

    @Override
    public void onNeighborUpdate(NeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();
        var neighbor = context.neighbor();
        if (posEqVec3ic(neighbor.getPosition(), posOfOtherPart(block))
            && neighbor.getBlockType() != getBlockType()) {
            block.breakBlock();
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        // Wake up any entity sleeping in this bed
        if (block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT)) {
            wakeSleeper(block);
            var footPos = posOfOtherPart(block);
            block.getDimension().breakBlock(footPos, null, entity);
        } else {
            var headBlock = getPairBlock(block);
            wakeSleeper(headBlock);
        }
        super.onBreak(block, usedItem, entity);
    }

    private void wakeSleeper(Block headBlock) {
        var headPos = headBlock.getPosition();
        for (var entity : headBlock.getDimension().getEntities().values()) {
            if (entity instanceof EntitySleepableComponent sleepable && sleepable.isSleeping()) {
                var sleepPos = sleepable.getSleepingPos();
                if (sleepPos != null && sleepPos.x() == headPos.x() && sleepPos.y() == headPos.y() && sleepPos.z() == headPos.z()) {
                    sleepable.wake();
                }
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? Set.of() : createBedDrop(block);
    }

    private Set<ItemStack> createBedDrop(Block blockState) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(blockState.getPosition());
        var drop = blockState.toItemStack();
        drop.setMeta(blockEntity.getColor().ordinal());
        return Set.of(drop);
    }
}
