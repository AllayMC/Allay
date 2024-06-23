package org.allaymc.server.entity.registry;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.entity.type.EntityTypeDefaultInitializer;
import org.allaymc.server.entity.type.EntityTypeInitializer;
import org.allaymc.server.utils.ResourceUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.allaymc.server.utils.ResourceUtils.callInitializer;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEntityTypeRegistry extends SimpleMappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> implements EntityTypeRegistry {
    @Getter
    private NbtMap availableEntityIdentifierTag;

    public AllayEntityTypeRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
        loadVanillaEntityIdentifierTag();
    }

    @SneakyThrows
    public void init() {
        log.info(I18n.get().tr(TrKeys.A_ENTITYTYPE_LOADING));
        var defaultInitializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(EntityTypeDefaultInitializer.class);
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(EntityTypeInitializer.class);
        try (var progressBar = ProgressBar
                .builder()
                .setInitialMax(defaultInitializers.size())
                .setTaskName("Loading Entity Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            initializers.forEach(method -> callInitializer(method, null));
            defaultInitializers.forEach(method -> callInitializer(method, progressBar));
        }
        log.info(I18n.get().tr(TrKeys.A_ENTITYTYPE_LOADED, defaultInitializers.size()));
    }

    @SneakyThrows
    private void loadVanillaEntityIdentifierTag() {
        //TODO: Support custom entity
        availableEntityIdentifierTag = (NbtMap) NbtUtils.createNetworkReader(
                ResourceUtils.getResource("entity_identifiers.nbt")
        ).readTag();
    }
}
