package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntitySheepImpl} implements the sheep API through supplied components.
 * {@code ComponentClass} constructs and injects those components before this
 * constructor returns, connecting the entity's delegated operations to the same
 * component manager and initialization data.
 *
 * <p>
 * {@code sheepBaseComponent}, {@code livingComponent},
 * {@code physicsComponent}, {@code aiComponent}, {@code parallelTickComponent},
 * {@code animalComponent}, {@code babyComponent}, {@code dyeableComponent}, and
 * {@code headYawComponent} retain the selected component instances after
 * construction. Delegated operations use those instances' shared mutable state;
 * the component fields do not contain independent copies of their behavior or
 * gameplay data.
 */
public class EntitySheepImpl extends EntityImpl implements EntitySheep {

    /**
     * Sheep wool-availability delegate.
     */
    @Delegate
    private EntitySheepBaseComponent sheepBaseComponent;

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
     * Breeding-item recognition delegate.
     */
    @Delegate
    private EntityAnimalComponent animalComponent;

    /**
     * Baby-form state and scale delegate.
     */
    @Delegate
    private EntityBabyComponent babyComponent;

    /**
     * Sheep dye-color state delegate.
     */
    @Delegate
    private EntityDyeableComponent dyeableComponent;

    /**
     * Independent head-yaw rotation delegate.
     */
    @Delegate
    private EntityHeadYawComponent headYawComponent;

    /**
     * Creates a component-backed sheep from the supplied initialization data.
     * Superclass wiring constructs providers and resolves each delegate field
     * before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntitySheepImpl(EntityInitInfo initInfo,
                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
