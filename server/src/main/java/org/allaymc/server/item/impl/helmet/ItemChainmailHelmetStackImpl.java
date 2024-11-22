package org.allaymc.server.item.impl.helmet;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.helmet.ItemChainmailHelmetStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemChainmailHelmetStackImpl extends ItemStackImpl implements ItemChainmailHelmetStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemChainmailHelmetStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
