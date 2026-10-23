package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFilledMapBaseComponent;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemFilledMapStackImpl} implements the filled map item API through
 * supplied component delegates. {@link ItemStackImpl} supplies ordinary stack
 * behavior, while {@code filledMapBaseComponent} handles map image access,
 * rendering, and delivery.
 * Callers access these contracts through this stack instance.
 */
public class ItemFilledMapStackImpl extends ItemStackImpl implements ItemFilledMapStack {

    /**
     * {@code filledMapBaseComponent} holds the injected
     * {@link ItemFilledMapBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemFilledMapBaseComponent filledMapBaseComponent;

    /**
     * Creates the filled map stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemFilledMapStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
