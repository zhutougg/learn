https://www.tutorialspoint.com/spring_boot/spring_boot_zuul_proxy_server_and_routing.htm
Externalized Properties
-Dspring.config.location = C:\application.properties

激活普通,dev,prod
--spring.profiles.acitve=dev

Console Log Output
java –jar demo.jar --debug
debug = true

File Log Output
logging.path = /var/tmp/
logging.file = /var/tmp/mylog.log
logging.level.root = WARN

logback
<configuration>
   <appender name = "STDOUT" class = "ch.qos.logback.core.ConsoleAppender"></appender>
   <root level = "INFO">
      <appender-ref ref = "STDOUT"/> 
   </root>
</configuration>

<?xml version = "1.0" encoding = "UTF-8"?>
<configuration>
   <appender name = "FILE" class = "ch.qos.logback.core.FileAppender">
      <File>/var/tmp/mylog.log</File>
   </appender>   
   <root level = "INFO">
      <appender-ref ref = "FILE"/>
   </root>
</configuration>



//动态url
@RequestMapping(value = "/products")
public ResponseEntity<Object> createProduct(@RequestBody Product product) {
}

Request Parameter
public ResponseEntity<Object> getProduct(
   @RequestParam(value = "name", required = false, defaultValue = "honey") String name) {
}

get vs post
get所有参数拼接到url
post参数在client


method.get   list
method.post  put/createProduct
method.delete 删除


//异常处理
异常接口
package com.tutorialspoint.demo.exception;
public class ProductNotfoundException extends RuntimeException {
   private static final long serialVersionUID = 1L;
}
处理异常
package com.tutorialspoint.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
   @ExceptionHandler(value = ProductNotfoundException.class)
   public ResponseEntity<Object> exception(ProductNotfoundException exception) {
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
   }
}
异常引用
package com.tutorialspoint.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialspoint.demo.exception.ProductNotfoundException;
import com.tutorialspoint.demo.model.Product;

@RestController
public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      productRepo.put(almond.getId(), almond);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      if(!productRepo.containsKey(id))throw new ProductNotfoundException();
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
   }
}

拦截器
preHandle()
postHandle()
afterCompletion()
@Component
public class ProductServiceInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	  {
      
      return true;
	}
   @Override
   public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler, 
      ModelAndView modelAndView) throws Exception 
	  {
	  
	  }
   
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
      Object handler, Exception exception) throws Exception 
	  {
	  }
}

端口
server:port =0 随机
server.port = 1000

服务正常注册，最大可能会有120s滞后

30(首次注册 init registe) + 30(readOnlyCacheMap)+30(client fetch interval)+30(ribbon)=120
如果是在Spring Cloud环境下使用这些组件(Eureka, Ribbon)，不会有首次注册30秒延迟的问题，服务启动后会马上注册,所以从注册到发现，最多可能是90s。




服务异常下线：最大可能会有270s滞后
定时清理任务每eureka.server. evictionIntervalTimerInMs(默认60)执行一次清理任务
每次清理任务会把90秒(3个心跳周期，eureka.instance.leaseExpirationDurationInSeconds)没收到心跳的踢除，但是根据官方的说法 ，因为代码实现的bug，这个时间其实是两倍，即180秒，也就是说如果一个客户端因为网络问题或者主机问题异常下线，可能会在180秒后才剔除
读取端，因为readOnlyCacheMap以及客户端缓存的存在，可能会在30(readOnlyCacheMap)+30(client fetch interval)+30(ribbon)=90
所以极端情况最终可能会是180+90=270


