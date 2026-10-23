package org.allaymc.server.item.component.projectile;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.property.enums.ClimateVariant;
import org.allaymc.api.entity.property.type.EntityPropertyTypes;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.joml.Vector3d;

import java.util.function.Supplier;

/**
 * {@code ItemEggProjectileComponentImpl} carries an egg item's climate variant
 * onto the projectile it launches. The egg collision handler later reads that
 * property when spawning chickens, so blue and brown egg items retain their
 * variant through the projectile stage.
 *
 * @author daoge_cmd
 */
public class ItemEggProjectileComponentImpl extends ItemProjectileComponentImpl {

    /**
     * {@code climateVariant} is the property value assigned to each created egg
     * projectile. The egg collision handler copies that value to any chickens
     * hatched from the projectile.
     */
    protected final ClimateVariant climateVariant;

    /**
     * Creates a launcher for the standard egg entity with the supplied climate
     * variant. The variant is retained for projectile creation and later determines
     * the variant of chickens hatched by that projectile.
     */
    public ItemEggProjectileComponentImpl(ClimateVariant climateVariant) {
        this(() -> EntityTypes.EGG, climateVariant);
    }

    /**
     * Creates an egg launcher with a deferred projectile type and retained climate
     * variant. The inherited component uses an initial throw force of {@code 1.5};
     * created projectiles receive {@code climateVariant} before launch processing continues.
     */
    protected ItemEggProjectileComponentImpl(Supplier<EntityType<?>> projectileEntityTypeSupplier, ClimateVariant climateVariant) {
        // Egg launches use this initial projectile motion multiplier.
        super(projectileEntityTypeSupplier, 1.5);
        this.climateVariant = climateVariant;
    }

    /**
     * Creates the projectile through the inherited factory and assigns its climate
     * variant before launch events run. A {@code null} result from that factory
     * remains {@code null}, so an incompatible type still declines the launch.
     */
    @Override
    protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
        var projectile = super.createProjectile(shooter, shootPos);
        if (projectile != null) {
            projectile.setPropertyValue(EntityPropertyTypes.CLIMATE_VARIANT, climateVariant);
        }
        return projectile;
    }
}
