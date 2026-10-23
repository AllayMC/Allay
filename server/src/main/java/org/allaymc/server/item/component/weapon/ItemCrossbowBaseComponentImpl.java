package org.allaymc.server.item.component.weapon;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.EntityShootCrossbowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemCrossbowBaseComponent;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.item.interfaces.ItemFireworkRocketStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.sound.CrossbowLoadSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.container.impl.OffhandContainerImpl;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code ItemCrossbowBaseComponentImpl} manages crossbow charging, retained
 * ammunition, and projectile firing. Charging selects offhand arrows or
 * fireworks before inventory arrows, consumes one outside creative mode, and
 * retains a copy for a later shot. That copy keeps the selected stack's
 * post-consumption count; firing uses it as the projectile description rather
 * than spawning one projectile for every stored item.
 *
 * <p>
 * A loaded stack is saved under {@code "chargedItem"} when it is nonempty
 * and non-air. Loading extra tags only replaces that stack when the compound
 * is present. Firing prepares one or three entities, requests durability
 * damage, and then dispatches a cancellable event. Cancellation keeps the
 * load without undoing the earlier damage request; accepted shots insert the
 * event's shared projectile list before clearing the load.
 *
 * @author daoge_cmd
 */
public class ItemCrossbowBaseComponentImpl extends ItemBaseComponentImpl implements ItemCrossbowBaseComponent {

    /**
     * {@code TAG_CHARGED_ITEM} names the extra-tag compound storing a loaded
     * projectile stack. {@code loadExtraTag} restores that compound, and
     * {@code saveExtraTag} writes it only for a usable load.
     */
    protected static final String TAG_CHARGED_ITEM = "chargedItem";

    // Charge timing constants govern release eligibility; projectile constants
    // govern launch motion, arrow damage, and the spread of additional shots.
    /**
     * Unenchanted charge duration, in game ticks.
     */
    protected static final long BASE_CHARGE_DURATION_TICKS = 25;
    /**
     * Charge reduction per Quick Charge level, in ticks.
     */
    protected static final long QUICK_CHARGE_REDUCTION_PER_LEVEL = 5;
    /**
     * Arrow launch motion multiplier.
     */
    protected static final double ARROW_VELOCITY = 5.25;
    /**
     * Firework launch motion multiplier.
     */
    protected static final double FIREWORK_VELOCITY = 1.6;
    /**
     * Base damage assigned to crossbow arrows.
     */
    protected static final float ARROW_BASE_DAMAGE = 9.0f;
    /**
     * Yaw offset of each side projectile, in degrees.
     */
    protected static final double MULTISHOT_ANGLE = 10.0;

    /**
     * {@code loadedProjectile} retains the stack describing the next shot.
     * The generated getter and setter expose and replace this reference directly.
     * Firing expects an {@code ItemArrowStack} or {@code ItemFireworkRocketStack};
     * {@code isLoaded} checks only for a nonempty, non-air value.
     */
    @Getter
    @Setter
    protected ItemStack loadedProjectile;

    // hasPlayedStartSound, hasCompletedCharge, and lastMiddleSoundTick track
    // feedback for one charge attempt. canUseItemInAir resets all three
    // before routing a new click, and none of this feedback state is persisted.
    /**
     * Start sound emitted for the current charge attempt.
     */
    protected boolean hasPlayedStartSound = false;
    /**
     * Completion feedback emitted for the current charge attempt.
     */
    protected boolean hasCompletedCharge = false;
    /**
     * Last middle-sound adjusted tick, or {@code -1} before any such sound.
     */
    protected int lastMiddleSoundTick = -1;

    /**
     * Creates a crossbow base component from the supplied stack initialization data.
     */
    public ItemCrossbowBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns whether the retained load is a nonempty, non-air stack.
     * The method does not validate its projectile type; the firing path expects
     * an arrow or firework, even if another item stack passes this loaded-state check.
     */
    @Override
    public boolean isLoaded() {
        return loadedProjectile != null && !loadedProjectile.isEmptyOrAir();
    }

    /**
     * Resets charge feedback and returns whether the player can begin charging.
     * A loaded crossbow returns {@code false} so click handling can route to
     * {@code rightClickItemInAir}; an unloaded crossbow needs creative mode or
     * available ammunition to return {@code true}.
     */
    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        hasPlayedStartSound = false;
        hasCompletedCharge = false;
        lastMiddleSoundTick = -1;

        if (isLoaded()) {
            return false;
        }
        return player.getGameMode() == GameMode.CREATIVE || hasProjectile(player);
    }

    /**
     * Fires the loaded crossbow when click handling does not begin a charge.
     * An unloaded crossbow does nothing here, including the no-ammunition case
     * that also makes {@code canUseItemInAir} return {@code false}.
     */
    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        if (isLoaded()) {
            shoot(player);
        }
    }

    /**
     * Loads a projectile after the required charge duration and returns whether
     * loading succeeded. A short charge or missing ammunition returns
     * {@code false}; otherwise noncreative players consume one selected item
     * before the remaining stack state is copied without assigning a new
     * network identifier, and the player's held item is refreshed.
     */
    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        long requiredTicks = getChargeDuration();
        if (usedTime < requiredTicks) {
            return false;
        }

        var projectile = findAndConsumeProjectile(player);
        if (projectile == null) {
            return false;
        }

        setLoadedProjectile(projectile.copy(false));
        player.notifyItemInHandChange();
        return true;
    }

    /**
     * Emits charge sounds and completion feedback for an unloaded crossbow when
     * ammunition exists or the player is creative. The start and completion
     * flags suppress repeated feedback within an attempt, and the controller
     * receives completion only for an actual player.
     *
     * <p>
     * Middle sounds use a Quick Charge-adjusted tick value and play only when a
     * sampled positive value is exactly divisible by sixteen and differs from
     * the last emitted value. This sound timeline uses a denominator bounded at
     * one, while release eligibility separately uses {@code getChargeDuration}
     * and its ten-tick minimum.
     */
    @Override
    public void onUseInAirTick(EntityPlayer player, long usedTime) {
        if (isLoaded()) {
            return;
        }

        if (player.getGameMode() != GameMode.CREATIVE && !hasProjectile(player)) {
            return;
        }

        int quickChargeLevel = getEnchantmentLevel(EnchantmentTypes.QUICK_CHARGE);
        long chargeDuration = getChargeDuration();

        // A charge first observed after this two-tick window receives no start sound.
        if (usedTime <= 2 && !hasPlayedStartSound) {
            playLoadSound(player, CrossbowLoadSound.LoadingStage.START);
            hasPlayedStartSound = true;
        }

        // Map real ticks onto the base reload sound timeline. Keep the divisor
        // positive even for high enchantment levels; the cast drops fractional sound ticks.
        double multiplier = 25.0 / Math.max(25 - (5 * quickChargeLevel), 1);
        int adjustedTicks = (int) (usedTime * multiplier);

        // Check sampled boundaries only; skipped sixteen-tick boundaries are not
        // replayed later, so this callback does not catch up missed middle sounds.
        if (adjustedTicks > 0 && adjustedTicks % 16 == 0 && adjustedTicks != lastMiddleSoundTick) {
            playLoadSound(player, CrossbowLoadSound.LoadingStage.MIDDLE);
            lastMiddleSoundTick = adjustedTicks;
        }

        if (usedTime >= chargeDuration && !hasCompletedCharge) {
            hasCompletedCharge = true;
            playLoadSound(player, CrossbowLoadSound.LoadingStage.END);

            // Only actual players have a client that needs the charging-finished
            // notification; simulated players still receive the world sound above.
            if (player.isActualPlayer()) {
                player.getController().sendItemChargingFinished();
            }
        }
    }

    /**
     * Returns the release charge duration in game ticks after Quick Charge
     * reduction. Each level removes five ticks from the base duration, but the
     * returned duration remains at least ten ticks even at higher levels.
     */
    protected long getChargeDuration() {
        int quickChargeLevel = getEnchantmentLevel(EnchantmentTypes.QUICK_CHARGE);
        long reduction = quickChargeLevel * QUICK_CHARGE_REDUCTION_PER_LEVEL;
        // Keep at least half a second of charging even at higher enchantment levels.
        return Math.max(BASE_CHARGE_DURATION_TICKS - reduction, 10);
    }

    /**
     * Returns whether ammunition exists in the offhand or inventory without
     * consuming it. The offhand accepts fireworks or arrows, while the inventory
     * search accepts only arrows; inventory fireworks do not satisfy this check.
     */
    protected boolean hasProjectile(EntityPlayer player) {
        Container offhand = player.getContainer(ContainerTypes.OFFHAND);
        var offhandItem = offhand.getItemStack(OffhandContainerImpl.OFFHAND_SLOT);
        if (offhandItem instanceof ItemFireworkRocketStack || offhandItem instanceof ItemArrowStack) {
            return true;
        }

        // With no supported offhand load, fall back to inventory arrows only;
        // fireworks must stay in the offhand to qualify for crossbow loading.
        Container inventory = player.getContainer(ContainerTypes.INVENTORY);
        for (var itemStack : inventory.getItemStacks()) {
            if (itemStack instanceof ItemArrowStack) {
                return true;
            }
        }

        return false;
    }

    /**
     * Selects offhand fireworks or arrows before the first inventory arrow.
     * Noncreative players consume one from the chosen slot, and the method
     * returns that stack reference after any count reduction so the caller can
     * copy its remaining state. Creative players keep the chosen stack unchanged.
     *
     * <p>
     * When no supported ammunition exists, creative mode receives a newly
     * created ordinary arrow stack and other modes receive {@code null}. A
     * one-item source slot is cleared instead of reducing its retained stack
     * object to zero.
     */
    protected ItemStack findAndConsumeProjectile(EntityPlayer player) {
        boolean creative = player.getGameMode() == GameMode.CREATIVE;

        Container container;
        int slot;
        ItemStack projectile = null;

        container = player.getContainer(ContainerTypes.OFFHAND);
        slot = OffhandContainerImpl.OFFHAND_SLOT;
        var offhandItem = container.getItemStack(slot);
        if (offhandItem instanceof ItemFireworkRocketStack || offhandItem instanceof ItemArrowStack) {
            projectile = offhandItem;
        }

        // Prefer the offhand descriptor when both locations contain ammunition;
        // otherwise the first inventory arrow supplies the stored potion variant.
        if (projectile == null) {
            container = player.getContainer(ContainerTypes.INVENTORY);
            var itemStacks = container.getItemStacks();
            for (slot = 0; slot < itemStacks.size(); slot++) {
                var item = itemStacks.get(slot);
                if (item instanceof ItemArrowStack) {
                    projectile = item;
                    break;
                }
            }
        }

        // Creative loading can proceed without inventory ammunition by creating
        // an ordinary arrow descriptor; other modes must decline this missing-ammo case.
        if (projectile == null) {
            if (creative) {
                return ItemTypes.ARROW.createItemStack(1);
            }
            return null;
        }

        // Clear a one-item source slot; otherwise notify clients after mutating
        // its shared stack so the remaining inventory count matches this load.
        if (!creative) {
            if (projectile.getCount() == 1) {
                container.clearSlot(slot);
            } else {
                projectile.reduceCount(1);
                container.notifySlotChange(slot);
            }
        }

        return projectile;
    }

    /**
     * Prepares and fires the retained arrow or firework load, doing nothing when
     * {@code isLoaded} is {@code false}. Multishot prepares three entities from
     * one load. Noncreative shots request three durability points for fireworks
     * or one for arrows before {@code EntityShootCrossbowEvent} is dispatched.
     *
     * <p>
     * A cancelled event leaves the load stored and does not undo that damage
     * request. The event exposes the same mutable projectile list subsequently
     * inserted into the dimension, so listener edits affect the spawned set. An
     * accepted shot inserts that list, plays its sound, clears the load, and
     * refreshes the player's held item.
     */
    protected void shoot(EntityPlayer player) {
        if (!isLoaded()) {
            return;
        }

        var dimension = player.getDimension();
        var location = player.getLocation();
        // Keep entity creation and the shooting sound at the same eye-relative
        // origin; the small downward offset applies to both effects.
        var shootPos = new Vector3d(location.x(), location.y() + player.getEyeHeight() - 0.1, location.z());
        var hasMultishot = getEnchantmentLevel(EnchantmentTypes.MULTISHOT) > 0;
        var piercingLevel = getEnchantmentLevel(EnchantmentTypes.PIERCING);
        var creative = player.getGameMode() == GameMode.CREATIVE;

        List<Entity> projectiles = new ArrayList<>();

        // Choose durability cost from the loaded ammunition before listeners can
        // edit the prepared projectile list; extra Multishot entities do not multiply it.
        if (loadedProjectile instanceof ItemFireworkRocketStack fireworkStack) {
            if (hasMultishot) {
                projectiles.add(createFirework(player, shootPos, 0, fireworkStack));
                projectiles.add(createFirework(player, shootPos, -MULTISHOT_ANGLE, fireworkStack));
                projectiles.add(createFirework(player, shootPos, MULTISHOT_ANGLE, fireworkStack));
            } else {
                projectiles.add(createFirework(player, shootPos, 0, fireworkStack));
            }
            if (!creative) {
                tryIncreaseDamage(3);
            }
        } else {
            if (hasMultishot) {
                // Only the center arrow permits pickup, preventing the additional
                // side arrows from turning one consumed load into extra recoverable ammunition.
                projectiles.add(createArrow(player, shootPos, 0, piercingLevel, false));
                projectiles.add(createArrow(player, shootPos, -MULTISHOT_ANGLE, piercingLevel, true));
                projectiles.add(createArrow(player, shootPos, MULTISHOT_ANGLE, piercingLevel, true));
            } else {
                projectiles.add(createArrow(player, shootPos, 0, piercingLevel, false));
            }
            if (!creative) {
                tryIncreaseDamage(1);
            }
        }

        var event = new EntityShootCrossbowEvent(player, thisItemStack, projectiles);
        if (!event.call()) {
            return;
        }

        for (var projectile : projectiles) {
            dimension.getEntityManager().addEntity(projectile);
        }

        dimension.addSound(shootPos, SimpleSound.CROSSBOW_SHOOT);

        setLoadedProjectile(null);
        player.notifyItemInHandChange();
    }

    /**
     * Creates a critical crossbow arrow from the retained arrow load.
     * The caller must keep {@code loadedProjectile} as an {@code ItemArrowStack};
     * its potion type is copied, while the supplied piercing level, pickup flag,
     * and yaw offset configure this projectile. Creative arrows are marked
     * infinite, and airborne player motion contributes to their initial motion.
     */
    protected EntityArrow createArrow(EntityPlayer player, Vector3dc shootPos, double yawOffset, int piercingLevel, boolean pickUpDisabled) {
        var location = player.getLocation();
        var playerMotion = new Vector3d(player.getMotion());
        // Arrow velocity inherits player motion. Remove grounded vertical motion
        // so aim and horizontal movement control a grounded shot's trajectory.
        if (player.isOnGround()) {
            playerMotion.setComponent(1, 0);
        }
        var direction = MathUtils.getDirectionVector(location.yaw() + yawOffset, location.pitch());
        var arrow = EntityTypes.ARROW.createEntity(
                EntityInitInfo.builder()
                        .dimension(player.getDimension())
                        .pos(shootPos)
                        .rot(-location.yaw() - yawOffset, -location.pitch())
                        .motion(direction.mul(ARROW_VELOCITY).add(playerMotion))
                        .build()
        );

        arrow.setShooter(player);
        arrow.setBaseDamage(ARROW_BASE_DAMAGE);
        arrow.setPiercingLevel(piercingLevel);
        arrow.setPickUpDisabled(pickUpDisabled);
        arrow.setCritical(true);
        arrow.setPotionType(((ItemArrowStack) loadedProjectile).getPotionType());
        arrow.setInfinite(player.getGameMode() == GameMode.CREATIVE);

        return arrow;
    }

    /**
     * Creates a crossbow firework using the supplied rocket's explosion set and
     * flight duration. The lifetime threshold is
     * {@code fireworkStack.getDuration() * 10 + 10} ticks; this path retains
     * the shared explosion set and disables the ordinary
     * upward thrust while preserving its sideways motion.
     */
    protected EntityFireworksRocket createFirework(EntityPlayer player, Vector3dc shootPos, double yawOffset, ItemFireworkRocketStack fireworkStack) {
        var location = player.getLocation();
        var direction = MathUtils.getDirectionVector(location.yaw() + yawOffset, location.pitch());
        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder()
                        .dimension(player.getDimension())
                        .pos(shootPos)
                        .rot(-location.yaw() - yawOffset, -location.pitch())
                        .motion(direction.mul(FIREWORK_VELOCITY))
                        .build()
        );

        // Use the crossbow lifetime threshold instead of the rocket item's
        // randomized normal-flight duration, making the expiration threshold deterministic.
        firework.setExistenceTicks(fireworkStack.getDuration() * 10 + 10);
        firework.setExplosions(fireworkStack.getExplosions());
        firework.setSidewaysMotionMultiplier(1.0);
        firework.setUpwardsMotion(0);

        return firework;
    }

    /**
     * Plays the requested loading stage using the current Quick Charge sound
     * variant. The method emits the sound at the player's current location and
     * does not change the charge feedback flags itself.
     */
    protected void playLoadSound(EntityPlayer player, CrossbowLoadSound.LoadingStage stage) {
        boolean quickCharge = getEnchantmentLevel(EnchantmentTypes.QUICK_CHARGE) > 0;
        player.getDimension().addSound(player.getLocation(), new CrossbowLoadSound(stage, quickCharge));
    }

    /**
     * Loads inherited item tags and restores a charged projectile when its
     * compound is present. An absent or differently typed
     * {@code "chargedItem"} entry leaves the existing {@code loadedProjectile}
     * reference unchanged rather than unloading the crossbow.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_CHARGED_ITEM, nbt -> {
            this.loadedProjectile = NBTIO.getAPI().fromItemStackNBT(nbt);
        });
    }

    /**
     * Returns inherited item tags with the charged projectile added for a usable
     * load. A {@code null}, empty, or air load contributes no
     * {@code "chargedItem"} compound; a retained load is serialized with its
     * current stack count and extra data.
     */
    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (isLoaded()) {
            builder.putCompound(TAG_CHARGED_ITEM, loadedProjectile.saveNBT());
        }
        return builder.build();
    }
}
