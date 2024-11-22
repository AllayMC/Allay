package org.allaymc.server.item.impl.door;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.door.ItemWeatheredCopperDoorStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWeatheredCopperDoorStackImpl extends ItemStackImpl implements ItemWeatheredCopperDoorStack {
    public ItemWeatheredCopperDoorStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
