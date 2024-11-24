package org.allaymc.server.block.impl.concretepowder;

import java.util.List;
import org.allaymc.api.block.interfaces.concretepowder.BlockLightBlueConcretePowderBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightBlueConcretePowderBehaviorImpl extends BlockBehaviorImpl implements BlockLightBlueConcretePowderBehavior {
    public BlockLightBlueConcretePowderBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
