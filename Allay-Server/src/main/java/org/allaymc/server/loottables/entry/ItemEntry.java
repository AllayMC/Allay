package org.allaymc.server.loottables.entry;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.function.Functions;
import org.allaymc.api.utils.Identifier;

import java.util.Set;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public class ItemEntry extends BaseEntry<Context> {
    protected Functions functions;
    protected ItemType<?> itemType;

    public ItemEntry(String name, int weight, Conditions<Context> conditions, Functions functions) {
        super(name, weight, conditions);
        this.functions = functions;
        this.itemType = ItemTypeRegistry.getRegistry().get(new Identifier(name));
    }

    @Override
    public Set<ItemStack> loot(Context context) {
        var item = itemType.createItemStack();
        functions.apply(item);
        return Set.of(item);
    }

    @Override
    public String getType() {
        return "item";
    }
}
