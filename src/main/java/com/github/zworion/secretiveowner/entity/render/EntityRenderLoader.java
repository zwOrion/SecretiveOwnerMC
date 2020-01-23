package com.github.zworion.secretiveowner.entity.render;

import com.github.zworion.secretiveowner.entity.EntityLoader;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/22 14:24
 * 实体模型注册类
 */
public class EntityRenderLoader {

    public  EntityRenderLoader(){
        //注册实体模型
        EntityLoader.registerRenders();
    }
}
