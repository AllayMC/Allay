package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.component.event.CItemLoadExtraTagEvent;
import org.allaymc.server.item.component.event.CItemSaveExtraTagEvent;

import java.awt.*;

/**
 * Implementation of dyeable item component for leather armor.
 *
 * @author daoge_cmd
 */
@Getter
public class ItemDyeableComponentImpl implements ItemDyeableComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_dyeable_component");

    protected static final String TAG_CUSTOM_COLOR = "customColor";

    protected Color customColor;

    @Override
    public void setCustomColor(Color color) {
        this.customColor = color;
    }

    @EventHandler
    protected void onLoadExtraTag(CItemLoadExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        extraTag.listenForInt(TAG_CUSTOM_COLOR, value -> this.customColor = new Color(value));
    }

    @EventHandler
    protected void onSaveExtraTag(CItemSaveExtraTagEvent event) {
        if (customColor != null) {
            event.getExtraTag().putInt(TAG_CUSTOM_COLOR, customColor.getRGB());
        }
    }
}
