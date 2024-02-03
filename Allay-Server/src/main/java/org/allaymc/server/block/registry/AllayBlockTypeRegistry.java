package org.allaymc.server.block.registry;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.ReflectionUtils;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayBlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> implements BlockTypeRegistry {
    public AllayBlockTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADING));
        var classes = ReflectionUtils.getAllClasses("org.allaymc.api.block.interfaces");
        classes.removeIf(clazz -> clazz.contains("Component"));
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Block Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var blockClassName : classes) {
                Class.forName(blockClassName);
                pgbar.step();
            }
        }
        rebuildDefinitionList();
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADED, classes.size()));
    }

    private final List<BlockDefinition> blockDefinitions = new ArrayList<>();

    @Override
    public List<BlockDefinition> getBlockDefinitions() {
        return blockDefinitions;
    }

    private void rebuildDefinitionList() {
        blockDefinitions.clear();
        for (var blockType : this.getContent().values()) {
            blockType.getAllStates().forEach(state -> blockDefinitions.add(state.toNetworkBlockDefinition()));
        }
    }
}
