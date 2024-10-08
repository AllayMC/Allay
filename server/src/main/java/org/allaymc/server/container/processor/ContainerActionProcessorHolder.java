package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface ContainerActionProcessorHolder {
    static void registerDefaultContainerActionProcessors(ContainerActionProcessorHolder holder) {
        holder.registerProcessor(new CraftCreativeActionProcessor());
        holder.registerProcessor(new PlaceActionProcessor());
        holder.registerProcessor(new TakeActionProcessor());
        holder.registerProcessor(new DestroyActionProcessor());
        holder.registerProcessor(new DropActionProcessor());
        holder.registerProcessor(new SwapActionProcessor());
        holder.registerProcessor(new CraftRecipeActionProcessor());
        holder.registerProcessor(new ConsumeActionProcessor());
        holder.registerProcessor(new CreateActionProcessor());
        holder.registerProcessor(new CraftResultDeprecatedActionProcessor());
        holder.registerProcessor(new MineBlockActionProcessor());
    }

    <R extends ContainerActionProcessor<?>> R getProcessor(ItemStackRequestActionType type);

    void registerProcessor(ContainerActionProcessor<?> processor);

    @UnmodifiableView
    Map<ItemStackRequestActionType, ContainerActionProcessor<?>> getProcessors();
}
