package org.allaymc.server.block.component.liquid;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.eventbus.event.block.LiquidHardenEvent;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource;

/**
 * {@code BlockLavaBaseComponentImpl} implements lava flow characteristics, contact damage,
 * nearby ignition, and hardening. Lava cannot occupy another block's liquid layer or form new
 * sources, and its horizontal decay and update delay depend on whether the dimension is the
 * Nether.
 *
 * <p>
 * Hardening combines the current lava's source flag with nearby water, blue ice, and
 * underlying soul soil. Neighbor scans exclude the lower face and update the proposed solid as
 * qualifying neighbors are visited, so scan order can affect mixed basalt and water
 * conditions. Every accepted hardening proposal writes the handler-selected state before
 * emitting fizz, while cancellation leaves the caller free to continue normal liquid
 * processing.
 *
 * @author daoge_cmd
 */
public class BlockLavaBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    /**
     * Creates the lava behavior component for the supplied block type.
     */
    public BlockLavaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns whether the supplied block type carries the lava-family tag.
     */
    @Override
    public boolean isSameLiquidType(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.LAVA);
    }

    /**
     * Applies lava combustion and periodic contact damage to living entities. Combustion and
     * damage have separate cancellable events; fire-vulnerable entities receive a four-point
     * lava damage proposal every ten entity ticks.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        // Convert fifteen seconds of combustion to twenty game ticks per second.
        var event1 = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, block, 20 * 15);
        if (event1.call()) {
            living.setOnFireTicks(event1.getOnFireTicks());
        }

        // The ten-tick cadence limits repeated contact damage while the entity remains inside lava.
        if (living.hasFireDamage() && entity.getTick() % 10 == 0) {
            var event2 = new EntityDamageEvent(entity, DamageContainer.lava(4));
            if (event2.call()) {
                living.attack(event2.getDamageContainer(), true);
            }
        }
    }

    /**
     * Attempts surrounding-block hardening before processing a neighbor update as flowing
     * lava. A failed or cancelled hardening proposal falls through to the inherited hook,
     * which dispatches the neighbor event and requests liquid scheduling.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (!tryHarden(block, null)) {
            super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        }
    }

    /**
     * Attempts surrounding-block hardening before the scheduled liquid update proceeds.
     * Successful hardening consumes this update; absent conditions or a cancelled proposal
     * leave the inherited source and flow processing available.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (!tryHarden(block, null)) {
            super.onScheduledUpdate(block);
        }
    }

    /**
     * Dispatches inherited post-placement behavior and checks the newly placed lava for
     * hardening. The check uses a snapshot containing {@code newBlockState} at the old block's
     * position and layer, rather than the replaced state.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        tryHarden(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()), null);
    }

    /**
     * Attempts to replace lava with a solid selected from its source state and surroundings. A
     * supplied {@code flownIntoBy} must be water: source lava proposes obsidian and other lava
     * proposes cobblestone. A {@code null} input instead scans every neighboring face except
     * the lower face.
     *
     * <p>
     * During that scan, blue ice with soul soil below proposes basalt, while water proposes
     * obsidian or cobblestone. Later qualifying neighbors can overwrite the proposed solid.
     * The hardening event can replace the solid or cancel the operation; acceptance writes at
     * the lava position before emitting fizz and returning {@code true}.
     */
    @Override
    public boolean tryHarden(Block block, Block flownIntoBy) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        BlockState hardenedBlockState = null;
        if (flownIntoBy == null) {
            BlockState waterBlockState = null;
            var down = block.offsetPos(BlockFace.DOWN);
            var soulSoilUnder = down.getBlockType() == BlockTypes.SOUL_SOIL;
            for (var face : BlockFace.VALUES) {
                if (face == BlockFace.DOWN) {
                    continue;
                }

                var neighborBlock = block.offsetPos(face);
                var neighborBlockType = neighborBlock.getBlockType();
                if (neighborBlockType == BlockTypes.BLUE_ICE && soulSoilUnder) {
                    hardenedBlockState = BlockTypes.BASALT.getDefaultState();
                    continue;
                }

                if (BlockTypes.WATER.getBlockBehavior().isSameLiquidType(neighborBlockType)) {
                    waterBlockState = neighborBlock.getBlockState();
                    if (isSource(block.getBlockState())) {
                        hardenedBlockState = BlockTypes.OBSIDIAN.getDefaultState();
                    } else {
                        hardenedBlockState = BlockTypes.COBBLESTONE.getDefaultState();
                    }
                }
            }

            if (hardenedBlockState != null) {
                var event = new LiquidHardenEvent(block, waterBlockState, hardenedBlockState, pos);
                if (!event.call()) {
                    return false;
                }

                dimension.setBlockState(pos, event.getHardenedBlockState());
                block.addSound(SimpleSound.FIZZ);
                return true;
            }

            return false;
        }

        var isWaterFlownInto = BlockTypes.WATER.getBlockBehavior().isSameLiquidType(flownIntoBy.getBlockType());
        if (!isWaterFlownInto) {
            return false;
        }

        if (isSource(block.getBlockState())) {
            hardenedBlockState = BlockTypes.OBSIDIAN.getDefaultState();
        } else {
            hardenedBlockState = BlockTypes.COBBLESTONE.getDefaultState();
        }

        var event = new LiquidHardenEvent(block, flownIntoBy.getBlockState(), hardenedBlockState, pos);
        if (!event.call()) {
            return false;
        }

        dimension.setBlockState(pos, event.getHardenedBlockState());
        dimension.addSound(MathUtils.center(pos), SimpleSound.FIZZ);
        return true;
    }

    /**
     * Attempts nearby ignition when {@code DO_FIRE_TICK} is enabled. A random branch checks
     * air positions one level above lava for combustible neighbors; its first eligible
     * candidate ends the attempt after dispatching an ignition event.
     *
     * <p>
     * The other branch checks three nearby positions at the lava's height for combustible
     * blocks with air above. That branch proposes ignition using the checked block and writes
     * the selected fire state at that same position after acceptance. Both branches choose
     * soul fire from the block below their write position, and cancellation skips the
     * corresponding fire write.
     */
    // See https://minecraft.wiki/w/Lava#Fire_spread
    @Override
    public void onRandomUpdate(Block block) {
        if (!block.getDimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
            return;
        }

        var pos = block.getPosition();
        var dimension = block.getDimension();
        var random = ThreadLocalRandom.current();
        var i = random.nextInt(3);

        if (i > 0) {
            for (int k = 0; k < i; ++k) {
                var v = new Vector3i(pos.x() + random.nextInt(3) - 2, pos.y() + 1, pos.z() + random.nextInt(3) - 2);
                var blockState = dimension.getBlockState(v);

                if (blockState.getBlockType() == BlockTypes.AIR) {
                    if (!this.canNeighborBurn(dimension, v)) {
                        continue;
                    }

                    var event = new BlockIgniteEvent(new Block(blockState, new Position3i(v, dimension), 0), block, null, BlockIgniteEvent.BlockIgniteCause.LAVA);
                    if (event.call()) {
                        var fireBlockState = getFireBlockState(dimension, v);
                        dimension.setBlockState(v, fireBlockState);
                    }

                    return;
                }
            }
        } else {
            for (int k = 0; k < 3; ++k) {
                var v = new Vector3i(pos.x() + random.nextInt(3) - 1, pos.y(), pos.z() + random.nextInt(3) - 1);
                var blockState = dimension.getBlockState(v);
                if (dimension.getBlockState(v.x(), v.y() + 1, v.z()).getBlockType() != BlockTypes.AIR || blockState.getBlockStateData().flameOdds() <= 0) {
                    continue;
                }

                var event = new BlockIgniteEvent(new Block(blockState, new Position3i(v, dimension), 0), block, null, BlockIgniteEvent.BlockIgniteCause.LAVA);
                if (event.call()) {
                    var fireBlockState = getFireBlockState(dimension, v);
                    dimension.setBlockState(v, fireBlockState);
                }
            }
        }
    }

    /**
     * Returns soul fire when the block below {@code pos} carries the soul-fire-converter tag.
     * Otherwise the ignition attempt uses ordinary fire; the supplied position identifies the
     * proposed fire location.
     */
    protected BlockState getFireBlockState(Dimension dimension, Vector3ic pos) {
        return dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType().hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER) ? BlockTypes.SOUL_FIRE.getDefaultState() : BlockTypes.FIRE.getDefaultState();
    }

    /**
     * Returns whether any directly adjacent block has positive flame odds.
     */
    protected boolean canNeighborBurn(Dimension dimension, Vector3ic pos) {
        for (var face : BlockFace.VALUES) {
            if (dimension.getBlockState(face.offsetPos(pos)).getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns whether lava receives random updates for nearby ignition attempts.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Returns the depth lost by horizontal lava flow in the supplied dimension. Nether lava
     * loses one level per step; every other dimension loses two levels per step.
     */
    @Override
    public int getFlowDecay(DimensionType dimensionType) {
        return dimensionType == DimensionTypes.NETHER ? 1 : 2;
    }

    /**
     * Returns lava's scheduled flow delay in game ticks for the supplied dimension. Nether
     * lava updates after ten ticks, while lava in every other dimension uses thirty ticks.
     */
    @Override
    public int getFlowSpeed(DimensionType dimensionType) {
        return dimensionType == DimensionTypes.NETHER ? 10 : 30;
    }

    /**
     * Returns whether lava participates in source formation, which it disables.
     */
    @Override
    public boolean canFormSource() {
        return false;
    }

    /**
     * Returns whether lava supports containment, which this implementation always disables.
     */
    @Override
    public boolean canBeContained() {
        return false;
    }
}
