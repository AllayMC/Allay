package org.allaymc.server.entity.component;

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
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author daoge_cmd
 */
public class EntityFishingHookBaseComponentImpl extends EntityBaseComponentImpl implements EntityFishingHookBaseComponent {

    /**
     * The minimum wait time in ticks (5 seconds).
     */
    protected static final int MIN_WAIT_TIME = 100;
    /**
     * The maximum wait time in ticks (30 seconds).
     */
    protected static final int MAX_WAIT_TIME = 600;
    /**
     * Time reduction per level of Lure (5 seconds per level).
     */
    protected static final int LURE_TIME_REDUCTION = 100;
    /**
     * Maximum distance from owner before auto-despawn (32 blocks).
     */
    protected static final double MAX_DISTANCE_FROM_OWNER_SQUARED = 32 * 32;

    @ComponentObject
    protected EntityFishingHook thisEntity;

    @Dependency
    protected EntityProjectileComponent projectileComponent;

    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    @Getter
    @Setter
    protected Entity hookedEntity;

    @Getter
    @Setter
    protected ItemStack fishingRod;

    @Getter
    @Setter
    protected FishingState fishingState = FishingState.FLYING;

    @Getter
    @Setter
    protected boolean inOpenWater = true;

    /**
     * Timer for the current fishing phase.
     */
    protected int fishingTimer;
    /**
     * Timer for attracting phase.
     */
    protected int attractTimer;
    /**
     * Virtual fish position during attracting phase.
     */
    protected Vector3d fishPosition;
    /**
     * Tick counter for periodic open water checks.
     */
    protected int openWaterCheckCounter;

    public EntityFishingHookBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.persistent = false;
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        updateFishingState();
        checkDistanceFromOwner();
        updateHookedEntityPosition();
    }

    /**
     * Gets the owner player of this fishing hook.
     *
     * @return the owner player, or {@code null} if the shooter is not a player
     */
    protected EntityPlayer getOwnerPlayer() {
        var shooter = projectileComponent.getShooter();
        return shooter instanceof EntityPlayer player ? player : null;
    }

    @Override
    public void reelLine() {
        var owner = getOwnerPlayer();
        if (owner == null) {
            thisEntity.remove();
            return;
        }

        var dimension = thisEntity.getDimension();

        // Check if we hooked an entity
        if (hookedEntity != null && hookedEntity.isAlive()) {
            // Pull the entity towards the player
            var motion = calculateReelMotion(owner);
            var event = new PlayerFishEvent(owner, thisEntity, hookedEntity, motion);
            if (event.call() && hookedEntity instanceof EntityPhysicsComponent physicsEntity) {
                physicsEntity.setMotion(event.getMotion());
            }

            thisEntity.remove();
            owner.setFishingHook(null);
            return;
        }

        // Check if we caught something
        if (fishingState == FishingState.CAUGHT) {
            // Calculate loot
            int luckLevel = fishingRod != null ? fishingRod.getEnchantmentLevel(EnchantmentTypes.LUCK_OF_THE_SEA) : 0;
            int lureLevel = fishingRod != null ? fishingRod.getEnchantmentLevel(EnchantmentTypes.LURE) : 0;

            var loot = FishingLootTable.selectLoot(luckLevel, lureLevel, inOpenWater);
            int experience = ThreadLocalRandom.current().nextInt(1, 7);

            // Calculate motion for the item
            var motion = calculateReelMotion(owner);
            var event = new PlayerFishEvent(owner, thisEntity, loot, experience, motion);
            if (event.call()) {
                // Drop the item
                if (event.getCaughtItem() != null) {
                    dimension.dropItem(event.getCaughtItem(), thisEntity.getLocation(), event.getMotion(), 0);
                }

                // Drop experience
                if (event.getExperience() > 0) {
                    dimension.splitAndDropXpOrb(owner.getLocation(), event.getExperience());
                }
            }
        }

        thisEntity.remove();
        owner.setFishingHook(null);
    }

    private Vector3d calculateReelMotion(EntityPlayer owner) {
        var hookPos = thisEntity.getLocation();
        var ownerPos = owner.getLocation();
        var extra = Math.sqrt(Math.sqrt(hookPos.distanceSquared(ownerPos)));
        return new Vector3d(
                (ownerPos.x() - hookPos.x()) * 0.1,
                (ownerPos.y() - hookPos.y()) * 0.1 + extra * 0.08,
                (ownerPos.z() - hookPos.z()) * 0.1
        );
    }

    protected void updateHookedEntityPosition() {
        if (hookedEntity == null || !hookedEntity.isAlive()) {
            return;
        }

        var loc = hookedEntity.getLocation();
        thisEntity.teleport(new Location3d(loc.x(), loc.y() + hookedEntity.getEyeHeight(), loc.z(), loc.dimension()));
    }

    protected void updateFishingState() {
        // If hooked entity, skip fishing state machine (hook follows entity via onTick)
        if (hasHookedEntity()) {
            if (!hookedEntity.isAlive()) {
                setHookedEntity(null);
            }
            return;
        }

        // Fishing state machine only works in water
        if (!thisEntity.isTouchingWater()) {
            if (fishingState != FishingState.FLYING) {
                setFishingState(FishingState.FLYING);
            }
            return;
        }

        // Update open water check periodically
        openWaterCheckCounter++;
        if (openWaterCheckCounter >= 20) {
            openWaterCheckCounter = 0;
            inOpenWater = checkOpenWater(location);
        }

        // If just entered water, start waiting
        if (fishingState == FishingState.FLYING) {
            startWaiting();
            return;
        }

        var random = ThreadLocalRandom.current();

        switch (fishingState) {
            case WAITING -> {
                fishingTimer--;
                if (fishingTimer <= 0) {
                    // Chance to start attracting (90%) or reset (10%)
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
                    // Fish reached hook
                    startCaught();
                }
            }
            case CAUGHT -> {
                fishingTimer--;
                if (fishingTimer <= 0) {
                    // Timeout: fish got away
                    startWaiting();
                    // Extend wait time after timeout
                    fishingTimer *= 3;
                }
            }
        }
    }

    protected void startWaiting() {
        setFishingState(FishingState.WAITING);
        fishingTimer = calculateWaitTime();
    }

    protected void startAttracting() {
        setFishingState(FishingState.ATTRACTING);
        attractTimer = ThreadLocalRandom.current().nextInt(20, 80);
        spawnFish();
    }

    protected void startCaught() {
        setFishingState(FishingState.CAUGHT);
        // Player has about 0.5 seconds (10 ticks) to reel in before the fish escapes
        fishingTimer = 10;
        fishBites();
    }

    protected int calculateWaitTime() {
        int lureLevel = fishingRod != null ? fishingRod.getEnchantmentLevel(EnchantmentTypes.LURE) : 0;

        // Base wait time
        int minWait = Math.max(20, MIN_WAIT_TIME - lureLevel * LURE_TIME_REDUCTION);
        int maxWait = Math.max(minWait + 20, MAX_WAIT_TIME - lureLevel * LURE_TIME_REDUCTION);

        // Check sky exposure and weather conditions
        // See: https://minecraft.wiki/w/Fishing#Mechanics
        var world = thisEntity.getWorld();
        var pos = this.location.floor(new Vector3d());
        boolean hasSkyExposure = getDimension().canPosSeeSky((int) pos.x, (int) pos.y, (int) pos.z);
        boolean isRaining = world.getWeather() == Weather.RAIN || world.getWeather() == Weather.THUNDER;

        if (!hasSkyExposure && isRaining) {
            // No sky exposure + rain: wait time increased by ~33.3%
            minWait = (int) (minWait * 1.333);
            maxWait = (int) (maxWait * 1.333);
        } else if (!hasSkyExposure) {
            // No sky exposure: wait time approximately doubled
            minWait *= 2;
            maxWait *= 2;
        } else if (isRaining) {
            // Rain with sky exposure: wait time reduced by ~20%
            minWait = (int) (minWait * 0.8);
            maxWait = (int) (maxWait * 0.8);
        }

        return ThreadLocalRandom.current().nextInt(minWait, maxWait + 1);
    }

    protected void spawnFish() {
        var location = thisEntity.getLocation();
        var random = ThreadLocalRandom.current();
        // Spawn fish 1-4 blocks away
        double distance = 1 + random.nextDouble() * 3;
        double angle = random.nextDouble() * Math.PI * 2;
        fishPosition = new Vector3d(
                location.x() + Math.cos(angle) * distance,
                location.y(),
                location.z() + Math.sin(angle) * distance
        );
    }

    protected boolean attractFish(Location3dc hookLocation) {
        if (fishPosition == null) {
            return true;
        }

        // Move fish towards hook
        double speed = 0.1;
        fishPosition.add(
                (hookLocation.x() - fishPosition.x()) * speed,
                0,
                (hookLocation.z() - fishPosition.z()) * speed
        );

        // Spawn water wake particle at fish position (85% chance per tick)
        if (ThreadLocalRandom.current().nextInt(100) < 85) {
            thisEntity.getDimension().addParticle(fishPosition, SimpleParticle.WATER_WAKE);
        }

        // Check if fish reached hook
        double dist = Math.sqrt(
                Math.pow(hookLocation.x() - fishPosition.x(), 2) +
                Math.pow(hookLocation.z() - fishPosition.z(), 2)
        );
        return dist < 0.15;
    }

    protected void fishBites() {
        // Set motion to make the bobber bob
        physicsComponent.setMotion(new Vector3d(0, -0.2, 0));

        // Apply fishing hook bite action (sends entity events to viewers)
        // This triggers FISH_HOOK_BUBBLE, FISH_HOOK_TIME, and FISH_HOOK_TEASE events
        // which handle the visual effects on the client side
        thisEntity.applyAction(SimpleEntityAction.FISHING_HOOK_BITE);

        // Spawn bubble particles around the hook
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

    protected void checkDistanceFromOwner() {
        var shooter = projectileComponent.getShooter();
        if (shooter == null || !shooter.isAlive()) {
            thisEntity.remove();
            return;
        }

        var hookLoc = thisEntity.getLocation();
        var shooterLoc = shooter.getLocation();

        // Different dimension check
        if (hookLoc.dimension() != shooterLoc.dimension()) {
            thisEntity.remove();
            return;
        }

        // Distance check
        if (hookLoc.distanceSquared(shooterLoc) > MAX_DISTANCE_FROM_OWNER_SQUARED) {
            thisEntity.remove();
        }
    }

    protected boolean checkOpenWater(Location3dc location) {
        var dimension = location.dimension();
        int centerX = (int) Math.floor(location.x());
        int centerY = (int) Math.floor(location.y());
        int centerZ = (int) Math.floor(location.z());

        // Check 5x4x5 area around the hook
        var aabb = new AABBd(
                centerX - 2, centerY - 1, centerZ - 2,
                centerX + 3, centerY + 3, centerZ + 3
        );
        var result = new AtomicBoolean(true);
        dimension.forEachBlockStates(aabb, 0, (x, y, z, blockState) -> {
            // Water or air is fine, anything else blocks open water
            if (!blockState.getBlockType().hasBlockTag(BlockTags.WATER) &&
                blockState.getBlockStateData().collisionShape() != VoxelShape.EMPTY) {
                result.set(false);
            }
        });
        return result.get();
    }
}
