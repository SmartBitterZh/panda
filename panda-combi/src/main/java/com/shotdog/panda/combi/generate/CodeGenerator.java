package com.shotdog.panda.combi.generate;

import com.shotdog.panda.common.model.Config;
import com.shotdog.panda.common.model.Table;

import java.util.List;

/***
 *
 * @author ziming  Create At 2018-11-24 14:40
 *
 */
public interface CodeGenerator {

    String generate(Config config, List<Table> tableList) throws Exception;
}
