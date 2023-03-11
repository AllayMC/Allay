package cn.allay.api;

import cn.allay.component.interfaces.ComponentInjector;
import cn.allay.server.Server;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
@Getter
public final class AllayAPI {
    private static final AllayAPI INSTANCE = new AllayAPI();
    private final Map<Class<?>, Object> bindings = new HashMap<>();
    private boolean implemented = false;

    public static AllayAPI getInstance() {
        return INSTANCE;
    }

    public AllayAPI() {
        defaultAPIRequirements();
    }

    public void implement() throws MissingBindingException {
        for (Map.Entry<Class<?>, ?> entry : bindings.entrySet()) {
            if (entry.getValue() == null) {
                throw new MissingBindingException("Missing binding for " + entry.getKey().getName());
            }
        }
        implemented = true;
    }

    public void requireImpl(Class<?> api) {
        bindings.put(api, null);
    }

    public <T> void bind(Class<T> api, T instance) {
        Objects.requireNonNull(api);
        Objects.requireNonNull(instance);
        bindings.put(api, instance);
    }

    public <T> T getAPIInstance(Class<T> api) {
        if (!implemented) throw new RuntimeException("AllayAPI::getAPIInstance cannot be called before it been implemented");
        return api.cast(bindings.get(api));
    }

    private void defaultAPIRequirements() {
        requireImpl(Server.class);
        requireImpl(ComponentInjector.ComponentInjectorFactory.class);
    }
}