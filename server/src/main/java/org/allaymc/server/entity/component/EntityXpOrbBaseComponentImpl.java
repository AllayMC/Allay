package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityXpOrbBaseComponentImpl} represents a pickable experience amount
 * and resolves collection into Mending repairs and player experience. A
 * collision first chooses one damaged equipped item with Mending, spends
 * experience on that item, and grants the remaining amount. On normal
 * completion, the stored amount is cleared before removal so subsequent
 * collision callbacks do not grant it again.
 *
 * <p>
 * Attraction adds motion toward an eligible controlled player within a fixed
 * radius. {@code findNearestPlayer} currently retains the last eligible player
 * encountered, because its comparison distance is not narrowed after a match.
 * The attraction calculation targets half eye height above that player
 * location and combines distance with the current orb motion.
 *
 * @author daoge_cmd
 */
public class EntityXpOrbBaseComponentImpl extends EntityPickableBaseComponentImpl implements EntityXpOrbBaseComponent {

    /**
     * {@code TAG_EXPERIENCE_VALUE} names the integer amount persisted with
     * inherited entity data for later orb pickup.
     */
    protected static final String TAG_EXPERIENCE_VALUE = "ExperienceValue";
    /**
     * {@code MAX_MOVE_DISTANCE} defines the attraction eligibility radius in
     * blocks. Its squared companion is initialized from this value when the
     * class loads.
     */
    protected static float MAX_MOVE_DISTANCE = 7.25f;
    /**
     * {@code MAX_MOVE_DISTANCE_SQUARED} stores the squared attraction radius
     * used by {@code findNearestPlayer}. Changing {@code MAX_MOVE_DISTANCE}
     * later does not recompute this initialized value.
     */
    protected static float MAX_MOVE_DISTANCE_SQUARED = MAX_MOVE_DISTANCE * MAX_MOVE_DISTANCE;

    /**
     * Injected motion accumulation component.
     */
    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    /**
     * {@code experienceValue} holds the collectable experience amount.
     * Successful pickup processing clears it before removal, while a failure
     * during repair or player experience updates can leave it available.
     */
    @Getter
    protected int experienceValue;

    /**
     * Creates the experience orb base with its entity initialization context.
     */
    public EntityXpOrbBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Runs inherited entity ticking and then adds player attraction motion.
     * Attraction changes the stored motion only when an eligible target exists
     * and its normalized distance leaves a positive pull factor.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        moveToNearestPlayer();
    }

    /**
     * Collects a nonzero pickable orb when the other entity is a player.
     * Mending runs first and can mutate equipment before the remaining
     * experience is proposed through {@code player.addExperience}. Only after
     * that call returns does the method clear the orb amount and request
     * removal.
     *
     * <p>
     * The player helper performs separate progress and level updates that can
     * be cancelled or fail independently. A failure before the clearing step
     * can therefore leave repaired equipment or partially changed player
     * experience together with the original orb value. This sequence provides
     * no rollback or failure-safe exactly-once collection guarantee.
     */
    @Override
    public void onCollideWithEntity(Entity other) {
        if (this.experienceValue == 0 || !canBePicked() || !(other instanceof EntityPlayer player)) {
            return;
        }

        var remainingExperience = applyMending(player, this.experienceValue);
        player.addExperience(remainingExperience);
        this.setExperienceValue(0);
        remove();
    }

    /**
     * Repairs one randomly selected eligible equipped item and returns unspent
     * experience. Nonpositive experience or an empty target list returns the
     * original amount without changing an item. The chosen item is read again
     * from its recorded container slot before repair.
     *
     * <p>
     * Each experience point can repair two durability points, but repair is
     * capped by current item damage. The consumed amount rounds upward for an
     * odd repaired point count, preventing a one-point repair from costing no
     * experience. Slot change notification follows the damage update before
     * the remainder is returned.
     */
    protected int applyMending(EntityPlayer player, int experienceValue) {
        if (experienceValue <= 0) {
            return experienceValue;
        }

        var targets = collectMendingTargets(player);
        if (targets.isEmpty()) {
            return experienceValue;
        }

        var target = targets.get(ThreadLocalRandom.current().nextInt(targets.size()));
        var item = target.container().getItemStack(target.slot());

        var damage = item.getDamage();
        // One experience point repairs two durability points, capped by the
        // selected item damage.
        var repairAmount = Math.min(damage, experienceValue * 2);

        item.setDamage(damage - repairAmount);
        target.container().notifySlotChange(target.slot());

        // Round the experience cost upward so an odd repaired durability point
        // still consumes experience.
        var consumedExperience = (repairAmount + 1) / 2;
        return experienceValue - consumedExperience;
    }

    /**
     * Returns eligible main-hand, offhand, and armor slots in a new list. Each
     * entry retains its container and slot, so the later repair reads the item
     * currently occupying that location.
     */
    protected List<MendingTarget> collectMendingTargets(EntityPlayer player) {
        var targets = new ArrayList<MendingTarget>();
        var inventory = player.getContainer(ContainerTypes.INVENTORY);
        tryAddMendingTarget(targets, inventory, inventory.getHandSlot());
        tryAddMendingTarget(targets, player.getContainer(ContainerTypes.OFFHAND), 0);
        var armor = player.getContainer(ContainerTypes.ARMOR);
        for (int slot = 0; slot < armor.getItemStackArray().length; slot++) {
            tryAddMendingTarget(targets, armor, slot);
        }
        return targets;
    }

    /**
     * Appends a container-slot target when a nonnull container currently holds
     * an eligible item. A missing container contributes nothing, and the
     * helper retains the slot reference rather than copying its stack.
     */
    protected void tryAddMendingTarget(List<MendingTarget> targets, Container container, int slot) {
        if (container != null && isMendingTarget(container.getItemStack(slot))) {
            targets.add(new MendingTarget(container, slot));
        }
    }

    /**
     * Returns whether an item is damageable, damaged, non-air, and enchanted
     * with Mending.
     */
    protected boolean isMendingTarget(ItemStack item) {
        return item != ItemAirStack.AIR_STACK &&
               item.getItemType().getItemData().isDamageable() &&
               item.getDamage() > 0 &&
               item.hasEnchantment(EnchantmentTypes.MENDING);
    }

    /**
     * {@code MendingTarget} identifies an equipped container slot eligible for
     * a Mending repair.
     *
     * @param container retained equipment container
     * @param slot equipment slot index
     */
    protected record MendingTarget(Container container, int slot) {
    }

    /**
     * Adds a distance-scaled pull toward the player selected by {@code
     * findNearestPlayer}. The target lies at half the player eye height, and
     * distances are scaled by eight blocks before the pull factor is computed.
     * A target outside that normalized range contributes no motion.
     *
     * <p>
     * The squared remaining range strengthens attraction as the orb approaches
     * its target. Direction normalization uses the measured distance directly;
     * coincident target coordinates therefore have no zero-distance guard in
     * this implementation. The calculated pull is added to existing motion
     * rather than replacing other physical forces.
     */
    protected void moveToNearestPlayer() {
        var nearestPlayer = findNearestPlayer();
        if (nearestPlayer == null) return;

        var playerLoc = nearestPlayer.getLocation();
        // Scale displacement by eight blocks so the remaining-range factor controls
        // attraction strength.
        var dX = (playerLoc.x() - this.location.x) / 8f;
        var dY = (playerLoc.y() + nearestPlayer.getEyeHeight() / 2f - this.location.y) / 8f;
        var dZ = (playerLoc.z() - this.location.z) / 8f;
        var d = (float) Math.sqrt(dX * dX + dY * dY + dZ * dZ);
        var diff = 1f - d;

        if (diff > 0D) {
            // Squaring the remaining range strengthens the pull as the target gets
            // closer.
            diff = diff * diff;
            physicsComponent.addMotion(
                    dX / d * diff * 0.1f,
                    dY / d * diff * 0.1f,
                    dZ / d * diff * 0.1f
            );
        }
    }

    /**
     * Returns the last controlled player encountered strictly within the
     * attraction radius. A missing eligible player produces {@code null};
     * callers must not assume the result is the geometrically nearest player.
     * The local comparison threshold remains the initial squared radius
     * throughout iteration.
     *
     * <p>
     * Consequently, every eligible player replaces the previous selection even
     * when farther away. The dimension player iteration order decides the
     * final target, while the strict comparison excludes a player exactly on
     * the radius. Attraction consumes this selected player on the same tick.
     */
    protected EntityPlayer findNearestPlayer() {
        EntityPlayer nearestPlayer = null;
        var nearestDistanceSquared = MAX_MOVE_DISTANCE_SQUARED;
        for (var player : getDimension().getPlayers()) {
            var entity = player.getControlledEntity();
            var distanceSquared = entity.getLocation().distanceSquared(location);
            if (distanceSquared < nearestDistanceSquared) {
                nearestPlayer = entity;
            }
        }
        return nearestPlayer;
    }

    /**
     * Loads inherited entity data and restores a present experience amount
     * through {@code setExperienceValue}. That setter also publishes state,
     * while a missing amount tag preserves the current stored value.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_EXPERIENCE_VALUE, this::setExperienceValue);
    }

    /**
     * Returns inherited entity data with the current experience amount
     * persisted.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_EXPERIENCE_VALUE, this.experienceValue)
                .build();
    }

    /**
     * Returns the experience orb local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        // The compact box limits collision pickup to the small orb footprint.
        return new AABBd(-0.05, 0, -0.05, 0.05, 0.1, 0.05);
    }

    /**
     * Replaces the stored experience amount and broadcasts the updated orb
     * state. A value of {@code 0} disables the collection path before the orb
     * entity is removed.
     */
    @Override
    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
        broadcastState();
    }
}
