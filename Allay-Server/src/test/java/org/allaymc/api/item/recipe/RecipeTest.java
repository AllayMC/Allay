package org.allaymc.api.item.recipe;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.descriptor.ItemIdentifierDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.api.item.interfaces.ItemGrassStack;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class RecipeTest {

    public static final Map<Character, ItemDescriptor> GRASS_KEY = Map.of('x', new ItemIdentifierDescriptor(VanillaItemId.GRASS.getIdentifier()));

    @Test
    void testShapedRecipe() {
        ItemDiamondStack diamond = ItemDiamondStack.DIAMOND_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
        var grassMagic1 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_1"))
                .group("test_group")
                .tags(new String[]{"test_tag"})
                .pattern(
                        "xx",
                        "xx"
                )
                .keys(GRASS_KEY)
                .outputs(diamond)
                .build();

        assertEquals(new Identifier("minecraft:grass_magic_1"), grassMagic1.getIdentifier());
        assertEquals(ItemDiamondStack.DIAMOND_TYPE, grassMagic1.getOutputs()[0].getItemType());
        assertEquals("test_group", grassMagic1.getGroup());
        assertArrayEquals(new String[]{"test_tag"}, grassMagic1.getTags());

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
        var input5 = new ShapedInput(
                air(), air(), air(),
                grass(), grass(), air(),
                grass(), grass(), air()
        );

        assertTrue(grassMagic1.match(input1));
        assertTrue(grassMagic1.match(input2));
        assertTrue(grassMagic1.match(input3));
        assertTrue(grassMagic1.match(input4));
        assertTrue(grassMagic1.match(input5));

        var input6 = new ShapedInput(
                grass(), grass(), grass(),
                grass(), grass(), grass(),
                grass(), grass(), grass()
        );
        var input7 = new ShapedInput(
                air(), air(), air(),
                air(), air(), air(),
                air(), air(), air()
        );

        assertFalse(grassMagic1.match(input6));
        assertFalse(grassMagic1.match(input7));

        var grassMagic2 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_2"))
                .pattern(
                        "x",
                        "x"
                )
                .keys(GRASS_KEY)
                .outputs(diamond)
                .build();

        var input8 = new ShapedInput(
                grass(), air(), air(),
                grass(), air(), air(),
                air(),   air(), air()
        );
        var input9 = new ShapedInput(
                air(), grass(), air(),
                air(), grass(), air(),
                air(),   air(), air()
        );
        var input10 = new ShapedInput(
                air(), air(), grass(),
                air(), air(), grass(),
                air(), air(), air()
        );
        var input11 = new ShapedInput(
                air(), air(), air(),
                grass(), air(), air(),
                grass(), air(), air()
        );
        var input12 = new ShapedInput(
                air(), air(), air(),
                air(), grass(), air(),
                air(), grass(), air()
        );
        var input13 = new ShapedInput(
                air(), air(), air(),
                air(), air(), grass(),
                air(), air(), grass()
        );

        assertTrue(grassMagic2.match(input8));
        assertTrue(grassMagic2.match(input9));
        assertTrue(grassMagic2.match(input10));
        assertTrue(grassMagic2.match(input11));
        assertTrue(grassMagic2.match(input12));
        assertTrue(grassMagic2.match(input13));

        assertFalse(grassMagic2.match(input1));
        assertFalse(grassMagic2.match(input2));
        assertFalse(grassMagic2.match(input3));
        assertFalse(grassMagic2.match(input4));
        assertFalse(grassMagic2.match(input5));
        assertFalse(grassMagic2.match(input6));
        assertFalse(grassMagic2.match(input7));
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