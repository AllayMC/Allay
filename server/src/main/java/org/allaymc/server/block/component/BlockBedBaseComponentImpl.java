package org.allaymc.server.block.component;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent.SpawnPointType;
import org.allaymc.api.entity.component.EntitySleepableComponent;
import org.allaymc.api.eventbus.event.block.BlockExplodeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * {@code BlockBedBaseComponentImpl} implements paired bed placement, sleeping
 * interactions, and colored bed drops. Each placed bed uses a foot and head
 * sharing an orientation; the head identifies the stored spawn and sleeping
 * position. Placement writes the foot before the head and can leave the foot when
 * the second write fails.
 *
 * <p>
 * Bed interaction first handles dimensions where beds explode, subject to the game
 * rule and explosion event. In a working dimension, the component updates the
 * player's spawn before checking weather, time, occupancy, and the player's
 * existing sleep state. Breaking either part wakes matching sleepers, while only
 * the foot produces the colored item.
 *
 * @author harry-xi
 */
public class BlockBedBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} resolves bed block entities for colored
     * drops. Component initialization injects this dependency before the composed
     * block behavior is used.
     */
    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityBed> blockEntityHolderComponent;

    /**
     * Creates the bed block component for the supplied registered block type.
     */
    public BlockBedBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns the block wrapper at the other bed part's expected position. The
     * lookup uses {@code HEAD_PIECE_BIT} and {@code DIRECTION_4}; the returned
     * position is computed without validating the other block type.
     */
    public static Block getPairBlock(Block block) {
        var otherPos = posOfOtherPart(block);
        return new Block(block.getDimension(), otherPos);
    }

    /**
     * Returns the expected partner coordinate from the head flag and orientation.
     */
    private static Vector3ic posOfOtherPart(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return (head ? face.opposite() : face).offsetPos(block.getPosition());
    }

    /**
     * Returns whether both positions share the same integer coordinates.
     */
    private static boolean posEqVec3ic(Position3ic pos, Vector3ic other) {
        return pos.x() == other.x() && pos.y() == other.y() && pos.z() == other.z();
    }

    /**
     * Places a foot and head facing the player's horizontal direction.
     * {@code placementInfo} is required, and the head destination must have the
     * replaceable block tag before either write is attempted.
     *
     * <p>
     * Both states clear occupancy and pass through {@code processBlockProperties}.
     * The foot write precedes the head write through short-circuit evaluation. A
     * failed foot write suppresses the head write; a failed head write returns
     * {@code false} while leaving the successful foot write in place.
     */
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

        // Write the foot first so its failure suppresses head placement. A later
        // head failure leaves the foot because this sequence has no compensating
        // write.
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

    /**
     * Handles bed explosions, spawn selection, and attempts to sleep. A missing
     * interaction returns {@code false}; an explosion veto also returns
     * {@code false}, while game-rule suppression consumes the interaction.
     *
     * <p>
     * In dimensions where beds work, the player's spawn is stored at the head
     * before sleep eligibility is evaluated. Weather and time jointly control
     * eligibility, and occupancy or an already sleeping player prevents another
     * sleep attempt. These declined sleep cases still return {@code true} because
     * the interaction was handled.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();

        if (!dimension.getDimensionType().bedWorks()) {
            if (!dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.RESPAWN_BLOCKS_EXPLODE)) {
                return true;
            }

            // Use the bed explosion radius of five blocks for prohibited
            // dimensions.
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

        var headBlock = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT)
                ? block : getPairBlock(block);
        var headPos = headBlock.getPosition();

        var spawnPoint = player.validateAndGetSpawnPoint();
        var sameLocation = spawnPoint != null
                && spawnPoint.x() == headPos.x() && spawnPoint.y() == headPos.y() && spawnPoint.z() == headPos.z()
                && spawnPoint.dimension() == dimension;
        if (!sameLocation || player.getSpawnPointType() != SpawnPointType.BED) {
            player.setBlockSpawnPoint(headBlock.getLocation(), SpawnPointType.BED);
            player.sendTranslatable(TrKeys.MC_TILE_BED_RESPAWNSET);
        }

        var world = dimension.getWorld();
        // Reduce world time to its daily cycle before applying the dry and rainy
        // sleep windows; thunder bypasses both windows.
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

        if (headBlock.getPropertyValue(BlockPropertyTypes.OCCUPIED_BIT)) {
            player.sendTranslatable(TrKeys.MC_TILE_BED_OCCUPIED);
            return true;
        }

        if (player.isSleeping()) {
            return true;
        }

        player.sleep(headPos);
        return true;
    }

    /**
     * Runs shared neighbor handling and checks the expected partner position. A
     * partner-position update breaks this part when the neighbor's block type
     * differs from this component's registered bed type.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (posEqVec3ic(neighbor.getPosition(), posOfOtherPart(block))
            && neighbor.getBlockType() != getBlockType()) {
            block.breakBlock();
        }
    }

    /**
     * Wakes entities sleeping at this bed's head before shared break handling.
     * Breaking the head also requests a break of the foot; breaking the foot
     * locates the head and wakes its sleepers.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
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

    /**
     * Wakes sleeping entities whose stored sleep coordinates match the supplied
     * head. The search covers entities in that head's dimension and ignores
     * sleepers whose stored sleeping position is {@code null}.
     */
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

    /**
     * Returns the colored bed item only for the foot part. The head returns an
     * empty set so breaking the paired structure does not generate a second bed
     * item.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? Set.of() : createBedDrop(block);
    }

    /**
     * Creates one bed item carrying the block entity's color metadata.
     */
    private Set<ItemStack> createBedDrop(Block blockState) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(blockState.getPosition());
        var drop = blockState.toItemStack();
        drop.setMeta(blockEntity.getColor().ordinal());
        return Set.of(drop);
    }
}
