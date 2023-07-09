package cn.allay.server.block.type;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.MappedRegistry;
import cn.allay.api.registry.SimpleMappedRegistry;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectRBTreeMap;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/5/29
 *
 * @author Cool_Loong
 */
@Slf4j
public class AllayBlockStateHashPalette extends SimpleMappedRegistry<Integer, BlockState, Map<Integer, BlockState>> implements BlockStateHashPalette {

    private final List<Integer> paletteIndexList = new ArrayList<>();

    public AllayBlockStateHashPalette() {
        super(null, input -> new Int2ObjectOpenHashMap<>());
    }

    @Override
    public BlockState register(Integer hash, BlockState blockState) {
        return register(hash, blockState, true);
    }

    @Override
    public BlockState register(Integer hash, BlockState blockState, boolean assignPaletteIndex) {
        if (getContent().containsKey(hash)) throw new BlockStateHashCollisionException("Checked for possible block state hash collisions! Hash: " + hash + " ,BlockState: " + blockState);
        else {
            if (assignPaletteIndex) {
                paletteIndexList.add(hash);
                paletteIndexList.sort(Integer::compareUnsigned);
                ((AllayBlockType.AllayBlockState) blockState).setPaletteIndex(paletteIndexList.indexOf(hash));
            }
            return super.register(hash, blockState);
        }
    }

    @Override
    public void rebuildPaletteIndexList() {
        paletteIndexList.clear();
        paletteIndexList.addAll(mappings.keySet());
        paletteIndexList.sort(Integer::compareUnsigned);
        for (var blockState : mappings.values()) {
            ((AllayBlockType.AllayBlockState) blockState).setPaletteIndex(paletteIndexList.indexOf(blockState.blockStateHash()));
        }
    }
}