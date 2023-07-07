package cn.allay.server.entity.type;

import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.entity.type.VanillaEntityTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.server.world.biome.AllayBiomeTypeRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEntityTypeRegistry extends SimpleMappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> implements EntityTypeRegistry {

    private NbtMap availableEntityIdentifierTag;

    public AllayEntityTypeRegistry() {
        super(null, input -> new HashMap<>());
        loadVanillaEntityIdentifierTag();
    }

    @SneakyThrows
    public void init() {
        var fields = VanillaEntityTypes.class.getDeclaredFields();
        log.info("Loading Entity Types...");
        fields[0].get(null);
        log.info("Loaded " + fields.length + " Entity Types");
    }

    @SneakyThrows
    private void loadVanillaEntityIdentifierTag() {
        //TODO: Support custom entity
        availableEntityIdentifierTag = (NbtMap) NbtUtils.createNetworkReader(AllayBiomeTypeRegistry.class.getClassLoader().getResourceAsStream("entity_identifiers.nbt")).readTag();
    }

    @Override
    public NbtMap getAvailableEntityIdentifierTag() {
        return availableEntityIdentifierTag;
    }
}
