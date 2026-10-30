package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBlastFurnace;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.blockentity.interfaces.BlockEntitySmoker;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityFurnaceImpl} implements the composed block entity's furnace
 * inventory and processing capabilities through injected components. Component
 * assembly assigns the delegation fields before completion hooks run, and the
 * inherited base component supplies identity, position, naming, and NBT
 * serialization for the same object. The configured providers distinguish
 * ordinary furnaces, blast furnaces, and smokers despite their shared
 * implementation class.
 *
 * @author daoge_cmd
 */
public class BlockEntityFurnaceImpl extends BlockEntityImpl implements BlockEntityFurnace, BlockEntitySmoker, BlockEntityBlastFurnace {
    /**
     * {@code containerHolderComponent} supplies furnace inventory operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * {@code furnaceBaseComponent} supplies furnace processing operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityFurnaceBaseComponent furnaceBaseComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityFurnaceImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
