package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedTulipBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedTulipBehaviorImpl extends BlockBehaviorImpl implements BlockRedTulipBehavior {
    public BlockRedTulipBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
