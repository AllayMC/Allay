package org.allaymc.server.item.component.projectile;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.property.enums.ClimateVariant;
import org.allaymc.api.entity.property.type.EntityPropertyTypes;
import org.allaymc.server.entity.data.EntityId;
import org.joml.Vector3d;

/**
 * Projectile component for climate-variant eggs (blue egg, brown egg).
 * Sets the climate variant on the spawned egg entity so that
 * hatched chickens inherit the correct variant.
 *
 * @author daoge_cmd
 */
public class ItemEggProjectileComponentImpl extends ItemProjectileComponentImpl {

    protected final ClimateVariant climateVariant;

    public ItemEggProjectileComponentImpl(ClimateVariant climateVariant) {
        super(EntityId.EGG, 1.5);
        this.climateVariant = climateVariant;
    }

    @Override
    protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
        var projectile = super.createProjectile(shooter, shootPos);
        if (projectile != null) {
            projectile.setPropertyValue(EntityPropertyTypes.CLIMATE_VARIANT, climateVariant);
        }
        return projectile;
    }
}
