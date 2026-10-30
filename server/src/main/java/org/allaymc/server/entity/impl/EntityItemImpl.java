package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityItemImpl} represents a dropped item composed from supplied
 * component implementations. {@link EntityImpl} supplies the common entity
 * behavior, while the injected delegates expose their component contracts
 * through the same entity object. The constructor selects those
 * implementations from the supplied providers.
 */
public class EntityItemImpl extends EntityImpl implements EntityItem {

    // itemBaseComponent, livingComponent, physicsComponent, and ageComponent retain
    // the component instances selected during superclass initialization. Injection
    // populates all these delegates before completion hooks run, so delegated calls
    // use the initialized components.
    /**
     * Dropped item stack delegate.
     */
    @Delegate
    private EntityItemBaseComponent itemBaseComponent;
    /**
     * Entity health and damage delegate.
     */
    @Delegate
    private EntityLivingComponent livingComponent;
    /**
     * Entity motion and collision delegate.
     */
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    /**
     * Entity lifetime tick delegate.
     */
    @Delegate
    private EntityAgeComponent ageComponent;

    /**
     * Creates this entity using {@code initInfo} and the supplied component
     * providers. Each delegated component type must resolve to exactly one
     * component instance; superclass initialization wires dependencies and
     * runs completion hooks before this constructor returns.
     */
    public EntityItemImpl(EntityInitInfo initInfo,
                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
