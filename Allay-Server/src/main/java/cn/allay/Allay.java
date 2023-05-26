package cn.allay;

import cn.allay.api.AllayAPI;
import cn.allay.api.MissingImplementationException;
import cn.allay.block.attribute.AllayVanillaBlockAttributeRegistry;
import cn.allay.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.palette.AllayVanillaBlockPaletteRegistry;
import cn.allay.block.palette.VanillaBlockPaletteRegistry;
import cn.allay.block.property.AllayBlockPropertyTypeRegistry;
import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.block.type.AllayBlockType;
import cn.allay.block.type.AllayBlockTypeRegistry;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;
import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.ComponentInjector;
import cn.allay.entity.type.AllayEntityType;
import cn.allay.entity.type.AllayEntityTypeRegistry;
import cn.allay.entity.type.EntityTypeBuilder;
import cn.allay.entity.type.EntityTypeRegistry;
import cn.allay.item.attribute.AllayVanillaItemAttributeRegistry;
import cn.allay.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.item.type.AllayItemType;
import cn.allay.item.type.AllayItemTypeRegistry;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;
import cn.allay.scheduler.AllayScheduler;
import cn.allay.scheduler.Scheduler;
import cn.allay.server.AllayServer;
import cn.allay.server.Server;
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
        Server.getInstance().initServer();
        Server.getInstance().startMainLoop();
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
        api.bind(BlockPropertyTypeRegistry.class, AllayBlockPropertyTypeRegistry::new);
        api.bind(VanillaBlockPaletteRegistry.class, () -> new AllayVanillaBlockPaletteRegistry(new AllayVanillaBlockPaletteRegistry.Loader()));
        api.bind(VanillaBlockAttributeRegistry.class, () -> new AllayVanillaBlockAttributeRegistry(new AllayVanillaBlockAttributeRegistry.Loader()));
        api.bind(BlockTypeRegistry.class, AllayBlockTypeRegistry::new, instance -> ((AllayBlockTypeRegistry) instance).init());

        //Item
        api.bind(ItemTypeBuilder.ItemTypeBuilderFactory.class, () -> AllayItemType::builder);
        api.bind(VanillaItemAttributeRegistry.class, () -> new AllayVanillaItemAttributeRegistry(new AllayVanillaItemAttributeRegistry.Loader()));
        api.bind(ItemTypeRegistry.class, AllayItemTypeRegistry::new, instance -> ((AllayItemTypeRegistry) instance).init());

        //Entity
        api.bind(EntityTypeBuilder.EntityTypeBuilderFactory.class, () -> AllayEntityType::builder);
        api.bind(EntityTypeRegistry.class, AllayEntityTypeRegistry::new, instance -> ((AllayEntityTypeRegistry) instance).init());

        api.implement("Allay");
    }
}