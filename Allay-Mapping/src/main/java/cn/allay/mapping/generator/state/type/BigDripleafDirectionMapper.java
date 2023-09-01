package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "facing", blockRegex = "^minecraft:big_dripleaf|^minecraft:big_dripleaf_stem")
public class BigDripleafDirectionMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("direction", switch (value) {
            case "south" -> 0;
            case "west" -> 1;
            case "north" -> 2;
            case "east" -> 3;
            default -> throw new RuntimeException("Could not determine big dripleaf direction!");
        });
    }
}
