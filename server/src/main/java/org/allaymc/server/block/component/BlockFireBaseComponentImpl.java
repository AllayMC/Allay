package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockBurnEvent;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.gamerule.GameRule;
import org.joml.Vector3i;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockFireBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final int FIRE_SCHEDULED_UPDATE_DELAY = 30;

    public BlockFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(currentBlockState, newBlockState, placementInfo);
        currentBlockState.dimension().getBlockUpdateService().scheduleBlockUpdate(currentBlockState.pos(), FIRE_SCHEDULED_UPDATE_DELAY);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        var pos = current.pos();
        var dimension = pos.dimension();
        var downBlockState = current.offsetPos(BlockFace.DOWN).blockState();

        if (tryConvertToSoulFire(current)) {
            return;
        }

        if (!canSupportFire(downBlockState) && !canNeighborBurn(current)) {
            var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
            }
        } else if (dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK) && !dimension.getBlockUpdateService().hasScheduledBlockUpdate(pos)) {
            dimension.getBlockUpdateService().scheduleBlockUpdate(pos, FIRE_SCHEDULED_UPDATE_DELAY);
        }
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {
        // Fire spreading is done in scheduled tick
        if (!blockStateWithPos.pos().dimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
            // Fire tick is disabled
            return;
        }

        var pos = blockStateWithPos.pos();
        var dimension = pos.dimension();
        var downBlockState = blockStateWithPos.offsetPos(BlockFace.DOWN).blockState();

        if (!canSupportFire(downBlockState) && !canNeighborBurn(blockStateWithPos)) {
            var event = new BlockFadeEvent(blockStateWithPos, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
                return;
            }
        }

        if (tryWipedOutByRain(blockStateWithPos)) {
            return;
        }

        var blockState = blockStateWithPos.blockState();
        var random = ThreadLocalRandom.current();
        var age = blockState.getPropertyValue(BlockPropertyTypes.AGE_16);

        if (age < 15) {
            var newAge = age + random.nextInt(3);
            var newBlockState = blockState.setProperty(BlockPropertyTypes.AGE_16, Math.min(newAge, 15));
            dimension.setBlockState(pos, newBlockState);
        }

        dimension.getBlockUpdateService().scheduleBlockUpdate(pos, FIRE_SCHEDULED_UPDATE_DELAY + random.nextInt(10));

        var burnForever = canFireBurnForever(downBlockState);

        if (!burnForever) {
            if (!canNeighborBurn(blockStateWithPos)) {
                if (canSupportFire(downBlockState) && age <= 3) {
                    return;
                }

                var event = new BlockFadeEvent(blockStateWithPos, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    blockStateWithPos.dimension().setBlockState(blockStateWithPos.pos(), event.getNewBlockState());
                    return;
                }
            }

            if (!(downBlockState.getBlockStateData().burnOdds() > 0) && age == 15 && random.nextInt(4) == 0) {
                var event = new BlockFadeEvent(blockStateWithPos, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    blockStateWithPos.dimension().setBlockState(blockStateWithPos.pos(), event.getNewBlockState());
                    return;
                }
            }
        }

        // TODO: decrease the value of base if the rainfall values are high
        var base = 0;
        trySpreadFireOn(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.EAST), 300 + base, age);
        trySpreadFireOn(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.WEST), 300 + base, age);
        trySpreadFireOn(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.DOWN), 250 + base, age);
        trySpreadFireOn(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.UP), 250 + base, age);
        trySpreadFireOn(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.SOUTH), 300 + base, age);
        trySpreadFireOn(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.NORTH), 300 + base, age);

        trySpreadFire(blockStateWithPos);
    }

    protected boolean tryConvertToSoulFire(BlockStateWithPos blockStateWithPos) {
        var downBlockType = blockStateWithPos.offsetPos(BlockFace.DOWN).blockState().getBlockType();
        // Block that has SOUL_FIRE_CONVERTER tag can transform
        // normal fire to soul fire, and the soul fire will
        // transform back to normal block when block below change
        if (downBlockType.hasBlockTag(BlockCustomTags.SOUL_FIRE_CONVERTER)) {
            blockStateWithPos.dimension().setBlockState(blockStateWithPos.pos(), BlockTypes.SOUL_FIRE.copyPropertyValuesFrom(blockStateWithPos.blockState()));
            return true;
        }

        return false;
    }

    protected void trySpreadFire(BlockStateWithPos source) {
        var random = ThreadLocalRandom.current();
        var pos = source.pos();
        var x = pos.x();
        var z = pos.z();
        var y = pos.y();
        var dimension = pos.dimension();
        var age = source.blockState().getPropertyValue(BlockPropertyTypes.AGE_16);

        for (var lx = x - 1; lx <= x + 1; ++lx) {
            for (var lz = z - 1; lz <= z + 1; ++lz) {
                for (var ly = y - 1; ly <= y + 4; ++ly) {
                    if (lx == x && ly == y && lz == z) {
                        continue;
                    }

                    var localBlockState = dimension.getBlockState(lx, ly, lz);
                    var flameOdds = getMaxFlameOddsOfNeighborsEncouragingFire(new BlockStateWithPos(localBlockState, new Position3i(lx, ly, lz, dimension), 0));

                    if (flameOdds <= 0) {
                        continue;
                    }

                    var k = 100;
                    if (ly > y + 1) {
                        k += (ly - (y + 1)) * 100;
                    }
                    // TODO: decrease the value of t if the rainfall values are high
                    var t = (flameOdds + 40 + dimension.getWorld().getWorldData().getDifficulty().ordinal() * 7) / (age + 30);

                    if (t > 0 && random.nextInt(k) <= t) {
                        var newAge = Math.min(age + (random.nextInt(5) >> 2), 15);
                        var localBlockStateWithPos = new BlockStateWithPos(localBlockState, new Position3i(lx, ly, lz, dimension), 0);
                        var event = new BlockIgniteEvent(localBlockStateWithPos, source, null, BlockIgniteEvent.BlockIgniteCause.SPREAD);

                        if (event.call()) {
                            dimension.setBlockState(lx, ly, lz, BlockTypes.FIRE.ofState(BlockPropertyTypes.AGE_16.createValue(newAge)));
                            dimension.getBlockUpdateService().scheduleBlockUpdate(new Vector3i(lx, ly, lz), FIRE_SCHEDULED_UPDATE_DELAY);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Utils.EMPTY_ITEM_STACK_SET;
    }

    protected void trySpreadFireOn(BlockStateWithPos source, BlockStateWithPos target, int bound, int sourceFireAge) {
        var targetBlockState = target.blockState();
        var dimension = source.dimension();
        var random = ThreadLocalRandom.current();

        if (random.nextInt(bound) < targetBlockState.getBlockStateData().burnOdds()) {
            if (random.nextInt(sourceFireAge + 10) < 5) {
                var currentFireAge = Math.min(sourceFireAge + random.nextInt(5) / 4, 15);

                var event = new BlockIgniteEvent(target, source, null, BlockIgniteEvent.BlockIgniteCause.SPREAD);
                if (event.call()) {
                    dimension.setBlockState(target.pos(), BlockTypes.FIRE.ofState(BlockPropertyTypes.AGE_16.createValue(currentFireAge)));
                    dimension.getBlockUpdateService().scheduleBlockUpdate(target.pos(), FIRE_SCHEDULED_UPDATE_DELAY);
                }
            } else {
                var event = new BlockBurnEvent(target);
                if (event.call()) {
                    dimension.setBlockState(target.pos(), BlockTypes.AIR.getDefaultState());
                }
            }

            // TODO: prime tnt
//            if (block instanceof BlockTnt blockTnt) {
//                blockTnt.prime();
//            }
        }
    }

    protected boolean tryWipedOutByRain(BlockStateWithPos blockStateWithPos) {
        if (willBeWipedOutByRain(blockStateWithPos)) {
            var event = new BlockFadeEvent(blockStateWithPos, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                blockStateWithPos.dimension().setBlockState(blockStateWithPos.pos(), event.getNewBlockState());
                return true;
            }
        }

        return false;
    }

    protected static int getMaxFlameOddsOfNeighborsEncouragingFire(BlockStateWithPos blockStateWithPos) {
        if (blockStateWithPos.blockState().getBlockType() != BlockTypes.AIR) {
            return 0;
        } else {
            int flameOdds = 0;
            flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(BlockFace.EAST).blockState().getBlockStateData().flameOdds());
            flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(BlockFace.WEST).blockState().getBlockStateData().flameOdds());
            flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(BlockFace.DOWN).blockState().getBlockStateData().flameOdds());
            flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(BlockFace.UP).blockState().getBlockStateData().flameOdds());
            flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(BlockFace.SOUTH).blockState().getBlockStateData().flameOdds());
            flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(BlockFace.NORTH).blockState().getBlockStateData().flameOdds());
            return flameOdds;
        }
    }

    protected static boolean canNeighborBurn(BlockStateWithPos blockStateWithPos) {
        for (BlockFace face : BlockFace.values()) {
            if (blockStateWithPos.offsetPos(face).blockState().getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }
        return false;
    }

    protected static boolean willBeWipedOutByRain(BlockStateWithPos blockStateWithPos) {
        var pos = blockStateWithPos.pos();
        var dimension = pos.dimension();
        var downBlockState = blockStateWithPos.offsetPos(BlockFace.DOWN).blockState();
        var burnForever = canFireBurnForever(downBlockState);

        return !burnForever && dimension.getWorld().getWeathers().contains(Weather.RAIN) &&
               (dimension.canPosSeeSky(pos) ||
                dimension.canPosSeeSky(BlockFace.EAST.offsetPos(pos)) ||
                dimension.canPosSeeSky(BlockFace.WEST.offsetPos(pos)) ||
                dimension.canPosSeeSky(BlockFace.SOUTH.offsetPos(pos)) ||
                dimension.canPosSeeSky(BlockFace.NORTH.offsetPos(pos)));
    }

    protected static boolean canFireBurnForever(BlockState blockState) {
        var blockType = blockState.getBlockType();
        var burnForever = blockType.hasBlockTag(BlockCustomTags.INFINITE_FIRE_SUPPORTER);
        // INFINIBURN_BIT is used by bedrock
        if (blockType.hasProperty(BlockPropertyTypes.INFINIBURN_BIT)) {
            burnForever = blockState.getPropertyValue(BlockPropertyTypes.INFINIBURN_BIT);
        }
        return burnForever;
    }

    // This method is also used by ItemFlintAndSteelBaseComponentImpl
    public static boolean canSupportFire(BlockState downBlockState) {
        if (downBlockState.getBlockType() == BlockTypes.SOUL_SAND) {
            // Soul sand can also support fire even
            // its up surface is not full
            return true;
        }
        // The block state below must be a solid block
        // and the top surface of this block must be full
        // to support the fire block
        return downBlockState.getBlockStateData().collisionShape().isFull(BlockFace.UP);
    }
}
