package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityZombie;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityZombieImpl} implements the zombie API through supplied
 * components. {@code ComponentClass} constructs and injects those components
 * before this constructor returns, connecting the entity's delegated operations
 * to the same component manager and initialization data.
 *
 * <p>
 * {@code livingComponent}, {@code containerHolderComponent},
 * {@code physicsComponent}, {@code aiComponent}, {@code parallelTickComponent},
 * {@code babyComponent}, and {@code headYawComponent} retain the selected
 * component instances after construction. Delegated operations use those
 * instances' shared mutable state; the component fields do not contain
 * independent copies of their behavior or gameplay data.
 */
public class EntityZombieImpl extends EntityImpl implements EntityZombie, EntityContainerHolderComponent {

    /**
     * Health and living-state delegate.
     */
    @Delegate
    private EntityLivingComponent livingComponent;
    /**
     * Equipment and inventory container delegate.
     */
    @Delegate
    private EntityContainerHolderComponent containerHolderComponent;
    /**
     * Movement and physics behavior delegate.
     */
    @Delegate
    private EntityPhysicsComponent physicsComponent;
    /**
     * Behavior group and AI-memory delegate.
     */
    @Delegate
    private EntityAIComponent aiComponent;
    /**
     * Parallel entity tick delegate.
     */
    @Delegate
    private EntityParallelTickComponent parallelTickComponent;
    /**
     * Baby-form state and scale delegate.
     */
    @Delegate
    private EntityBabyComponent babyComponent;
    /**
     * Independent head-yaw rotation delegate.
     */
    @Delegate
    private EntityHeadYawComponent headYawComponent;

    /**
     * Creates a component-backed zombie from the supplied initialization data.
     * Superclass wiring constructs providers and resolves each delegate field
     * before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntityZombieImpl(EntityInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
