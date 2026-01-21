package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockBurnEvent;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRule;
import org.joml.Vector3i;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_16;

/**
 * @author daoge_cmd
 */
public class BlockFireBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final int FIRE_SCHEDULED_UPDATE_DELAY = 30;

    public BlockFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    protected static int getMaxFlameOddsOfNeighborsEncouragingFire(Block block) {
        if (block.getBlockType() != BlockTypes.AIR) {
            return 0;
        } else {
            int flameOdds = 0;
            for (var face : BlockFace.VALUES) {
                flameOdds = Math.max(flameOdds, block.offsetPos(face).getBlockStateData().flameOdds());
            }
            return flameOdds;
        }
    }

    protected static boolean canNeighborBurn(Block block) {
        for (BlockFace face : BlockFace.VALUES) {
            if (block.offsetPos(face).getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }
        return false;
    }

    protected static boolean willBeWipedOutByRain(Block block) {
        var pos = block.getPosition();
        var dimension = pos.dimension();
        var downBlockState = block.offsetPos(BlockFace.DOWN);
        var burnForever = canFireBurnForever(downBlockState.getBlockState());

        return !burnForever && dimension.getWorld().getWeather() != Weather.CLEAR &&
               (dimension.canPosSeeSky(pos) ||
                dimension.canPosSeeSky(BlockFace.EAST.offsetPos(pos)) ||
                dimension.canPosSeeSky(BlockFace.WEST.offsetPos(pos)) ||
                dimension.canPosSeeSky(BlockFace.SOUTH.offsetPos(pos)) ||
                dimension.canPosSeeSky(BlockFace.NORTH.offsetPos(pos)));
    }

    protected static boolean canFireBurnForever(BlockState blockState) {
        var blockType = blockState.getBlockType();
        var burnForever = blockType.hasBlockTag(BlockTags.INFINITE_FIRE_SUPPORTER);
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

    @Override
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(block, newBlockState, placementInfo);
        block.getDimension().getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(block.getPosition(), FIRE_SCHEDULED_UPDATE_DELAY);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        if (tryConvertToSoulFire(block)) {
            return;
        }

        var pos = block.getPosition();
        var dimension = block.getDimension();
        var downBlockState = block.offsetPos(BlockFace.DOWN);
        if (!canSupportFire(downBlockState.getBlockState()) && !canNeighborBurn(block)) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
            }
        } else if (dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK) && !dimension.getBlockUpdateManager().hasScheduledBlockUpdate(pos)) {
            dimension.getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(pos, FIRE_SCHEDULED_UPDATE_DELAY);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        // Fire spreading is done in scheduled tick
        if (!block.getDimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
            // Fire tick is disabled
            return;
        }

        var pos = block.getPosition();
        var dimension = block.getDimension();
        var downBlockState = block.offsetPos(BlockFace.DOWN);
        if (!canSupportFire(downBlockState.getBlockState()) && !canNeighborBurn(block)) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
                return;
            }
        }

        if (tryWipedOutByRain(block)) {
            return;
        }

        var random = ThreadLocalRandom.current();

        var age = block.getPropertyValue(AGE_16);
        if (age < AGE_16.getMax()) {
            var newAge = age + random.nextInt(3);
            var newBlockState = block.setPropertyValue(AGE_16, Math.min(newAge, AGE_16.getMax()));
            dimension.setBlockState(pos, newBlockState.getBlockState());
        }

        dimension.getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(pos, FIRE_SCHEDULED_UPDATE_DELAY + random.nextInt(10));

        var burnForever = canFireBurnForever(downBlockState.getBlockState());
        if (!burnForever) {
            if (!canNeighborBurn(block)) {
                if (canSupportFire(downBlockState.getBlockState()) && age <= 3) {
                    return;
                }

                var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                    return;
                }
            }

            if (!(downBlockState.getBlockStateData().burnOdds() > 0) && age == AGE_16.getMax() && random.nextInt(4) == 0) {
                var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                    return;
                }
            }
        }

        burnBlockAround(block, age);
        spreadFire(block);
    }

    @Override
    public void onSplash(Block block) {
        var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    private void burnBlockAround(Block block, Integer age) {
        // TODO: INCREASED_FIRE_BURNOUT
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            burnBlock(block, block.offsetPos(face), 300, age);
        }

        burnBlock(block, block.offsetPos(BlockFace.UP), 250, age);
        burnBlock(block, block.offsetPos(BlockFace.DOWN), 250, age);
    }

    protected boolean tryConvertToSoulFire(Block block) {
        var downBlockType = block.offsetPos(BlockFace.DOWN).getBlockType();
        // Block that has SOUL_FIRE_CONVERTER tag can transform
        // normal fire to soul fire, and the soul fire will
        // transform back to normal block when block below change
        if (downBlockType.hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER)) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.SOUL_FIRE.copyPropertyValuesFrom(block.getBlockState()));
            return true;
        }

        return false;
    }

    protected void spreadFire(Block source) {
        var random = ThreadLocalRandom.current();
        var pos = source.getPosition();
        var x = pos.x();
        var z = pos.z();
        var y = pos.y();
        var dimension = pos.dimension();
        var age = source.getPropertyValue(AGE_16);

        for (var lx = x - 1; lx <= x + 1; ++lx) {
            for (var lz = z - 1; lz <= z + 1; ++lz) {
                for (var ly = y - 1; ly <= y + 4; ++ly) {
                    if (lx == x && ly == y && lz == z) {
                        continue;
                    }

                    var localBlockState = dimension.getBlockState(lx, ly, lz);
                    var flameOdds = getMaxFlameOddsOfNeighborsEncouragingFire(new Block(localBlockState, new Position3i(lx, ly, lz, dimension), 0));
                    if (flameOdds <= 0) {
                        continue;
                    }

                    var k = 100;
                    if (ly > y + 1) {
                        k += (ly - (y + 1)) * 100;
                    }
                    var maxChance = (flameOdds + 40 + dimension.getWorld().getWorldData().getDifficulty().ordinal() * 7) / (age + 30);
                    if (dimension.getBiome(lx, ly, lz).getBiomeData().isHumid()) {
                        maxChance /= 2;
                    }

                    if (maxChance > 0 && random.nextInt(k) <= maxChance) {
                        var newAge = Math.min(age + (random.nextInt(5) >> 2), AGE_16.getMax());
                        var localBlock = new Block(localBlockState, new Position3i(lx, ly, lz, dimension), 0);
                        var event = new BlockIgniteEvent(localBlock, source, null, BlockIgniteEvent.BlockIgniteCause.SPREAD);

                        if (event.call()) {
                            dimension.setBlockState(lx, ly, lz, BlockTypes.FIRE.ofState(AGE_16.createValue(newAge)));
                            dimension.getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(new Vector3i(lx, ly, lz), FIRE_SCHEDULED_UPDATE_DELAY);
                        }
                    }
                }
            }
        }
    }

    protected void burnBlock(Block source, Block target, int bound, int sourceFireAge) {
        var dimension = source.getDimension();
        var random = ThreadLocalRandom.current();

        if (random.nextInt(bound) < target.getBlockStateData().burnOdds()) {
            if (random.nextInt(sourceFireAge + 10) < 5) {
                var currentFireAge = Math.min(sourceFireAge + random.nextInt(5) / 4, AGE_16.getMax());

                var event = new BlockIgniteEvent(target, source, null, BlockIgniteEvent.BlockIgniteCause.SPREAD);
                if (event.call()) {
                    dimension.setBlockState(target.getPosition(), BlockTypes.FIRE.ofState(AGE_16.createValue(currentFireAge)));
                    dimension.getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(target.getPosition(), FIRE_SCHEDULED_UPDATE_DELAY);
                }
            } else {
                var event = new BlockBurnEvent(target);
                if (event.call()) {
                    dimension.setBlockState(target.getPosition(), BlockTypes.AIR.getDefaultState());
                }
            }

            if (target.getBlockType() == BlockTypes.TNT) {
                BlockTypes.TNT.getBlockBehavior().prime(target.getPosition());
            }
        }
    }

    protected boolean tryWipedOutByRain(Block block) {
        if (willBeWipedOutByRain(block)) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                return true;
            }
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        var event = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, block, 20 * 8);
        if (event.call()) {
            living.setOnFireTicks(event.getOnFireTicks());
        }
    }
}
