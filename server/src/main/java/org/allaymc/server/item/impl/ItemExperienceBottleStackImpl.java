package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemExperienceBottleStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemExperienceBottleStackImpl} implements the experience bottle item
 * API through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code projectileComponent} exposes
 * projectile entity selection, throw force, and launching behavior. Callers
 * access these contracts through this stack instance.
 */
public class ItemExperienceBottleStackImpl extends ItemStackImpl implements ItemExperienceBottleStack {
    /**
     * {@code projectileComponent} holds the injected
     * {@link ItemProjectileComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemProjectileComponent projectileComponent;

    /**
     * Creates the experience bottle stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemExperienceBottleStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
