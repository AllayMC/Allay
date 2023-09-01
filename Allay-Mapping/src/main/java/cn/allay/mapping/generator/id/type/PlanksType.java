package cn.allay.mapping.generator.id.type;

import cn.allay.mapping.generator.id.IdentifierMapper;
import cn.allay.mapping.generator.id.IdentifierRemapper;
import org.apache.commons.lang3.tuple.Pair;


@IdentifierRemapper(blockRegex = ".*_planks$")
public class PlanksType implements IdentifierMapper {
    @Override
    public Pair<String, Pair<String, Object>> translateIdentifier(String fullIdentifier) {
        return Pair.of("minecraft:planks", null);
    }
}
