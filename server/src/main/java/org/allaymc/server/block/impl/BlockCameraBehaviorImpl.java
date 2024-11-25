package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCameraBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCameraBehaviorImpl extends BlockBehaviorImpl implements BlockCameraBehavior {
    public BlockCameraBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
