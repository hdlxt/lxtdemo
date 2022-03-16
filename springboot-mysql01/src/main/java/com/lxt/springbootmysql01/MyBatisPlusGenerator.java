package com.lxt.springbootmysql01;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig
                .Builder("jdbc:mysql://localhost:3307/test","root","root").build();
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig.Builder().outputDir(projectPath+"/src/main/java").openDir(false).build();
        PackageConfig packageConfig = new PackageConfig.Builder().moduleName("springboot-mysql01").parent("com.lxt.springbootmysql01").build();
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig).packageInfo(packageConfig);
        autoGenerator.execute();
    }
}
