package org.allaymc.server.pdc;

import com.google.common.base.Preconditions;
import org.allaymc.api.pdc.ListPersistentDataType;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.pdc.PersistentDataTypeRegistry;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * @author IWareQ
 */
public class AllayPersistentDataTypeRegistry implements PersistentDataTypeRegistry {
    private final Map<Class<?>, TagAdapter<?, ?>> adapters = new ConcurrentHashMap<>();

    public AllayPersistentDataTypeRegistry() {
        this.registerAdapters();
    }

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

        var dataContainerAdapter = this.createAdapter(AllayPersistentDataContainer.class, NbtMap.class, AllayPersistentDataContainer::toCompoundTag, tag -> {
            var container = new AllayPersistentDataContainer(this);
            container.putAll(tag);
            return container;
        });
        this.adapters.put(PersistentDataContainer.class, dataContainerAdapter);

        this.createAdapter(PersistentDataContainer[].class, List.class, containers -> {
            List<NbtMap> tags = new ArrayList<>();
            for (var container : containers) {
                tags.add(((AllayPersistentDataContainer) container).toCompoundTag());
            }
            return tags;
        }, tags -> {
            List<AllayPersistentDataContainer> containers = new ArrayList<>(tags.size());
            for (var tag : tags) {
                var container = new AllayPersistentDataContainer(this);
                container.putAll((NbtMap) tag);
                containers.add(container);
            }
            return containers.toArray(new AllayPersistentDataContainer[0]);
        });
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerPrimitiveAdapters(Class... primitiveTypes) {
        for (Class primitiveType : primitiveTypes) {
            this.createAdapter(primitiveType, primitiveType, v -> v, v -> v);
        }
    }

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

    @Override
    public <T> Object wrap(PersistentDataType<T, ?> type, final T value) {
        return this.getAdapter(type).build(type, value);
    }

    @Override
    public <T> boolean isInstanceOf(PersistentDataType<T, ?> type, Object value) {
        return this.getAdapter(type).isInstance(type, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T, Z> TagAdapter<T, Z> getAdapter(PersistentDataType<T, ?> type) {
        var adapter = this.adapters.get(type.getPrimitiveType());
        if (adapter == null) {
            throw new IllegalArgumentException("Could not find a tag adapter for " + type.getPrimitiveType().getSimpleName());
        }

        return (TagAdapter<T, Z>) adapter;
    }

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
