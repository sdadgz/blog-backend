package cn.sdadgz.blogbackend.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * mp代码构造器
 *
 * <p>
 * 废物本物
 * </p>
 *
 * @author sdadgz
 * @since 2023/3/22 19:18:19
 */
public class Generator {
    public static void main(String[] args) {
        // java 输出目录
        String outputDir = "D:\\tool\\workspace\\idea\\blog-backend\\src\\main\\java";
        // resources 输出目录
        String mapperOutputDir = "D:\\tool\\workspace\\idea\\blog-backend\\src\\main\\resources"
                + "\\mapper";
        // 父项目包名
        String packagePath = "cn.sdadgz.blogbackend";

        // 表名
        String tableName = "post";

        // 这里也改改
        FastAutoGenerator.create("jdbc:mysql://sdadgz.cn:3307/blog_backend", "root", "sdadgz.cn")
                .globalConfig(builder -> builder.author("sdadgz") // 设置作者
                        .enableSwagger() // 开启 swagger 模式
                        .disableOpenDir()//完成后不打开文件夹
                        // 指定输出目录
                        //直接右键复制项目根目录的绝对路径
                        .outputDir(outputDir))
                .packageConfig(builder -> builder.parent(packagePath) // 设置父包名
                        // 设置mapperXml生成路径
                        //直接右键复制项目mapper文件夹的绝对路径
                        .pathInfo(Collections.singletonMap(OutputFile.xml, mapperOutputDir)))
                .strategyConfig(builder -> {
                    builder.addInclude(tableName)//设置表名
                            .entityBuilder()    //entity前置，才能用lombok
                            .enableLombok()  //使用lombok
                            .controllerBuilder()
                            .enableRestStyle() //restMapping注解
                            .mapperBuilder()
                            .enableMapperAnnotation(); // 开启mapper注解
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
