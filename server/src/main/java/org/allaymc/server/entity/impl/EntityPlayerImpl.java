package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityPlayerImpl} implements the player API through supplied
 * components. {@code ComponentClass} constructs and injects those components
 * before this constructor returns, connecting the entity's delegated operations
 * to the same component manager and initialization data.
 *
 * <p>
 * {@code playerBaseComponent}, {@code containerHolderComponent},
 * {@code livingComponent}, {@code physicsComponent}, and
 * {@code sleepableComponent} retain the selected component instances after
 * construction. Delegated operations use those instances' shared mutable state;
 * the component fields do not contain independent copies of their behavior or
 * gameplay data.
 */
public class EntityPlayerImpl extends EntityImpl implements EntityPlayer {

    /**
     * Player identity and gameplay delegate.
     */
    @Delegate
    private EntityPlayerBaseComponent playerBaseComponent;
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
    @Getter
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    /**
     * Sleeping and wake-state delegate.
     */
    @Delegate
    private EntitySleepableComponent sleepableComponent;

    /**
     * Creates a component-backed player from the supplied initialization data.
     * Superclass wiring constructs providers and resolves each delegate field
     * before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntityPlayerImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}