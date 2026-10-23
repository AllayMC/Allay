package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code ItemMusicDiscStackImpl} implements the music disc item API through
 * supplied component delegates. {@link ItemStackImpl} supplies ordinary stack
 * behavior, while {@code musicDiscBaseComponent} exposes the disc type and its
 * comparator output in a jukebox. Callers access these contracts through this
 * stack instance.
 */
public class ItemMusicDiscStackImpl extends ItemStackImpl implements ItemMusicDiscStack {

    /**
     * {@code musicDiscBaseComponent} holds the injected
     * {@link ItemMusicDiscBaseComponent} implementation. Lombok-generated
     * forwarding methods use this instance after superclass component
     * initialization completes.
     */
    @Delegate
    private ItemMusicDiscBaseComponent musicDiscBaseComponent;

    /**
     * Creates the music disc stack using {@code initInfo} and
     * {@code componentProviders}. {@code componentProviders} must produce
     * exactly one assignable implementation for each component-typed field,
     * including inherited fields; superclass construction injects these
     * instances before this constructor returns.
     */
    public ItemMusicDiscStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
