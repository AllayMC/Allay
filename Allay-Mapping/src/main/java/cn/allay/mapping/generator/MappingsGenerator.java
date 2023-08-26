package cn.allay.mapping.generator;

import cn.allay.api.block.component.impl.attribute.BlockAttributes;
import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identifier;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappingsGenerator {
    private static String stateId(String identifier, BlockState blockState) {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(identifier);
        if (!blockState.getValues().isEmpty()) {
            stringbuilder.append('[');
            stringbuilder.append(blockState.getValues().entrySet().stream().map(PROPERTY_ENTRY_TO_STRING_FUNCTION).collect(Collectors.joining(",")));
            stringbuilder.append(']');
        }
        return stringbuilder.toString();
    }

    private static final Path OUTPUT = Path.of("output");
    static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    static final Function<Map.Entry<Property<?>, Comparable<?>>, String> PROPERTY_ENTRY_TO_STRING_FUNCTION;

    static {
        try {
            Field propertyEntryToStringFunction = StateHolder.class.getDeclaredField("PROPERTY_ENTRY_TO_STRING_FUNCTION");
            propertyEntryToStringFunction.setAccessible(true);
            PROPERTY_ENTRY_TO_STRING_FUNCTION = (Function<Map.Entry<Property<?>, Comparable<?>>, String>) propertyEntryToStringFunction.get(StateHolder.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        File file = OUTPUT.toFile();
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void generateBlocks() {
        for (var entry : BuiltInRegistries.BLOCK.entrySet()) {
            Block value = entry.getValue();
            String identifier = BuiltInRegistries.BLOCK.getKey(value).toString();
            ImmutableList<BlockState> allStates = value.getStateDefinition().getPossibleStates();
            Map<Integer, BlockAttributes> integerBlockAttributesMap = VanillaBlockAttributeRegistry.getRegistry().get(VanillaBlockId.fromIdentifier(new Identifier(identifier)));
            if (integerBlockAttributesMap == null) {
                System.out.println(identifier);
            }
        }
    }
}
