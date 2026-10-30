package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityArmorStandBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityArmorStandImpl} implements the armor stand API through supplied
 * components. {@code ComponentClass} constructs and injects those components
 * before this constructor returns, connecting the entity's delegated operations
 * to the same component manager and initialization data.
 *
 * <p>
 * {@code armorStandBaseComponent}, {@code containerHolderComponent},
 * {@code livingComponent}, and {@code physicsComponent} retain the selected
 * component instances after construction. Delegated operations use those
 * instances' shared mutable state; the component fields do not contain
 * independent copies of their behavior or gameplay data.
 */
public class EntityArmorStandImpl extends EntityImpl implements EntityArmorStand {

    /**
     * Armor-stand pose selection delegate.
     */
    @Delegate
    private EntityArmorStandBaseComponent armorStandBaseComponent;

    /**
     * Equipment and inventory container delegate.
     */
    @Getter
    @Delegate
    private EntityContainerHolderComponent containerHolderComponent;

    /**
     * Health and living-state delegate.
     */
    @Delegate
    private EntityLivingComponent livingComponent;

    /**
     * Movement and physics behavior delegate.
     */
    @Delegate
    private EntityPhysicsComponent physicsComponent;

    /**
     * Creates a component-backed armor stand from the supplied initialization
     * data. Superclass wiring constructs providers and resolves each delegate
     * field before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntityArmorStandImpl(EntityInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
