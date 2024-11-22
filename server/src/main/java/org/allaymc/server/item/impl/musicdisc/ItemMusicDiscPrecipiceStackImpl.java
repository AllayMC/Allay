package org.allaymc.server.item.impl.musicdisc;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscPrecipiceStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemMusicDiscPrecipiceStackImpl extends ItemStackImpl implements ItemMusicDiscPrecipiceStack {
    public ItemMusicDiscPrecipiceStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
