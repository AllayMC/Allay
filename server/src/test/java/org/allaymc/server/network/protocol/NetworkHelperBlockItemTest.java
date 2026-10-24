package org.allaymc.server.network.protocol;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void blockItemsKeepTheirMetaInTheBlockLikeBds() {
        // BDS 1.26.52 sends the creative piston (meta 1) as an upward piston with meta 0.
        var piston = encode(ItemTypes.PISTON, 1);
        assertEquals(0, piston.getDamage());
        assertEquals(1961230256, piston.getBlockDefinition().runtimeId());
        // A meta the table does not know falls back to the default block, still with meta 0.
        var unknownMeta = encode(ItemTypes.PISTON, 5);
        assertEquals(0, unknownMeta.getDamage());
        assertEquals(ItemTypes.PISTON.getBlockType().getDefaultState().blockStateHash(), unknownMeta.getBlockDefinition().runtimeId());
        // Items that are not block items keep their meta.
        assertEquals(3, encode(ItemTypes.WOODEN_DOOR, 3).getDamage());
    }

    private static ItemData encode(ItemType<?> itemType) {
        return encode(itemType, 0);
    }

    private static ItemData encode(ItemType<?> itemType, int meta) {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193);
        return NetworkHelper.toNetwork(
                itemType.createItemStack(ItemStackInitInfo.builder().count(1).meta(meta).build()),
                protocol.getItemDefinitionRegistry(),
                protocol.getBlockDefinitionRegistry(),
                protocol.getData().blockPalette(),
                protocol.getData().itemPalette()
        );
    }
}
