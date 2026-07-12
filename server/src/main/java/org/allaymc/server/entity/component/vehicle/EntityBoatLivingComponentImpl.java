package org.allaymc.server.entity.component.vehicle;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityLivingComponentImpl;

/**
 * Damage/break behavior for boats without applying mob death semantics.
 */
public class EntityBoatLivingComponentImpl extends EntityLivingComponentImpl {
    protected static final float BREAK_DAMAGE = 4;

    @ComponentObject
    protected EntityBoat boat;
    protected float accumulatedDamage;

    public EntityBoatLivingComponentImpl() {
        setMaxHealth(BREAK_DAMAGE);
    }

    @Override
    public boolean attack(DamageContainer damage, boolean ignoreCoolDown) {
        if (!thisEntity.isAlive() || !canBeAttacked(damage) ||
            !checkAndUpdateCoolDown(damage, ignoreCoolDown || isCreativeAttacker(damage))) {
            return false;
        }

        var event = new EntityDamageEvent(thisEntity, damage);
        if (!event.call()) {
            return false;
        }
        damage = event.getDamageContainer();
        this.lastDamage = damage;
        this.lastDamageTime = baseComponent.getTick();
        thisEntity.applyAction(SimpleEntityAction.HURT);

        if (isCreativeAttacker(damage)) {
            breakBoat(false);
            return true;
        }

        accumulatedDamage += Math.max(0, damage.getFinalDamage());
        thisEntity.broadcastState();
        if (accumulatedDamage >= BREAK_DAMAGE) {
            breakBoat(true);
        }
        return true;
    }

    protected boolean isCreativeAttacker(DamageContainer damage) {
        return damage.getAttacker() instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE;
    }

    protected void breakBoat(boolean dropItem) {
        boat.ejectPassengers();
        if (dropItem && thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_ENTITY_DROPS)) {
            thisEntity.getDimension().dropItem(BoatItemHelper.createItem(boat.getBoatVariant()), thisEntity.getLocation());
        }
        thisEntity.remove();
    }

    public float getAccumulatedDamage() {
        return accumulatedDamage;
    }

    @Override
    public boolean hasFallDamage() {
        return false;
    }

    @Override
    public boolean hasDrowningDamage() {
        return false;
    }

    @Override
    public boolean canBreathe() {
        return true;
    }

    @Override
    protected boolean hasDeadTimer() {
        return false;
    }
}
