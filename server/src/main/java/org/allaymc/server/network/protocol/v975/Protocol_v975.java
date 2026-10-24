package org.allaymc.server.network.protocol.v975;

import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.allaymc.server.network.protocol.NetworkItemPalette;
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

/**
 * {@code Protocol_v975} configures wire protocol 975 within the international
 * protocol inheritance chain. Furnace recipes use the shapeless crafting
 * representation with a network ID instead of the earlier legacy furnace
 * payload. The supplied ID stays associated with the original furnace recipe
 * through the common recipe-table construction.
 *
 * <p> The conversion preserves the recipe identifier, ingredient descriptor,
 * output list, crafting station, and priority, while adding an always-unlocked
 * requirement. This lets supported item-tag ingredients use the same descriptor
 * path as other shapeless recipes. The new UUID belongs to the encoded payload
 * rather than changing the server recipe identifier. Other processor and
 * definition behavior remains inherited, and the factory creates the v975
 * encoder for this protocol's completed data.
 */
public class Protocol_v975 extends Protocol_v944 {
    /**
     * Creates an uninitialized international protocol using the v975 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v975() {
        this(Bedrock_v975.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v975(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Encodes a furnace recipe as an indexed shapeless payload rather than a
     * legacy furnace entry. The supplied network ID is retained so the common
     * recipe-table builder can associate client selections with the originating
     * furnace recipe.
     *
     * <p> The payload preserves the ingredient descriptor, outputs, recipe
     * identifier, station type, and priority, including supported item-tag
     * descriptors. It adds a fresh payload UUID and an always-unlocked
     * requirement. Conversion errors propagate rather than returning
     * {@code null}, so unsupported descriptors cannot silently disappear from
     * this version's recipe table.
     */
    @Override
    protected RecipeData encodeFurnaceRecipe(FurnaceRecipe recipe, int networkId) {
        // Carry the assigned network ID in the shapeless representation so
        // furnace requests resolve through the same server-side recipe index.
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

    /**
     * Creates the {@code PacketEncoder_v975} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected NetworkBlockPalette createBlockPalette() {
        return NetworkBlockPalette.of("975");
    }

    @Override
    protected NetworkItemPalette createItemPalette() {
        return NetworkItemPalette.of("975");
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v975(data);
    }
}
