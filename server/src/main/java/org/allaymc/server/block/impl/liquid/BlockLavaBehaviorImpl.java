package org.allaymc.server.block.impl.liquid;

import java.util.List;
import org.allaymc.api.block.interfaces.liquid.BlockLavaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLavaBehaviorImpl extends BlockBehaviorImpl implements BlockLavaBehavior {
    public BlockLavaBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
