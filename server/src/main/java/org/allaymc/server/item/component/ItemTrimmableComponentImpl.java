package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.data.TrimMaterial;
import org.allaymc.api.item.data.TrimPattern;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.component.event.CItemLoadExtraTagEvent;
import org.allaymc.server.item.component.event.CItemSaveExtraTagEvent;
import org.allaymc.server.registry.InternalRegistries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code ItemTrimmableComponentImpl} holds an item's armor trim pattern and
 * material and handles their extra-tag persistence. Loading resolves stored names
 * through the internal trim registries. Saving emits the {@code "Trim"} compound
 * only when both values are present.
 *
 * @author IWareQ
 */
@Getter
public class ItemTrimmableComponentImpl implements ItemTrimmableComponent {
    /**
     * {@code IDENTIFIER} names this behavior provider in the item component system,
     * allowing item type builders to select or replace this component by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_trimmable_component");

    /**
     * {@code pattern} holds the selected trim pattern or {@code null}. Saving requires
     * both {@code pattern} and {@code material}, while loading resolves each part
     * independently from its registry.
     */
    protected TrimPattern pattern;
    /**
     * {@code material} holds the selected trim material or {@code null}. An absent or
     * unknown registry entry can leave the pair incomplete, which suppresses trim
     * serialization.
     */
    protected TrimMaterial material;

    /**
     * Assigns the supplied trim pattern and material without resolving or validating
     * them. Either value may be {@code null}; the save handler emits a trim only
     * when both assigned values are present.
     */
    @Override
    public void trim(TrimPattern pattern, TrimMaterial material) {
        this.pattern = pattern;
        this.material = material;
    }

    /**
     * Resolves present trim names from the extra-tag load event.
     * Absent entries retain the current corresponding value; a present unknown name
     * assigns the registry's {@code null} result, potentially leaving an incomplete
     * trim pair.
     */
    @EventHandler
    public void onCItemLoadExtraTag(CItemLoadExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        extraTag.listenForCompound("Trim", trimNbt -> {
            trimNbt.listenForString("Pattern", patternId -> pattern = InternalRegistries.TRIM_PATTERNS.get(patternId));
            trimNbt.listenForString("Material", materialId -> material = InternalRegistries.TRIM_MATERIALS.get(materialId));
        });
    }

    /**
     * Writes the trim's pattern and material names when both values are present.
     * An incomplete pair contributes no {@code "Trim"} entry to the shared save
     * builder; the handler does not synthesize a missing value.
     */
    @EventHandler
    public void onCItemSaveExtraTag(CItemSaveExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        if (pattern != null && material != null) {
            extraTag.putCompound("Trim", NbtMap.builder()
                    .putString("Pattern", pattern.patternId())
                    .putString("Material", material.materialId())
                    .build());
        }
    }
}
