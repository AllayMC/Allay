package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.api.item.interfaces.ItemBannerStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemBannerStackImpl} implements the banner item API through supplied
 * component delegates. {@link ItemStackImpl} supplies ordinary stack behavior,
 * while {@code bannerBaseComponent} exposes base color, banner kind, and
 * ordered pattern editing. Callers access these contracts through this stack
 * instance.
 */
public class ItemBannerStackImpl extends ItemStackImpl implements ItemBannerStack {
    /**
     * {@code bannerBaseComponent} holds the injected
     * {@link ItemBannerBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemBannerBaseComponent bannerBaseComponent;

    /**
     * Creates the banner stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemBannerStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
