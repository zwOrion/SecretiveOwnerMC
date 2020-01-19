package com.github.zworion.secretiveowner.event;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.advancement.TriggerLoader;
import com.github.zworion.secretiveowner.block.BlockLoader;
import com.github.zworion.secretiveowner.client.KeyLoader;
import com.github.zworion.secretiveowner.enchantment.EnchantmentLoader;
import com.github.zworion.secretiveowner.potion.PotionLoader;
import com.github.zworion.secretiveowner.sound.SoundLoader;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/13 19:52
 * 事件注册类
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class EventLoader {
    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
        EventLoader.EVENT_BUS.register(this);
    }

    /**
     * 实例化一个EventBus
     */
    public static final EventBus EVENT_BUS = new EventBus();

    /**
     * @param event 玩家右击事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/15 13:50
     * 订阅玩家点击草块事件
     */
    @SubscribeEvent
    public static void onPlayerClickGrassBlock(PlayerRightClickGrassBlockEvent event) {
        if (!event.world.isRemote) {
            BlockPos pos = event.pos;
            Entity tnt = new EntityTNTPrimed(event.world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, null);
            event.world.spawnEntity(tnt);
        }
    }

    /**
     * @param event 玩家与实体交互事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/15 13:51
     * 订阅玩家给猪喂食小麦或者小麦种子事件
     */
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        //获取事件玩家实体
        EntityPlayer player = event.getEntityPlayer();
        //判断交互对象是否是猪
        if (!player.world.isRemote && event.getTarget() instanceof EntityPig) {
            //获取猪实体的信息
            EntityPig pig = (EntityPig) event.getTarget();
            //获取玩家主手中的物品
            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            //判断玩家手中物品是否是小麦或者小麦种子
            boolean b = (stack.areCapsCompatible(new ItemStack(Items.WHEAT)) || stack.areCapsCompatible(new ItemStack(Items.WHEAT_SEEDS)));
            if (b) {
                if (!player.world.isRemote) {
                    if (player instanceof EntityPlayerMP) {
                        TriggerLoader.WORSE_THAN_PIG_TRIGGER.tigger((EntityPlayerMP) player, pig, stack);
                    }
                }
                //设置玩家伤害来源
                //伤害来源于猪的爆炸
                player.attackEntityFrom((new DamageSource("byPig")).setDifficultyScaled().setExplosion(), 25.0F);
                //在交互猪实体位置生成爆炸
                player.world.createExplosion(pig, pig.posX, pig.posY, pig.posZ, 100.0F, false);
                //猪死亡
                pig.setHealth(10.0F);

            }
        }
    }

    /**
     * @param event 方块掉落事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/16 12:21
     * 火焰灼烧附魔对应方块掉落事件
     */
    @SubscribeEvent
    public static void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        //判断事件发生是否在服务器上以及是否是玩家收获
        if (!event.getWorld().isRemote && event.getHarvester() != null) {
            //获取玩家主手物品
            ItemStack mainStack = event.getHarvester().getHeldItemMainhand();
            //获取玩家副手武平
            ItemStack offStack = event.getHarvester().getHeldItemOffhand();
            //判断物品是否有火焰灼烧附魔并且判断是不是剪刀
            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.fireBurn, mainStack) > 0 && mainStack.getItem() != Items.SHEARS) {
                //循环遍历掉落物
                for (int i = 0; i < event.getDrops().size(); i++) {
                    //获取掉落物stack
                    ItemStack stack = event.getDrops().get(i);
                    //获取掉落物经过熔炉熔炼后的产物
                    ItemStack newStack = FurnaceRecipes.instance().getSmeltingResult(stack);
                    //判断物品是否有熔炼后的产物
                    if (newStack != null) {
                        //将产物Stack复制
                        newStack = newStack.copy();
                        //设置产物的数量
                        newStack.setCount(stack.getCount());
                        //设置掉落物变为熔炼后的产物
                        event.getDrops().set(i, newStack);
                    } else if (stack != null) {
                        //获取方块
                        Block block = Block.getBlockFromItem(stack.getItem());
                        //判断方块是否为空
                        boolean b = (block == null);
                        //判断方块是否会着火
                        boolean b2 = (block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.DOWN)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.EAST)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.WEST)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.UP)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.SOUTH)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.NORTH));
                        //如果方块存在并且方块可以着火就移除方块
                        if (!b && b2) {
                            event.getDrops().remove(i);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param event 受伤事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/16 15:10
     * 生命受到伤害事件
     */
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        //判断伤害的来源是否是掉落
        if (event.getSource().getDamageType().equals(DamageSource.FALL.getDamageType())) {
            //获取药水效果
            PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionLoader.potionFallProtection);
            //判断效果是否为空
            if (effect != null) {
                //判断药水等级是否为0
                if (effect.getAmplifier() == 0) {
                    //伤害减半
                    event.setAmount(event.getAmount() / 2);
                } else {
                    //伤害置为0
                    event.setAmount(0);
                }
            }
        }
    }

    /**
     * @param event
     * @return void
     * @author ZWOrion
     * @date 2020/1/17 13:55
     * 按键输入事件，仅仅在客户端生效
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        //判断是否是自定义显示时间热键
        if (KeyLoader.showTime.isPressed()) {
            //获取玩家
            EntityPlayer player = Minecraft.getMinecraft().player;
            //获取世界
            World world = Minecraft.getMinecraft().world;
            //向玩家聊天窗口发送消息
            player.sendMessage(new TextComponentTranslation("chat.secretiveowner.time", world.getTotalWorldTime()));
        }
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/19 15:15
     * 玩家合成事件
     * @param event
     * @return void
     */
    @SubscribeEvent
    public static void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        //判断玩家合成的物品是否是草块
        if (event.crafting.getItem() == Item.getItemFromBlock(BlockLoader.grassBlock)) {
            //播放音效
            event.player.world.playSound(event.player, event.player.getPosition(), SoundLoader.TEST_SOUND, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }
}
