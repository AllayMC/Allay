package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * {@code ContainerActionProcessorHolder} registers and resolves handlers by
 * stack-request action type. Its registry is static, so all holder instances share
 * replacements and each constructor installs a fresh set of built-in handlers into that
 * same registry rather than maintaining an isolated per-holder map.
 *
 * @author daoge_cmd
 */
public final class ContainerActionProcessorHolder {
    /**
     * {@code PROCESSORS} is the shared action-type registry used by every holder.
     * Construction and explicit registration replace existing entries for their action
     * types.
     */
    private static final Map<ItemStackRequestActionType, ContainerActionProcessor<?>> PROCESSORS = new EnumMap<>(ItemStackRequestActionType.class);

    /**
     * Installs the built-in stack-request handlers into the shared registry.
     * Constructing another holder replaces registered handlers for those types,
     * including custom replacements previously installed through another holder.
     */
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
        registerProcessor(new CraftRecipeAutoActionProcessor());
    }

    /**
     * Returns the handler currently registered for the action type, or {@code null} when
     * absent. The generic cast relies on callers choosing a handler type compatible with
     * the requested action.
     */
    public <R extends ContainerActionProcessor<?>> R getProcessor(ItemStackRequestActionType type) {
        return (R) PROCESSORS.get(type);
    }

    /**
     * Registers the handler under its reported action type in the shared registry. An
     * existing entry is replaced for all holder instances, and no handler state is
     * copied.
     */
    public void registerProcessor(ContainerActionProcessor<?> processor) {
        PROCESSORS.put(processor.getType(), processor);
    }

    /**
     * Returns an unmodifiable live view of the shared handler registry. Later
     * registrations through any holder remain visible, and handler instances themselves
     * are retained rather than copied.
     */
    @UnmodifiableView
    public Map<ItemStackRequestActionType, ContainerActionProcessor<?>> getProcessors() {
        return Collections.unmodifiableMap(PROCESSORS);
    }
}
