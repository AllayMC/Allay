package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCherryPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCherryPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockCherryPressurePlateBehavior {
    public BlockCherryPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
