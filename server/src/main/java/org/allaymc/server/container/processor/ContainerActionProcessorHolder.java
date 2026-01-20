package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public final class ContainerActionProcessorHolder {
    private static final Map<ItemStackRequestActionType, ContainerActionProcessor<?>> PROCESSORS = new EnumMap<>(ItemStackRequestActionType.class);

    public ContainerActionProcessorHolder() {
        registerProcessor(new CraftCreativeActionProcessor());
        registerProcessor(new PlaceActionProcessor());
        registerProcessor(new TakeActionProcessor());
        registerProcessor(new DestroyActionProcessor());
        registerProcessor(new DropActionProcessor());
        registerProcessor(new SwapActionProcessor());
        registerProcessor(new CraftRecipeActionProcessor());
        registerProcessor(new ConsumeActionProcessor());
        registerProcessor(new CreateActionProcessor());
        registerProcessor(new CraftResultDeprecatedActionProcessor());
        registerProcessor(new MineBlockActionProcessor());
        registerProcessor(new BeaconPaymentActionProcessor());
        registerProcessor(new CraftGrindstoneActionProcessor());
        registerProcessor(new CraftLoomActionProcessor());
        registerProcessor(new CraftRecipeOptionalActionProcessor());
    }

    public <R extends ContainerActionProcessor<?>> R getProcessor(ItemStackRequestActionType type) {
        return (R) PROCESSORS.get(type);
    }

    public void registerProcessor(ContainerActionProcessor<?> processor) {
        PROCESSORS.put(processor.getType(), processor);
    }

    @UnmodifiableView
    public Map<ItemStackRequestActionType, ContainerActionProcessor<?>> getProcessors() {
        return Collections.unmodifiableMap(PROCESSORS);
    }
}
