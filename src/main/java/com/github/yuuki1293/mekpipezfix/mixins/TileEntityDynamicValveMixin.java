package com.github.yuuki1293.mekpipezfix.mixins;

import com.github.yuuki1293.mekpipezfix.IValve;
import de.maxhenkel.pipez.utils.DummyFluidHandler;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.CapabilityTileEntity;
import mekanism.common.tile.multiblock.TileEntityDynamicValve;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = TileEntityDynamicValve.class)
public abstract class TileEntityDynamicValveMixin extends CapabilityTileEntity implements IValve {
    public TileEntityDynamicValveMixin(TileEntityTypeRegistryObject<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side){
        var cap = super.getCapability(capability, side);
        if (!cap.isPresent()) {
            if(capability == ForgeCapabilities.FLUID_HANDLER)
                return LazyOptional.of(() -> (T) DummyFluidHandler.INSTANCE);
        }
        return cap;
    }
}
