package org.allaymc.server.item.registry;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.item.type.ItemTypeDefaultInitializer;
import org.allaymc.server.item.type.ItemTypeInitializer;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayItemTypeRegistry extends SimpleMappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> implements ItemTypeRegistry {
    public AllayItemTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_ITEMTYPE_LOADING));
        var defaultInitializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeDefaultInitializer.class);
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(ItemTypeInitializer.class);
        try (var progressBar = ProgressBar
                .builder()
                .setInitialMax(defaultInitializers.size())
                .setTaskName("Loading Item Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            initializers.forEach(method -> callInitializer(method, null));
            defaultInitializers.forEach(method -> callInitializer(method, progressBar));
        }
        rebuildDefinitionList();
        log.info(I18n.get().tr(TrKeys.A_ITEMTYPE_LOADED, defaultInitializers.size()));
    }

    private static void callInitializer(Method method, ProgressBar progressBar) {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            if (progressBar != null)
                progressBar.step();
        }
    }

    private final List<ItemDefinition> itemDefinitions = new ArrayList<>();

    @Override
    public List<ItemDefinition> getItemDefinitions() {
        return itemDefinitions;
    }

    @Override
    public void rebuildDefinitionList() {
        itemDefinitions.clear();
        for (var itemType : this.getContent().values()) {
            itemDefinitions.add(itemType.toNetworkDefinition());
        }
    }
}
