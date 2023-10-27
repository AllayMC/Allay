package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    @Override
    public int getId() {
        return 10;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.REGENERATION;
    }

    @Override
    public Color getColor() {
        return new Color(205, 92, 171);
    }
}
