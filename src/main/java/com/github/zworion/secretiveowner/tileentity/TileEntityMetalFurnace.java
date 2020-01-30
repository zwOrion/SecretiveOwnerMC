package com.github.zworion.secretiveowner.tileentity;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.block.BlockMetalFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/29 18:24
 */
public class TileEntityMetalFurnace extends TileEntity implements ITickable {
    protected int burnTime = 0;
    protected ItemStackHandler upInventory = new ItemStackHandler();
    protected ItemStackHandler downInventory = new ItemStackHandler();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            T result = (T) (facing == EnumFacing.DOWN ? downInventory : upInventory);
            return result;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.upInventory.deserializeNBT(compound.getCompoundTag("UpInventory"));
        this.downInventory.deserializeNBT(compound.getCompoundTag("DownInventory"));
        this.burnTime = compound.getInteger("BurnTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("UpInventory", this.upInventory.serializeNBT());
        compound.setTag("DownInventory", this.downInventory.serializeNBT());
        compound.setInteger("BurnTime", this.burnTime);
        return compound;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {
        if (!this.world.isRemote) {
            ItemStack itemStack = upInventory.extractItem(0, 1, true);
            IBlockState state = this.world.getBlockState(pos);
            if (itemStack != ItemStack.EMPTY && downInventory.insertItem(0, itemStack, true) == ItemStack.EMPTY) {
                this.world.setBlockState(pos, state.withProperty(BlockMetalFurnace.BURNING, Boolean.TRUE));
                int burnTotalTime = 200;
                switch (state.getValue(BlockMetalFurnace.MATERIAL)) {
                    case IRON:
                        burnTotalTime = 150;
                        break;
                    case GOLD:
                        burnTotalTime = 100;
                        break;
                    default:
                        break;
                }
                if (++this.burnTime >= burnTotalTime) {
                    this.burnTime = 0;
                    itemStack = upInventory.extractItem(0, 1, false);
                    downInventory.insertItem(0, itemStack, false);
                    this.markDirty();
                }
            } else {
                this.world.setBlockState(pos, state.withProperty(BlockMetalFurnace.BURNING, Boolean.FALSE));
            }
        }
    }

    /**
     * Called from Chunk.setBlockIDWithMetadata and Chunk.fillChunk, determines if this tile entity should be re-created when the ID, or Metadata changes.
     * Use with caution as this will leave straggler TileEntities, or create conflicts with other TileEntities if not used properly.
     *
     * @param world    Current world
     * @param pos      Tile's world position
     * @param oldState The old ID of the block
     * @param newSate
     * @return true forcing the invalidation of the existing TE, false not to invalidate the existing TE
     */
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
