package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemSplashPotionStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemSplashPotionStackImpl} implements the splash potion item API
 * through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code projectileComponent} handles
 * projectile launching and {@code potionComponent} handles potion type
 * selection. Callers access these contracts through this stack instance.
 */
public class ItemSplashPotionStackImpl extends ItemStackImpl implements ItemSplashPotionStack {
    // projectileComponent and potionComponent receive their supplied implementations
    // during superclass initialization. Lombok-generated forwarding methods use these fields to expose
    // the corresponding component contracts on this stack instance.
    /**
     * Projectile launching delegate.
     */
    @Delegate
    private ItemProjectileComponent projectileComponent;
    /**
     * Potion type selection delegate.
     */
    @Delegate
    private ItemPotionComponent potionComponent;

    /**
     * Creates the splash potion stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemSplashPotionStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
