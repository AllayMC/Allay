package org.allaymc.server.network.protocol.v975;

import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v944.Protocol_v944;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v975.Bedrock_v975;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Protocol_v975 extends Protocol_v944 {
    public Protocol_v975() {
        this(Bedrock_v975.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v975(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected RecipeData encodeFurnaceRecipe(FurnaceRecipe recipe, int networkId) {
        return ShapelessRecipeData.of(
                CraftingDataType.SHAPELESS,
                recipe.getIdentifier().toString(),
                List.of(encodeItemDescriptorWithCount(recipe.getIngredient())),
                buildNetworkOutputs(recipe.getOutputs()),
                UUID.randomUUID(),
                recipe.getType().name().toLowerCase(Locale.ROOT),
                recipe.getPriority(),
                networkId,
                immutableRequirement(RecipeUnlockingRequirement.UnlockingContext.ALWAYS_UNLOCKED)
        );
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v975(data);
    }
}
