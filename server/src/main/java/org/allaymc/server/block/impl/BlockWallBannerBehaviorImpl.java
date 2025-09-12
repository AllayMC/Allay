package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWallBannerBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWallBannerBehaviorImpl extends BlockBehaviorImpl implements BlockWallBannerBehavior {
    public BlockWallBannerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
