package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOrangeTulipBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockOrangeTulipBehaviorImpl extends BlockBehaviorImpl implements BlockOrangeTulipBehavior {
    public BlockOrangeTulipBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
