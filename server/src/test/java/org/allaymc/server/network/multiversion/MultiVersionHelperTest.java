package org.allaymc.server.network.multiversion;

import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.server.network.NetworkData;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.MANGROVE_LOG;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class MultiVersionHelperTest {

    @Test
    void testFurnaceRecipeDescriptorAdaptation() {
        var packet = NetworkData.CRAFTING_DATA_PACKET.get();
        var tagRecipeId = FurnaceRecipe.buildIdentifier(
                new ItemTagDescriptor(ItemTags.LOGS_THAT_BURN),
                FurnaceRecipe.Type.FURNACE
        ).toString();
        var tagRecipeData = findShapelessRecipe(packet, tagRecipeId);
        var networkDescriptor = assertInstanceOf(
                org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemTagDescriptor.class,
                tagRecipeData.getIngredients().getFirst().descriptor()
        );

        assertEquals(ItemTags.LOGS_THAT_BURN.name(), networkDescriptor.itemTag());
        assertNull(MultiVersionHelper.adaptCraftingData(tagRecipeData));

        var concreteRecipeId = FurnaceRecipe.buildIdentifier(
                MANGROVE_LOG.createItemStack(),
                FurnaceRecipe.Type.FURNACE
        ).toString();
        var concreteRecipeData = findShapelessRecipe(packet, concreteRecipeId);

        assertInstanceOf(FurnaceRecipeData.class, MultiVersionHelper.adaptCraftingData(concreteRecipeData));
    }

    private static ShapelessRecipeData findShapelessRecipe(
            org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket packet,
            String identifier
    ) {
        return packet.getCraftingData().stream()
                .filter(ShapelessRecipeData.class::isInstance)
                .map(ShapelessRecipeData.class::cast)
                .filter(recipe -> recipe.getId().equals(identifier))
                .findFirst()
                .orElseThrow();
    }
}
