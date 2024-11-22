package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.data.ItemDataComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public abstract class ItemStackImpl extends ComponentClass implements ItemStack {
    protected ItemBaseComponent baseComponent;
    protected ItemDataComponent dataComponent;

    public ItemStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    protected ItemBaseComponent getBaseComponent() {
        return baseComponent;
    }

    @Delegate
    protected ItemDataComponent getDataComponent() {
        return dataComponent;
    }
}
