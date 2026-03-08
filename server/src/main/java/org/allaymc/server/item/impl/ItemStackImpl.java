package org.allaymc.server.item.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class ItemStackImpl extends ComponentClass implements ItemStack {

    @Getter
    @Delegate
    private ItemBaseComponent baseComponent;

    public ItemStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
