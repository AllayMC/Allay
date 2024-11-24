package org.allaymc.server.block.impl.tnt;

import java.util.List;
import org.allaymc.api.block.interfaces.tnt.BlockTntBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockTntBehaviorImpl extends BlockBehaviorImpl implements BlockTntBehavior {
    public BlockTntBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
