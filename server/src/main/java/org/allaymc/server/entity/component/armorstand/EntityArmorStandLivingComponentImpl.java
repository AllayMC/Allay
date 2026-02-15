package org.allaymc.server.entity.component.armorstand;

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
 * Living component implementation for armor stands.
 * Handles the two-hit kill mechanism and disables environmental damage.
 *
 * @author daoge_cmd
 */
public class EntityArmorStandLivingComponentImpl extends EntityLivingComponentImpl {

    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;

    @Override
    public boolean attack(DamageContainer damage, boolean ignoreCoolDown) {
        var currentTime = thisEntity.getDimension().getWorld().getTick();
        var interval = currentTime - this.lastDamageTime;
        this.lastDamage = damage;
        this.lastDamageTime = currentTime;

        // Creative mode instant break
        if (damage.getAttacker() instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_BREAK);
            thisEntity.remove();
            return true;
        }

        // Two-hit kill mechanism
        if (interval <= 5) {
            // Second hit - destroy armor stand
            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_BREAK);

            // Drop armor stand item
            var dropPos = thisEntity.getLocation().add(0, 1, 0, new Vector3d());
            var dimension = thisEntity.getDimension();
            dropItemInContainer(ContainerTypes.ARMOR, dimension, dropPos);
            dropItemInContainer(ContainerTypes.ARMOR_STAND_HAND, dimension, dropPos);
            dropItemInContainer(ContainerTypes.OFFHAND, dimension, dropPos);
            dimension.dropItem(ItemTypes.ARMOR_STAND.createItemStack(1), dropPos);

            thisEntity.remove();
            return true;
        } else {
            // First hit
            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_HIT);
            return false;
        }
    }

    protected void dropItemInContainer(ContainerType<?> type, Dimension dimension, Vector3d dropPos) {
        containerHolderComponent.getContainer(type)
                .getItemStacks()
                .stream()
                .filter(item -> item != ItemAirStack.AIR_STACK)
                .forEach(item -> dimension.dropItem(item, dropPos));
    }

    @Override
    public boolean hasDrowningDamage() {
        return false;
    }

    @Override
    protected boolean hasDeadTimer() {
        return false;
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (lastDamage == null) {
            return;
        }

        var interval = thisEntity.getWorld().getTick() - this.lastDamageTime;
        if (interval <= 5) {
            thisEntity.broadcastState();
        }
    }
}
