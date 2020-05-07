# Wills 博客前后端分离后台接口代码

>  本项目使用springboot+es+shiro+mysql+redis等技术来构建





如果您想使用本项目

1. 请您修改ElasticSearch的服务地址，具体配置在resources里的Config.properties文件内
2. 然后在文件中使用mvn package进行打包
3. 再去target中找到项目打包的jar包即可在控制台使用java -jar blog.jar来开启项目
4. 访问localhost:8099/swagger-ui.html来访问相关接口信息
5. 搭配blog-web仓库运行前端vue页面进行访问进行页面展示