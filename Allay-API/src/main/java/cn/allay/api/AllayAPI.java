package cn.allay.api;

import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.palette.VanillaBlockPaletteRegistry;
import cn.allay.api.block.property.BlockPropertyTypeRegistry;
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
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 * <p>
 * This class is used to manage the implementation of the API to the module<br>
 * The Allay-API defines a number of interfaces that need to be implemented in advance, <br>
 * and in Allay-Server, this will be implemented through such registrations<br>
 * <p>
 * Note that for each interface that needs to be implemented, there is only one instance of the implementation class in runtime
 */
@Slf4j
@Getter
public final class AllayAPI {

    //TODO: move to file?Prevent problems caused by compiler inlining
    public static final String API_VERSION = "1.0.0";

    private static final AllayAPI INSTANCE = new AllayAPI();
    private final Map<Class<?>, ApiBindingAction<?>> bindings = new LinkedHashMap<>();
    private final Map<Class<?>, Consumer<?>> consumers = new HashMap<>();
    private boolean implemented = false;

    private AllayAPI() {
        defaultAPIRequirements();
    }

    /**
     * @return the API instance
     */
    public static AllayAPI getInstance() {
        return INSTANCE;
    }

    /**
     * After you have finished registering your implementation, you need to call this method to complete the implementation injection
     *
     * @throws MissingImplementationException If there are interface which are not been implemented
     */
    @SuppressWarnings("unchecked")
    public void implement(String coreName) throws MissingImplementationException {
        for (Map.Entry<Class<?>, ApiBindingAction<?>> entry : bindings.entrySet()) {
            if (entry.getValue() == null) {
                throw new MissingImplementationException("Missing binding for " + entry.getKey().getName());
            }
            var apiInstance = entry.getValue().bindingAction.get();
            ((Consumer<Object>) consumers.get(entry.getKey())).accept(apiInstance);
            if (entry.getValue().afterBound != null) {
                ((Consumer<Object>) entry.getValue().afterBound).accept(apiInstance);
            }
        }
        //TODO: multilingual support
        log.info("This server is running " + coreName + ", implement Allay-API version §b" + API_VERSION);
        implemented = true;
    }

    public <T> void requireImpl(Class<T> api) {
        requireImpl(api, null);
    }

    /**
     * Add an interface to be implemented<br>
     * It needs to be implemented by the server
     *
     * @param api the interface
     */
    public <T> void requireImpl(Class<T> api, @Nullable Consumer<T> apiInstanceConsumer) {
        bindings.put(api, null);
        if (apiInstanceConsumer != null) {
            consumers.put(api, apiInstanceConsumer);
        }
    }

    public <T> void bind(Class<T> api, Supplier<T> supplier) {
        bind(api, supplier, null);
    }

    public <T> void bind(Class<T> api, Supplier<T> bindingAction, @Nullable Consumer<T> afterBound) {
        Objects.requireNonNull(api);
        Objects.requireNonNull(bindingAction);
        bindings.put(api, new ApiBindingAction<>(bindingAction, afterBound));

    }

    /**
     * Each interface has only one instance of the corresponding implementation class, so if you call this method with the same parameters, you will return an identical object <br>
     * If the interface has not been implemented, it will throw an exception <br>
     *
     * @param api the interface
     * @return the implementation instance of the specific interface <br/>
     */
    public <T> T getAPIInstance(Class<T> api) {
        if (!implemented)
            throw new RuntimeException("AllayAPI::getAPIInstance cannot be called before it been implemented");
        return api.cast(bindings.get(api));
    }

    private void defaultAPIRequirements() {
        //Common
        requireImpl(Server.class, Server.INSTANCE::set);
        requireImpl(ComponentInjector.ComponentInjectorFactory.class, ComponentInjector.ComponentInjectorFactory.FACTORY::set);
        requireImpl(Scheduler.SchedulerFactory.class, Scheduler.SchedulerFactory.FACTORY::set);

        //Block
        requireImpl(BlockTypeBuilder.BlockTypeBuilderFactory.class, BlockTypeBuilder.FACTORY::set);
        requireImpl(BlockPropertyTypeRegistry.class, BlockPropertyTypeRegistry.REGISTRY::set);
        requireImpl(VanillaBlockPaletteRegistry.class, VanillaBlockPaletteRegistry.REGISTRY::set);
        requireImpl(VanillaBlockAttributeRegistry.class, VanillaBlockAttributeRegistry.REGISTRY::set);
        requireImpl(BlockTypeRegistry.class, BlockTypeRegistry.REGISTRY::set);

        //Item
        requireImpl(ItemTypeBuilder.ItemTypeBuilderFactory.class, ItemTypeBuilder.FACTORY::set);
        requireImpl(VanillaItemAttributeRegistry.class, VanillaItemAttributeRegistry.REGISTRY::set);
        requireImpl(ItemTypeRegistry.class, ItemTypeRegistry.REGISTRY::set);

        //Entity
        requireImpl(EntityTypeBuilder.EntityTypeBuilderFactory.class, EntityTypeBuilder.FACTORY::set);
        requireImpl(EntityTypeRegistry.class, EntityTypeRegistry.REGISTRY::set);
    }

    private record ApiBindingAction<T>(Supplier<T> bindingAction, @Nullable Consumer<T> afterBound) {
    }
}