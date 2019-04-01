jstack 查看栈信息
jmap 查看堆内存信息
jvivsual  图像化工具
jstat gc统计
jmc
jConsole


Minor GC执行非常迅速（50ms以内）
Minor GC没有频繁执行（大约10s执行一次）
Full GC执行非常迅速（1s以内）
Full GC没有频繁执行（大约10min执行一次）

jstat -gcutil
S0 S1 E O P YGC YGCT FGC FGCT GCT
12.16 0.00 5.18 63.78 20.32 54 2.047 5 6.946 8.993

jstat -gccapacity
NGCMN NGCMX NGC S0C S1C EC OGCMN OGCMX OGC OC PGCMN PGCMX PGC PC YGC FGC
212992.0 212992.0 212992.0 21248.0 21248.0 170496.0 1884160.0 1884160.0 1884160.0 1884160.0 262144.0 262144.0 262144.0 262144.0 54 5

具体案例：https://crowhawk.github.io/2017/08/21/jvm_4/

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


gc调优
内存占用 延迟  吞吐量


G1
e| |s|o|H
 | |e| |E
 
 
region大小是一致的，数值在1m-32m字节,jvm会尽量分2048个左右
region大小和大对象很难一致，导致空间浪费,并且分配大对象难以找到连续的看空间
直接设置较大的region大小
-XX:G1HeapRegionSize=<N,例如 16>M

rememenberd Set 用于记录和维护region之间对象的引用关系
新生代复制算法，从eden/survivor 到其他区
通常占heap大小的20%
老年带到新生带需要引用
GCRoots       a(老年带)->(rememenberd sets)           a.b(新生带)

parNew和CMS stopTheWorld
1.标记阶段,首先root标记，发生前检查young.触发minitorGC(GC log:GC pause(young)(inital-mark))
2.并发标记非root,看是否存活。
4.再标记,看并发非root中是否又有root变化






