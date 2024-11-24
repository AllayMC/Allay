package org.allaymc.server.block.impl.coral;

import java.util.List;
import org.allaymc.api.block.interfaces.coral.BlockFireCoralBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockFireCoralBehaviorImpl extends BlockBehaviorImpl implements BlockFireCoralBehavior {
    public BlockFireCoralBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
