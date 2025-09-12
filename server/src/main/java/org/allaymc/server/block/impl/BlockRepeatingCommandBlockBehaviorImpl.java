package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRepeatingCommandBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRepeatingCommandBlockBehaviorImpl extends BlockBehaviorImpl implements BlockRepeatingCommandBlockBehavior {
    public BlockRepeatingCommandBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
