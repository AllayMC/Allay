package org.allaymc.server.item.impl.wallsign;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.wallsign.ItemMangroveWallSignStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemMangroveWallSignStackImpl extends ItemStackImpl implements ItemMangroveWallSignStack {
    public ItemMangroveWallSignStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}