什么时候使用mybaits，什么时候使用jpa
1.负载sql join 几张表 count 
2.dba 对sql性能有要求
简单sql可以使用jpa

spring
spring boot
使用mybaits


简单配置
mybatis.mapper-location=class*:mapper/**/*.xml
mybatis.type-aliases-package=类型别名的包名
mybatis.type-handlers-packge=typeHandler扫描包名
mybatis.configuration.map-undersocore-to-camel-case = true



mybaits2个工具
 generator
 mvn mybaits-generator:generate