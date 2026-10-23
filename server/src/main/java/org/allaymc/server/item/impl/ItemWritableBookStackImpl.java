package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWritableBookBaseComponent;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemWritableBookStackImpl} implements the writable book item API
 * through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code writableBookBaseComponent} handles
 * page access, editing, and ordering. Callers access these contracts through
 * this stack instance.
 */
public class ItemWritableBookStackImpl extends ItemStackImpl implements ItemWritableBookStack {

    /**
     * {@code writableBookBaseComponent} holds the injected
     * {@link ItemWritableBookBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemWritableBookBaseComponent writableBookBaseComponent;

    /**
     * Creates the writable book stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemWritableBookStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
