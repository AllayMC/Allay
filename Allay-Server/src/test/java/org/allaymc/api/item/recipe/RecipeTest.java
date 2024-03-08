package org.allaymc.api.item.recipe;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.DefaultDescriptor;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.api.item.interfaces.ItemGrassStack;
import org.allaymc.api.item.recipe.input.CraftingInput;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class RecipeTest {

    public static final Map<Character, ItemDescriptor> GRASS_KEY = Map.of('x', new DefaultDescriptor(GRASS_TYPE));

    @Test
    void testShapedRecipe() {
        var grassMagic1 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_1"))
                .tag("test_tag")
                .pattern(
                        ShapedRecipe.PatternHelper.build(
                                "xx",
                                "xx"
                        )
                )
                .keys(GRASS_KEY)
                .outputs(new ItemStack[]{diamond()})
                .build();

        assertEquals(new Identifier("minecraft:grass_magic_1"), grassMagic1.getName());
        assertEquals(DIAMOND_TYPE, grassMagic1.getOutputs()[0].getItemType());
        assertEquals("test_tag", grassMagic1.getTag());

        var input1 = new CraftingInput(
                grass(), grass(), air(),
                grass(), grass(), air(),
                air(),   air(),   air()
        );
        var input2 = new CraftingInput(
                grass(), grass(),
                grass(), grass()
        );
        var input3 = new CraftingInput(
                air(), grass(), grass(),
                air(), grass(), grass(),
                air(), air(),   air()
        );
        var input4 = new CraftingInput(
                air(), air(), air(),
                air(), grass(), grass(),
                air(), grass(), grass()
        );
        var input5 = new CraftingInput(
                air(), air(), air(),
                grass(), grass(), air(),
                grass(), grass(), air()
        );

        assertTrue(grassMagic1.match(input1));
        assertTrue(grassMagic1.match(input2));
        assertTrue(grassMagic1.match(input3));
        assertTrue(grassMagic1.match(input4));
        assertTrue(grassMagic1.match(input5));

        var input6 = new CraftingInput(
                grass(), grass(), grass(),
                grass(), grass(), grass(),
                grass(), grass(), grass()
        );
        var input7 = new CraftingInput(
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
                        ShapedRecipe.PatternHelper.build(
                                "x",
                                "x"
                        )
                )
                .keys(GRASS_KEY)
                .outputs(new ItemStack[]{diamond()})
                .build();

        var input8 = new CraftingInput(
                grass(), air(), air(),
                grass(), air(), air(),
                air(),   air(), air()
        );
        var input9 = new CraftingInput(
                air(), grass(), air(),
                air(), grass(), air(),
                air(),   air(), air()
        );
        var input10 = new CraftingInput(
                air(), air(), grass(),
                air(), air(), grass(),
                air(), air(), air()
        );
        var input11 = new CraftingInput(
                air(), air(), air(),
                grass(), air(), air(),
                grass(), air(), air()
        );
        var input12 = new CraftingInput(
                air(), air(), air(),
                air(), grass(), air(),
                air(), grass(), air()
        );
        var input13 = new CraftingInput(
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

        var grassMagic3 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_3"))
                .pattern(
                        ShapedRecipe.PatternHelper.build(
                                'x'
                        )
                )
                .keys(GRASS_KEY)
                .outputs(new ItemStack[]{diamond()})
                .build();

        var input14 = new CraftingInput(
                grass(), air(),
                air(), air()
        );
        var input15 = new CraftingInput(
                air(), grass(),
                air(), air()
        );
        var input16 = new CraftingInput(
                air(), air(),
                grass(), air()
        );
        var input17 = new CraftingInput(
                air(), air(),
                air(), grass()
        );

        assertTrue(grassMagic3.match(input14));
        assertTrue(grassMagic3.match(input15));
        assertTrue(grassMagic3.match(input16));
        assertTrue(grassMagic3.match(input17));
    }

    @Test
    void testShapeless() {
        var grassMagic1 = ShapelessRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_1"))
                .ingredients(
                        new ItemDescriptor[]{
                                new DefaultDescriptor(GRASS_TYPE),
                                new DefaultDescriptor(GRASS_TYPE),
                                new DefaultDescriptor(GRASS_TYPE)
                        }
                )
                .outputs(new ItemStack[]{diamond()})
                .build();

        var input1 = new CraftingInput(grass(), grass(), grass(), air());

        assertTrue(grassMagic1.match(input1));

        var input2 = new CraftingInput(
                grass(), grass(),
                grass(), grass());
        var input3 = new CraftingInput(
                grass(), grass(), grass(),
                diamond());

        assertFalse(grassMagic1.match(input2));
        assertFalse(grassMagic1.match(input3));
    }

    private ItemDiamondStack diamond() {
        return DIAMOND_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
    }


    private ItemGrassStack grass() {
        return GRASS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
    }

    private ItemAirStack air() {
        return AIR_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
    }
}