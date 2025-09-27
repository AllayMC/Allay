package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCrimsonNyliumBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCrimsonNyliumBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonNyliumBehavior {
    public BlockCrimsonNyliumBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
