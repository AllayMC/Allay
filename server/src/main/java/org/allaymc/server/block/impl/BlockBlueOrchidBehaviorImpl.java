package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBlueOrchidBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBlueOrchidBehaviorImpl extends BlockBehaviorImpl implements BlockBlueOrchidBehavior {
    public BlockBlueOrchidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
