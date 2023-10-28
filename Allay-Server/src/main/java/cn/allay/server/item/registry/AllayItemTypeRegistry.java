package cn.allay.server.item.registry;

import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.registry.ItemTypeRegistry;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.ReflectionUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayItemTypeRegistry extends SimpleMappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> implements ItemTypeRegistry {
    public AllayItemTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info("Loading Item Types...");
        var classes = ReflectionUtils.getAllClasses("cn.allay.api.item.interfaces");
        classes.removeIf(clazz -> clazz.contains("Component"));
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Item Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var itemClassName : classes) {
                Class.forName(itemClassName);
                pgbar.step();
            }
        }
        rebuildDefinitionList();
        log.info("Loaded " + classes.size() + " Item Types");
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
