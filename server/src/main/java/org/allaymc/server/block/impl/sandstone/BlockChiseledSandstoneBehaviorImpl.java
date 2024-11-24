package org.allaymc.server.block.impl.sandstone;

import java.util.List;
import org.allaymc.api.block.interfaces.sandstone.BlockChiseledSandstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockChiseledSandstoneBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledSandstoneBehavior {
    public BlockChiseledSandstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
