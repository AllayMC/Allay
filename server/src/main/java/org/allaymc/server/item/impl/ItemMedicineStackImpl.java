package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMedicineStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMedicineStackImpl extends ItemStackImpl implements ItemMedicineStack {
    public ItemMedicineStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
