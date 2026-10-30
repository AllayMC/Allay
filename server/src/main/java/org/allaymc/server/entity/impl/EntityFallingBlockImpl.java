package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityFallingBlockImpl} represents a falling block composed from
 * supplied component implementations. {@link EntityImpl} supplies the common
 * entity behavior, while the injected delegates expose their component
 * contracts through the same entity object. The constructor selects those
 * implementations from the supplied providers.
 */
public class EntityFallingBlockImpl extends EntityImpl implements EntityFallingBlock {

    // fallingBlockBaseComponent, and physicsComponent retain the component
    // instances selected during superclass initialization. Injection populates all
    // these delegates before completion hooks run, so delegated calls use the
    // initialized components.
    /**
     * Falling block state delegate.
     */
    @Delegate
    private EntityFallingBlockBaseComponent fallingBlockBaseComponent;
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
    public EntityFallingBlockImpl(EntityInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
