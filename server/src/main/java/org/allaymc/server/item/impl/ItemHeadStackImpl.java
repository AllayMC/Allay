package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.interfaces.ItemHeadStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemHeadStackImpl} implements the head item API through supplied
 * component delegates. {@link ItemStackImpl} supplies ordinary stack behavior,
 * while {@code wearableComponent} exposes the armor slot used when the head is
 * equipped. Callers access these contracts through this stack instance.
 */
public class ItemHeadStackImpl extends ItemStackImpl implements ItemHeadStack {

    /**
     * {@code wearableComponent} holds the injected
     * {@link ItemWearableComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemWearableComponent wearableComponent;

    /**
     * Creates the head stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemHeadStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
