jstack 查看栈信息
jmap 查看堆内存信息
jvivsual  图像化工具
jstat gc统计
jmc
jConsole

TLAB(thread local allocation buffer)
jvm为每个线程分配一个私有缓存地址，避免多线程同时分配内存是，操作同一个地址，从而加锁

-Xms value
-XmX value
-xmn value
-xss 线程栈大小
-PermSize
-MaxPermSize
-XX:MaxTenuringThreshold=n超过n值晋升老年代

新生代和老年比列
--XX:NewRatio=2  1:2
--xx:NewSize=200M
eden和survivor比列
--XX:SurvivorRatio=value



Serial GC
新生带 复制算法
老年带 标记整理算法
-XX:+UseSerialGC (开启此参数使用serial & serial old搜集器（client模式默认值）。)
-XX:+UseParNewGC(开启此参数使用ParNew & serial old搜集器（不推荐）)
-XX:+UseParNewGC -XX:+UseConcMarkSweepGC(开启此参数使用ParNew & CMS（serial old为替补）搜集器)
-XX:+UseConcMarkSweepGC (开启此参数使用ParNew & CMS（serial old为替补）搜集器)
-XX:+UseParallelGC(开启此参数使用parallel scavenge & parallel old搜集器（server模式默认值）)
-XX:MaxGCPauseMills=value
-XX:GCTimeRation=N (GC时间和用户时间比例1/n+1)


G1 GC oracle jdk9默认 cms废弃
G1可以直观设定停顿时间相比CMS GC,G1不能想CMS最好的情况延迟停顿，但是最差的情况要好很多
G1仍然存在年代的概念，内存不是条带式划分，而是类似棋盘一个个region.region之间是复制算法。但是整体是标记-整理算法,可以避免内存碎片,尤其是java堆非常大的时候,G1优势更明显


jdk11 epsilionGC
ZGC Zing Shenandoah
