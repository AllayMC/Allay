package org.allaymc.server.item.impl.leggings;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.leggings.ItemChainmailLeggingsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemChainmailLeggingsStackImpl extends ItemStackImpl implements ItemChainmailLeggingsStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemChainmailLeggingsStackImpl(ItemStackInitInfo initInfo,
                                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
