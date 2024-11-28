package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCobbledDeepslateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCobbledDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockCobbledDeepslateBehavior {
    public BlockCobbledDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
