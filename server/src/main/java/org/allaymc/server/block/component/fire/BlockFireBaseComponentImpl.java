package org.allaymc.server.block.component.fire;

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
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockBurnEvent;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.eventbus.event.block.PortalCreateEvent;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.block.NetherPortalHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_16;

/**
 * {@code BlockFireBaseComponentImpl} implements ordinary fire survival, age progression,
 * burning, spread, portal creation, and contact effects. Fire schedules its own updates and
 * honors the fire-tick game rule during scheduled progression. Support, nearby combustible
 * blocks, weather exposure, and infinite-burning rules determine whether it fades or
 * continues.
 *
 * <p>
 * Burning and spreading combine source age, target material odds, difficulty, height,
 * humidity, and randomness. Their events govern individual replacement writes, while TNT
 * priming follows a successful burn-odds roll even if the associated burn or ignition event
 * was cancelled. Post-placement portal detection occurs after the fire write so accepted
 * portal filling can replace that position. Contact combustion and direct fire damage are
 * separate effects, so cancelling combustion does not cancel the attack.
 *
 * @author daoge_cmd
 */
public class BlockFireBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code FIRE_SCHEDULED_UPDATE_DELAY} supplies the base game-tick delay for fire
     * progression and newly ignited fire scheduling.
     */
    protected static final int FIRE_SCHEDULED_UPDATE_DELAY = 30;

    /**
     * Creates the fire behavior component for the supplied block type.
     */
    public BlockFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns the highest flame odds of the six neighbors around an air block. A nonair
     * candidate yields {@code 0}, preventing the spreading calculation from treating occupied
     * space as an ignition destination.
     */
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

    /**
     * Returns whether any directly adjacent block has positive flame odds.
     */
    protected static boolean canNeighborBurn(Block block) {
        for (BlockFace face : BlockFace.VALUES) {
            if (block.offsetPos(face).getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether weather and sky exposure allow rain removal at this fire.
     * Infinite-burning support prevents removal; otherwise nonclear weather and visible sky at
     * the fire or any horizontal neighbor are sufficient.
     *
     * <p>
     * This predicate does not itself remove the fire or dispatch an event. The caller must
     * honor cancellation after the result, and the support's infinite-burning property can
     * override its tag before weather is considered. The test does not inspect a biome
     * precipitation flag or sample a separate extinction probability.
     */
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

    /**
     * Returns whether the supporting state enables infinite fire. The block tag supplies the
     * initial value, but an available {@code INFINIBURN_BIT} property overrides that value
     * even when the property is {@code false}.
     */
    protected static boolean canFireBurnForever(BlockState blockState) {
        var blockType = blockState.getBlockType();
        var burnForever = blockType.hasBlockTag(BlockTags.INFINITE_FIRE_SUPPORTER);
        // Bedrock states carrying INFINIBURN_BIT override the tag-derived support rule,
        // including a false property value on a tagged infinite-fire supporter.
        if (blockType.hasProperty(BlockPropertyTypes.INFINIBURN_BIT)) {
            burnForever = blockState.getPropertyValue(BlockPropertyTypes.INFINIBURN_BIT);
        }
        return burnForever;
    }

    /**
     * Returns whether a block can physically support ordinary fire above it. Soul sand
     * qualifies despite its nonfull upper surface; other states require a collision shape
     * whose upper face is full.
     */
    public static boolean canSupportFire(BlockState downBlockState) {
        if (downBlockState.getBlockType() == BlockTypes.SOUL_SAND) {
            // Preserve soul-sand fire support despite its lowered collision surface.
            return true;
        }
        return downBlockState.getBlockStateData().collisionShape().isFull(BlockFace.UP);
    }

    /**
     * Dispatches the base placement hook and schedules fire's first delayed update. The hook
     * runs before the block is committed, so this scheduling does not attempt portal filling
     * or spread immediately.
     */
    @Override
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onPlace(block, newBlockState, placementInfo);
        block.getDimension().getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(block.getPosition(), FIRE_SCHEDULED_UPDATE_DELAY);
    }

    /**
     * Checks the committed fire position for a Nether portal frame after inherited
     * post-placement handling. A detected interior is passed to a cancellable portal-create
     * event; only an accepted proposal fills the portal.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Try to create a nether portal (must be in afterPlaced because
        // onPlace runs before the block is committed to the chunk, so
        // fill() would be overwritten by the fire block placement)
        var dimension = oldBlock.getDimension();
        var shape = NetherPortalHelper.detect(dimension, oldBlock.getPosition());
        if (shape != null) {
            var portalBlocks = shape.getInteriorPositions();
            if (new PortalCreateEvent(dimension, portalBlocks, PortalCreateEvent.CreateReason.FIRE).call()) {
                shape.fill();
            }
        }
    }

    /**
     * Dispatches the base neighbor hook and first attempts conversion to soul fire. Ordinary
     * fire without support or combustible neighbors proposes fading; supported fire schedules
     * progression only when fire ticks are enabled and no update is already scheduled.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

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

    /**
     * Processes fire survival, weather removal, aging, nearby burning, and spreading when
     * {@code DO_FIRE_TICK} is enabled. Accepted unsupported-fire fading or rain removal ends
     * the callback. Otherwise the method advances the stored age and schedules another
     * randomized delayed update.
     *
     * <p>
     * The remaining decisions use the age from the incoming snapshot, including supported
     * young-fire survival, terminal-age extinction, neighbor burning, and spreading.
     * Infinite-burning support skips the ordinary burnout checks. Every fade proposal can be
     * cancelled independently; cancellation can therefore permit later aging or spreading
     * paths in the same callback rather than guaranteeing that the whole update stops.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (!block.getDimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
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

        // Add up to nine ticks of jitter to the next progression deadline
        // so fires placed together do not all retain identical update timing.
        dimension.getBlockUpdateManager().scheduleRandomBlockUpdateInDelay(pos, FIRE_SCHEDULED_UPDATE_DELAY + random.nextInt(10));

        var burnForever = canFireBurnForever(downBlockState.getBlockState());
        if (!burnForever) {
            if (!canNeighborBurn(block)) {
                // Young supported fire survives without a combustible neighbor until later aging.
                if (canSupportFire(downBlockState.getBlockState()) && age <= 3) {
                    return;
                }

                var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                    return;
                }
            }

            // Mature fire above nonburnable support has an additional extinction chance
            // even if neighboring materials kept it past the earlier survival check.
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

    /**
     * Proposes removing fire through a cancellable fade event after a splash. Acceptance
     * writes the event-selected replacement at the fire position; cancellation leaves that
     * replacement write unperformed by this hook.
     */
    @Override
    public void onSplash(Block block) {
        var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    /**
     * Attempts burning on every adjacent face using the supplied fire age. Horizontal faces
     * use a random bound of three hundred, while upper and lower faces use two hundred fifty,
     * making the same material odds more likely to pass vertically.
     */
    private void burnBlockAround(Block block, Integer age) {
        // TODO: Apply the INCREASED_FIRE_BURNOUT rule to the burn-attempt bounds when that rule is supported.
        // The smaller vertical sampling bound makes equal material odds more likely
        // to burn above or below the source than along its horizontal sides.
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            burnBlock(block, block.offsetPos(face), 300, age);
        }

        burnBlock(block, block.offsetPos(BlockFace.UP), 250, age);
        burnBlock(block, block.offsetPos(BlockFace.DOWN), 250, age);
    }

    /**
     * Converts ordinary fire when the lower block has the soul-fire-converter tag. The
     * soul-fire state copies compatible properties from the current fire, and this helper
     * reports the conversion attempt without inspecting the dimension's write result.
     */
    protected boolean tryConvertToSoulFire(Block block) {
        var downBlockType = block.offsetPos(BlockFace.DOWN).getBlockType();
        if (downBlockType.hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER)) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.SOUL_FIRE.copyPropertyValuesFrom(block.getBlockState()));
            return true;
        }

        return false;
    }

    /**
     * Attempts ignition in the surrounding three-by-six-by-three region, excluding the source.
     * Only air candidates with a combustible neighbor receive a positive flame score. Source
     * age and difficulty adjust the ignition threshold, humidity halves it, and candidates
     * farther above the source use a larger random bound.
     *
     * <p>
     * An accepted ignition event writes fire with an age derived from the source, capped at
     * the property maximum, then schedules its first update. Cancellation skips that
     * candidate's write and schedule while the surrounding scan continues. This helper uses
     * the supplied source snapshot throughout rather than rereading any age written earlier in
     * the callback.
     */
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

                    // Increase the sampling bound above the first upper layer so
                    // more distant vertical candidates require a rarer roll with the same flame score.
                    var k = 100;
                    if (ly > y + 1) {
                        k += (ly - (y + 1)) * 100;
                    }
                    // Older source fire lowers spreading, difficulty raises it, and humidity
                    // reduces the resulting integer threshold before the ignition roll is sampled.
                    var maxChance = (flameOdds + 40 + dimension.getWorld().getWorldData().getDifficulty().ordinal() * 7) / (age + 30);
                    if (dimension.getBiome(lx, ly, lz).getBiomeData().isHumid()) {
                        maxChance /= 2;
                    }

                    if (maxChance > 0 && random.nextInt(k) <= maxChance) {
                        // The shifted sample adds at most one age unit to a new flame.
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

    /**
     * Attempts to burn one target using material odds and the supplied random bound. A
     * successful first roll selects either a cancellable ignition proposal or a cancellable
     * burn-removal proposal according to source age and a second roll.
     *
     * <p>
     * Accepted ignition writes a bounded-age fire and schedules its update; accepted burning
     * writes air. TNT priming occurs after the branch whenever the first burn-odds roll
     * succeeds, even if either event was cancelled. Callers must pass a positive {@code
     * bound}, which is required by the random generator.
     */
    protected void burnBlock(Block source, Block target, int bound, int sourceFireAge) {
        var dimension = source.getDimension();
        var random = ThreadLocalRandom.current();

        if (random.nextInt(bound) < target.getBlockStateData().burnOdds()) {
            if (random.nextInt(sourceFireAge + 10) < 5) {
                // Integer division limits the new flame to zero or one additional age unit.
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

            // TNT priming depends on the successful burn-odds roll, not on acceptance
            // of the preceding ignition or removal event, so cancellation does not suppress priming.
            if (target.getBlockType() == BlockTypes.TNT) {
                BlockTypes.TNT.getBlockBehavior().prime(target.getPosition());
            }
        }
    }

    /**
     * Attempts a cancellable fade when the rain-removal predicate accepts the fire. The method
     * returns {@code true} only after an accepted replacement call; cancellation and
     * unsuitable weather both return {@code false}.
     */
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

    /**
     * Returns no item drops when an ordinary fire block breaks.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    /**
     * Proposes eight seconds of combustion for a living entity and attempts one point of fire
     * damage. Cancelling the combustion event skips the fire-duration update, but the separate
     * attack still runs through ordinary entity damage handling.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        // Convert the eight-second combustion duration to twenty game ticks per second.
        var event = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, block, 20 * 8);
        if (event.call()) {
            living.setOnFireTicks(event.getOnFireTicks());
        }

        living.attack(new DamageContainer(block, DamageType.FIRE, 1));
    }
}
