package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityVillagerV2Impl} implements the newer villager API through
 * supplied components. {@code ComponentClass} constructs and injects those
 * components before this constructor returns, connecting the entity's delegated
 * operations to the same component manager and initialization data.
 *
 * <p>
 * {@code livingComponent} and {@code physicsComponent} retain the selected
 * component instances after construction. Delegated operations use those
 * instances' shared mutable state; the component fields do not contain
 * independent copies of their behavior or gameplay data.
 */
public class EntityVillagerV2Impl extends EntityImpl implements EntityVillagerV2 {

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
     * Creates a component-backed newer villager from the supplied
     * initialization data. Superclass wiring constructs providers and resolves
     * each delegate field before returning; the supplied providers must yield
     * exactly one assignable component for every required delegate type.
     */
    public EntityVillagerV2Impl(EntityInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
