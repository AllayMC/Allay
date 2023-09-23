package cn.allay.api.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

import static org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessorHolder {
    @Nullable
    <R extends ContainerActionProcessor<?>> R getProcessor(ItemStackRequestActionType type);

    void registerProcessor(ContainerActionProcessor<?> processor);

    @UnmodifiableView
    Map<ItemStackRequestActionType, ContainerActionProcessor<?>> getProcessors();

    static void registerDefaultContainerActionProcessors(ContainerActionProcessorHolder holder) {
        holder.registerProcessor(new CraftCreativeActionProcessor());
        holder.registerProcessor(new DeprecatedActionProcessor<CraftResultsDeprecatedAction>(CRAFT_RESULTS_DEPRECATED));
        holder.registerProcessor(new PlaceActionProcessor());
        holder.registerProcessor(new TakeActionProcessor());
        holder.registerProcessor(new DestroyActionProcessor());
        holder.registerProcessor(new DropActionProcessor());
    }
}
