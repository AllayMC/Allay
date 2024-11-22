package org.allaymc.server.item.impl.musicdisc;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDisc11Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemMusicDisc11StackImpl extends ItemStackImpl implements ItemMusicDisc11Stack {
    public ItemMusicDisc11StackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
