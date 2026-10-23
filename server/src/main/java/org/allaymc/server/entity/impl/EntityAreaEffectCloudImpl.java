package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAreaEffectCloudBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.interfaces.EntityAreaEffectCloud;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code EntityAreaEffectCloudImpl} implements the area-effect cloud API
 * through supplied components. {@code ComponentClass} constructs and injects
 * those components before this constructor returns, connecting the entity's
 * delegated operations to the same component manager and initialization data.
 *
 * <p>
 * {@code areaEffectCloudBaseComponent} and {@code potionComponent} retain the
 * selected component instances after construction. Delegated operations use
 * those instances' shared mutable state; the component fields do not contain
 * independent copies of their behavior or gameplay data.
 */
public class EntityAreaEffectCloudImpl extends EntityImpl implements EntityAreaEffectCloud {
    /**
     * Cloud radius and lifetime delegate.
     */
    @Delegate
    private EntityAreaEffectCloudBaseComponent areaEffectCloudBaseComponent;
    /**
     * Cloud potion-content delegate.
     */
    @Delegate
    private EntityPotionComponent potionComponent;

    /**
     * Creates a component-backed area-effect cloud from the supplied
     * initialization data. Superclass wiring constructs providers and resolves
     * each delegate field before returning; the supplied providers must yield
     * exactly one assignable component for every required delegate type.
     */
    public EntityAreaEffectCloudImpl(EntityInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
