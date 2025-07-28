package org.allaymc.server.item.component.projectile;

import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.joml.Vector3dc;

import static java.lang.Math.*;

/**
 * @author daoge_cmd
 */
public class ItemBottleProjectileComponentImpl extends ItemProjectileComponentImpl {
    public ItemBottleProjectileComponentImpl(EntityId projectileEntityId, double throwForce) {
        super(projectileEntityId, throwForce);
    }

    @Override
    protected Vector3dc computeMotion(Location3dc location) {
        return MathUtils.getDirectionVector(addBottleOffset(location)).mul(getThrowForce());
    }

    /**
     * This method adds an upwards offset pitch. In vanilla, items such as Splash Potions,
     * Lingering Potions, and Bottle o' Enchanting are thrown at a higher angle than where
     * the player is looking at.
     * <p>
     * The added offset is an ellipse-like shape based on what the input pitch is.
     */
    protected Location3dc addBottleOffset(Location3dc location) {
        var pitch = location.pitch();
        pitch = max(min(pitch, 89.9), -89.9);
        pitch -= sqrt(pow(89.9, 2) - pow(pitch, 2)) * (26.5 / 89.9);
        pitch = max(min(pitch, 89.9), -89.9);
        var newLocation = new Location3d(location);
        newLocation.setPitch(pitch);
        return newLocation;
    }
}
