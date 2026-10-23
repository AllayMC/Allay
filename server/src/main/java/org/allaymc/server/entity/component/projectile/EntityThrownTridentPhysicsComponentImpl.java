package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityThrownTridentBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.world.LightningStrikeEvent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code EntityThrownTridentPhysicsComponentImpl} resolves trident impacts,
 * enchantment effects, and Loyalty return travel. Outbound movement uses
 * projectile collision handling, while returning movement bypasses both block
 * and entity ray checks. Return acceleration depends on the current Loyalty
 * level and remaining displacement toward the shooter eye position.
 *
 * <p>
 * The return state coordinates shooter eligibility, dimension, pickup range,
 * and inventory recovery. An invalid shooter causes an item drop and removal;
 * another dimension pauses motion until the shooter can be reached again. A
 * nearby player receives the item through preferred-slot or ordinary inventory
 * handling before projectile removal. Impact damage also combines Impaling
 * with water or rain, while Channeling separately checks thunder and sky
 * exposure.
 *
 * @author daoge_cmd
 */
public class EntityThrownTridentPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * {@code BASE_DAMAGE} supplies the eight outbound damage points before an
     * eligible Impaling enchantment adds its target-specific bonus.
     */
    protected static final float BASE_DAMAGE = 8.0f;

    /**
     * Injected carried item and Loyalty state component.
     */
    @Dependency
    protected EntityThrownTridentBaseComponent tridentBaseComponent;

    /**
     * {@code hitBlock} records that an entity or block impact has completed.
     * It suppresses repeated block-impact processing and can hold outbound
     * motion while collision geometry remains.
     */
    protected boolean hitBlock;

    /**
     * Returns trident downward acceleration in blocks per tick squared.
     */
    @Override
    public double getGravity() {
        return 0.05;
    }

    /**
     * Returns the fraction of trident motion removed by water drag.
     */
    @Override
    public double getWaterDragFactor() {
        return 0.01;
    }

    /**
     * Returns the fraction of trident motion removed by lava drag.
     */
    @Override
    public double getLavaDragFactor() {
        return 0.01;
    }

    /**
     * Returns the next trident motion using return state before ordinary
     * projectile physics. Returning delegates immediately to homing, including
     * its pickup and invalid-owner side effects. Otherwise enabled liquid
     * physics takes precedence over the embedded-block stop check.
     *
     * <p>
     * An impacted outbound trident remains still when its block collision
     * persists outside that liquid branch. Removing the support restores
     * gravity and air drag without clearing {@code hitBlock}. Each branch
     * returns a vector for the engine motion setter, while actual position
     * updates remain in the movement phase.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (tridentBaseComponent.isReturning()) {
            return updateReturningMotion();
        }

        if (liquidState.inLiquid() && computeLiquidPhysics()) {
            return updateMotionInLiquid(liquidState);
        }

        // Preserve impact embedding outside the liquid branch until support
        // geometry disappears, so an impacted trident can fall after support
        // removal.
        if (hitBlock && tridentBaseComponent.checkBlockCollision()) {
            return new Vector3d(0, 0, 0);
        }

        return new Vector3d(
                this.motion.x * (1 - this.getDragFactorInAir()),
                (this.motion.y - this.getGravity()) * (1 - this.getDragFactorInAir()),
                this.motion.z * (1 - this.getDragFactorInAir())
        );
    }

    /**
     * Returns homing motion or resolves a terminal return outcome. An
     * ineligible shooter causes a carried-item drop, removal request, and zero
     * vector. A shooter in another dimension leaves the returning trident
     * stationary without dropping it.
     *
     * <p>
     * The target is the shooter eye position. A player within one and a half
     * blocks receives the trident before removal is requested; otherwise
     * displacement is multiplied by five percent per Loyalty level. This
     * proportional update slows as distance shrinks and leaves position
     * application to the no-clip movement path. The helper can mutate
     * inventory or queue removal during an engine motion update.
     */
    protected Vector3d updateReturningMotion() {
        var shooter = projectileComponent.getShooter();
        if (!canReturnToShooter(shooter)) {
            // Failed return eligibility preserves the retained item as a world drop
            // before the projectile removal request is queued.
            dropTridentItem();
            thisEntity.remove();
            return new Vector3d(0, 0, 0);
        }

        // A live eligible shooter in another dimension pauses homing, preserving
        // the returning projectile until both occupy the same dimension.
        if (shooter.getDimension() != thisEntity.getDimension()) {
            return new Vector3d(0, 0, 0);
        }

        var location = thisEntity.getLocation();
        var shooterPos = shooter.getLocation();

        var direction = new Vector3d(
                shooterPos.x() - location.x(),
                shooterPos.y() - location.y() + shooter.getEyeHeight(),
                shooterPos.z() - location.z()
        );

        var distance = direction.length();

        // A one-and-a-half-block eye-position radius resolves player recovery
        // before proportional motion would converge indefinitely.
        if (distance < 1.5 && shooter instanceof EntityPlayer player) {
            giveTridentToPlayer(player);
            thisEntity.remove();
            return new Vector3d(0, 0, 0);
        }

        // Five percent of remaining displacement per Loyalty level scales return
        // speed without normalizing away the approach distance.
        var loyaltyLevel = tridentBaseComponent.getLoyaltyLevel();
        var force = 0.05 * loyaltyLevel;

        // Return a velocity only because the engine movement phase owns location
        // changes; updating position here would apply return travel twice.
        return new Vector3d(
                direction.x * force,
                direction.y * force,
                direction.z * force
        );
    }

    /**
     * Attempts to return the retained non-air item to a player without
     * clearing the trident item reference. A nonnegative favored slot must be
     * valid for the player inventory. An empty favored slot receives the same
     * item object, while any existing trident in that slot suppresses another
     * insertion.
     *
     * <p>
     * An occupied nontrident slot or absent preference falls back to {@code
     * player.tryAddItem}, whose implementation inserts what fits and drops
     * remaining contents. The method returns no success status, so the
     * returning-motion caller requests projectile removal afterward regardless
     * of whether the preferred-slot branch inserted an item.
     */
    protected void giveTridentToPlayer(EntityPlayer player) {
        var tridentItem = tridentBaseComponent.getTridentItem();
        if (tridentItem == null || tridentItem.getItemType() == ItemTypes.AIR) {
            return;
        }

        var favoredSlot = tridentBaseComponent.getFavoredSlot();
        if (favoredSlot >= 0) {
            var inventory = player.getContainer(ContainerTypes.INVENTORY);
            var slotItem = inventory.getItemStack(favoredSlot);
            if (slotItem.getItemType() == ItemTypes.AIR) {
                inventory.setItemStack(favoredSlot, tridentItem);
                return;
            }
            // An existing trident in the favored slot suppresses insertion
            // regardless of player mode, avoiding another copy on that branch.
            if (slotItem.getItemType() == ItemTypes.TRIDENT) {
                return;
            }
        }

        // A missing or occupied nontrident preference delegates insertion and
        // remainder dropping to the player inventory helper.
        player.tryAddItem(tridentItem);
    }

    /**
     * Drops the retained non-air trident stack at the current entity location.
     * A missing or air item produces no drop, and the helper does not clear
     * the retained reference or remove the projectile.
     */
    protected void dropTridentItem() {
        var tridentItem = tridentBaseComponent.getTridentItem();
        if (tridentItem != null && tridentItem.getItemType() != ItemTypes.AIR) {
            thisEntity.getDimension().dropItem(tridentItem, thisEntity.getLocation());
        }
    }

    /**
     * Processes an outbound trident entity hit and then resolves post-impact
     * return state. Queued removal and returning state suppress the callback.
     * Living targets receive base damage plus an Impaling bonus when water
     * contact or exposed rain permits it.
     *
     * <p>
     * Accepted damage attempts an additional durability cost, while Channeling
     * processing runs afterward independently of whether living damage
     * succeeded. The helper then records impact state and starts return when
     * the shooter remains eligible. Knockback uses a source obtained by
     * subtracting current motion from the hit position.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (tridentBaseComponent.isReturning()) {
            return;
        }

        addHitSound(hitPos, false);

        if (other instanceof EntityLiving living) {
            float damage = BASE_DAMAGE;

            // Impaling contributes two and a half damage points per level only when
            // water contact or exposed rain makes the target eligible.
            var impalingLevel = tridentBaseComponent.getImpalingLevel();
            if (impalingLevel > 0) {
                if (other.isTouchingWater() || isEntityInRain(other)) {
                    damage += 2.5f * impalingLevel;
                }
            }

            var damageContainer = DamageContainer.projectile(thisEntity, damage);
            // Subtract current motion from the hit point to place the knockback
            // source behind the trident along its travel direction.
            damageContainer.setKnockbackSource(hitPos.sub(this.motion, new Vector3d()));

            if (living.attack(damageContainer)) {
                applyHitDurabilityCost();
            }
        }

        handleChanneling(hitPos);

        handlePostCollision();
    }

    /**
     * Records the first outbound block impact and optionally begins Loyalty
     * return. Queued removal, a previous impact flag, or returning state
     * suppresses this callback, and the hit sound distinguishes block impact
     * from entity impact.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater() || this.hitBlock) {
            return;
        }

        if (tridentBaseComponent.isReturning()) {
            return;
        }

        addHitSound(hitPos, true);
        this.hitBlock = true;

        if (canReturnToShooter(projectileComponent.getShooter())) {
            startReturning();
        }
    }

    /**
     * Attempts a Channeling lightning strike during thunder when the trident
     * location sees the sky. The helper uses the supplied hit point for the
     * strike and emits thunder sound only when the dimension accepts it.
     */
    protected void handleChanneling(Vector3dc hitPos) {
        if (!tridentBaseComponent.hasChanneling()) {
            return;
        }

        var dimension = thisEntity.getDimension();
        var world = dimension.getWorld();

        if (world.getWeather() != Weather.THUNDER) {
            return;
        }

        var pos = thisEntity.getLocation();
        if (!dimension.canPosSeeSky((int) pos.x(), (int) pos.y(), (int) pos.z())) {
            return;
        }

        if (dimension.strikeLightning(hitPos.x(), hitPos.y(), hitPos.z(), LightningStrikeEvent.Cause.TRIDENT)) {
            dimension.addSound(hitPos, SimpleSound.TRIDENT_THUNDER, false);
        }
    }

    /**
     * Records impact state and begins Loyalty return when the current shooter
     * is eligible. The impact flag is set even when the shooter cannot receive
     * a return, leaving ordinary movement to resolve later support.
     */
    protected void handlePostCollision() {
        this.hitBlock = true;

        if (canReturnToShooter(projectileComponent.getShooter())) {
            startReturning();
        }
    }

    /**
     * Emits the return sound and enables the trident returning flag. The base
     * setter publishes state, while later movement and motion-update phases
     * select no-clip travel and homing from that flag.
     */
    protected void startReturning() {
        thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.TRIDENT_RETURN, false);
        tridentBaseComponent.setReturning(true);
    }

    /**
     * Returns whether positive Loyalty and a live nonspectator shooter allow
     * return. A live nonplayer shooter is accepted, and dimension equality is
     * deliberately left to the returning-motion path.
     */
    protected boolean canReturnToShooter(Entity shooter) {
        if (tridentBaseComponent.getLoyaltyLevel() <= 0) {
            return false;
        }

        if (shooter == null || !shooter.isAlive()) {
            return false;
        }

        if (shooter instanceof EntityPlayer player) {
            return player.getGameMode() != GameMode.SPECTATOR;
        }

        return true;
    }

    /**
     * Returns whether nonclear weather and sky exposure make a target eligible
     * for rain-based Impaling. The sky sample casts location coordinates
     * directly to integers, and the helper does not query a biome
     * precipitation type.
     */
    protected boolean isEntityInRain(Entity entity) {
        var dimension = entity.getDimension();
        var world = dimension.getWorld();

        if (world.getWeather() == Weather.CLEAR) {
            return false;
        }

        var pos = entity.getLocation();
        return dimension.canPosSeeSky((int) pos.x(), (int) pos.y(), (int) pos.z());
    }

    /**
     * Emits the ground or entity trident hit sound at {@code hitPos}.
     */
    protected void addHitSound(Vector3dc hitPos, boolean hitGround) {
        var sound = hitGround ? SimpleSound.TRIDENT_HIT_GROUND : SimpleSound.TRIDENT_HIT;
        thisEntity.getDimension().addSound(hitPos, sound, false);
    }

    /**
     * Attempts one additional damage point on the retained non-air trident
     * after an accepted living hit. The item damage routine decides whether
     * the increase succeeds, and the projectile does not inspect its result.
     */
    protected void applyHitDurabilityCost() {
        var tridentItem = tridentBaseComponent.getTridentItem();
        if (tridentItem != null && tridentItem.getItemType() != ItemTypes.AIR) {
            tridentItem.tryIncreaseDamage(1);
        }
    }

    /**
     * Attempts no-clip movement while returning or delegates to ordinary
     * projectile travel otherwise. The return branch skips both block and
     * entity ray collisions, leaving pickup resolution to the homing update.
     */
    @Override
    public boolean applyMotion() {
        // Return travel bypasses all ray collisions so the projectile can reach its
        // owner through intervening blocks or entities.
        if (tridentBaseComponent.isReturning()) {
            return applyMotionNoClip();
        }
        return super.applyMotion();
    }

    /**
     * Attempts to move by stored motion without ray collision checks or
     * hit-event dispatch. Zero motion returns {@code false}; a nonzero
     * displacement updates rotation before the entity location setter decides
     * acceptance.
     */
    protected boolean applyMotionNoClip() {
        if (motion.lengthSquared() == 0) {
            return false;
        }

        var location = thisEntity.getLocation();
        var newPos = new Location3d(location);
        newPos.add(motion);

        if (newPos.distance(location) > 0) {
            computeRotationFromMotion(newPos, this.motion);
        }

        return !newPos.equals(location) && thisEntity.trySetLocation(newPos);
    }

    /**
     * Returns whether parent filtering or returning state excludes an entity
     * collision. Outbound tridents retain the shared same-shooter projectile
     * exclusion, while returning movement normally bypasses the ray query
     * entirely.
     */
    @Override
    protected boolean shouldSkipEntityCollision(Entity entity) {
        if (super.shouldSkipEntityCollision(entity)) {
            return true;
        }

        return tridentBaseComponent.isReturning();
    }
}
