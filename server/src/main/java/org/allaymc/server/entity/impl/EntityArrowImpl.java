package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityArrowImpl} represents an arrow composed from supplied component
 * implementations. {@link EntityImpl} supplies the common entity behavior,
 * while the injected delegates expose their component contracts through the
 * same entity object. The constructor selects those implementations from the
 * supplied providers.
 */
public class EntityArrowImpl extends EntityImpl implements EntityArrow {

    // arrowBaseComponent, potionComponent, ageComponent, projectileComponent,
    // physicsComponent, and livingComponent retain the component instances selected
    // during superclass initialization. Injection populates all these delegates
    // before completion hooks run, so delegated calls use the initialized
    // components.
    /**
     * Arrow identity and embedded-state delegate.
     */
    @Delegate
    private EntityArrowBaseComponent arrowBaseComponent;
    /**
     * Carried potion type delegate.
     */
    @Delegate
    private EntityPotionComponent potionComponent;
    /**
     * Entity lifetime tick delegate.
     */
    @Delegate
    private EntityAgeComponent ageComponent;
    /**
     * Projectile shooter reference delegate.
     */
    @Delegate
    private EntityProjectileComponent projectileComponent;
    /**
     * Entity motion and collision delegate.
     */
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    /**
     * Entity health and damage delegate.
     */
    @Delegate
    private EntityLivingComponent livingComponent;

    /**
     * Creates this entity using {@code initInfo} and the supplied component
     * providers. Each delegated component type must resolve to exactly one
     * component instance; superclass initialization wires dependencies and
     * runs completion hooks before this constructor returns.
     */
    public EntityArrowImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
