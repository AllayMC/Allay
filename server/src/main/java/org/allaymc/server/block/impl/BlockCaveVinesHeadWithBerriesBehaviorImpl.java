package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCaveVinesHeadWithBerriesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesHeadWithBerriesBehavior {
    public BlockCaveVinesHeadWithBerriesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
