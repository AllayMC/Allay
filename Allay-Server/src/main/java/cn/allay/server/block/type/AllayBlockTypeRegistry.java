package cn.allay.server.block.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.ReflectionUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayBlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> implements BlockTypeRegistry {
    public AllayBlockTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        log.info("Loading Block Types...");
        var count = 0;
        var classes = ReflectionUtils.getAllClasses("cn.allay.api.block.interfaces");
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Block Types")
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var blockClassName : classes) {
                Class.forName(blockClassName);
                pgbar.step();
                count++;
            }

        }
        rebuildDefinitionList();
        log.info("Loaded " + count + " Block Types");
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
