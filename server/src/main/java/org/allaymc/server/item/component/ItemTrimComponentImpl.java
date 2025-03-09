package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemTrimComponent;
import org.allaymc.server.item.component.event.CItemLoadExtraTagEvent;
import org.allaymc.server.item.component.event.CItemSaveExtraTagEvent;
import org.allaymc.server.registry.InternalRegistries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;

/**
 * @author IWareQ
 */
@Getter
public class ItemTrimComponentImpl implements ItemTrimComponent {
    protected TrimPattern pattern;
    protected TrimMaterial material;

    @Override
    public void trim(TrimPattern pattern, TrimMaterial material) {
        this.pattern = pattern;
        this.material = material;
    }

    @EventHandler
    public void onCItemLoadExtraTag(CItemLoadExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        extraTag.listenForCompound("Trim", trimNbt -> {
            trimNbt.listenForString("Pattern", patternId ->
                    pattern = InternalRegistries.TRIM_PATTERNS.getContent().stream().filter(pattern1 ->
                            pattern1.getPatternId().equals(patternId)
                    ).findFirst().orElse(null)
            );
            trimNbt.listenForString("Material", materialId ->
                    material = InternalRegistries.TRIM_MATERIALS.getContent().stream().filter(material ->
                            material.getMaterialId().equals(materialId)
                    ).findFirst().orElse(null)
            );
        });
    }

    @EventHandler
    public void onCItemSaveExtraTag(CItemSaveExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        if (pattern != null && material != null) {
            extraTag.putCompound("Trim", NbtMap.builder()
                    .putString("Pattern", pattern.getPatternId())
                    .putString("Material", material.getMaterialId())
                    .build());
        }
    }
}
