package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.item.interfaces.ItemDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemDyeStackImpl} implements the dye item API through supplied
 * component delegates. {@link ItemStackImpl} supplies ordinary stack behavior,
 * while {@code dyeComponent} exposes the fixed dye color associated with this
 * item. Callers access these contracts through this stack instance.
 */
public class ItemDyeStackImpl extends ItemStackImpl implements ItemDyeStack {

    /**
     * {@code dyeComponent} holds the injected {@link ItemDyeComponent}
     * implementation. Lombok-generated forwarding methods use this instance
     * after superclass component initialization completes.
     */
    @Delegate
    private ItemDyeComponent dyeComponent;

    /**
     * Creates the dye stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemDyeStackImpl(ItemStackInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
