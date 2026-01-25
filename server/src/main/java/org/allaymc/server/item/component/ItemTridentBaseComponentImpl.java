package org.allaymc.server.item.component;

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
import org.joml.Vector3d;

/**
 * Item component implementation for tridents.
 * Handles throwing and riptide mechanics.
 *
 * @author daoge_cmd
 */
public class ItemTridentBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * Minimum ticks required to charge before throwing.
     */
    protected static final int MIN_CHARGE_TICKS = 5;

    /**
     * Minimum force required to throw.
     */
    protected static final double MIN_FORCE = 0.1;

    /**
     * Base speed for riptide propulsion.
     */
    protected static final double RIPTIDE_BASE_SPEED = 1.5;

    /**
     * Additional speed per riptide enchantment level.
     */
    protected static final double RIPTIDE_SPEED_PER_LEVEL = 1.0;

    public ItemTridentBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        if (usedTime < MIN_CHARGE_TICKS) {
            return false;
        }

        // Cannot throw trident with 1 or less durability remaining
        // See: https://minecraft.wiki/w/Trident
        if (getMaxDamage() - getDamage() <= 1) {
            return false;
        }

        // Calculate force based on charge time
        double p = usedTime / 20.0;
        double force = Math.min((p * p + p * 2) / 3.0, 1.0);

        if (force < MIN_FORCE) {
            return false;
        }

        // Check for riptide enchantment
        int riptideLevel = getEnchantmentLevel(EnchantmentTypes.RIPTIDE);
        if (riptideLevel > 0) {
            return handleRiptide(player, riptideLevel);
        }

        return throwTrident(player, force);
    }

    /**
     * Throws the trident as a projectile.
     */
    protected boolean throwTrident(EntityPlayer player, double force) {
        var dimension = player.getDimension();
        var location = player.getLocation();
        var shootPos = new Vector3d(location.x(), location.y() + player.getEyeHeight() - 0.1, location.z());

        // Calculate motion based on player's look direction
        var direction = MathUtils.getDirectionVector(location);
        var speed = force * 2.5;
        var motion = direction.mul(speed);

        // Add player's motion if on ground
        var playerMotion = new Vector3d(player.getMotion());
        if (player.isOnGround()) {
            playerMotion.y = 0;
        }
        motion.add(playerMotion);

        // Create trident entity
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

        // Set up trident properties
        trident.setShooter(player);

        // Store the trident item for later return (loyalty) or drop
        var tridentItem = (ItemTridentStack) thisItemStack.copy();
        tridentItem.setCount(1);
        trident.setTridentItem(tridentItem);

        // Set favored slot for loyalty return
        int loyaltyLevel = getEnchantmentLevel(EnchantmentTypes.LOYALTY);
        if (loyaltyLevel > 0) {
            trident.setFavoredSlot(player.getContainer(ContainerTypes.INVENTORY).getHandSlot());
        }

        // Fire event
        var event = new ProjectileLaunchEvent(trident, player, force);
        if (!event.call()) {
            return false;
        }

        // Spawn trident
        dimension.getEntityManager().addEntity(trident);

        // Play sound
        dimension.addSound(shootPos, SimpleSound.TRIDENT_THROW);

        // Reduce durability in survival mode
        if (player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(1);
        }

        // Remove from hand (trident is now thrown)
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.clearItemInHand();
        }

        return true;
    }

    /**
     * Handles riptide mechanic - propels the player instead of throwing the trident.
     * Only works when the player is in water or rain.
     * Level I = 9 blocks, Level II = 15 blocks, Level III = 21 blocks
     */
    protected boolean handleRiptide(EntityPlayer player, int riptideLevel) {
        // Check if player is in water or rain
        if (!player.canUseRiptide()) {
            return false;
        }

        // Cannot use riptide with 1 or less durability remaining
        if (player.getGameMode() != GameMode.CREATIVE && getMaxDamage() - getDamage() <= 1) {
            return false;
        }

        var dimension = player.getDimension();
        var location = player.getLocation();

        // Calculate propulsion direction and speed
        // Bedrock Edition: 3 + 6 * level blocks distance
        // Speed formula calibrated to achieve approximately correct distances
        var direction = MathUtils.getDirectionVector(location);
        var speed = RIPTIDE_BASE_SPEED + riptideLevel * RIPTIDE_SPEED_PER_LEVEL;

        // Apply propulsion to player
        var propulsion = direction.mul(speed, new Vector3d());
        player.setMotion(propulsion);

        // Set spin attack state
        player.setSpinAttacking(true);
        player.resetFallDistance();

        // Play riptide sound
        dimension.addSound(location, new TridentRiptideSound(riptideLevel));

        // Reduce durability in survival mode
        if (player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(1);
        }

        return true;
    }
}
