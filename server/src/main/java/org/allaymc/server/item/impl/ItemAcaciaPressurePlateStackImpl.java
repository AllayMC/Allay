package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAcaciaPressurePlateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAcaciaPressurePlateStackImpl extends ItemStackImpl implements ItemAcaciaPressurePlateStack {
    public ItemAcaciaPressurePlateStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
