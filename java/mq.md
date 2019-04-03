mq发展历史？
JMS 是一种提供消息服务的技术规范，制定整个消息服务提供过程中的所有数据结构和交互流程。
AMQP（高级消息队列协议）是一种协议
MQ是消息中间件最终实现。可以基于jMS或者其他行业规范。activeMQ就是基于jms规范实现的

什么时候不使用mq？
上游时事关注执行结果
什么时候使用？
上游时事不关注执行结果
数据驱动的任务依赖
异步返回时间长

mq vs rpc？
rpg不能做到像mq一样暂存消息，压力会直接传到服务privoder

rpc简单 同步场景

rabbitmq,rocketmq,kafka 对比https://blog.csdn.net/belvine/article/details/80842240
基本概念https://blog.csdn.net/binzhaomobile/article/details/73332463


核心概念？
top 类似标题
tag 类似副标题  consumer订阅同top 可以不同tag
GroupName作用是在集群HA的情况下，一个生产者down之后，本地事务回滚后，可以继续联系该组下的另外一个生产者实例，不至于导致业务走不下去。在消费者组中，可以实现消息消费的负载均衡和消息容错目标

topic分片？
			  break1				break2                break3
topicA       queue0 queue1         queue0 queue1


topicB       					   queue0 queue1         queue0 queue1




同步/异步刷盘？
同步刷盘:当数据写到内存之后立刻刷盘(同步),保证刷盘成功的前提下相应client
异步刷盘:当数据写入内存后,直接相应client，异步将内存的数据持久到磁盘里

同步双写
消息同时写入master和slave
异步复制
消息写入master,异步复制到slave

消息清理
文件保留时长72小时
清理时机是4或者超过阈值

消息回溯？
业务消费消息时，因为某些原因bug,异常，导致消费全部无效。需要重新消费



rocketmq结构 ？
			->长连接 30秒查询topic队列信息		nameServer)(N)		    <-长连接 30秒查询topic队列信息
    
producer（n）																							consumer(N)
		
			->长连接 30秒向broker发送心跳        brokder(N)				<-长连接 30秒向broker发送心跳
												 brokder[slave N]
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 
												 