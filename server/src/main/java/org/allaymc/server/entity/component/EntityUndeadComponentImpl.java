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
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * {@code EntityUndeadComponentImpl} applies sunlight combustion rules to
 * undead entities during ordinary ticks. Sky visibility and brightness must
 * permit burning, while water, current fire, fire resistance, disabled
 * sunlight ignition, or a non-air helmet suppress the ignition attempt.
 */
public class EntityUndeadComponentImpl implements EntityUndeadComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_undead_component");

    /**
     * {@code SUNLIGHT_IGNITE_TICKS} gives accepted sunlight ignition twenty
     * fire ticks, allowing continued exposure to reapply fire after it
     * expires.
     */
    protected static final int SUNLIGHT_IGNITE_TICKS = 20;
    /**
     * {@code MIN_BURN_SKY_LIGHT} requires internal sky brightness of at least
     * twelve before visible-sky exposure can trigger undead sunlight
     * combustion.
     */
    protected static final int MIN_BURN_SKY_LIGHT = 12;

    /**
     * Injected entity checked for sunlight exposure and equipment.
     */
    @ComponentObject
    protected Entity thisEntity;

    /**
     * Living component supplying fire state and status effects.
     */
    @Dependency
    protected EntityLivingComponent livingComponent;

    /**
     * Container holder queried for protective helmet equipment.
     */
    @Dependency
    protected EntityContainerHolderComponent containerHolderComponent;

    /**
     * Ignites an eligible undead entity after checking its current state, sky
     * exposure, effects, and helmet. Existing fire and water suppress another
     * ignition, and the brightness threshold is checked only in dimensions
     * with skylight.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (!thisEntity.isAlive() || thisEntity.isTouchingWater() || livingComponent.isOnFire() || !ignitedBySunlight()) {
            return;
        }

        var dimension = thisEntity.getDimension();
        if (!dimension.getDimensionType().hasSkyLight()) {
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

    /**
     * Returns whether an armor container holds a helmet other than the air
     * singleton.
     */
    protected boolean hasHelmetProtection() {
        return containerHolderComponent.hasContainer(ContainerTypes.ARMOR) &&
               containerHolderComponent.getContainer(ContainerTypes.ARMOR).getHelmet() != ItemAirStack.AIR_STACK;
    }
}
