package org.allaymc.server.item.impl.musicdisc;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscFarStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemMusicDiscFarStackImpl extends ItemStackImpl implements ItemMusicDiscFarStack {
    public ItemMusicDiscFarStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
