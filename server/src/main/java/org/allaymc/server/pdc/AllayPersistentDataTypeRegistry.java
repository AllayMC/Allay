package org.allaymc.server.pdc;

import com.google.common.base.Preconditions;
import org.allaymc.api.pdc.*;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * {@code AllayPersistentDataTypeRegistry} maps primitive classes to NBT conversion adapters for
 * custom metadata. Scalar adapters retain values directly, while list and container adapters
 * translate the structure required by NBT compounds and homogeneous lists. Complex converters
 * can share a primitive adapter because lookup uses the primitive class rather than the
 * application value's class.
 *
 * <p>
 * List conversion needs the element adapter even for an empty list, because the NBT list
 * retains an element tag type. The list matcher checks the container shape alone; extraction
 * then validates elements individually, so presence does not prove that every list element can
 * be decoded. Container extraction allocates a new entry map but retains raw values copied
 * from the compound, which can still share mutable storage.
 *
 * @author IWareQ
 */
public class AllayPersistentDataTypeRegistry implements PersistentDataTypeRegistry {
    /**
     * {@code adapters} maps primitive class tokens to the latest registered adapter.
     * Re-registering
     * a class replaces its converter for subsequent lookups, including existing containers using
     * this registry.
     */
    private final Map<Class<?>, TagAdapter<?, ?>> adapters = new ConcurrentHashMap<>();

    /**
     * Creates a registry and installs the built-in primitive and compound adapters.
     */
    public AllayPersistentDataTypeRegistry() {
        this.registerAdapters();
    }

    /**
     * Installs built-in adapters, replacing mappings for their primitive classes. Scalar
     * conversion
     * retains values, while compound extraction creates new container maps using this registry.
     *
     * <p>
     * List encoding selects an NBT element tag from the declared element adapter, including for
     * empty lists. The matcher accepts an {@code NbtList} without examining its element type, so
     * extraction validates each element separately and can fail after a presence check succeeded.
     * Container and array conversions allocate structural containers without promising independent
     * mutable element values.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void registerAdapters() {
        this.registerPrimitiveAdapters(
                Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, String.class,
                byte[].class, int[].class, long[].class
        );

        this.createAdapter(List.class, NbtList.class, (dataType, list) -> {
            if (dataType instanceof ListPersistentDataType listDataType) {
                var elementAdapter = this.getAdapter(listDataType.getElementType());

                List<Object> values = new ArrayList<>(list.size());
                for (var primitiveValue : list) {
                    values.add(this.wrap(listDataType.getElementType(), primitiveValue));
                }

                // Empty lists still need the declared element tag type so NBT can encode the list
                // consistently even when no value is available to infer that type.
                return new NbtList(NbtType.byClass(elementAdapter.getNbtBaseType()), values);
            } else {
                throw new IllegalArgumentException("The passed list cannot be written to the PDC with a "
                                                   + dataType.getClass().getSimpleName() +
                                                   " (expected a list data type)");
            }
        }, (dataType, tags) -> {
            if (dataType instanceof ListPersistentDataType listDataType) {
                List<Object> list = new ArrayList<>(tags.size());
                for (var primitiveValue : tags) {
                    list.add(this.extract(listDataType.getElementType(), primitiveValue));
                }

                return list;
            } else {
                throw new IllegalArgumentException("The found list tag cannot be read with a "
                                                   + dataType.getClass().getSimpleName() +
                                                   " (expected a list data type)");
            }
        }, (dataType, value) -> {
            if (!(dataType instanceof ListPersistentDataType)) {
                return false;
            }

            return value instanceof NbtList;
        });

        this.createAdapter(PersistentDataContainer.class, NbtMap.class, PersistentDataContainer::toNbt, tag -> {
            var container = new AllayPersistentDataContainer(this);
            container.putAll(tag);
            return container;
        });

        this.createAdapter(
                PersistentDataContainer[].class, List.class,
                containers -> Arrays.stream(containers).map(PersistentDataContainerView::toNbt).toList(),
                tags -> {
                    List<AllayPersistentDataContainer> containers = new ArrayList<>(tags.size());
                    for (var tag : tags) {
                        var container = new AllayPersistentDataContainer(this);
                        container.putAll((NbtMap) tag);
                        containers.add(container);
                    }
                    return containers.toArray(new AllayPersistentDataContainer[0]);
                }
        );
    }

    /**
     * Registers identity converters for the supplied supported primitive class tokens.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerPrimitiveAdapters(Class... primitiveTypes) {
        for (Class primitiveType : primitiveTypes) {
            this.createAdapter(primitiveType, primitiveType, v -> v, v -> v);
        }
    }

    /**
     * Registers an adapter whose matcher uses {@code nbtBaseType.isInstance}. The builder and
     * extractor ignore the persistent type argument, and registering an existing primitive class
     * replaces the adapter used by later lookups.
     */
    public <T, Z> TagAdapter<T, Z> createAdapter(
            Class<T> primitiveType,
            Class<Z> nbtBaseType,
            Function<T, Z> builder,
            Function<Z, T> extractor
    ) {
        return this.createAdapter(
                primitiveType, nbtBaseType,
                (type, t) -> builder.apply(t),
                (type, z) -> extractor.apply(z),
                (type, t) -> nbtBaseType.isInstance(t)
        );
    }

    /**
     * Creates and stores an adapter using the supplied conversion callbacks. Registration replaces
     * the previous adapter for {@code primitiveType}, and existing containers see that replacement
     * when they next resolve the same primitive class.
     */
    @Override
    public <T, Z> TagAdapter<T, Z> createAdapter(
            Class<T> primitiveType, Class<Z> nbtBaseType,
            BiFunction<PersistentDataType<T, ?>, T, Z> builder,
            BiFunction<PersistentDataType<T, ?>, Z, T> extractor,
            BiPredicate<PersistentDataType<T, ?>, Object> matcher
    ) {
        var adapter = new TagAdapter<>(primitiveType, nbtBaseType, builder, extractor, matcher);
        this.adapters.put(primitiveType, adapter);
        return adapter;
    }

    /**
     * Builds an encoded tag through the adapter for {@code type}. The adapter validates the
     * primitive value before invoking its builder; unsupported primitive classes fail instead of
     * being inserted into a container.
     */
    @Override
    public <T> Object wrap(PersistentDataType<T, ?> type, final T value) {
        return this.getAdapter(type).build(type, value);
    }

    /**
     * Returns the selected adapter's compatibility result for {@code value}. The matcher checks
     * stored representations without applying the complex converter, and list matching alone does
     * not validate element compatibility or custom converter constraints.
     */
    @Override
    public <T> boolean isInstanceOf(PersistentDataType<T, ?> type, Object value) {
        return this.getAdapter(type).isInstance(type, value);
    }

    /**
     * Returns the mapping for {@code type.getPrimitiveType()}, ignoring the complex class. The
     * adapter map can be updated after construction, so later calls can resolve a replacement
     * registered for the same primitive class.
     *
     * @throws IllegalArgumentException if no primitive adapter is registered
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T, Z> TagAdapter<T, Z> getAdapter(PersistentDataType<T, ?> type) {
        var adapter = this.adapters.get(type.getPrimitiveType());
        if (adapter == null) {
            throw new IllegalArgumentException("Could not find a tag adapter for " + type.getPrimitiveType().getSimpleName());
        }

        return (TagAdapter<T, Z>) adapter;
    }

    /**
     * Extracts a primitive value after validating the stored representation. The adapter matcher
     * must accept {@code value}, and the extractor result must be an instance of the requested
     * primitive class before it is returned to a complex converter.
     *
     * @throws IllegalArgumentException if the adapter is absent or a representation check rejects a non-{@code null} value
     */
    @Override
    public <T, Z> T extract(PersistentDataType<T, ?> type, Z value) throws ClassCastException, IllegalArgumentException {
        var primitiveType = type.getPrimitiveType();
        TagAdapter<T, Z> adapter = this.getAdapter(type);
        Preconditions.checkArgument(adapter.isInstance(type, value), "The found tag instance (%s) cannot store %s", value.getClass().getSimpleName(), primitiveType.getSimpleName());

        var foundValue = adapter.extract(type, value);
        Preconditions.checkArgument(primitiveType.isInstance(foundValue), "The found object is of the type %s. Expected type %s", foundValue.getClass().getSimpleName(), primitiveType.getSimpleName());
        return primitiveType.cast(foundValue);
    }
}
