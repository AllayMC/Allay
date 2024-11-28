package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBlueOrchidBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBlueOrchidBehaviorImpl extends BlockBehaviorImpl implements BlockBlueOrchidBehavior {
    public BlockBlueOrchidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
