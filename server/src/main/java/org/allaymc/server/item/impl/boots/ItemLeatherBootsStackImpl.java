package org.allaymc.server.item.impl.boots;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.boots.ItemLeatherBootsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemLeatherBootsStackImpl extends ItemStackImpl implements ItemLeatherBootsStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemLeatherBootsStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
