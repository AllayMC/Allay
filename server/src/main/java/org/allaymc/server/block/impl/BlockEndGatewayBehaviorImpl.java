package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEndGatewayBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockEndGatewayBehaviorImpl extends BlockBehaviorImpl implements BlockEndGatewayBehavior {
    public BlockEndGatewayBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
