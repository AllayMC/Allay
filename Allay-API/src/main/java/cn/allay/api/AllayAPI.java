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
 * <p/>
 * This class is used to manage the implementation of the API to the module<br/>
 * The Allay-API defines a number of interfaces that need to be implemented in advance, <br/>
 * and in Allay-Server, this will be implemented through such registrations<br/>
 * <p/>
 * Note that for each interface that needs to be implemented, there is only one instance of the implementation class in runtime
 */
@Getter
public final class AllayAPI {
    private static final AllayAPI INSTANCE = new AllayAPI();
    private final Map<Class<?>, Object> bindings = new HashMap<>();
    private boolean implemented = false;

    /**
     * @return the API instance
     */
    public static AllayAPI getInstance() {
        return INSTANCE;
    }

    private AllayAPI() {
        defaultAPIRequirements();
    }

    /**
     * After you have finished registering your implementation, you need to call this method to complete the implementation injection
     * @throws MissingImplementationException If there are interface which are not been implemented
     */
    public void implement() throws MissingImplementationException {
        for (Map.Entry<Class<?>, ?> entry : bindings.entrySet()) {
            if (entry.getValue() == null) {
                throw new MissingImplementationException("Missing binding for " + entry.getKey().getName());
            }
        }
        implemented = true;
    }

    /**
     * Add an interface to be implemented<br/>
     * It needs to be implemented by the server
     * @param api the interface
     */
    public void requireImpl(Class<?> api) {
        bindings.put(api, null);
    }

    public <T> void bind(Class<T> api, T instance) {
        Objects.requireNonNull(api);
        Objects.requireNonNull(instance);
        bindings.put(api, instance);
    }

    /**
     * @param api the interface
     * @return the implementation instance of the specific interface <br/>
     *         Each interface has only one instance of the corresponding implementation class, so if you call this method with the same parameters, you will return an identical object <br/>
     *         If the interface has not been implemented, it will throw an exception <br/>
     * @param <T> the interface type
     */
    public <T> T getAPIInstance(Class<T> api) {
        if (!implemented) throw new RuntimeException("AllayAPI::getAPIInstance cannot be called before it been implemented");
        return api.cast(bindings.get(api));
    }

    private void defaultAPIRequirements() {
        requireImpl(Server.class);
        requireImpl(ComponentInjector.ComponentInjectorFactory.class);
    }
}