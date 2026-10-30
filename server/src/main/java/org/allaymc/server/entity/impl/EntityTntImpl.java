package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityTntBaseComponent;
import org.allaymc.api.entity.interfaces.EntityTnt;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityTntImpl} represents a primed TNT entity composed from supplied
 * component implementations. {@link EntityImpl} supplies the common entity
 * behavior, while the injected delegates expose their component contracts
 * through the same entity object. The constructor selects those
 * implementations from the supplied providers.
 */
public class EntityTntImpl extends EntityImpl implements EntityTnt {

    // tntBaseComponent, and physicsComponent retain the component instances
    // selected during superclass initialization. Injection populates all these
    // delegates before completion hooks run, so delegated calls use the initialized
    // components.
    /**
     * TNT fuse countdown delegate.
     */
    @Delegate
    private EntityTntBaseComponent tntBaseComponent;
    /**
     * Entity motion and collision delegate.
     */
    @Delegate
    private EntityPhysicsComponent physicsComponent;

    /**
     * Creates this entity using {@code initInfo} and the supplied component
     * providers. Each delegated component type must resolve to exactly one
     * component instance; superclass initialization wires dependencies and
     * runs completion hooks before this constructor returns.
     */
    public EntityTntImpl(EntityInitInfo initInfo,
                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
