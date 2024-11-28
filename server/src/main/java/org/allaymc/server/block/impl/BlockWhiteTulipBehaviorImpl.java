package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWhiteTulipBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWhiteTulipBehaviorImpl extends BlockBehaviorImpl implements BlockWhiteTulipBehavior {
    public BlockWhiteTulipBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
