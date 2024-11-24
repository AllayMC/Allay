package org.allaymc.server.block.impl.coralblock;

import java.util.List;
import org.allaymc.api.block.interfaces.coralblock.BlockFireCoralBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockFireCoralBlockBehaviorImpl extends BlockBehaviorImpl implements BlockFireCoralBlockBehavior {
    public BlockFireCoralBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
