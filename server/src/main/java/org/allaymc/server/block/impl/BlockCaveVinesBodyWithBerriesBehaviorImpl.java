package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockCaveVinesBodyWithBerriesBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCaveVinesBodyWithBerriesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesBodyWithBerriesBehavior {
    public BlockCaveVinesBodyWithBerriesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
