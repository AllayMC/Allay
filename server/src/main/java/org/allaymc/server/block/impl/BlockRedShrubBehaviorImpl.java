package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockRedShrubBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

public class BlockRedShrubBehaviorImpl extends BlockBehaviorImpl implements BlockRedShrubBehavior {
    public BlockRedShrubBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
