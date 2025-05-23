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
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.eventbus.event.block.BlockBurnEvent;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.world.biome.BiomeData;
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

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(currentBlockState, newBlockState, placementInfo);
        currentBlockState.getDimension().getBlockUpdateService().scheduleRandomBlockUpdateInDelay(currentBlockState.getPos(), FIRE_SCHEDULED_UPDATE_DELAY);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (tryConvertToSoulFire(current)) {
            return;
        }

        var pos = current.getPos();
        var dimension = current.getDimension();
        var downBlockState = current.offsetPos(BlockFace.DOWN);
        if (!canSupportFire(downBlockState) && !canNeighborBurn(current)) {
            var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
            }
        } else if (dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK) && !dimension.getBlockUpdateService().hasScheduledBlockUpdate(pos)) {
            dimension.getBlockUpdateService().scheduleRandomBlockUpdateInDelay(pos, FIRE_SCHEDULED_UPDATE_DELAY);
        }
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos current) {
        // Fire spreading is done in scheduled tick
        if (!current.getDimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
            // Fire tick is disabled
            return;
        }

        var pos = current.getPos();
        var dimension = current.getDimension();
        var downBlockState = current.offsetPos(BlockFace.DOWN);
        if (!canSupportFire(downBlockState) && !canNeighborBurn(current)) {
            var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
                return;
            }
        }

        if (tryWipedOutByRain(current)) {
            return;
        }

        var random = ThreadLocalRandom.current();

        var age = current.getPropertyValue(AGE_16);
        if (age < AGE_16.getMax()) {
            var newAge = age + random.nextInt(3);
            var newBlockState = current.setPropertyValue(AGE_16, Math.min(newAge, AGE_16.getMax()));
            dimension.setBlockState(pos, newBlockState);
        }

        dimension.getBlockUpdateService().scheduleRandomBlockUpdateInDelay(pos, FIRE_SCHEDULED_UPDATE_DELAY + random.nextInt(10));

        var burnForever = canFireBurnForever(downBlockState);
        if (!burnForever) {
            if (!canNeighborBurn(current)) {
                if (canSupportFire(downBlockState) && age <= 3) {
                    return;
                }

                var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    current.getDimension().setBlockState(current.getPos(), event.getNewBlockState());
                    return;
                }
            }

            if (!(downBlockState.getBlockStateData().burnOdds() > 0) && age == AGE_16.getMax() && random.nextInt(4) == 0) {
                var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    current.getDimension().setBlockState(current.getPos(), event.getNewBlockState());
                    return;
                }
            }
        }

        burnBlockAround(current, age);
        spreadFire(current);
    }

    private void burnBlockAround(BlockStateWithPos blockStateWithPos, Integer age) {
        // TODO: INCREASED_FIRE_BURNOUT
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            burnBlock(blockStateWithPos, blockStateWithPos.offsetPos(face), 300, age);
        }

        burnBlock(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.UP), 250, age);
        burnBlock(blockStateWithPos, blockStateWithPos.offsetPos(BlockFace.DOWN), 250, age);
    }

    protected boolean tryConvertToSoulFire(BlockStateWithPos blockStateWithPos) {
        var downBlockType = blockStateWithPos.offsetPos(BlockFace.DOWN).getBlockType();
        // Block that has SOUL_FIRE_CONVERTER tag can transform
        // normal fire to soul fire, and the soul fire will
        // transform back to normal block when block below change
        if (downBlockType.hasBlockTag(BlockCustomTags.SOUL_FIRE_CONVERTER)) {
            blockStateWithPos.getDimension().setBlockState(blockStateWithPos.getPos(), BlockTypes.SOUL_FIRE.copyPropertyValuesFrom(blockStateWithPos));
            return true;
        }

        return false;
    }

    protected void spreadFire(BlockStateWithPos source) {
        var random = ThreadLocalRandom.current();
        var pos = source.getPos();
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
                    var flameOdds = getMaxFlameOddsOfNeighborsEncouragingFire(new BlockStateWithPos(localBlockState, new Position3i(lx, ly, lz, dimension), 0));
                    if (flameOdds <= 0) {
                        continue;
                    }

                    var k = 100;
                    if (ly > y + 1) {
                        k += (ly - (y + 1)) * 100;
                    }
                    var maxChance = (flameOdds + 40 + dimension.getWorld().getWorldData().getDifficulty().ordinal() * 7) / (age + 30);
                    if (BiomeData.getBiomeData(dimension.getBiome(lx, ly, lz)).isHumid()) {
                        maxChance /= 2;
                    }

                    if (maxChance > 0 && random.nextInt(k) <= maxChance) {
                        var newAge = Math.min(age + (random.nextInt(5) >> 2), AGE_16.getMax());
                        var localBlockStateWithPos = new BlockStateWithPos(localBlockState, new Position3i(lx, ly, lz, dimension), 0);
                        var event = new BlockIgniteEvent(localBlockStateWithPos, source, null, BlockIgniteEvent.BlockIgniteCause.SPREAD);

                        if (event.call()) {
                            dimension.setBlockState(lx, ly, lz, BlockTypes.FIRE.ofState(AGE_16.createValue(newAge)));
                            dimension.getBlockUpdateService().scheduleRandomBlockUpdateInDelay(new Vector3i(lx, ly, lz), FIRE_SCHEDULED_UPDATE_DELAY);
                        }
                    }
                }
            }
        }
    }

    protected void burnBlock(BlockStateWithPos source, BlockStateWithPos target, int bound, int sourceFireAge) {
        var dimension = source.getDimension();
        var random = ThreadLocalRandom.current();

        if (random.nextInt(bound) < target.getBlockStateData().burnOdds()) {
            if (random.nextInt(sourceFireAge + 10) < 5) {
                var currentFireAge = Math.min(sourceFireAge + random.nextInt(5) / 4, AGE_16.getMax());

                var event = new BlockIgniteEvent(target, source, null, BlockIgniteEvent.BlockIgniteCause.SPREAD);
                if (event.call()) {
                    dimension.setBlockState(target.getPos(), BlockTypes.FIRE.ofState(AGE_16.createValue(currentFireAge)));
                    dimension.getBlockUpdateService().scheduleRandomBlockUpdateInDelay(target.getPos(), FIRE_SCHEDULED_UPDATE_DELAY);
                }
            } else {
                var event = new BlockBurnEvent(target);
                if (event.call()) {
                    dimension.setBlockState(target.getPos(), BlockTypes.AIR.getDefaultState());
                }
            }

            if (target.getBlockType() == BlockTypes.TNT) {
                BlockTypes.TNT.getBlockBehavior().prime(target);
            }
        }
    }

    protected boolean tryWipedOutByRain(BlockStateWithPos blockStateWithPos) {
        if (willBeWipedOutByRain(blockStateWithPos)) {
            var event = new BlockFadeEvent(blockStateWithPos, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                blockStateWithPos.getDimension().setBlockState(blockStateWithPos.getPos(), event.getNewBlockState());
                return true;
            }
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public boolean canCollideWithEntity() {
        return true;
    }

    @Override
    public void onCollideWithEntity(BlockStateWithPos current, Entity entity) {
        if (!(entity instanceof EntityDamageComponent damageComponent)) {
            return;
        }

        var event = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, current, 20 * 8);
        if (event.call()) {
            damageComponent.setOnFireTicks(event.getOnFireTicks());
        }
    }

    protected static int getMaxFlameOddsOfNeighborsEncouragingFire(BlockStateWithPos blockStateWithPos) {
        if (blockStateWithPos.getBlockType() != BlockTypes.AIR) {
            return 0;
        } else {
            int flameOdds = 0;
            for (var face : BlockFace.values()) {
                flameOdds = Math.max(flameOdds, blockStateWithPos.offsetPos(face).getBlockStateData().flameOdds());
            }
            return flameOdds;
        }
    }

    protected static boolean canNeighborBurn(BlockStateWithPos blockStateWithPos) {
        for (BlockFace face : BlockFace.values()) {
            if (blockStateWithPos.offsetPos(face).getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }
        return false;
    }

    protected static boolean willBeWipedOutByRain(BlockStateWithPos blockStateWithPos) {
        var pos = blockStateWithPos.getPos();
        var dimension = pos.dimension();
        var downBlockState = blockStateWithPos.offsetPos(BlockFace.DOWN);
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
