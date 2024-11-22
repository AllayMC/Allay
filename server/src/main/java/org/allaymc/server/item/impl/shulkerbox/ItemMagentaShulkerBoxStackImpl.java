package org.allaymc.server.item.impl.shulkerbox;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemItemStorableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.shulkerbox.ItemMagentaShulkerBoxStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemMagentaShulkerBoxStackImpl extends ItemStackImpl implements ItemMagentaShulkerBoxStack {
    @Delegate
    protected ItemItemStorableComponent itemStorableComponent;

    public ItemMagentaShulkerBoxStackImpl(ItemStackInitInfo initInfo,
                                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
