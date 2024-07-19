package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.Registry;
import org.allaymc.api.registry.populator.RegistryPopulator;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.server.block.type.BlockLootTable;
import org.allaymc.server.block.type.BlockTypeDefaultInitializer;
import org.allaymc.server.block.type.BlockTypeInitializer;
import org.allaymc.server.block.type.InternalBlockTypeData;

import java.util.Map;

import static org.allaymc.server.utils.Utils.callInitializer;

/**
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockTypeRegistryPopulator implements RegistryPopulator<Map<Identifier, BlockType<?>>> {
    @Override
    public <REGISTRY extends Registry<Map<Identifier, BlockType<?>>>> void populate(REGISTRY registry) {
        InternalBlockTypeData.init();
        BlockLootTable.init();
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADING));
        var defaultInitializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockTypeDefaultInitializer.class);
        var initializers = ReflectionUtils.getAllStaticVoidParameterlessMethods(BlockTypeInitializer.class);
        try (var progressBar = ProgressBar
                .builder()
                .setInitialMax(defaultInitializers.size())
                .setTaskName("Loading Block Types")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            initializers.forEach(method -> callInitializer(method, null));
            defaultInitializers.forEach(method -> callInitializer(method, progressBar));
        }
        log.info(I18n.get().tr(TrKeys.A_BLOCKTYPE_LOADED, defaultInitializers.size()));
    }
}
