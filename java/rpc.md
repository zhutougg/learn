rpc概念？
屏蔽底层的传输方式（tcp、udp）,序列方式(xml,json,二进制),服务调用者想本地调用
序列方式：googleprotobuf hession thrift
rpc发展？
SOA，Webservice，SOAP，REST，RPC，RMI的区别与联系
soa:服务提供一个简单的接口,用户可以访问。不依赖技术是一种思想。
因此REST、SOAP、RPC、RMI、DCOM等都是SOA的一种实现而已
rmi是java分布式远程调用
webservice是一种标准 soap,rest
soap协议基于xml设计web交互


rpc调用过程？
1.客户端调用过程
k=add(i,j)
2.存根程序创建消息
k=add(i,j)客户存根
proc:add
int:val(i)
int:val(j)
3.通过网络发送消息


4.服务器操作系统交给服务器存根
proc:add
int:val(i)
int:val(j)
5.存根程序吧消息解包
6.存根程序对add做本地调用


5.dubbo调用过程
https://www.jianshu.com/p/89b5cd823b27
https://wely.iteye.com/blog/2378164
https://www.jianshu.com/p/5b0c87f025d5  mockCluserInvokers集群容错
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
						
						
failoverClusterInvoker	 									执行invoker
通过目录服务找到所有订阅服务提供者的invoker对象
路由服务根据策略过滤选择调用的invoker
通过负载均衡策略loadBalance来选择一个invoker


