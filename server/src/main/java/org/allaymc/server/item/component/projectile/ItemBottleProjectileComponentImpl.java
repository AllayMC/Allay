package org.allaymc.server.item.component.projectile;

import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.entity.data.EntityId;
import org.joml.Vector3dc;

import java.util.function.Supplier;

import static java.lang.Math.*;

/**
 * {@code ItemBottleProjectileComponentImpl} launches bottle-like projectiles
 * with an upward pitch adjustment. The inherited launcher still creates the
 * entity and handles cancellation and item consumption; this specialization
 * changes only the motion direction through a bounded, ellipse-shaped pitch
 * offset.
 *
 * @author daoge_cmd
 */
public class ItemBottleProjectileComponentImpl extends ItemProjectileComponentImpl {
    /**
     * Creates a bottle launcher with deferred entity-type resolution and the
     * supplied throw force. The inherited component retains
     * {@code projectileEntityTypeSupplier}; the supplier must resolve a usable
     * projectile type when a launch is attempted.
     */
    public ItemBottleProjectileComponentImpl(Supplier<EntityType<?>> projectileEntityTypeSupplier, double throwForce) {
        super(projectileEntityTypeSupplier, throwForce);
    }

    /**
     * Creates a bottle launcher that resolves its entity identifier on demand.
     * {@code projectileEntityId} must be non-{@code null}; {@code throwForce} is
     * passed through unchanged as the initial motion multiplier for launches.
     */
    public ItemBottleProjectileComponentImpl(EntityId projectileEntityId, double throwForce) {
        this(projectileEntityId::getEntityType, throwForce);
    }

    /**
     * Returns bottle motion using an upward-adjusted copy of the supplied
     * orientation. {@code location} itself is unchanged, and {@code throwForce}
     * scales the new direction vector after the pitch adjustment is applied.
     */
    @Override
    protected Vector3dc computeMotion(Location3dc location, double throwForce) {
        return MathUtils.getDirectionVector(addBottleOffset(location)).mul(throwForce);
    }

    /**
     * Returns a location copy with the bottle's upward pitch adjustment.
     * The ellipse-shaped offset is largest near horizontal aim and decreases
     * toward vertical aim; both the input used for calculation and the resulting
     * pitch are bounded to {@code -89.9} through {@code 89.9} degrees for a
     * finite input pitch.
     */
    protected Location3dc addBottleOffset(Location3dc location) {
        var pitch = location.pitch();
        // Clamp pitch before the ellipse calculation so its square root has a
        // nonnegative radicand; clamp again afterward to bound the adjusted direction.
        pitch = max(min(pitch, 89.9), -89.9);
        pitch -= sqrt(pow(89.9, 2) - pow(pitch, 2)) * (26.5 / 89.9);
        pitch = max(min(pitch, 89.9), -89.9);
        var newLocation = new Location3d(location);
        newLocation.setPitch(pitch);
        return newLocation;
    }
}
