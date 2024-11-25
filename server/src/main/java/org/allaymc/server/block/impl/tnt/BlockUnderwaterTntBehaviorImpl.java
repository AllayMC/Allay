package org.allaymc.server.block.impl.tnt;

import org.allaymc.api.block.interfaces.tnt.BlockUnderwaterTntBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockUnderwaterTntBehaviorImpl extends BlockBehaviorImpl implements BlockUnderwaterTntBehavior {
    public BlockUnderwaterTntBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
