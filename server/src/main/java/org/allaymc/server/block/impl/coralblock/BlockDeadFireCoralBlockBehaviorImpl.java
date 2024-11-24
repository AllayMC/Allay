package org.allaymc.server.block.impl.coralblock;

import java.util.List;
import org.allaymc.api.block.interfaces.coralblock.BlockDeadFireCoralBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadFireCoralBlockBehaviorImpl extends BlockBehaviorImpl implements BlockDeadFireCoralBlockBehavior {
    public BlockDeadFireCoralBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
