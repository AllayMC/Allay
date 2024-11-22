package org.allaymc.server.item.impl.shulkerbox;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemItemStorableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.shulkerbox.ItemBlackShulkerBoxStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemBlackShulkerBoxStackImpl extends ItemStackImpl implements ItemBlackShulkerBoxStack {
    @Delegate
    protected ItemItemStorableComponent itemStorableComponent;

    public ItemBlackShulkerBoxStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
