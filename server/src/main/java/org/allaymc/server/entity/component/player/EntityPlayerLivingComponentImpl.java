package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.InventoryContainer;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityDamageBlockedEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemShieldBaseComponent;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityLivingComponentImpl;
import org.joml.Vector3d;

/**
 * {@code EntityPlayerLivingComponentImpl} applies player-specific damage rules,
 * shield blocking, totem use, and controller updates around the shared living
 * component. Phantom and creative players accept only API damage, while other
 * modes use the inherited category checks. Client health and effect messages
 * are sent only when the player has a controller.
 *
 * <p>
 * Damage application tests totem use before shield blocking. A qualifying totem
 * path attempts health and effect changes and consumes its hand slot; otherwise
 * an accepted shield block zeroes final damage and suppresses victim knockback
 * before returning without inherited hit processing. Block cancellation resumes
 * that inherited path. Death handling first invokes shared cleanup, then wakes
 * a sleeping player and builds death wording from the retained last damage.
 *
 * @author daoge_cmd
 */
public class EntityPlayerLivingComponentImpl extends EntityLivingComponentImpl {
    /**
     * Player receiving damage rules and controller updates.
     */
    @ComponentObject
    protected EntityPlayer thisPlayer;

    /**
     * Returns whether the damage category is allowed for the player's mode.
     * Phantom or creative players accept only {@code DamageType.API}; other
     * players use the inherited checks for fall, fire, drowning, and void
     * damage.
     */
    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        if (thisPlayer.isPhantom()) {
            return damage.getDamageType() == DamageType.API;
        }

        var gameMode = thisPlayer.getGameMode();
        if (gameMode == GameMode.CREATIVE) {
            return damage.getDamageType() == DamageType.API;
        }

        return super.canBeAttacked(damage);
    }

    /**
     * Returns whether this player uses survival or adventure fall-damage rules.
     * This override decides from game mode alone; the separate attack pipeline
     * still handles health state, damage cooldown, and cancellable damage
     * events.
     */
    @Override
    public boolean hasFallDamage() {
        var gameMode = thisPlayer.getGameMode();
        return gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE;
    }

    /**
     * Returns whether fire damage is enabled by living rules and player mode.
     * The inherited resistance, fireproof, and world-rule checks must pass, and
     * the player must be in {@code SURVIVAL} or {@code ADVENTURE}.
     */
    @Override
    public boolean hasFireDamage() {
        return super.hasFireDamage() && (thisPlayer.getGameMode() == GameMode.SURVIVAL || thisPlayer.getGameMode() == GameMode.ADVENTURE);
    }

    /**
     * Returns whether the player's mode permits damage from the void. Creative
     * and spectator modes are excluded here; other modes remain subject to the
     * surrounding attack pipeline's health, cooldown, and event checks.
     */
    @Override
    public boolean hasVoidDamage() {
        return thisPlayer.getGameMode() != GameMode.CREATIVE &&
               thisPlayer.getGameMode() != GameMode.SPECTATOR;
    }

    /**
     * Applies player damage after first attempting totem use and shield
     * blocking. A successful special path returns before the inherited health,
     * hit-action, and knockback processing; ordinary damage reaches the
     * inherited implementation only when both special paths decline.
     */
    @Override
    protected void applyDamage(DamageContainer damage) {
        if (tryConsumeTotem(damage)) {
            return;
        }
        if (processShieldBlocking(damage)) {
            return;
        }
        super.applyDamage(damage);
    }

    /**
     * Returns a held shield when sneaking and shield cooldown permit blocking.
     * The main hand takes priority over the offhand. A player who is not
     * sneaking, is still on cooldown, or holds no shield produces {@code null}.
     */
    private ItemShieldStack findActiveShield() {
        if (!thisPlayer.isSneaking()) {
            return null;
        }

        if (!thisPlayer.isCooldownEnd(ItemShieldBaseComponent.SHIELD_COOLDOWN_CATEGORY)) {
            return null;
        }

        InventoryContainer inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);
        OffhandContainer offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);

        var handItem = inventory.getItemInHand();
        var offhandItem = offhand.getOffhand();

        if (handItem instanceof ItemShieldStack shield) {
            return shield;
        }
        if (offhandItem instanceof ItemShieldStack shield) {
            return shield;
        }

        return null;
    }

    /**
     * Attempts a shield block and returns whether ordinary damage application
     * should stop. The selected shield must accept the incoming direction, and
     * {@code EntityDamageBlockedEvent} must remain uncancelled. Cancellation
     * leaves the mutable damage container available to the inherited damage
     * path.
     *
     * <p>
     * An accepted block zeroes final damage and disables victim knockback
     * before updating shield durability and possible disable cooldown.
     * Durability uses {@code sourceDamage}, and event flags independently
     * control attacker knockback and sound. The handler notifies the hand slot
     * that still holds the same shield reference after durability changes.
     */
    private boolean processShieldBlocking(DamageContainer damage) {
        var shield = findActiveShield();
        if (shield == null) {
            return false;
        }

        if (!shield.tryBlockDamage(thisPlayer, damage)) {
            return false;
        }

        // Dispatch before reducing damage or wearing the shield so cancellation
        // can resume ordinary damage handling without those block side effects.
        var event = new EntityDamageBlockedEvent(thisPlayer, damage, shield);
        if (!event.call()) {
            return false;
        }

        float originalDamage = damage.getSourceDamage();

        damage.setFinalDamage(0);
        damage.setHasKnockback(false);

        shield.applyBlockDurability(originalDamage);

        notifyShieldDurabilityChange(shield);

        if (shield.shouldDisableShield(damage.getAttacker())) {
            thisPlayer.setCooldown(
                    ItemShieldBaseComponent.SHIELD_COOLDOWN_CATEGORY,
                    ItemShieldBaseComponent.SHIELD_DISABLE_COOLDOWN,
                    true
            );
        }

        if (event.isKnockbackAttacker()) {
            applyShieldKnockback(damage);
        }

        if (event.isPlayAnimation()) {
            var location = thisPlayer.getLocation();
            var dimension = location.dimension();
            if (dimension != null) {
                dimension.addSound(location, SimpleSound.SHIELD_BLOCK);
            }
        }

        return true;
    }

    /**
     * Notifies the hand slot that still holds the supplied shield instance.
     * Identity checks give the main hand priority over the offhand; no slot is
     * notified if neither container currently retains that same reference.
     */
    private void notifyShieldDurabilityChange(ItemShieldStack shield) {
        InventoryContainer inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);
        OffhandContainer offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);

        if (inventory.getItemInHand() == shield) {
            inventory.notifySlotChange(inventory.getHandSlot());
        } else if (offhand.getOffhand() == shield) {
            offhand.notifySlotChange(OffhandContainer.OFFHAND_SLOT);
        }
    }

    /**
     * Applies shield-response knockback to an attacker with a physics
     * component. Other source objects and entities without physics are ignored.
     * The player location supplies the source, with horizontal strength
     * {@code 0.5} and vertical strength {@code 0.4}.
     */
    private void applyShieldKnockback(DamageContainer damage) {
        if (!(damage.getAttacker() instanceof Entity attacker)) {
            return;
        }

        if (!(attacker instanceof EntityPhysicsComponent physics)) {
            return;
        }

        var playerLoc = thisPlayer.getLocation();
        // Shield feedback uses its own push strengths rather than reusing the
        // incoming attack's victim-directed knockback values or additional motion.
        physics.knockback(playerLoc, 0.5, 0.4, new Vector3d());
    }

    /**
     * Attempts to consume a held totem when this damage would leave health
     * below one. {@code VOID}, {@code COMMAND}, and {@code API} damage bypass
     * totem use. The offhand takes priority over the main hand, and an accepted
     * path clears the whole selected slot after triggering totem feedback.
     *
     * <p>
     * The handler attempts extinguishing, effect removal, one health, and
     * regeneration, fire-resistance, and absorption effects before consumption.
     * Those delegated health and effect operations retain their own cancellable
     * events, so this method's {@code true} result describes a consumed totem
     * path rather than guaranteed successful replacement of every health or
     * effect value.
     */
    private boolean tryConsumeTotem(DamageContainer damage) {
        if (damage.getDamageType() == DamageType.VOID ||
            damage.getDamageType() == DamageType.COMMAND ||
            damage.getDamageType() == DamageType.API) {
            return false;
        }

        if (this.health - damage.getFinalDamage() >= 1f) {
            return false;
        }

        OffhandContainer offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);
        InventoryContainer inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);

        var offhandItem = offhand.getOffhand();
        var hasOffhandTotem = isTotem(offhandItem);
        var handItem = inventory.getItemInHand();
        var hasHandTotem = isTotem(handItem);

        if (!hasOffhandTotem && !hasHandTotem) {
            return false;
        }

        thisPlayer.extinguish();
        thisPlayer.removeAllEffects();
        thisPlayer.setHealth(1f);

        // Totem effect durations are entity ticks: regeneration lasts forty-five
        // seconds, fire resistance forty seconds, and absorption five seconds.
        thisPlayer.addEffect(new EffectInstance(EffectTypes.REGENERATION, 1, 900, false, true));
        thisPlayer.addEffect(new EffectInstance(EffectTypes.FIRE_RESISTANCE, 0, 800, false, true));
        thisPlayer.addEffect(new EffectInstance(EffectTypes.ABSORPTION, 1, 100, false, true));

        thisPlayer.applyAction(SimpleEntityAction.TOTEM_USE);
        var location = thisPlayer.getLocation();
        var dimension = location.dimension();
        if (dimension != null) {
            dimension.addSound(location, SimpleSound.TOTEM);
        }

        if (hasOffhandTotem) {
            offhand.clearSlot(OffhandContainer.OFFHAND_SLOT);
        } else {
            inventory.clearItemInHand();
        }

        return true;
    }

    /**
     * Returns whether the non-{@code null} stack has the totem-of-undying item type.
     */
    private boolean isTotem(ItemStack itemStack) {
        var type = itemStack.getItemType();
        return type == ItemTypes.TOTEM_OF_UNDYING;
    }

    /**
     * Updates breathing only for modes other than creative and spectator. Those
     * two modes leave the inherited air-supply state unchanged through this
     * method; other modes delegate to the living component's breathing and
     * drowning rules.
     */
    @Override
    protected void tickBreathe() {
        if (thisPlayer.getGameMode() == GameMode.CREATIVE || thisPlayer.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        super.tickBreathe();
    }

    /**
     * Sends an effect change to entity viewers and the player's controller. A
     * {@code null} new effect represents removal, and a {@code null} old effect
     * represents addition; fake players receive only the inherited viewer
     * notifications.
     */
    @Override
    protected void sendEffects(EffectInstance newEffect, EffectInstance oldEffect) {
        super.sendEffects(newEffect, oldEffect);
        if (thisPlayer.isActualPlayer()) {
            thisPlayer.getController().viewEntityEffectChange(thisPlayer, newEffect, oldEffect);
        }
    }

    /**
     * Stores absorption through the living component and informs an attached
     * controller. Fake players still update stored absorption, while actual
     * players also receive the supplied value through the controller's
     * absorption message.
     */
    @Override
    public void setAbsorption(float absorption) {
        super.setAbsorption(absorption);
        if (thisPlayer.isActualPlayer()) {
            thisPlayer.getController().sendAbsorption(absorption);
        }
    }

    /**
     * Applies the inherited health update and sends the resulting stored
     * health. Actual players receive the current health and maximum even when
     * the inherited health-change event cancels the requested value; fake
     * players receive no controller message.
     */
    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        if (thisPlayer.isActualPlayer()) {
            thisPlayer.getController().sendHealth(this.health, this.maxHealth);
        }
    }

    /**
     * Updates maximum health through the living component and reports the
     * resulting values. The inherited update also caps current health to the
     * new maximum, and only an actual player receives the controller's health
     * message afterward.
     */
    @Override
    public void setMaxHealth(float maxHealth) {
        super.setMaxHealth(maxHealth);
        if (thisPlayer.isActualPlayer()) {
            thisPlayer.getController().sendHealth(this.health, this.maxHealth);
        }
    }

    /**
     * Performs shared death handling, wakes a sleeping player, and reports
     * death wording. The last damage supplies its category and source when
     * available; otherwise {@code DamageType.API} supplies generic wording. The
     * world rule controls channel broadcast, while an attached controller
     * receives death information independently.
     */
    @Override
    protected void onDie() {
        super.onDie();

        if (thisPlayer.isSleeping()) {
            thisPlayer.wake();
        }

        var deathInfo = lastDamage != null ?
                lastDamage.getDamageType().getDeathInfo(thisPlayer, lastDamage.getAttacker()) :
                DamageType.API.getDeathInfo(thisPlayer, null);

        if (thisPlayer.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.SHOW_DEATH_MESSAGES)) {
            Server.getInstance().getMessageChannel().broadcastTranslatable(deathInfo.left(), (Object[]) deathInfo.right());
        }

        if (thisPlayer.isActualPlayer()) {
            thisPlayer.getController().sendDeathInfo(deathInfo);
        }
    }
}
