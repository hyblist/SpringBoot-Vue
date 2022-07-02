package com.yebin.springboot2.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 代码生成器，使用前记得备份，以防丢失代码，当然可以把.fileOverride()给注释了
 * @author yebin
 * @since 20220629
 */

public class CodeGenerator {

//    //建议谨慎而行
//    public static void main(String[] args) {
//        generate();
//    }
    private static void generate(){
        //在官网取代码:https://www.baomidou.com/ ——>代码生成器
        FastAutoGenerator.create(
                "jdbc:mysql://localhost:3306/my_project?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8",
                "root", "123456") //数据库信息
                .globalConfig(builder -> {
                    builder.author("ye_bin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件，考虑清楚后就可以开启
                            .outputDir("F:\\Project\\springboot2\\src\\main\\java\\"); // 指定输出目录,一般指java
                })
                .packageConfig(builder -> {
                    builder.parent("com.yebin.springboot2") // 设置父包名
                            .moduleName(null) // 设置父包模块名,这样生成得连接就少一条"\"
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F:\\Project\\springboot2\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok(); //采用Lombok依赖
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    //听说下面两个配置很关键，到时候看
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
