package org.allaymc.server.block.impl.purpur;

import java.util.List;
import org.allaymc.api.block.interfaces.purpur.BlockPurpurPillarBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPurpurPillarBehaviorImpl extends BlockBehaviorImpl implements BlockPurpurPillarBehavior {
    public BlockPurpurPillarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
