package org.allaymc.server.item.impl.trapdoor;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.trapdoor.ItemWaxedExposedCopperTrapdoorStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWaxedExposedCopperTrapdoorStackImpl extends ItemStackImpl implements ItemWaxedExposedCopperTrapdoorStack {
    public ItemWaxedExposedCopperTrapdoorStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
