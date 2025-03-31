package com.github.yuuki1293.mekpipezfix;

import de.maxhenkel.pipez.blocks.tileentity.PipeLogicTileEntity;
import de.maxhenkel.pipez.blocks.tileentity.PipeTileEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IValve {
    /**
     * Update pipez cache and reconnect.
     * @param self valve block entity
     * @param sides valid sides
     */
    default void mekpipezfix$updatePipezCache(BlockEntity self, Direction[] sides) {
        for (Direction side : sides) {
            var level = self.getLevel();
            if (level == null) return;
            var pipezSide = side.getOpposite();
            var pipePos = self.getBlockPos().relative(side);
            var be = level.getBlockEntity(pipePos);

            PipeTileEntity.markPipesDirty(level, pipePos);
            if (be instanceof PipeLogicTileEntity pipez && pipez.isExtracting(pipezSide)) {
                pipez.setExtracting(pipezSide, true);
            }
        }
    }
}
