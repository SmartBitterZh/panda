package com.shotdog.panda.combi.component;

import com.shotdog.panda.combi.model.GenerateConfig;

/***
 *
 * @author ziming  Create At 2018-11-24 12:47
 *
 */
public interface GenerateCodeComponent {

    /**
     * 根据配置参数生成代码
     * @param generateConfig  配置
     * @return
     */
    String generate(GenerateConfig generateConfig);

    /**
     * 自动以生成代码
     * @param generateConfig 配置
     * @return
     */
    String generateCustom(GenerateConfig generateConfig);
}

