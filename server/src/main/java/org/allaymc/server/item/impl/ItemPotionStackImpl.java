package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.interfaces.ItemPotionStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemPotionStackImpl} implements the potion item API through supplied
 * component delegates. {@link ItemStackImpl} supplies ordinary stack behavior,
 * while {@code potionComponent} handles potion type selection for the contained effects. Callers access these contracts through this stack instance.
 */
public class ItemPotionStackImpl extends ItemStackImpl implements ItemPotionStack {
    /**
     * {@code potionComponent} holds the injected {@link ItemPotionComponent}
     * implementation. Lombok-generated forwarding methods use this instance
     * after superclass component initialization completes.
     */
    @Delegate
    private ItemPotionComponent potionComponent;

    /**
     * Creates the potion stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemPotionStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
