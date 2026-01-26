package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.InventoryContainer;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityLivingComponent;
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
import org.allaymc.api.message.I18n;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityLivingComponentImpl;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;
import org.cloudburstmc.protocol.bedrock.packet.DeathInfoPacket;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAttributesPacket;
import org.joml.Vector3d;

import java.util.Collections;

/**
 * @author daoge_cmd
 */
public class EntityPlayerLivingComponentImpl extends EntityLivingComponentImpl {
    @ComponentObject
    protected EntityPlayer thisPlayer;

    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        var gameMode = thisPlayer.getGameMode();
        if (gameMode == GameMode.SPECTATOR || gameMode == GameMode.CREATIVE) {
            return damage.getDamageType() == DamageType.API;
        }

        return super.canBeAttacked(damage);
    }

    @Override
    public boolean hasFallDamage() {
        var gameMode = thisPlayer.getGameMode();
        return gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE;
    }

    @Override
    public boolean hasFireDamage() {
        // Player in creative/spectator game mode can't be damaged by fire
        return super.hasFireDamage() && (thisPlayer.getGameMode() == GameMode.SURVIVAL || thisPlayer.getGameMode() == GameMode.ADVENTURE);
    }

    @Override
    public boolean hasVoidDamage() {
        return thisPlayer.getGameMode() != GameMode.CREATIVE &&
               thisPlayer.getGameMode() != GameMode.SPECTATOR;
    }

    @Override
    protected void applyDamage(DamageContainer damage) {
        if (tryConsumeTotem(damage)) {
            return;
        }
        // Process shield blocking before applying damage
        processShieldBlocking(damage);
        super.applyDamage(damage);
    }

    /**
     * Finds the active shield in the player's hands.
     * <p>
     * Shield can only be active when player is sneaking and holding a shield
     * in either main hand or off hand. Shield cooldown is also checked.
     *
     * @return the active shield stack, or {@code null} if not blocking
     */
    private ItemShieldStack findActiveShield() {
        // Player must be sneaking to block with shield
        if (!thisPlayer.isSneaking()) {
            return null;
        }

        // Check if shield is on cooldown
        if (!thisPlayer.isCooldownEnd(ItemShieldBaseComponent.SHIELD_COOLDOWN_CATEGORY)) {
            return null;
        }

        InventoryContainer inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);
        OffhandContainer offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);

        var handItem = inventory.getItemInHand();
        var offhandItem = offhand.getOffhand();

        // Check main hand first, then off hand
        if (handItem instanceof ItemShieldStack shield) {
            return shield;
        }
        if (offhandItem instanceof ItemShieldStack shield) {
            return shield;
        }

        return null;
    }

    /**
     * Processes shield blocking for the incoming damage.
     * <p>
     * If the player successfully blocks, this method will:
     * <ul>
     *   <li>Reduce damage to 0 (full block)</li>
     *   <li>Apply shield durability damage</li>
     *   <li>Check for axe attack and apply shield cooldown if needed</li>
     *   <li>Knockback the attacker</li>
     *   <li>Play shield block sound</li>
     *   <li>Fire EntityDamageBlockedEvent</li>
     * </ul>
     *
     * @param damage the incoming damage container
     */
    private void processShieldBlocking(DamageContainer damage) {
        var shield = findActiveShield();
        if (shield == null) {
            return;
        }

        // Check if blocking is possible (directional check)
        if (!shield.tryBlockDamage(thisPlayer, damage)) {
            return;
        }

        // Fire event - allows plugins to modify or cancel blocking
        var event = new EntityDamageBlockedEvent(thisPlayer, damage, shield);
        if (!event.call()) {
            return;
        }

        // Store original damage for durability calculation
        float originalDamage = damage.getSourceDamage();

        // Block the damage completely
        damage.setFinalDamage(0);
        damage.setHasKnockback(false);

        // Apply shield durability damage
        shield.applyBlockDurability(originalDamage);

        // Notify inventory change (durability update)
        notifyShieldDurabilityChange(shield);

        // Check if attacker is using axe - disable shield if so
        if (shield.shouldDisableShield(damage.getAttacker())) {
            thisPlayer.setCooldown(
                    ItemShieldBaseComponent.SHIELD_COOLDOWN_CATEGORY,
                    ItemShieldBaseComponent.SHIELD_DISABLE_COOLDOWN,
                    true
            );
        }

        // Knockback attacker (if enabled in event)
        if (event.isKnockbackAttacker()) {
            applyShieldKnockback(damage);
        }

        // Play sound and animation (if enabled in event)
        if (event.isPlayAnimation()) {
            var location = thisPlayer.getLocation();
            var dimension = location.dimension();
            if (dimension != null) {
                dimension.addSound(location, SimpleSound.SHIELD_BLOCK);
            }
        }
    }

    /**
     * Notifies the inventory system that a shield's durability has changed.
     *
     * @param shield the shield item that was damaged
     */
    private void notifyShieldDurabilityChange(ItemShieldStack shield) {
        InventoryContainer inventory = thisPlayer.getContainer(ContainerTypes.INVENTORY);
        OffhandContainer offhand = thisPlayer.getContainer(ContainerTypes.OFFHAND);

        // Check which slot the shield is in and notify
        if (inventory.getItemInHand() == shield) {
            inventory.notifySlotChange(inventory.getHandSlot());
        } else if (offhand.getOffhand() == shield) {
            offhand.notifySlotChange(OffhandContainer.OFFHAND_SLOT);
        }
    }

    /**
     * Applies knockback to the attacker when shield blocking is successful.
     *
     * @param damage the damage container containing attacker information
     */
    private void applyShieldKnockback(DamageContainer damage) {
        if (!(damage.getAttacker() instanceof Entity attacker)) {
            return;
        }

        // Only knockback entities with physics component
        if (!(attacker instanceof EntityPhysicsComponent physics)) {
            return;
        }

        var playerLoc = thisPlayer.getLocation();
        physics.knockback(playerLoc, 0.5, 0.4, new Vector3d());
    }

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

    private boolean isTotem(ItemStack itemStack) {
        var type = itemStack.getItemType();
        return type == ItemTypes.TOTEM_OF_UNDYING;
    }

    @Override
    protected void tickBreathe() {
        // Creative and spectator mode players don't need breath updates
        if (thisPlayer.getGameMode() == GameMode.CREATIVE || thisPlayer.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        super.tickBreathe();
    }

    @Override
    protected void sendEffects(EffectInstance newEffect, EffectInstance oldEffect) {
        super.sendEffects(newEffect, oldEffect);
        if (thisPlayer.isActualPlayer()) {
            thisPlayer.getController().viewEntityEffectChange(thisPlayer, newEffect, oldEffect);
        }
    }

    @Override
    public void setAbsorption(float absorption) {
        super.setAbsorption(absorption);
        if (thisPlayer.isActualPlayer()) {
            var maxAbsorption = 16.0f;
            sendAttribute(new AttributeData(
                    "minecraft:absorption", 0, maxAbsorption,
                    this.absorption, 0, maxAbsorption, 0,
                    Collections.emptyList()
            ));
        }
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        if (thisPlayer.isActualPlayer()) {
            sendHealth(this.health, this.maxHealth);
        }
    }

    @Override
    public void setMaxHealth(float maxHealth) {
        super.setMaxHealth(maxHealth);
        if (thisPlayer.isActualPlayer()) {
            sendHealth(this.health, this.maxHealth);
        }
    }

    protected void sendHealth(float health, float maxHealth) {
        var defaultMax = EntityLivingComponent.DEFAULT_MAX_HEALTH;
        sendAttribute(new AttributeData(
                "minecraft:health", 0, maxHealth,
                health, 0, defaultMax, defaultMax,
                Collections.emptyList()
        ));
    }

    protected void sendAttribute(AttributeData attributeData) {
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(thisPlayer.getRuntimeId());
        packet.getAttributes().add(attributeData);
        thisPlayer.getController().sendPacket(packet);
    }

    @Override
    protected void onDie() {
        super.onDie();

        var deathInfo = lastDamage != null ?
                lastDamage.getDamageType().getDeathInfo(thisPlayer, lastDamage.getAttacker()) :
                DamageType.API.getDeathInfo(thisPlayer, null);
        Server.getInstance().getMessageChannel().broadcastTranslatable(deathInfo.left(), (Object[]) deathInfo.right());

        if (thisPlayer.isActualPlayer()) {
            var controller = thisPlayer.getController();

            var packet1 = new DeathInfoPacket();
            packet1.setCauseAttackName(I18n.get().tr(controller.getLoginData().getLangCode(), deathInfo.left(), (Object[]) deathInfo.right()));
            controller.sendPacket(packet1);

            var packet2 = new RespawnPacket();
            packet2.setPosition(Vector3f.ZERO);
            packet2.setState(RespawnPacket.State.SERVER_SEARCHING);
            controller.sendPacket(packet2);
        }
    }
}
