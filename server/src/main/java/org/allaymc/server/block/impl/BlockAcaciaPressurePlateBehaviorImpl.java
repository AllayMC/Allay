package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAcaciaPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAcaciaPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockAcaciaPressurePlateBehavior {
    public BlockAcaciaPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
