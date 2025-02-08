package org.allaymc.server.pdc;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Primitives;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.pdc.ListPersistentDataType;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.pdc.PersistentDataType;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class PersistentDataTypeRegistry {
    private final Map<Class<?>, TagAdapter<?, ?>> adapters = new ConcurrentHashMap<>();

    private <T> TagAdapter<?, ?> createAdapter(Class<T> type) {
        if (!Primitives.isWrapperType(type)) {
            // int -> Integer
            type = Primitives.wrap(type);
        }

        if (Objects.equals(Byte.class, type)) {
            return this.createAdapter(Byte.class, Byte.class, v -> v, v -> v);
        }
        if (Objects.equals(Short.class, type)) {
            return this.createAdapter(Short.class, Short.class, v -> v, v -> v);
        }
        if (Objects.equals(Integer.class, type)) {
            return this.createAdapter(Integer.class, Integer.class, v -> v, v -> v);
        }
        if (Objects.equals(Long.class, type)) {
            return this.createAdapter(Long.class, Long.class, v -> v, v -> v);
        }
        if (Objects.equals(Float.class, type)) {
            return this.createAdapter(Float.class, Float.class, v -> v, v -> v);
        }
        if (Objects.equals(Double.class, type)) {
            return this.createAdapter(Double.class, Double.class, v -> v, v -> v);
        }
        if (Objects.equals(String.class, type)) {
            return this.createAdapter(String.class, String.class, v -> v, v -> v);
        }

        if (Objects.equals(byte[].class, type)) {
            return this.createAdapter(byte[].class, byte[].class, v -> v, v -> v);
        }
        if (Objects.equals(int[].class, type)) {
            return this.createAdapter(int[].class, int[].class, v -> v, v -> v);
        }
        if (Objects.equals(long[].class, type)) {
            return this.createAdapter(long[].class, long[].class, v -> v, v -> v);
        }

        if (Objects.equals(List.class, type)) {
            return this.createAdapter(List.class, NbtList.class, (dataType, list) -> {
                if (dataType instanceof ListPersistentDataType listDataType) {
                    var elementAdapter = this.getOrCreateAdapter(listDataType.getElementType());

                    List<Object> values = new ArrayList<>(list.size());
                    for (var primitiveValue : list) {
                        values.add(this.wrap(listDataType.getElementType(), primitiveValue));
                    }

                    return new NbtList(NbtType.byClass(elementAdapter.nbtBaseType), values);
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
        }

        // Custom
        if (Objects.equals(PersistentDataContainer.class, type)) {
            return this.createAdapter(AllayPersistentDataContainer.class, NbtMap.class, AllayPersistentDataContainer::toCompoundTag, tag -> {
                var container = new AllayPersistentDataContainer(this);
                container.putAll(tag);
                return container;
            });
        }

        if (Objects.equals(PersistentDataContainer[].class, type)) {
            return this.createAdapter(PersistentDataContainer[].class, List.class, containers -> {
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

        throw new IllegalArgumentException("Could not find a valid TagAdapter implementation for the requested type " + type.getSimpleName());
    }

    private <T, Z> TagAdapter<T, Z> createAdapter(
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

    private <T, Z> TagAdapter<T, Z> createAdapter(
            Class<T> primitiveType, Class<Z> nbtBaseType,
            BiFunction<PersistentDataType<T, ?>, T, Z> builder,
            BiFunction<PersistentDataType<T, ?>, Z, T> extractor,
            BiPredicate<PersistentDataType<T, ?>, Object> matcher
    ) {
        return new TagAdapter<>(primitiveType, nbtBaseType, builder, extractor, matcher);
    }

    public <T> Object wrap(final PersistentDataType<T, ?> type, final T value) {
        return this.getOrCreateAdapter(type).build(type, value);
    }

    public <T> boolean isInstanceOf(PersistentDataType<T, ?> type, Object value) {
        return this.getOrCreateAdapter(type).isInstance(type, value);
    }

    private <T, Z> TagAdapter<T, Z> getOrCreateAdapter(PersistentDataType<T, ?> type) {
        return (TagAdapter<T, Z>) this.adapters.computeIfAbsent(type.getPrimitiveType(), this.CREATE_ADAPTER);
    }

    public <T, Z> T extract(final PersistentDataType<T, ?> type, Z value) throws ClassCastException, IllegalArgumentException {
        var primitiveType = type.getPrimitiveType();
        TagAdapter<T, Z> adapter = this.getOrCreateAdapter(type);
        Preconditions.checkArgument(adapter.isInstance(type, value), "The found tag instance (%s) cannot store %s", value.getClass().getSimpleName(), primitiveType.getSimpleName());

        var foundValue = adapter.extract(type, value);
        Preconditions.checkArgument(primitiveType.isInstance(foundValue), "The found object is of the type %s. Expected type %s", foundValue.getClass().getSimpleName(), primitiveType.getSimpleName());
        return primitiveType.cast(foundValue);
    }

    @RequiredArgsConstructor
    private static class TagAdapter<P, T> {
        private final Class<P> primitiveType;
        private final Class<T> nbtBaseType;
        private final BiFunction<PersistentDataType<P, ?>, P, T> builder;
        private final BiFunction<PersistentDataType<P, ?>, T, P> extractor;
        private final BiPredicate<PersistentDataType<P, ?>, Object> matcher;

        public P extract(PersistentDataType<P, ?> dataType, T value) {
            Preconditions.checkArgument(this.isInstance(dataType, value), "Invalid type: " + value.getClass().getSimpleName());
            return extractor.apply(dataType, this.nbtBaseType.cast(value));
        }

        public Object build(PersistentDataType<P, ?> dataType, final P value) {
            Preconditions.checkArgument(this.primitiveType.isInstance(value), "Invalid value type: " + value.getClass().getSimpleName());
            return builder.apply(dataType, this.primitiveType.cast(value));
        }

        public boolean isInstance(PersistentDataType<P, ?> dataType, Object value) {
            return this.matcher.test(dataType, value);
        }
    }

    private final Function<Class<?>, TagAdapter<?, ?>> CREATE_ADAPTER = this::createAdapter;


}
