package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWrittenBookBaseComponent;
import org.allaymc.api.item.interfaces.ItemWrittenBookStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemWrittenBookStackImpl} implements the written book item API
 * through supplied component delegates. {@link ItemStackImpl} supplies
 * ordinary stack behavior, while {@code writtenBookBaseComponent} handles
 * signed metadata and stored page content. Callers access these contracts
 * through this stack instance.
 */
public class ItemWrittenBookStackImpl extends ItemStackImpl implements ItemWrittenBookStack {

    /**
     * {@code writtenBookBaseComponent} holds the injected
     * {@link ItemWrittenBookBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemWrittenBookBaseComponent writtenBookBaseComponent;

    /**
     * Creates the written book stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemWrittenBookStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
