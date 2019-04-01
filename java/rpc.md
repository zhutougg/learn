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


代理对象                invokeInvocationHandler										mockCluserInvoker
sayHello("world")		toString, hashCode, equals直接调用invoke(为啥不用报错)
						由调用的远程方法名和参数构建远程调用对象RPCInvocation
						invoke


