package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.interfaces.ItemBucketStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemBucketStackImpl} implements the bucket item API through supplied
 * component delegates. {@link ItemStackImpl} supplies ordinary stack behavior,
 * while {@code bucketComponent} exposes the contained liquid and optional
 * captured entity. Callers access these contracts through this stack instance.
 */
public class ItemBucketStackImpl extends ItemStackImpl implements ItemBucketStack {
    /**
     * {@code bucketComponent} holds the injected {@link ItemBucketComponent}
     * implementation. Lombok-generated forwarding methods use this instance
     * after superclass component initialization completes.
     */
    @Delegate
    private ItemBucketComponent bucketComponent;

    /**
     * Creates the bucket stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemBucketStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}