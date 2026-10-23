package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.component.event.CItemLoadExtraTagEvent;
import org.allaymc.server.item.component.event.CItemSaveExtraTagEvent;

import java.awt.*;

/**
 * {@code ItemDyeableComponentImpl} stores an optional item color and participates
 * in component extra-tag events. The component saves a present color as packed
 * RGB data under {@code "customColor"}; an absent color adds no entry, and a
 * load without that entry retains the current value.
 *
 * @author daoge_cmd
 */
@Getter
public class ItemDyeableComponentImpl implements ItemDyeableComponent {

    /**
     * {@code IDENTIFIER} names this behavior provider in the item component system,
     * allowing item type builders to select or replace this component by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_dyeable_component");

    /**
     * {@code TAG_CUSTOM_COLOR} identifies packed item color data shared by this
     * component's load and save event handlers.
     */
    protected static final String TAG_CUSTOM_COLOR = "customColor";

    /**
     * {@code customColor} holds the optional applied color. The generated getter
     * returns {@code null} until a setter or load event supplies a color.
     */
    protected Color customColor;

    /**
     * Replaces the applied color with the supplied value, including {@code null}.
     * A cleared color adds no entry during saving; loading without a color entry
     * leaves the current value unchanged.
     */
    @Override
    public void setCustomColor(Color color) {
        this.customColor = color;
    }

    /**
     * Loads a present packed RGB color from the component event's extra NBT.
     * The {@code Color(int)} conversion creates an opaque color, so saved alpha bits
     * do not survive this load path; an absent entry retains the current value.
     */
    @EventHandler
    protected void onLoadExtraTag(CItemLoadExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        // Color(int) interprets only RGB and makes the result opaque, so loading
        // this item format does not preserve a stored alpha channel.
        extraTag.listenForInt(TAG_CUSTOM_COLOR, value -> this.customColor = new Color(value));
    }

    /**
     * Adds the packed color to the shared save builder when a color is present.
     * A {@code null} color adds no entry; the packed value comes from
     * {@code Color.getRGB()} for later item persistence.
     */
    @EventHandler
    protected void onSaveExtraTag(CItemSaveExtraTagEvent event) {
        if (customColor != null) {
            event.getExtraTag().putInt(TAG_CUSTOM_COLOR, customColor.getRGB());
        }
    }
}
