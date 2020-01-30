package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import com.github.zworion.secretiveowner.tileentity.TileEntityMetalFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/28 20:49
 * 金属炉
 */
public class BlockMetalFurnace extends BlockContainerState{
    /**
     * 方向
     */
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    /**
     * 是否燃烧
     */
    public static final PropertyBool BURNING = PropertyBool.create("burning");
    /**
     * 金属材质
     */
    public static final PropertyEnum<EnumMaterial> MATERIAL = PropertyEnum.create("material", EnumMaterial.class);

    public BlockMetalFurnace() {
        //材料
        super(Material.IRON);
        //国际化键名
        this.setTranslationKey("secretiveowner.metalFurnace");
        //硬度
        this.setHardness(2.5F);
        //声音类型
        this.setSoundType(SoundType.METAL);
        //创造模式物品栏
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        //注册名
        this.setRegistryName(SecretiveOwner.MODID, "metal_furnace");
        //默认BlockState
        this.setDefaultState(this.getBlockState().getBaseState()
                .withProperty(FACING, EnumFacing.NORTH)
                .withProperty(MATERIAL, EnumMaterial.IRON)
                .withProperty(BURNING, Boolean.FALSE));
    }

    /**
     * @return net.minecraft.block.state.BlockStateContainer
     * @author ZWOrion
     * @date 2020/1/29 14:30
     * 返回方块的状态
     */
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BURNING, MATERIAL);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     * 原版也是这个方法，没搞懂是不是1.13取消这个方法
     *
     * @param meta
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.byHorizontalIndex(meta & 3);
        Boolean burning = Boolean.valueOf((meta & 4) != 0);
        EnumMaterial material = EnumMaterial.values()[meta >> 3];
        return this.getDefaultState()
                .withProperty(FACING, facing)
                .withProperty(BURNING, burning)
                .withProperty(MATERIAL, material);
    }

    /**
     * Convert the BlockState into the correct metadata value
     *
     * @param state
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        int facing = state.getValue(FACING).getHorizontalIndex();
        int burning = state.getValue(BURNING).booleanValue() ? 4 : 0;
        int material = state.getValue(MATERIAL).ordinal() << 3;
        return facing | burning | material;
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     *
     * @param state
     * @param worldIn
     * @param pos
     */
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return super.getActualState(state, worldIn, pos);
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     *
     * @param state
     */
    @Override
    public int damageDropped(IBlockState state) {
        return super.damageDropped(state);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     * 这里使用了和原版熔炉相同的方法
     *
     * @param worldIn
     * @param pos
     * @param state
     * @param placer
     * @param stack
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityFurnace) {
                ((TileEntityFurnace) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    /**
     * Called when the block is right clicked by a player.
     * 熔炉被右击
     *
     * @param worldIn
     * @param pos
     * @param state
     * @param playerIn
     * @param hand
     * @param facing
     * @param hitX
     * @param hitY
     * @param hitZ
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //worldIn.setBlockState(pos, state.cycleProperty(BURNING));
        if(!worldIn.isRemote){
            TileEntityMetalFurnace tileEntityMetalFurnace = (TileEntityMetalFurnace)worldIn.getTileEntity(pos);
            ItemStackHandler up = (ItemStackHandler) tileEntityMetalFurnace.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
            ItemStackHandler down = (ItemStackHandler) tileEntityMetalFurnace.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
            String msg = String.format("Up: %s,Down: %s", up.getStackInSlot(0),down.getStackInSlot(0));
            playerIn.sendMessage(new TextComponentString(msg));
        }
        return true;
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     *
     * @param worldIn
     * @param pos
     * @param state
     */
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityMetalFurnace tileEntityMetalFurnace = (TileEntityMetalFurnace)worldIn.getTileEntity(pos);
        IItemHandler up = tileEntityMetalFurnace.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        IItemHandler down = tileEntityMetalFurnace.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        for (int i = up.getSlots()-1;i>=0;i--){
            if(up.getStackInSlot(i)!=null){
                Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(i));
                ((IItemHandlerModifiable)up).setStackInSlot(i, ItemStack.EMPTY);
            }
        }
        for (int i=down.getSlots()-1;i>0;i--){
            if(down.getStackInSlot(i)!=null){
                Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(i));
                ((IItemHandlerModifiable)down).setStackInSlot(i, ItemStack.EMPTY);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     *
     * @param itemIn
     * @param items
     */
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 8));
    }

    public enum EnumMaterial implements IStringSerializable {
        /**
         * 铁
         */
        IRON("iron"),
        /**
         * 金
         */
        GOLD("gold");

        private String name;

        EnumMaterial(String material) {
            this.name = material;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * Called throughout the code as a replacement for block instanceof BlockContainer
     * Moving this to the Block base class allows for mods that wish to extend vanilla
     * blocks, and also want to have a tile entity on that block, may.
     * <p>
     * Return true from this function to specify this block has a tile entity.
     *
     * @param state State of the current block
     * @return True if block has a tile entity, false otherwise
     */
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param worldIn
     * @param meta
     */
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMetalFurnace();
    }

   @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}
