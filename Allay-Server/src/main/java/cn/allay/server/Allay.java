package cn.allay.server;

import cn.allay.api.AllayAPI;
import cn.allay.api.MissingImplementationException;
import cn.allay.api.block.component.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.blockentity.type.BlockEntityTypeBuilder;
import cn.allay.api.blockentity.type.BlockEntityTypeRegistry;
import cn.allay.api.component.interfaces.ComponentInjector;
import cn.allay.api.datastruct.DynamicURLClassLoader;
import cn.allay.api.entity.effect.EffectRegistry;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.item.component.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.enchantment.EnchantmentRegistry;
import cn.allay.api.item.type.CreativeItemRegistry;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import cn.allay.server.block.attribute.AllayVanillaBlockAttributeRegistry;
import cn.allay.server.block.type.AllayBlockStateHashPalette;
import cn.allay.server.block.type.AllayBlockType;
import cn.allay.server.block.type.AllayBlockTypeRegistry;
import cn.allay.server.blockentity.type.AllayBlockEntityType;
import cn.allay.server.blockentity.type.AllayBlockEntityTypeRegistry;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.entity.effect.AllayEffectRegistry;
import cn.allay.server.entity.type.AllayEntityType;
import cn.allay.server.entity.type.AllayEntityTypeRegistry;
import cn.allay.server.item.attribute.AllayVanillaItemAttributeRegistry;
import cn.allay.server.item.enchantment.AllayEnchantmentRegistry;
import cn.allay.server.item.type.AllayCreativeItemRegistry;
import cn.allay.server.item.type.AllayItemType;
import cn.allay.server.item.type.AllayItemTypeRegistry;
import cn.allay.server.scheduler.AllayScheduler;
import cn.allay.server.utils.ComponentClassCacheUtils;
import cn.allay.server.world.biome.AllayBiomeTypeRegistry;
import cn.allay.server.world.generator.jegenerator.JeGeneratorLoader;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.VisibleForTesting;

@Slf4j
public final class Allay {
    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());

    public static void main(String[] args) {
        log.info("Starting Allay...");
        try {
            initAllayAPI();
            JeGeneratorLoader.setup();
            JeGeneratorLoader.waitStart();
        } catch (Exception e) {
            log.error("Cannot init Allay API!", e);
            System.exit(1);
        }
        Server.getInstance().start();
    }

    @VisibleForTesting
    public static void initAllayAPI() throws MissingImplementationException {
        var api = AllayAPI.getInstance();
        if (api.isImplemented()) return;
        ComponentClassCacheUtils.checkCacheValid();
        ComponentClassCacheUtils.readCacheMapping();
        //Common
        api.bind(ComponentInjector.ComponentInjectorFactory.class, () -> AllayComponentInjector::new);
        api.bind(Server.class, AllayServer::getInstance);
        api.bind(Scheduler.SchedulerFactory.class, () -> asyncTaskExecutor -> {
            if (asyncTaskExecutor == null) return new AllayScheduler();
            else return new AllayScheduler(asyncTaskExecutor);
        });

        //Item
        api.bind(EnchantmentRegistry.class, AllayEnchantmentRegistry::new, instance -> ((AllayEnchantmentRegistry) instance).init());
        api.bind(ItemTypeBuilder.ItemTypeBuilderFactory.class, () -> AllayItemType::builder);
        api.bind(VanillaItemAttributeRegistry.class, () -> new AllayVanillaItemAttributeRegistry(new AllayVanillaItemAttributeRegistry.Loader()));
        api.bind(ItemTypeRegistry.class, AllayItemTypeRegistry::new, instance -> ((AllayItemTypeRegistry) instance).init());
        api.bind(CreativeItemRegistry.class, () -> new AllayCreativeItemRegistry(new AllayCreativeItemRegistry.Loader()));

        //BlockEntity
        api.bind(BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory.class, () -> AllayBlockEntityType::builder);
        api.bind(BlockEntityTypeRegistry.class, AllayBlockEntityTypeRegistry::new, instance -> ((AllayBlockEntityTypeRegistry) instance).init());

        //Block
        api.bind(BlockTypeBuilder.BlockTypeBuilderFactory.class, () -> AllayBlockType::builder);
        api.bind(VanillaBlockAttributeRegistry.class, () -> new AllayVanillaBlockAttributeRegistry(new AllayVanillaBlockAttributeRegistry.Loader()));
        api.bind(BlockStateHashPalette.class, AllayBlockStateHashPalette::new);
        api.bind(BlockTypeRegistry.class, AllayBlockTypeRegistry::new, instance -> ((AllayBlockTypeRegistry) instance).init());

        //Entity
        api.bind(EffectRegistry.class, AllayEffectRegistry::new, instance -> ((AllayEffectRegistry) instance).init());
        api.bind(EntityTypeBuilder.EntityTypeBuilderFactory.class, () -> AllayEntityType::builder);
        api.bind(EntityTypeRegistry.class, AllayEntityTypeRegistry::new, instance -> ((AllayEntityTypeRegistry) instance).init());

        //Biome
        api.bind(BiomeTypeRegistry.class, AllayBiomeTypeRegistry::new);
        api.implement("Allay");
        ComponentClassCacheUtils.saveCacheMapping();
    }
}