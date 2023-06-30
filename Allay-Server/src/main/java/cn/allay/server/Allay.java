package cn.allay.server;

import cn.allay.api.AllayAPI;
import cn.allay.api.MissingImplementationException;
import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.palette.VanillaBlockStatePalette;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.component.interfaces.ComponentInjector;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.biome.BiomeRegistry;
import cn.allay.server.block.attribute.AllayVanillaBlockAttributeRegistry;
import cn.allay.server.block.palette.AllayVanillaBlockStatePalette;
import cn.allay.server.block.type.AllayBlockStateHashPalette;
import cn.allay.server.block.type.AllayBlockType;
import cn.allay.server.block.type.AllayBlockTypeRegistry;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.entity.type.AllayEntityType;
import cn.allay.server.entity.type.AllayEntityTypeRegistry;
import cn.allay.server.item.attribute.AllayVanillaItemAttributeRegistry;
import cn.allay.server.item.type.AllayItemType;
import cn.allay.server.item.type.AllayItemTypeRegistry;
import cn.allay.server.scheduler.AllayScheduler;
import cn.allay.server.world.biome.AllayBiomeRegistry;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.VisibleForTesting;

@Slf4j
public final class Allay {

    public static void main(String[] args) {
        log.info("Starting Allay...");
        try {
            initAllayAPI();
        } catch (Exception e) {
            log.error("Cannot init Allay API!");
            e.printStackTrace();
            System.exit(1);
        }
        Server.getInstance().start();
    }

    @VisibleForTesting
    public static void initAllayAPI() throws MissingImplementationException {
        var api = AllayAPI.getInstance();
        //Common
        api.bind(ComponentInjector.ComponentInjectorFactory.class, () -> AllayComponentInjector::new);
        api.bind(Server.class, AllayServer::new);
        api.bind(Scheduler.SchedulerFactory.class, () -> AllayScheduler::new);

        //Block
        api.bind(BlockTypeBuilder.BlockTypeBuilderFactory.class, () -> AllayBlockType::builder);
        api.bind(VanillaBlockAttributeRegistry.class, () -> new AllayVanillaBlockAttributeRegistry(new AllayVanillaBlockAttributeRegistry.Loader()));
        api.bind(BlockStateHashPalette.class, AllayBlockStateHashPalette::new);
        api.bind(BlockTypeRegistry.class, AllayBlockTypeRegistry::new, instance -> ((AllayBlockTypeRegistry) instance).init());
        api.bind(VanillaBlockStatePalette.class, () -> new AllayVanillaBlockStatePalette(new AllayVanillaBlockStatePalette.Loader()));

        //Item
        api.bind(ItemTypeBuilder.ItemTypeBuilderFactory.class, () -> AllayItemType::builder);
        api.bind(VanillaItemAttributeRegistry.class, () -> new AllayVanillaItemAttributeRegistry(new AllayVanillaItemAttributeRegistry.Loader()));
        api.bind(ItemTypeRegistry.class, AllayItemTypeRegistry::new, instance -> ((AllayItemTypeRegistry) instance).init());

        //Entity
        api.bind(EntityTypeBuilder.EntityTypeBuilderFactory.class, () -> AllayEntityType::builder);
        api.bind(EntityTypeRegistry.class, AllayEntityTypeRegistry::new, instance -> ((AllayEntityTypeRegistry) instance).init());

        //Biome
        api.bind(BiomeRegistry.class, AllayBiomeRegistry::new);

        api.implement("Allay");
    }
}