package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityEnderCrystalBaseComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.interfaces.EntityEnderCrystal;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityEnderCrystalImpl} implements the end crystal API through
 * supplied components. {@code ComponentClass} constructs and injects those
 * components before this constructor returns, connecting the entity's delegated
 * operations to the same component manager and initialization data.
 *
 * <p>
 * {@code enderCrystalBaseComponent} and {@code livingComponent} retain the
 * selected component instances after construction. Delegated operations use
 * those instances' shared mutable state; the component fields do not contain
 * independent copies of their behavior or gameplay data.
 */
public class EntityEnderCrystalImpl extends EntityImpl implements EntityEnderCrystal {

    /**
     * Crystal base-visibility delegate.
     */
    @Delegate
    private EntityEnderCrystalBaseComponent enderCrystalBaseComponent;
    /**
     * Health and living-state delegate.
     */
    @Delegate
    private EntityLivingComponent livingComponent;

    /**
     * Creates a component-backed end crystal from the supplied initialization
     * data. Superclass wiring constructs providers and resolves each delegate
     * field before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntityEnderCrystalImpl(EntityInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
