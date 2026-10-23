package org.allaymc.server.item.component.weapon;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.ProjectileLaunchEvent;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemTridentStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.TridentRiptideSound;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3d;

/**
 * {@code ItemTridentBaseComponentImpl} handles charged trident throws and
 * Riptide propulsion. Both paths require enough charge and remaining
 * durability through {@code useItemInAir}. A throw prepares a projectile with
 * a one-item copy of the stack, while Riptide keeps the item in hand and
 * replaces the player's motion after its environmental check.
 *
 * <p>
 * The thrown copy preserves the item state used for later pickup or Loyalty
 * return. The copy is made before the original stack receives the throw
 * durability request, so that later request does not update the projectile's
 * copy. The launch event runs before world insertion, sound, item damage, or
 * hand removal; cancellation leaves those later effects unapplied. Creative
 * throws retain the player's held item.
 *
 * @author daoge_cmd
 */
public class ItemTridentBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * {@code MIN_CHARGE_TICKS} sets the minimum use duration in ticks before
     * throw or Riptide selection. Shorter uses leave the held item and player
     * motion unchanged.
     */
    protected static final int MIN_CHARGE_TICKS = 5;

    /**
     * {@code MIN_FORCE} sets the minimum computed throw force accepted after
     * charging. The release handler rejects weaker charges before choosing a
     * throw or Riptide action.
     */
    protected static final double MIN_FORCE = 0.1;

    /**
     * {@code RIPTIDE_BASE_SPEED} supplies the base Riptide motion magnitude.
     * The propulsion calculation adds the enchantment-level contribution before
     * scaling the player's facing direction.
     */
    protected static final double RIPTIDE_BASE_SPEED = 1.5;

    /**
     * {@code RIPTIDE_SPEED_PER_LEVEL} supplies the Riptide motion increase per
     * enchantment level. The propulsion calculation combines this contribution
     * with the base magnitude before replacing player motion.
     */
    protected static final double RIPTIDE_SPEED_PER_LEVEL = 1.0;

    /**
     * Creates a trident base component from the supplied stack initialization data.
     */
    public ItemTridentBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns {@code true} so the player can begin charging the trident.
     * Remaining durability, charge strength, and the environmental requirements
     * for Riptide are checked when the use is released rather than here.
     */
    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        return true;
    }

    /**
     * Attempts a throw or Riptide action after the minimum charge duration.
     * The method rejects a trident with at most one durability point remaining,
     * including in creative mode, and rejects insufficient computed force.
     * An enchanted Riptide action delegates to {@code handleRiptide}; other
     * accepted releases delegate to {@code throwTrident}.
     */
    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        if (usedTime < MIN_CHARGE_TICKS) {
            return false;
        }

        if (getMaxDamage() - getDamage() <= 1) {
            return false;
        }

        // Convert charge ticks to seconds before applying the capped force curve;
        // twenty ticks reach full force and longer charges cannot increase it.
        double p = usedTime / 20.0;
        double force = Math.min((p * p + p * 2) / 3.0, 1.0);

        if (force < MIN_FORCE) {
            return false;
        }

        int riptideLevel = getEnchantmentLevel(EnchantmentTypes.RIPTIDE);
        if (riptideLevel > 0) {
            return handleRiptide(player, riptideLevel);
        }

        return throwTrident(player, force);
    }

    /**
     * Prepares and launches a thrown trident with a one-item copy of this stack.
     * The copied item retains the pre-throw durability and other serialized state,
     * and Loyalty records the player's current hand slot as the return preference.
     *
     * <p>
     * A missing projectile or cancelled {@code ProjectileLaunchEvent} returns
     * {@code false} before insertion or hand removal. The event's force changes
     * are not read back because motion has already been computed. After insertion
     * and sound, noncreative players receive a durability request on the original
     * stack and then have their held item cleared; those steps do not modify the
     * copy retained by the projectile.
     */
    protected boolean throwTrident(EntityPlayer player, double force) {
        var dimension = player.getDimension();
        var location = player.getLocation();
        // Keep entity creation and the shooting sound at the same eye-relative
        // origin; the small downward offset applies to both effects.
        var shootPos = new Vector3d(location.x(), location.y() + player.getEyeHeight() - 0.1, location.z());

        var direction = MathUtils.getDirectionVector(location);
        // Full charge gives the trident its configured maximum launch speed.
        var speed = force * 2.5;
        var motion = direction.mul(speed);

        var playerMotion = new Vector3d(player.getMotion());
        // Trident velocity inherits player motion. Remove grounded vertical motion
        // so aim and horizontal movement control a grounded throw's trajectory.
        if (player.isOnGround()) {
            playerMotion.y = 0;
        }
        motion.add(playerMotion);

        var trident = EntityTypes.THROWN_TRIDENT.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(shootPos)
                        .rot(-location.yaw(), -location.pitch())
                        .motion(motion)
                        .build()
        );

        if (trident == null) {
            return false;
        }

        trident.setShooter(player);

        // Retain one copied item for pickup or Loyalty return before removing
        // the held stack; later durability requests still target the original.
        var tridentItem = (ItemTridentStack) thisItemStack.copy();
        tridentItem.setCount(1);
        trident.setTridentItem(tridentItem);

        int loyaltyLevel = getEnchantmentLevel(EnchantmentTypes.LOYALTY);
        if (loyaltyLevel > 0) {
            trident.setFavoredSlot(player.getContainer(ContainerTypes.INVENTORY).getHandSlot());
        }

        var event = new ProjectileLaunchEvent(trident, player, force);
        if (!event.call()) {
            return false;
        }

        dimension.getEntityManager().addEntity(trident);

        dimension.addSound(shootPos, SimpleSound.TRIDENT_THROW);

        if (player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(1);
        }

        if (player.getGameMode() != GameMode.CREATIVE) {
            player.clearItemInHand();
        }

        return true;
    }

    /**
     * Propels the player instead of creating a thrown projectile.
     * The player must pass {@code canUseRiptide}, and a noncreative player needs
     * more than one durability point remaining. Success replaces motion using the
     * view direction and enchantment-scaled speed, enables the spin attack,
     * resets fall distance, plays the Riptide sound, and requests one item damage
     * point outside creative mode.
     */
    protected boolean handleRiptide(EntityPlayer player, int riptideLevel) {
        if (!player.canUseRiptide()) {
            return false;
        }

        if (player.getGameMode() != GameMode.CREATIVE && getMaxDamage() - getDamage() <= 1) {
            return false;
        }

        var dimension = player.getDimension();
        var location = player.getLocation();

        var direction = MathUtils.getDirectionVector(location);
        var speed = RIPTIDE_BASE_SPEED + riptideLevel * RIPTIDE_SPEED_PER_LEVEL;

        var propulsion = direction.mul(speed, new Vector3d());
        player.setMotion(propulsion);

        player.setSpinAttacking(true);
        player.resetFallDistance();

        dimension.addSound(location, new TridentRiptideSound(riptideLevel));

        if (player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(1);
        }

        return true;
    }
}
