package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCobbledDeepslateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCobbledDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockCobbledDeepslateBehavior {
    public BlockCobbledDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
