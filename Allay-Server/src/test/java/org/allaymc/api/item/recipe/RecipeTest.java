package org.allaymc.api.item.recipe;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.descriptor.ItemIdentifierDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.api.item.interfaces.ItemGrassStack;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class RecipeTest {
    @Test
    void testShapedRecipe() {
        ItemDiamondStack diamond = ItemDiamondStack.DIAMOND_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
        var grassMagic1 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:test_shaped"))
                .pattern(
                        "xx",
                        "xx"
                )
                .keys(Map.of('x', new ItemIdentifierDescriptor(VanillaItemId.GRASS.getIdentifier())))
                .output(diamond)
                .build();
        var input1 = new ShapedInput(
                grass(), grass(), air(),
                grass(), grass(), air(),
                air(),   air(),   air()
        );
        var input2 = new ShapedInput(
                grass(), grass(),
                grass(), grass()
        );
        var input3 = new ShapedInput(
                air(), grass(), grass(),
                air(), grass(), grass(),
                air(), air(),   air()
        );
        var input4 = new ShapedInput(
                air(), air(), air(),
                air(), grass(), grass(),
                air(), grass(), grass()
        );

        assertTrue(grassMagic1.match(input1));
        assertTrue(grassMagic1.match(input2));
        assertTrue(grassMagic1.match(input3));
        assertTrue(grassMagic1.match(input4));

        var input5 = new ShapedInput(
                grass(), grass(), grass(),
                grass(), grass(), grass(),
                grass(), grass(), grass()
        );
        var input6 = new ShapedInput(
                air(), air(), air(),
                air(), air(), air(),
                air(), air(), air()
        );

        assertFalse(grassMagic1.match(input5));
        assertFalse(grassMagic1.match(input6));
    }

    private ItemGrassStack grass() {
        return ItemGrassStack.GRASS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
    }

    private ItemAirStack air() {
        return ItemAirStack.AIR_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
    }
}