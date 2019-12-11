package com.zc.generator.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ResourceBundle;

/**
 * @author zhanglobalConfighi
 * @date 2019/12/11
 */
public class AutoGenerator {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Mybatis-Plus");
        com.baomidou.mybatisplus.generator.AutoGenerator autoGenerator = new com.baomidou.mybatisplus.generator.AutoGenerator();

        GlobalConfig globalConfig = new GlobalConfig();
        // 本地输出路径
        globalConfig.setOutputDir(resourceBundle.getString("OutputDir"));
        // 是否覆盖
        globalConfig.setFileOverride(true);
        // 开启 activeRecord 模式
        globalConfig.setActiveRecord(true);
        // XML 二级缓存
        globalConfig.setEnableCache(false);
        // XML ResultMap
        globalConfig.setBaseResultMap(true);
        // XML columList
        globalConfig.setBaseColumnList(false);
        // @author
        globalConfig.setAuthor(resourceBundle.getString("author"));
        autoGenerator.setGlobalConfig(globalConfig);


        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername(resourceBundle.getString("userName"));
        dataSourceConfig.setPassword(resourceBundle.getString("passWord"));
        dataSourceConfig.setUrl(resourceBundle.getString("url"));
        autoGenerator.setDataSource(dataSourceConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 需要生成的表的前缀 ，strategy.setTablePrefix(new String[]{"ka_", "nf_"});
        // 需要生成的具体的表，strategy.setInclude(new String[]{"ka_app_act_config"});
        //表名生成策略，驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        autoGenerator.setStrategy(strategy);


        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(resourceBundle.getString("parent"));
        packageConfig.setController("web.controller");
        packageConfig.setEntity("service.api.model");
        packageConfig.setMapper("service.server.mapper");
        packageConfig.setXml("mapper");
        packageConfig.setService("service.api.service");
        packageConfig.setServiceImpl("service.server.service.impl");
        autoGenerator.setPackageInfo(packageConfig);

        // 执行生成
        autoGenerator.execute();
    }
}
