package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemProjectileComponent;
import org.allaymc.api.item.interfaces.ItemSnowballStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemSnowballStackImpl} represents a server item stack with delegated
 * projectile launching. {@code ItemStackImpl} supplies the shared stack state
 * and item operations, while the local delegates expose their component
 * contracts through the same typed stack object.
 */
public class ItemSnowballStackImpl extends ItemStackImpl implements ItemSnowballStack {
    /**
     * {@code projectileComponent} holds the injected delegate for projectile launching.
     * {@code ComponentClass} assigns this stack's unique compatible provider result
     * during construction before delegated operations can be used.
     */
    @Delegate
    private ItemProjectileComponent projectileComponent;

    /**
     * Creates the typed stack using the supplied initialization data and component
     * providers. {@code ComponentClass} requires exactly one compatible provider
     * result for each delegate type, including the inherited base component;
     * missing or ambiguous matches fail construction.
     */
    public ItemSnowballStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
