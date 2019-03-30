线程状态
new
running  运行cpu或者等待cpu分配cpu片段 在就绪队列里面
blocked  线程在等待monitor lock. 比如 synchronized sleep
waiting  执行wait 等待notify
timed_wait  执行wait(500) 等
terminated   线程执行完

只有running 占用cpu 其他都不占用 除了动作

线程结构
指令寄存器
当前栈 
  当前操作栈
  局部变量
  操作栈
  动态链接 （类第一次加载符号解析地址 静态解析，调用时候转换直接引用动态链接）
  返回地址
栈n
栈2
栈1
  