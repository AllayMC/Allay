/*
 * Copyright (c) 2023, Kaooot
 */
package org.allaymc.data;

import com.google.common.base.Preconditions;
import com.google.gson.annotations.SerializedName;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.Allay;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.ContainerMixData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.*;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Allay Project 11/25/2023
 *
 * @author Kaooot | Cool_Loong
 */
@Deprecated
public class RecipeExportUtil {
    private static final BedrockCodec CODEC = ProtocolInfo.PACKET_CODEC;
    private static final Int2ObjectOpenHashMap<String> ITEM_RUNTIME_ID_TO_IDENTIFIER = new Int2ObjectOpenHashMap<>();
    private static final char[] SHAPE_CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    static {
        for (var i : VanillaItemId.values()) {
            ITEM_RUNTIME_ID_TO_IDENTIFIER.put(i.getRuntimeId(), i.getIdentifier().toString());
        }
        ITEM_RUNTIME_ID_TO_IDENTIFIER.trim();
    }

    @SneakyThrows
    public static void main(String[] args) {
        Allay.initI18n();
        Allay.initAllay();
        BedrockCodecHelper helper = CODEC.createHelper();
        helper.setEncodingSettings(EncodingSettings.CLIENT);
        helper.setItemDefinitions(
                SimpleDefinitionRegistry
                        .<ItemDefinition>builder()
                        .addAll(ItemTypeRegistry.getRegistry().getItemDefinitions())
                        .build()
        );

        helper.setBlockDefinitions(
                SimpleDefinitionRegistry
                        .<BlockDefinition>builder()
                        .addAll(Registries.BLOCK_DEFINITIONS.getContent())
                        .build()
        );
        try (InputStream resourceAsStream = RecipeExportUtil.class.getClassLoader().getResourceAsStream("crafting_data_packet.bin")) {
            Preconditions.checkNotNull(resourceAsStream);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(resourceAsStream.readAllBytes());
            // 跳过CraftingDataPacket的packet id
            byteBuf.skipBytes(1);
            CraftingDataPacket craftingDataPacket = new CraftingDataPacket();
            CODEC.getPacketDefinition(CraftingDataPacket.class).getSerializer().deserialize(byteBuf, helper, craftingDataPacket);
            writeRecipes(craftingDataPacket);
        }
    }

    @SneakyThrows
    public static void writeRecipes(CraftingDataPacket packet) {
        List<CraftingDataEntry> craftingData = new ArrayList<>();
        List<PotionMixDataEntry> potionMixes = new ArrayList<>();
        List<ContainerMixDataEntry> containerMixes = new ArrayList<>();

        for (RecipeData recipe : packet.getCraftingData()) {
            int type = recipe.getType().ordinal();

            String block = null;
            UUID uuid = null;
            String id = null;

            if (recipe instanceof TaggedCraftingData taggedCraftingData) {
                block = taggedCraftingData.getTag();
            }
            if (recipe instanceof UniqueCraftingData uniqueCraftingData) {
                uuid = uniqueCraftingData.getUuid();
            }
            if (recipe instanceof IdentifiableRecipeData identifiableRecipeData) {
                id = identifiableRecipeData.getId();
            }

            Integer priority = null;
            Object output = null;

            if (recipe instanceof CraftingRecipeData craftingRecipeData) {
                id = craftingRecipeData.getId();
                priority = craftingRecipeData.getPriority();
                output = writeRecipeItems(craftingRecipeData.getResults());
            }

            String[] entryShape = null;
            Object input = null;
            Integer width = null;
            Integer height = null;

            if (recipe instanceof ShapedRecipeData shapedRecipeData) {
                width = shapedRecipeData.getWidth();
                height = shapedRecipeData.getHeight();
                uuid = shapedRecipeData.getUuid();

                int charCounter = 0;

                RecipeItemDescriptor[] inputs = shapedRecipeData.getIngredients().stream().map(RecipeExportUtil::fromNetwork).toArray(RecipeItemDescriptor[]::new);
                Map<RecipeItemDescriptor, Character> charItemMap = new HashMap<>();
                char[][] shape = new char[height][width];

                for (int h = 0; h < height; h++) {
                    Arrays.fill(shape[h], ' ');

                    int index = h * width;

                    for (int w = 0; w < width; w++) {
                        int slot = index + w;
                        RecipeItemDescriptor descriptor = inputs[slot];

                        if (descriptor.getType().equalsIgnoreCase(ItemDescriptorType.INVALID.name().toLowerCase())) {
                            continue;
                        }

                        Character shapeChar = charItemMap.get(descriptor);

                        if (shapeChar == null) {
                            shapeChar = SHAPE_CHARS[charCounter++];

                            charItemMap.put(descriptor, shapeChar);
                        }

                        shape[h][w] = shapeChar;
                    }
                }

                List<String> shapes = new ArrayList<>();

                for (char[] chars : shape) {
                    shapes.add(new String(chars));
                }

                entryShape = shapes.toArray(Utils.EMPTY_STRING_ARRAY);

                Map<Character, RecipeItemDescriptor> charItemMapReversed = new HashMap<>();

                for (Map.Entry<RecipeItemDescriptor, Character> entry : charItemMap.entrySet()) {
                    charItemMapReversed.put(entry.getValue(), entry.getKey());
                }

                input = charItemMapReversed;
            }

            if (recipe instanceof ShapelessRecipeData shapelessRecipeData) {
                uuid = shapelessRecipeData.getUuid();
                input = writeRecipeItemDescriptors(shapelessRecipeData.getIngredients());
            }

            if (recipe instanceof FurnaceRecipeData furnaceRecipeData) {
                Integer damage = furnaceRecipeData.getInputData();

                if (damage == 0) {
                    damage = null;
                }

                input = new RecipeItem(ITEM_RUNTIME_ID_TO_IDENTIFIER.get(furnaceRecipeData.getInputId()), null, damage, null);
                output = itemFromNetwork(furnaceRecipeData.getResult());
            }

            Object base = null;
            Object addition = null;
            Object template = null;
            Object result = null;

            if (recipe instanceof SmithingTransformRecipeData) {
                base = fromNetwork(((SmithingTransformRecipeData) recipe).getBase());
                addition = fromNetwork(((SmithingTransformRecipeData) recipe).getAddition());
                template = fromNetwork(((SmithingTransformRecipeData) recipe).getTemplate());
                result = itemFromNetwork(((SmithingTransformRecipeData) recipe).getResult());
            }

            if (recipe instanceof SmithingTrimRecipeData smithingTrimRecipeData) {
                base = fromNetwork(smithingTrimRecipeData.getBase());
                addition = fromNetwork(smithingTrimRecipeData.getAddition());
                template = fromNetwork(smithingTrimRecipeData.getTemplate());
            }

            craftingData.add(new CraftingDataEntry(id, type, input, output, entryShape, block, uuid, priority, base, addition, template, result, width, height));
        }

        for (PotionMixData potionMix : packet.getPotionMixData()) {
            potionMixes.add(new PotionMixDataEntry(ITEM_RUNTIME_ID_TO_IDENTIFIER.get(potionMix.getInputId()), potionMix.getInputMeta(), ITEM_RUNTIME_ID_TO_IDENTIFIER.get(potionMix.getReagentId()), potionMix.getReagentMeta(), ITEM_RUNTIME_ID_TO_IDENTIFIER.get(potionMix.getOutputId()), potionMix.getOutputMeta()));
        }

        for (ContainerMixData containerMix : packet.getContainerMixData()) {
            containerMixes.add(new ContainerMixDataEntry(ITEM_RUNTIME_ID_TO_IDENTIFIER.get(containerMix.getInputId()), ITEM_RUNTIME_ID_TO_IDENTIFIER.get(containerMix.getReagentId()), ITEM_RUNTIME_ID_TO_IDENTIFIER.get(containerMix.getOutputId())));
        }

        Files.deleteIfExists(Path.of("Allay-Data/resources/recipes.json"));
        JSONUtils.toFile("./Allay-Data/resources/recipes.json",
                new Recipes(CODEC.getProtocolVersion(), craftingData, potionMixes, containerMixes),
                writer -> writer.setIndent("  "));
    }

    private static List<RecipeItem> writeRecipeItems(List<ItemData> inputs) {
        List<RecipeItem> outputs = new ArrayList<>();

        for (ItemData input : inputs) {
            RecipeItem item = itemFromNetwork(input);

            if (!"minecraft:air".equals(item.getId())) {
                outputs.add(item);
            }
        }

        return outputs;
    }

    private static List<RecipeItemDescriptor> writeRecipeItemDescriptors(List<ItemDescriptorWithCount> inputs) {
        List<RecipeItemDescriptor> outputs = new ArrayList<>();

        for (ItemDescriptorWithCount input : inputs) {
            RecipeItemDescriptor descriptor = fromNetwork(input);

            if (!"invalid".equalsIgnoreCase(descriptor.getType())) {
                outputs.add(descriptor);
            }
        }
        return outputs;
    }

    private static RecipeItem itemFromNetwork(ItemData itemData) {
        String id = itemData.getDefinition().getIdentifier();
        int count = itemData.getCount();
        Integer damage = itemData.getDamage();
        String tag = null;

        if (itemData.getTag() != null) {
            tag = AllayNbtUtils.nbtToBase64LE(itemData.getTag());
        }

        if (damage != null && (damage == 0 || damage == -1)) {
            damage = null;
        }

        return new RecipeItem(id, count, damage, tag);
    }

    private static RecipeItemDescriptor fromNetwork(ItemDescriptorWithCount descriptorWithCount) {
        ItemDescriptor itemDescriptor = descriptorWithCount.getDescriptor();

        String name = null;
        Integer itemId = null;
        Integer auxValue = null;
        String fullName = null;
        String itemTag = null;
        String tagExpression = null;
        Integer molangVersion = null;

        if (itemDescriptor instanceof ComplexAliasDescriptor complexAliasDescriptor) {
            name = complexAliasDescriptor.getName();
        } else if (itemDescriptor instanceof DefaultDescriptor defaultDescriptor) {
            itemId = defaultDescriptor.getItemId().getRuntimeId();
            auxValue = defaultDescriptor.getAuxValue();
        } else if (itemDescriptor instanceof DeferredDescriptor deferredDescriptor) {
            fullName = deferredDescriptor.getFullName();
        } else if (itemDescriptor instanceof ItemTagDescriptor itemTagDescriptor) {
            itemTag = itemTagDescriptor.getItemTag();
        } else if (itemDescriptor instanceof MolangDescriptor molangDescriptor) {
            tagExpression = molangDescriptor.getTagExpression();
            molangVersion = molangDescriptor.getMolangVersion();
        }

        return new RecipeItemDescriptor(itemDescriptor.getType().name().toLowerCase(), descriptorWithCount.getCount(), name, ITEM_RUNTIME_ID_TO_IDENTIFIER.get(itemId), auxValue, fullName, itemTag, tagExpression, molangVersion);
    }

    @Data
    @AllArgsConstructor
    public static class Recipes {
        private int version;
        private List<CraftingDataEntry> recipes;
        private List<PotionMixDataEntry> potionMixes;
        private List<ContainerMixDataEntry> containerMixes;
    }

    @Data
    @AllArgsConstructor
    static class CraftingDataEntry {
        private String id;
        private int type;
        private Object input;
        private Object output;
        private String[] shape;
        private String block;
        private UUID uuid;
        private Integer priority;
        private Object base;
        private Object addition;
        private Object template;
        private Object result;
        private Integer width;
        private Integer height;
    }

    @Data
    @AllArgsConstructor
    static class PotionMixDataEntry {
        private String inputId;
        private int inputMeta;
        private String reagentId;
        private int reagentMeta;
        private String outputId;
        private int outputMeta;
    }

    @Data
    @AllArgsConstructor
    static class ContainerMixDataEntry {
        private String inputId;
        private String reagentId;
        private String outputId;
    }

    @Data
    @AllArgsConstructor
    static class RecipeItem {
        private String id;
        private Integer count;
        private Integer damage;

        @SerializedName("nbt_b64")
        private String nbtBase64;
    }

    @Data
    @AllArgsConstructor
    static class RecipeItemDescriptor {
        private String type;
        private int count;
        private String name;
        private String itemId;
        private Integer auxValue;
        private String fullName;
        private String itemTag;
        private String tagExpression;
        private Integer molangVersion;
    }
}
