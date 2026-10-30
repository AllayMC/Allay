package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDecoratedPotBaseComponent;
import org.allaymc.api.item.interfaces.ItemDecoratedPotStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemDecoratedPotStackImpl} implements the decorated pot item API
 * through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code decoratedPotBaseComponent} exposes the
 * sherd layout stored on the decorated pot. Callers access these contracts
 * through this stack instance.
 */
public class ItemDecoratedPotStackImpl extends ItemStackImpl implements ItemDecoratedPotStack {
    /**
     * {@code decoratedPotBaseComponent} holds the injected
     * {@link ItemDecoratedPotBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemDecoratedPotBaseComponent decoratedPotBaseComponent;

    /**
     * Creates the decorated pot stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemDecoratedPotStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
