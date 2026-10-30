package org.allaymc.server.entity.impl;


import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityImpl} implements the common entity API through supplied
 * components. {@code ComponentClass} constructs and injects those components
 * before this constructor returns, connecting the entity's delegated operations
 * to the same component manager and initialization data.
 *
 * <p>
 * {@code baseComponent} retains the selected component instance after
 * construction. Delegated operations use those instances' shared mutable state;
 * the component fields do not contain independent copies of their behavior or
 * gameplay data.
 *
 * @author daoge_cmd
 */
public class EntityImpl extends ComponentClass implements Entity {

    /**
     * Common entity lifecycle delegate.
     */
    @Getter
    @Delegate
    private EntityBaseComponent baseComponent;

    /**
     * Creates a component-backed common entity from the supplied initialization
     * data. Superclass wiring constructs providers and resolves each delegate
     * field before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntityImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
