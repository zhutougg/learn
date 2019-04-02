什么dubbo?
阿里开源高性能分布式rpc。


使用场景？
单一框架变成分布式后，大量的服务交互。需要服务治理。比如配置管理,服务依赖,服务扩容



核心概念


同级别产品对比，优缺
rpc 	 														rest
服务方和调用接口依赖方式强，如果不一致,会导致编译不通过         接口和描述不一致，整合swagger
																http协议+rpc协议											





5.dubbo调用过程
https://www.jianshu.com/p/89b5cd823b27
https://wely.iteye.com/blog/2378164
https://www.jianshu.com/p/5b0c87f025d5  mockCluserInvokers集群容错
https://blog.csdn.net/why_still_confused/article/details/86599613 Dubbo协议及编码过程源码解析

zookeeper
注测创建节点。
每个节点变化都会有事件，通知订阅的url消费

集群容错  abstractClusterInvoker
1.failover cluster  失败自动切换，出现失败重试其他服务器
2.failfast cluster  快速失败，只发起一次调用，立即报错。等幂性写操作，新增记录
3.failsafe cluster  失败安全，出现异常，直接忽略.
4.failback cluster  失败定时重发.
5.forking			  并行多个服务器，只要一个成功就返回
6.broadcast		    广播所有调用者,任意一台报错就错。



代理对象                invokeInvocationHandler(jdk或者javassist调用方法)										mockCluserInvoker(是否mock->容错)
sayHello("world")		toString, hashCode, equals直接调用invoke(为啥不用报错)									如果是mock->mock		
						由调用的远程方法名和参数构建远程调用对象RPCInvocation									不需要mock， 直接调用FailoverClusterInvoker
						invoke																					先调FailoverClusterInvoker，调用失败在mock


负载均衡策略
random 随机
roundRobin  轮训
leastActive 方法级别的抵用方法最少的服务
consisterHash  一致性hash

滤器链
过滤器对调用进行一些处理
token处理
超时处理
异常处理
解码和编码
需要上文环境，记录用户名或者sessionId

获取调用结果：
Oneway返回空RpcResult
异步，直接返回空RpcResult, ResponseFuture回调
同步， ResponseFuture模式同步转异步，等待响应返回

						
						
failoverClusterInvoker	 									执行invoker							  exchageClient	
通过目录服务找到所有订阅服务提供者的invoker对象				经过过滤器链，内置实现了很多	根据url得到exchangeClient 
路由服务根据策略过滤选择调用的invoker														判断远程调用时同步还是异步还是oneway
通过负载均衡策略loadBalance来选择一个invoker	


消息格式 16字节
长度 	描述
2 		magic
1 		flag  第四位序列化工具  高四位中 第一位1 request 第二位1reponse 第三位1ping
1		staus
8       invokeId
4       bodyLength
body

this.codec = getChannelCodec(url);//根据url配置，构造编码解码器（通过spi得到DubboCountCodec类实例）


Dubbo协议特点：
ubbo、rmi、hessian、http、webservice、thrift、redis 传入传出参数数据包较小（建议小于100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用dubbo协议传输大文件或超大字符串，基于以上描述，我们一般建议Dubbo用于小数据量大并发的服务调用，以及服务消费者机器数远大于服务提供者机器数的情况。
RMI协议特点： 传入传出参数数据包大小混合，消费者与提供者个数差不多，可传文件。基于以上描述，我们一般对传输管道和效率没有那么高的要求，同时又有传输文件这一类的要求时，可以尝试采用RMI协议。【不过笔者不太喜欢这个，o(￣︶￣)o】
Hessian协议特点： 传入传出参数数据包大小混合，提供者比消费者个数多，可用浏览器查看，可用表单或URL传入参数，暂不支持传文件。比较适用于需同时给应用程序和浏览器JS使用的服

调用模型
事件处理的逻辑迅速完成，没有io。直接io线程上处理
事件处理的逻辑不能迅速完成，有io，则丢入线程池
在io线程处理事件,又发起新的io请求。会发生锁。不会真死

消息事件
请求
相应
连接
断开
心跳

dispacher类型
all
direct
message   		请求响应发送线程池
execution		请求
connection      

spi(Service Provider Interface)
类似spring aoi但是比较简单
meta/service/xxxxinterfaceName里面填写具体类
xxxxinterfaceName
  实现类
https://www.jianshu.com/p/a72856c77b6a 
  


