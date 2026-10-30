package org.allaymc.server.item.component.projectile;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.ProjectileLaunchEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.position.Position3d;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.item.component.event.CItemRightClickInAirEvent;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.function.Supplier;

/**
 * {@code ItemProjectileComponentImpl} launches projectile entities from items
 * and consumes one item after a successful launch outside creative mode. A
 * retained supplier resolves the entity type when needed, and a cancellable
 * launch event can change the throw force before motion and world insertion.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemProjectileComponentImpl implements ItemProjectileComponent {
    /**
     * {@code IDENTIFIER} identifies the projectile-launching component during
     * component-provider registration and lookup. Its value distinguishes this
     * behavior from the stack's other item components.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_projectile_component");

    /**
     * {@code projectileEntityType} resolves the entity type for each lookup.
     * The constructors retain this supplier so entity registration can finish
     * before a launch needs the type.
     */
    protected final Supplier<EntityType<?>> projectileEntityType;
    /**
     * Initial projectile motion multiplier before launch-event changes.
     */
    protected final double throwForce;

    /**
     * {@code thisItemStack} is the owning stack injected during component
     * initialization. Successful launches reduce its count unless the shooter is
     * a player in creative mode.
     */
    @ComponentObject
    protected ItemStack thisItemStack;

    /**
     * Creates a projectile component with deferred entity-type resolution and the
     * supplied motion multiplier. The component retains
     * {@code projectileEntityTypeSupplier}; each launch needs it to resolve a
     * type that creates an {@code EntityProjectile}.
     */
    public ItemProjectileComponentImpl(Supplier<EntityType<?>> projectileEntityTypeSupplier, double throwForce) {
        this.projectileEntityType = projectileEntityTypeSupplier;
        this.throwForce = throwForce;
    }

    /**
     * Creates a projectile component that resolves the supplied entity identifier
     * on demand. {@code projectileEntityId} must be non-{@code null}; the retained
     * lookup uses its current registered entity type when a launch is requested.
     */
    public ItemProjectileComponentImpl(EntityId projectileEntityId, double throwForce) {
        this(projectileEntityId::getEntityType, throwForce);
    }

    /**
     * Returns the entity type obtained from the retained supplier.
     * Each call resolves the type again and returns the result directly, including
     * {@code null} when the supplier currently provides no registered type.
     */
    @Override
    public EntityType<?> getProjectileEntityType() {
        return this.projectileEntityType.get();
    }

    /**
     * Returns the configured initial motion multiplier for projectile launches.
     */
    @Override
    public double getThrowForce() {
        return this.throwForce;
    }

    /**
     * Attempts to launch an item projectile from the shooter's eye position.
     * An incompatible created entity or cancelled {@code ProjectileLaunchEvent}
     * returns {@code false} before world insertion or item consumption. Successful
     * launches use the event's throw force, add the entity, play the launch sound,
     * and reduce this stack by one unless the shooter is a creative-mode player.
     */
    @Override
    public boolean shoot(Entity shooter) {
        var location = shooter.getLocation();
        var shootPos = new Vector3d(location.x(), location.y() + shooter.getEyeHeight(), location.z());
        var projectile = createProjectile(shooter, shootPos);
        if (projectile == null) {
            return false;
        }

        var event = new ProjectileLaunchEvent(projectile, shooter, getThrowForce());
        if (!event.call()) {
            return false;
        }

        projectile.setMotion(computeMotion(shooter.getLocation(), event.getThrowForce()));
        shooter.getDimension().getEntityManager().addEntity(projectile);
        addShootSound(new Position3d(shootPos, location.dimension()));
        if (!(shooter instanceof EntityPlayer player) || player.getGameMode() != GameMode.CREATIVE) {
            thisItemStack.reduceCount(1);
        }

        return true;
    }

    /**
     * Creates a projectile at {@code shootPos} and assigns its shooter.
     * The resolved type must be available; a created entity that does not implement
     * {@code EntityProjectile} is logged and produces {@code null} without being
     * added to the dimension.
     */
    protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
        var projectileEntityType = getProjectileEntityType();
        var entity = projectileEntityType.createEntity(EntityInitInfo.builder().dimension(shooter.getDimension()).pos(shootPos).build());
        // A configured type can create an incompatible entity. Report that mismatch
        // and decline the launch rather than casting it or consuming the item.
        if (!(entity instanceof EntityProjectile projectile)) {
            log.error("Entity {} is not a projectile entity", projectileEntityType.getIdentifier());
            return null;
        }

        projectile.setShooter(shooter);
        return projectile;
    }

    /**
     * Returns a new directional motion vector scaled by the supplied throw force.
     */
    protected Vector3dc computeMotion(Location3dc location, double throwForce) {
        return MathUtils.getDirectionVector(location).mul(throwForce);
    }

    /**
     * Attempts a projectile launch for the player who right-clicked in air.
     * The handler delegates cancellation and consumption to {@code shoot} and
     * does not expose its success result through this component event.
     */
    @EventHandler
    protected void onClickItemInAir(CItemRightClickInAirEvent event) {
        shoot(event.getPlayer());
    }

    /**
     * Plays the standard projectile launch sound at the supplied world position.
     */
    protected void addShootSound(Position3d pos) {
        pos.dimension().addSound(pos.x, pos.y, pos.z, SimpleSound.BOW_SHOOT);
    }
}
