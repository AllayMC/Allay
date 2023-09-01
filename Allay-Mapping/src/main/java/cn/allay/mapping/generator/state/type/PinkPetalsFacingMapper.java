package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "facing", blockRegex = "minecraft:pink_petals")
public class PinkPetalsFacingMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        int direction = switch (value) {
            case "south" -> 0;
            case "west" -> 1;
            case "north" -> 2;
            case "east" -> 3;
            default -> throw new IllegalArgumentException("Got " + value + " instead of a cardinal direction");
        };
        return Pair.of("direction", direction);
    }
}
