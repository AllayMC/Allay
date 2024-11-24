package org.allaymc.server.block.impl.coralblock;

import java.util.List;
import org.allaymc.api.block.interfaces.coralblock.BlockBubbleCoralBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBubbleCoralBlockBehaviorImpl extends BlockBehaviorImpl implements BlockBubbleCoralBlockBehavior {
    public BlockBubbleCoralBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
