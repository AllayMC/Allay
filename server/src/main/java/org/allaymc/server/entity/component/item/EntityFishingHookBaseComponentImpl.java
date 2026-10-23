package org.allaymc.server.entity.component.item;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityFishingHookBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerFishEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.fishing.FishingLootTable;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@code EntityFishingHookBaseComponentImpl} represents a cast hook, its
 * fishing phases, and the result of reeling. A retained hooked entity takes
 * precedence over water-based fishing, while an unhooked cast waits, attracts
 * a virtual fish, and opens a short catch window. The fishing rod reference
 * supplies enchantments for wait duration and loot selection.
 *
 * <p>
 * Phase transitions replace timers and create the corresponding motion or
 * particle effects. Reeling dispatches a catch event, uses its permitted
 * motion or rewards, and ends the cast even when that event is cancelled. Tick
 * handling also checks shooter lifetime, dimension, and distance so a hook
 * cannot remain active after those owner conditions fail.
 *
 * @author daoge_cmd
 */
public class EntityFishingHookBaseComponentImpl extends EntityBaseComponentImpl implements EntityFishingHookBaseComponent {

    /**
     * {@code MIN_WAIT_TIME} supplies the lower base wait bound in ticks before
     * Lure and weather adjustments select a fishing delay.
     */
    protected static final int MIN_WAIT_TIME = 100;
    /**
     * {@code MAX_WAIT_TIME} supplies the upper base wait bound in ticks before
     * Lure and weather adjustments select a fishing delay.
     */
    protected static final int MAX_WAIT_TIME = 600;
    /**
     * {@code LURE_TIME_REDUCTION} subtracts ticks per Lure level from both
     * base wait bounds before their minimum-spacing clamps.
     */
    protected static final int LURE_TIME_REDUCTION = 100;
    /**
     * {@code MAX_DISTANCE_FROM_OWNER_SQUARED} stores the squared
     * thirty-two-block cast limit. Owner checks compare squared locations to
     * avoid computing a square root.
     */
    protected static final double MAX_DISTANCE_FROM_OWNER_SQUARED = 32 * 32;

    /**
     * Injected composed fishing hook owner.
     */
    @ComponentObject
    protected EntityFishingHook thisEntity;

    /**
     * Injected shooter reference component.
     */
    @Dependency
    protected EntityProjectileComponent projectileComponent;

    /**
     * Injected bobber motion control component.
     */
    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    /**
     * {@code hookedEntity} retains the current target or {@code null}. A
     * nonnull target suppresses water fishing progression until tick handling
     * clears a dead target.
     */
    @Getter
    @Setter
    protected Entity hookedEntity;

    /**
     * {@code fishingRod} retains the mutable rod used for enchantment queries.
     * A {@code null} rod contributes zero Lure and Luck of the Sea levels.
     */
    @Getter
    @Setter
    protected ItemStack fishingRod;

    /**
     * {@code fishingState} selects the current phase and starts at {@code
     * FishingState.FLYING}. Generated setters replace the phase without
     * initializing its associated timers.
     */
    @Getter
    @Setter
    protected FishingState fishingState = FishingState.FLYING;

    /**
     * {@code inOpenWater} caches treasure eligibility and starts as {@code
     * true}. Water-based ticking refreshes it periodically, so changes around
     * the hook are not observed immediately.
     */
    @Getter
    @Setter
    protected boolean inOpenWater = true;

    /**
     * {@code fishingTimer} counts remaining waiting or caught ticks. The
     * current {@code fishingState} decides which phase consumes it and which
     * expiration transition follows.
     */
    protected int fishingTimer;
    /**
     * {@code attractTimer} counts remaining attraction ticks. Attraction ends
     * when this timer expires or the virtual fish reaches the hook, whichever
     * occurs first.
     */
    protected int attractTimer;
    /**
     * {@code fishPosition} holds the mutable world-space virtual fish
     * position. {@code spawnFish} replaces it before attraction advances it
     * toward the hook each tick.
     */
    protected Vector3d fishPosition;
    /**
     * {@code openWaterCheckCounter} counts eligible water ticks between
     * surrounding-block checks. Fishing with a hooked target or outside water
     * leaves this counter unchanged.
     */
    protected int openWaterCheckCounter;

    /**
     * Creates a nonpersistent fishing hook using the supplied entity
     * initialization context. The hook starts in the flying phase, with no
     * retained entity target or assigned fishing rod.
     */
    public EntityFishingHookBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.persistent = false;
    }

    /**
     * Returns the fishing hook local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }

    /**
     * Runs inherited entity ticking, advances fishing, checks owner
     * eligibility, and follows a live hooked entity. Owner checks can queue
     * removal before the follow-target step still executes in this invocation.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        updateFishingState();
        checkDistanceFromOwner();
        updateHookedEntityPosition();
    }

    /**
     * Returns the shooter when it is a player, otherwise {@code null}. The
     * helper does not verify player lifetime or dimension; the separate
     * owner-distance check enforces those tick conditions.
     */
    protected EntityPlayer getOwnerPlayer() {
        var shooter = projectileComponent.getShooter();
        return shooter instanceof EntityPlayer player ? player : null;
    }

    /**
     * Resolves the current cast and requests hook removal. A live hooked
     * entity takes precedence over caught-fish loot, and its event-adjusted
     * motion is applied only when the event succeeds and the entity exposes
     * physics. Missing player ownership removes the hook without a
     * player-reference update.
     *
     * <p>
     * A caught phase selects loot using the current rod enchantments and
     * cached open-water flag, then offers rewards through {@code
     * PlayerFishEvent}. Cancellation suppresses rewards or pull motion while
     * the hook is still removed. The player fishing-hook reference is cleared
     * after either normal player-owned resolution path.
     */
    @Override
    public void reelLine() {
        var owner = getOwnerPlayer();
        if (owner == null) {
            thisEntity.remove();
            return;
        }

        var dimension = thisEntity.getDimension();

        // Resolve a live hooked target first so an existing catch phase cannot also
        // award fish loot during this reel.
        if (hookedEntity != null && hookedEntity.isAlive()) {
            var motion = calculateReelMotion(owner);
            var event = new PlayerFishEvent(owner, thisEntity, hookedEntity, motion);
            if (event.call() && hookedEntity instanceof EntityPhysicsComponent physicsEntity) {
                physicsEntity.setMotion(event.getMotion());
            }

            thisEntity.remove();
            owner.setFishingHook(null);
            return;
        }

        if (fishingState == FishingState.CAUGHT) {
            int luckLevel = fishingRod != null ? fishingRod.getEnchantmentLevel(EnchantmentTypes.LUCK_OF_THE_SEA) : 0;
            int lureLevel = fishingRod != null ? fishingRod.getEnchantmentLevel(EnchantmentTypes.LURE) : 0;

            var loot = FishingLootTable.selectLoot(luckLevel, lureLevel, inOpenWater);
            int experience = ThreadLocalRandom.current().nextInt(1, 7);

            var motion = calculateReelMotion(owner);
            var event = new PlayerFishEvent(owner, thisEntity, loot, experience, motion);
            if (event.call()) {
                if (event.getCaughtItem() != null) {
                    dimension.dropItem(event.getCaughtItem(), thisEntity.getLocation(), event.getMotion(), 0);
                }

                if (event.getExperience() > 0) {
                    dimension.splitAndDropXpOrb(owner.getLocation(), event.getExperience());
                }
            }
        }

        thisEntity.remove();
        owner.setFishingHook(null);
    }

    /**
     * Returns a new pull vector from the hook toward the owner. Horizontal
     * displacement is scaled directly, while a square-root-of-distance term
     * adds lift so both pulled entities and caught loot rise toward the
     * player.
     */
    private Vector3d calculateReelMotion(EntityPlayer owner) {
        var hookPos = thisEntity.getLocation();
        var ownerPos = owner.getLocation();
        // The square root of distance adds lift beyond the direct
        // displacement-scaled pull.
        var extra = Math.sqrt(Math.sqrt(hookPos.distanceSquared(ownerPos)));
        return new Vector3d(
                (ownerPos.x() - hookPos.x()) * 0.1,
                (ownerPos.y() - hookPos.y()) * 0.1 + extra * 0.08,
                (ownerPos.z() - hookPos.z()) * 0.1
        );
    }

    /**
     * Teleports the hook to a live target at that target eye height. A missing
     * or dead target produces no teleport, and the new location uses the
     * target current dimension.
     */
    protected void updateHookedEntityPosition() {
        if (hookedEntity == null || !hookedEntity.isAlive()) {
            return;
        }

        var loc = hookedEntity.getLocation();
        thisEntity.teleport(new Location3d(loc.x(), loc.y() + hookedEntity.getEyeHeight(), loc.z(), loc.dimension()));
    }

    /**
     * Advances water fishing only when the hook has no retained target. A dead
     * retained target is cleared and consumes this tick, while leaving water
     * resets the phase to flying without clearing the other phase fields.
     * Open-water eligibility is refreshed after twenty eligible water ticks.
     *
     * <p>
     * Entering water begins waiting. Waiting expiration usually starts
     * attraction but can restart waiting, and attraction ends on timer
     * expiration or arrival at the hook. A caught fish gives a short reeling
     * window; missing that window restarts waiting with a tripled newly
     * calculated delay. These transitions preserve the priority of hooked
     * entities over fish loot.
     */
    protected void updateFishingState() {
        // A retained target takes precedence over water phases; dead-target cleanup
        // still consumes this tick before fishing resumes.
        if (hasHookedEntity()) {
            if (!hookedEntity.isAlive()) {
                setHookedEntity(null);
            }
            return;
        }

        if (!thisEntity.isTouchingWater()) {
            if (fishingState != FishingState.FLYING) {
                setFishingState(FishingState.FLYING);
            }
            return;
        }

        // Refresh treasure eligibility once per twenty water ticks rather than
        // resampling the surrounding volume on every tick.
        openWaterCheckCounter++;
        if (openWaterCheckCounter >= 20) {
            openWaterCheckCounter = 0;
            inOpenWater = checkOpenWater(location);
        }

        if (fishingState == FishingState.FLYING) {
            startWaiting();
            return;
        }

        var random = ThreadLocalRandom.current();

        switch (fishingState) {
            case WAITING -> {
                fishingTimer--;
                if (fishingTimer <= 0) {
                    // Most completed waits begin approach; the remaining random
                    // outcomes restart the wait without a catch window.
                    if (random.nextInt(100) < 90) {
                        startAttracting();
                    } else {
                        startWaiting();
                    }
                }
            }
            case ATTRACTING -> {
                attractTimer--;
                if (attractTimer <= 0 || attractFish(location)) {
                    startCaught();
                }
            }
            case CAUGHT -> {
                fishingTimer--;
                if (fishingTimer <= 0) {
                    startWaiting();
                    // Missing the catch window restarts waiting with three times the
                    // new delay, postponing the next approach after the missed bite.
                    fishingTimer *= 3;
                }
            }
        }
    }

    /**
     * Enters the waiting phase and replaces its countdown with a newly
     * calculated delay. Rod enchantments, sky exposure, and current weather
     * are sampled by {@code calculateWaitTime} at this transition.
     */
    protected void startWaiting() {
        setFishingState(FishingState.WAITING);
        fishingTimer = calculateWaitTime();
    }

    /**
     * Enters attraction, chooses a countdown from twenty through seventy-nine
     * ticks, and creates a virtual fish. The virtual position is replaced
     * before later attraction ticks advance it toward the hook.
     */
    protected void startAttracting() {
        setFishingState(FishingState.ATTRACTING);
        // A bounded attraction phase prevents a distant virtual fish from
        // approaching indefinitely.
        attractTimer = ThreadLocalRandom.current().nextInt(20, 80);
        spawnFish();
    }

    /**
     * Enters the caught phase with a ten-tick reeling window and applies bite
     * effects. A later phase tick restarts waiting when that window expires
     * without the cast being reeled.
     */
    protected void startCaught() {
        setFishingState(FishingState.CAUGHT);
        // Ten ticks provide the brief reeling window after the virtual fish bites.
        fishingTimer = 10;
        fishBites();
    }

    /**
     * Returns a randomly selected inclusive wait duration after Lure, sky, and
     * weather adjustments. A missing rod contributes zero Lure. The lower
     * bound is clamped to twenty ticks, and the upper bound stays at least
     * twenty ticks above it before environmental scaling.
     *
     * <p>
     * Sky exposure and rain combine to select one adjustment: shelter with
     * rain increases time by approximately one third, shelter alone doubles
     * it, and exposed rain reduces it by one fifth. Integer conversion
     * truncates scaled bounds before the inclusive random selection, so the
     * stored countdown remains an integer tick duration.
     */
    protected int calculateWaitTime() {
        int lureLevel = fishingRod != null ? fishingRod.getEnchantmentLevel(EnchantmentTypes.LURE) : 0;

        // Clamp Lure-adjusted bounds so a wait stays positive and the random range
        // retains at least twenty ticks.
        int minWait = Math.max(20, MIN_WAIT_TIME - lureLevel * LURE_TIME_REDUCTION);
        int maxWait = Math.max(minWait + 20, MAX_WAIT_TIME - lureLevel * LURE_TIME_REDUCTION);

        var world = thisEntity.getWorld();
        var pos = this.location.floor(new Vector3d());
        boolean hasSkyExposure = getDimension().canPosSeeSky((int) pos.x, (int) pos.y, (int) pos.z);
        boolean isRaining = world.getWeather() == Weather.RAIN || world.getWeather() == Weather.THUNDER;

        if (!hasSkyExposure && isRaining) {
            // Sheltered rain uses an approximate one-third increase, truncated to
            // integer tick bounds.
            minWait = (int) (minWait * 1.333);
            maxWait = (int) (maxWait * 1.333);
        } else if (!hasSkyExposure) {
            // Shelter without rain doubles both bounds before a duration is
            // sampled.
            minWait *= 2;
            maxWait *= 2;
        } else if (isRaining) {
            // Exposed rain shortens both bounds, with integer truncation
            // determining the final tick range.
            minWait = (int) (minWait * 0.8);
            maxWait = (int) (maxWait * 0.8);
        }

        return ThreadLocalRandom.current().nextInt(minWait, maxWait + 1);
    }

    /**
     * Replaces the virtual fish position with a point one to less than four
     * blocks away. The point uses the hook current height and a random
     * horizontal angle for subsequent attraction particles.
     */
    protected void spawnFish() {
        var location = thisEntity.getLocation();
        var random = ThreadLocalRandom.current();
        // Start the virtual fish outside the hook so attraction produces a visible
        // horizontal approach.
        double distance = 1 + random.nextDouble() * 3;
        double angle = random.nextDouble() * Math.PI * 2;
        fishPosition = new Vector3d(
                location.x() + Math.cos(angle) * distance,
                location.y(),
                location.z() + Math.sin(angle) * distance
        );
    }

    /**
     * Moves the retained virtual fish toward {@code hookLocation} and reports
     * arrival. A missing fish position immediately reports {@code true},
     * allowing the state machine to resolve attraction without a target
     * vector. Each update moves one tenth of the remaining horizontal
     * displacement.
     *
     * <p>
     * The helper may emit a wake particle after moving the virtual fish.
     * Arrival uses a horizontal distance below {@code 0.15} blocks,
     * independent of vertical separation; the phase timer can also end
     * attraction before that distance is reached. The mutable position is
     * retained for the next tick.
     */
    protected boolean attractFish(Location3dc hookLocation) {
        // A missing virtual position leaves no path to advance, so finish
        // attraction and let the state transition provide the bite presentation.
        if (fishPosition == null) {
            return true;
        }

        // Move a fraction of remaining displacement so the approach slows near the
        // hook.
        double speed = 0.1;
        fishPosition.add(
                (hookLocation.x() - fishPosition.x()) * speed,
                0,
                (hookLocation.z() - fishPosition.z()) * speed
        );

        // Random wake emission leaves intermittent gaps along the virtual fish
        // approach path.
        if (ThreadLocalRandom.current().nextInt(100) < 85) {
            thisEntity.getDimension().addParticle(fishPosition, SimpleParticle.WATER_WAKE);
        }

        double dist = Math.sqrt(
                Math.pow(hookLocation.x() - fishPosition.x(), 2) +
                Math.pow(hookLocation.z() - fishPosition.z(), 2)
        );
        // Treat this small horizontal gap as arrival rather than waiting for exact
        // coordinate equality.
        return dist < 0.15;
    }

    /**
     * Adds the bite presentation by setting downward bobber motion, applying
     * the bite action, and emitting bubbles. The action expands to three
     * client entity events, while particle positions are randomized around the
     * hook.
     */
    protected void fishBites() {
        // Downward motion makes the hook visibly dip during the short catch window.
        physicsComponent.setMotion(new Vector3d(0, -0.2, 0));

        // The bite action groups bubble, time, and tease client events so viewers
        // receive the complete bite presentation.
        thisEntity.applyAction(SimpleEntityAction.FISHING_HOOK_BITE);

        // Scatter five bubbles within the hook neighborhood to accompany the
        // downward bite motion.
        var location = thisEntity.getLocation();
        var dimension = thisEntity.getDimension();
        var random = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            dimension.addParticle(
                    location.x() + random.nextDouble() * 0.5 - 0.25,
                    location.y(),
                    location.z() + random.nextDouble() * 0.5 - 0.25,
                    SimpleParticle.BUBBLE
            );
        }
    }

    /**
     * Requests removal when the shooter is absent, dead, in another dimension,
     * or beyond the cast distance. This helper does not clear the player
     * fishing-hook reference; reeling owns that separate update.
     */
    protected void checkDistanceFromOwner() {
        var shooter = projectileComponent.getShooter();
        if (shooter == null || !shooter.isAlive()) {
            thisEntity.remove();
            return;
        }

        var hookLoc = thisEntity.getLocation();
        var shooterLoc = shooter.getLocation();

        if (hookLoc.dimension() != shooterLoc.dimension()) {
            thisEntity.remove();
            return;
        }

        if (hookLoc.distanceSquared(shooterLoc) > MAX_DISTANCE_FROM_OWNER_SQUARED) {
            thisEntity.remove();
        }
    }

    /**
     * Returns whether sampled blocks around the hook permit open-water
     * treasure selection. The check examines a five-by-four-by-five block
     * region and rejects nonwater blocks with a nonempty collision shape;
     * unavailable blocks are skipped by dimension iteration.
     */
    protected boolean checkOpenWater(Location3dc location) {
        var dimension = location.dimension();
        int centerX = (int) Math.floor(location.x());
        int centerY = (int) Math.floor(location.y());
        int centerZ = (int) Math.floor(location.z());

        // Sample the surrounding treasure-eligibility volume, extending one block
        // below and two blocks above the floored hook cell.
        var aabb = new AABBd(
                centerX - 2, centerY - 1, centerZ - 2,
                centerX + 3, centerY + 3, centerZ + 3
        );
        var result = new AtomicBoolean(true);
        dimension.forEachBlockStates(aabb, 0, (x, y, z, blockState) -> {
            // Water and empty collision shapes preserve eligibility; another solid
            // shape blocks treasure selection for the cached open-water result.
            if (!blockState.getBlockType().hasBlockTag(BlockTags.WATER) &&
                blockState.getBlockStateData().collisionShape() != VoxelShape.EMPTY) {
                result.set(false);
            }
        });
        return result.get();
    }
}
