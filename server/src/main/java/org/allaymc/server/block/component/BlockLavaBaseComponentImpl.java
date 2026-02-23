package org.allaymc.server.block.component;

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
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource;

/**
 * @author daoge_cmd
 */
public class BlockLavaBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    public BlockLavaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean isSameLiquidType(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.LAVA);
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        // Set on fire ticks
        var event1 = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, block, 20 * 15);
        if (event1.call()) {
            living.setOnFireTicks(event1.getOnFireTicks());
        }

        // Lava damage
        if (living.hasFireDamage() && entity.getTick() % 10 == 0) {
            var event2 = new EntityDamageEvent(entity, DamageContainer.lava(4));
            if (event2.call()) {
                living.attack(event2.getDamageContainer(), true);
            }
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (!tryHarden(block, null)) {
            super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (!tryHarden(block, null)) {
            super.onScheduledUpdate(block);
        }
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        tryHarden(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()), null);
    }

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

                // This method also considered BlockTypes.FLOWING_WATER as the same liquid type
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

    protected BlockState getFireBlockState(Dimension dimension, Vector3ic pos) {
        // Check if the block that the player clicked on is a soul fire converter
        // In that case, we should place a soul fire instead of a normal fire
        return dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType().hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER) ? BlockTypes.SOUL_FIRE.getDefaultState() : BlockTypes.FIRE.getDefaultState();
    }

    protected boolean canNeighborBurn(Dimension dimension, Vector3ic pos) {
        for (var face : BlockFace.VALUES) {
            if (dimension.getBlockState(face.offsetPos(pos)).getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public int getFlowDecay(DimensionInfo dimensionInfo) {
        return dimensionInfo == DimensionInfo.NETHER ? 1 : 2;
    }

    @Override
    public int getFlowSpeed(DimensionInfo dimensionInfo) {
        return dimensionInfo == DimensionInfo.NETHER ? 10 : 30;
    }

    @Override
    public boolean canFormSource() {
        return false;
    }

    @Override
    public boolean canBeContained() {
        return false;
    }
}
