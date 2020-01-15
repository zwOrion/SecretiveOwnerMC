package com.github.zworion.secretiveowner.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/09 11:51
 */
public class ConfigLoader {
    /**
     * 声明一个 Configuration 类
     */
    private static Configuration configuration;
    private static Logger logger;
    public static int burnTime;

    public ConfigLoader(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        //在根目录位置 config 文件夹下生成 <Mod id>.cfg 的配置文件
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        //读入配置
        configuration.load();
        //加载配置
        load();
    }

    private static void load() {
        logger.info("Started loading config. ");
        String comment;
        //读取配置文件
        comment = "How many seconds can a diamond burn in a furnace. ";
        //get方法的第一个参数就是表示“general”类别。
        //get方法的第二个参数是表示该键的名字
        //get方法的第三个参数，是该键的默认值（这里是640），当对应的键不存在时，就会返回该默认值。
        //get方法的第四个参数，是该键的注释，用于描述该项配置的。
        burnTime = configuration.get(Configuration.CATEGORY_GENERAL, "diamondBurnTime", 640, comment).getInt();
        //保存配置
        configuration.save();
        logger.info("Finished loading config. ");
    }

}
