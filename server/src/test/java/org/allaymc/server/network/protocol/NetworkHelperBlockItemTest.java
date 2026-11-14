package org.allaymc.server.network.protocol;

import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(AllayTestExtension.class)
class NetworkHelperBlockItemTest {

    @Test
    void onlyBlockItemsCarryTheirBlockLikeBds() {
        // Recorded from BDS 1.26.52 creative content: these items place a block but are sent with block runtime id 0.
        for (var itemType : List.<ItemType<?>>of(
                ItemTypes.WOODEN_DOOR, ItemTypes.OAK_HANGING_SIGN, ItemTypes.CAULDRON, ItemTypes.WHEAT,
                ItemTypes.SUGAR_CANE, ItemTypes.RED_SHRUB
        )) {
            assertNull(encode(itemType).getBlockDefinition(), itemType.getIdentifier().toString());
        }
        // Block items, including the hidden item.* twins of the items above.
        for (var itemType : List.<ItemType<?>>of(
                ItemTypes.OAK_FENCE, ItemTypes.ITEM_CAULDRON, ItemTypes.ITEM_WHEAT, ItemTypes.ITEM_REEDS
        )) {
            assertNotNull(encode(itemType).getBlockDefinition(), itemType.getIdentifier().toString());
        }
    }

    private static ItemData encode(ItemType<?> itemType) {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193);
        return NetworkHelper.toNetwork(
                itemType.createItemStack(1),
                protocol.getItemDefinitionRegistry(),
                protocol.getBlockDefinitionRegistry()
        );
    }
}
