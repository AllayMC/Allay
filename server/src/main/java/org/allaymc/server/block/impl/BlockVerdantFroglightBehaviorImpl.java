package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockVerdantFroglightBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockVerdantFroglightBehaviorImpl extends BlockBehaviorImpl implements BlockVerdantFroglightBehavior {
    public BlockVerdantFroglightBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
