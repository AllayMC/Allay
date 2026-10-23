package org.allaymc.server.entity.component.item;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityLivingComponentImpl;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.joml.Vector3d;

/**
 * {@code EntityArmorStandLivingComponentImpl} resolves armor stand attacks
 * through a short hit window instead of ordinary health reduction. Each attack
 * records its damage and tick before choosing the outcome. A creative player
 * removes the stand immediately, while another attack within five ticks
 * removes it and drops the stand item and equipment.
 *
 * <p>
 * The initial timestamp participates in the same interval comparison as later
 * hits. The override does not consult {@code ignoreCoolDown} or invoke the
 * parent attack pipeline, so its return value reports the break decision
 * rather than accepted health damage. Tick handling broadcasts state during
 * the recent-hit interval, and the normal death timer is disabled.
 *
 * @author daoge_cmd
 */
public class EntityArmorStandLivingComponentImpl extends EntityLivingComponentImpl {

    /**
     * Injected equipment container provider for break drops.
     */
    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;

    /**
     * Processes an attack using attacker mode and the interval since the
     * previously recorded hit. The method records the incoming damage and
     * current tick even when the stand survives, and it does not use {@code
     * ignoreCoolDown}. Creative attackers remove the stand without the
     * equipment-drop branch.
     *
     * <p>
     * For other attackers, an interval of at most five ticks triggers
     * equipment and stand drops followed by removal and a {@code true} result.
     * A larger interval only plays the hit sound and returns {@code false}.
     * The initial inherited damage timestamp also affects this comparison
     * before a prior attack exists.
     */
    @Override
    public boolean attack(DamageContainer damage, boolean ignoreCoolDown) {
        var currentTime = baseComponent.getTick();
        var interval = currentTime - this.lastDamageTime;
        this.lastDamage = damage;
        this.lastDamageTime = currentTime;

        if (damage.getAttacker() instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_BREAK);
            thisEntity.remove();
            return true;
        }

        // The five-tick window lets a rapid follow-up hit break the stand after its
        // hit reaction.
        if (interval <= 5) {
            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_BREAK);

            var dropPos = thisEntity.getLocation().add(0, 1, 0, new Vector3d());
            var dimension = thisEntity.getDimension();
            dropItemInContainer(ContainerTypes.ARMOR, dimension, dropPos);
            dropItemInContainer(ContainerTypes.ENTITY_HAND, dimension, dropPos);
            dropItemInContainer(ContainerTypes.OFFHAND, dimension, dropPos);
            dimension.dropItem(ItemTypes.ARMOR_STAND.createItemStack(1), dropPos);

            thisEntity.remove();
            return true;
        } else {
            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_HIT);
            return false;
        }
    }

    /**
     * Drops every non-air stack retained by the selected equipment container
     * at {@code dropPos}. The helper does not clear the container, so callers
     * pair it with entity removal rather than repeat it for the same live
     * stand.
     */
    protected void dropItemInContainer(ContainerType<?> type, Dimension dimension, Vector3d dropPos) {
        containerHolderComponent.getContainer(type)
                .getItemStacks()
                .stream()
                .filter(item -> item != ItemAirStack.AIR_STACK)
                .forEach(item -> dimension.dropItem(item, dropPos));
    }

    /**
     * Returns {@code false} to disable drowning damage for this armor stand.
     */
    @Override
    public boolean hasDrowningDamage() {
        return false;
    }

    /**
     * Returns {@code false} because armor stand breaks request removal
     * directly.
     */
    @Override
    protected boolean hasDeadTimer() {
        return false;
    }

    /**
     * Publishes armor stand state while the last recorded attack is at most
     * five ticks old. A stand without recorded damage skips publication, and
     * the handler does not extend the interval.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (lastDamage == null) {
            return;
        }

        var interval = baseComponent.getTick() - this.lastDamageTime;
        // Limit repeated hit-state publication to the same short window used by
        // attack handling.
        if (interval <= 5) {
            thisEntity.broadcastState();
        }
    }
}
