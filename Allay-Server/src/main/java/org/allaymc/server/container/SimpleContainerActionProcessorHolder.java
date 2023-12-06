package org.allaymc.server.container;

import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.container.processor.ContainerActionProcessorHolder;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public class SimpleContainerActionProcessorHolder implements ContainerActionProcessorHolder {

    EnumMap<ItemStackRequestActionType, ContainerActionProcessor<?>> PROCESSORS = new EnumMap<>(ItemStackRequestActionType.class);

    @Override
    public <R extends ContainerActionProcessor<?>> @Nullable R getProcessor(ItemStackRequestActionType type) {
        return (R) PROCESSORS.get(type);
    }

    @Override
    public void registerProcessor(ContainerActionProcessor<?> processor) {
        PROCESSORS.put(processor.getType(), processor);
    }

    @UnmodifiableView
    @Override
    public Map<ItemStackRequestActionType, ContainerActionProcessor<?>> getProcessors() {
        return Collections.unmodifiableMap(PROCESSORS);
    }
}
