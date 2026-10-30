package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.component.EntityFireworksRocketPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityFireworksRocketImpl} represents a firework rocket composed from
 * supplied component implementations. {@link EntityImpl} supplies the common
 * entity behavior, while the injected delegates expose their component
 * contracts through the same entity object. The constructor selects those
 * implementations from the supplied providers.
 */
public class EntityFireworksRocketImpl extends EntityImpl implements EntityFireworksRocket {

    // fireworksRocketBaseComponent, and physicsComponent retain the component
    // instances selected during superclass initialization. Injection populates all
    // these delegates before completion hooks run, so delegated calls use the
    // initialized components.
    /**
     * Rocket lifetime and explosion delegate.
     */
    @Delegate
    private EntityFireworksRocketBaseComponent fireworksRocketBaseComponent;
    /**
     * Entity motion and collision delegate.
     */
    @Delegate
    private EntityFireworksRocketPhysicsComponent physicsComponent;

    /**
     * Creates this entity using {@code initInfo} and the supplied component
     * providers. Each delegated component type must resolve to exactly one
     * component instance; superclass initialization wires dependencies and
     * runs completion hooks before this constructor returns.
     */
    public EntityFireworksRocketImpl(EntityInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
