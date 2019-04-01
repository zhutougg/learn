add     add 2 tail
offer   add 2 tail              offer(E e,long timeout,TimeUnit unit)
peek    get from head
poll    get and remove          poll(E e,long timeout,TimeUnit unit)
reomve  if present
								put add 2 tail    阻塞
								take getAndRemove from head 阻塞

concurrentLinkedqueque  node没范围
linkedBlockingQueue   默认intever.maxSize
