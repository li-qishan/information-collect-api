//package com.hst.johns.collection.config;
//
//
//import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2//开启swagger2
//@EnableKnife4j//启用swagger2的一个ui样式
//public class Swagger2Config {
//
//    @Bean
//    public Docket createRestApi() {
//        return  new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.hst.johns.collection.modules"))//扫描哪个包下的接口
//                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求 Swagger 会扫描该包下所有 Controller 定义的 API，并产生文档内容（除了被 @ApiIgnore 指定的请求）
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("项目骨架相关接口文档")//标题文字
//                .description("项目骨架相关接口文档")//接口描述
//                .contact(new Contact("jhons-li","http://www.baidu.com",""))//联系人相关信息
//                .termsOfServiceUrl("http://localhost:7777/")//设置文档的License信息
//                .version("1.0")//接口版本号
//                .build();
//    }
//}
