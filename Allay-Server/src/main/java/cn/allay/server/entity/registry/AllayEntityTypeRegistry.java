package cn.allay.server.entity.registry;

import cn.allay.api.entity.registry.EntityTypeRegistry;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.ReflectionUtils;
import cn.allay.server.world.biome.AllayBiomeTypeRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        log.info("Loading Entity Types...");
        var classes = ReflectionUtils.getAllClasses("cn.allay.api.entity.interfaces");
        classes.removeIf(clazz -> clazz.contains("Component"));
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Entity Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var entityClassName : classes) {
                Class.forName(entityClassName);
                pgbar.step();
            }
        }
        log.info("Loaded " + classes.size() + " Entity Types");
    }

    @SneakyThrows
    private void loadVanillaEntityIdentifierTag() {
        //TODO: Support custom entity
        availableEntityIdentifierTag = (NbtMap) NbtUtils.createNetworkReader(Objects.requireNonNull(AllayBiomeTypeRegistry.class.getClassLoader().getResourceAsStream("entity_identifiers.nbt"))).readTag();
    }

    @Override
    public NbtMap getAvailableEntityIdentifierTag() {
        return availableEntityIdentifierTag;
    }
}
