package org.allaymc.server.entity.component;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityUndeadComponent;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * Applies sunlight combustion logic for undead entities.
 */
public class EntityUndeadComponentImpl implements EntityUndeadComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_undead_component");

    protected static final int SUNLIGHT_IGNITE_TICKS = 20;
    protected static final int MIN_BURN_SKY_LIGHT = 12;

    @ComponentObject
    protected Entity thisEntity;

    @Dependency
    protected EntityLivingComponent livingComponent;

    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (!thisEntity.isAlive() || livingComponent.isOnFire() || !ignitedBySunlight()) {
            return;
        }

        var dimension = thisEntity.getDimension();
        if (dimension.getDimensionInfo() != DimensionInfo.OVERWORLD) {
            return;
        }

        var location = thisEntity.getLocation();
        var x = (int) Math.floor(location.x());
        var y = (int) Math.floor(location.y());
        var z = (int) Math.floor(location.z());
        if (!dimension.canPosSeeSky(x, y, z) ||
            dimension.getLightEngine().getInternalSkyLight(x, y, z) < MIN_BURN_SKY_LIGHT ||
            thisEntity.isEyesInWater() ||
            livingComponent.hasEffect(EffectTypes.FIRE_RESISTANCE) ||
            hasHelmetProtection()) {
            return;
        }

        livingComponent.setOnFireTicks(SUNLIGHT_IGNITE_TICKS);
    }

    protected boolean hasHelmetProtection() {
        return containerHolderComponent.hasContainer(ContainerTypes.ARMOR) &&
               containerHolderComponent.getContainer(ContainerTypes.ARMOR).getHelmet() != ItemAirStack.AIR_STACK;
    }
}
