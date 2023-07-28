package cn.allay.api.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface InventoryActionProcessorHolder {
    @Nullable
    <R extends ContainerActionProcessor> R getProcessor(ItemStackRequestActionType type);

    void registerProcessor(ContainerActionProcessor processor);

    @UnmodifiableView
    Map<ItemStackRequestActionType, ContainerActionProcessor> getProcessors();

    static void registerDefaultInventoryActionProcessors(InventoryActionProcessorHolder holder) {
        holder.registerProcessor(new CraftCreativeActionProcessor());
        holder.registerProcessor(new PlaceActionProcessor());
        holder.registerProcessor(new TakeActionProcessor());
    }
}
