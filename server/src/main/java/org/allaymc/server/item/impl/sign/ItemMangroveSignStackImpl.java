package org.allaymc.server.item.impl.sign;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.sign.ItemMangroveSignStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemMangroveSignStackImpl extends ItemStackImpl implements ItemMangroveSignStack {
    public ItemMangroveSignStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }

    @Delegate
    @Override
    protected ItemSignBaseComponent getBaseComponent() {
        return (ItemSignBaseComponent) super.getBaseComponent();
    }
}
