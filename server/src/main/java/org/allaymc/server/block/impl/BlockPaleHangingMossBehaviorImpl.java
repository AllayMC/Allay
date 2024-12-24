package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPaleHangingMossBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPaleHangingMossBehaviorImpl extends BlockBehaviorImpl implements BlockPaleHangingMossBehavior {
    public BlockPaleHangingMossBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
