package cn.allay.server.block.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
        var fields = VanillaBlockTypes.class.getDeclaredFields();
        log.info("Loading Block Types...");
        fields[0].get(null);
        rebuildDefinitionList();
        log.info("Loaded " + fields.length + " Block Types");
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
