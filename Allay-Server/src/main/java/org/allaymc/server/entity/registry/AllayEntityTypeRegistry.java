package org.allaymc.server.entity.registry;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.world.biome.AllayBiomeTypeRegistry;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEntityTypeRegistry extends SimpleMappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> implements EntityTypeRegistry {

    private NbtMap availableEntityIdentifierTag;

    public AllayEntityTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
        loadVanillaEntityIdentifierTag();
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_ENTITYTYPE_LOADING));
        var classes = ReflectionUtils.getAllClasses("org.allaymc.server.entity.initializer");
        try (var progressBar = ProgressBar
                .builder()
                .setInitialMax(classes.size())
                .setTaskName("Loading Entity Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            for (var entityClassName : classes) {
                Class.forName(entityClassName).getMethod("init").invoke(null);
                progressBar.step();
            }
        }
        log.info(I18n.get().tr(TrKeys.A_ENTITYTYPE_LOADED, classes.size()));
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
