		 全量备份	   增量备份	            差异备份
方法     备份所有文件   备份新文件和改动	段时间备份所有+备份新文件和改动
速度     最慢			最快				较快
还原速度 最快			最慢		        较快
空间要求 最多           最少				较多



全局锁和表锁都是mysql实现
lock table read/write
metaLock 每次访问都会默认加

alter tabel xxx;会


但是行锁是由底下engine实现
myiasm没有实现
inndb 实现行锁

inndb行锁是需要的时候加上去，不能立即释放而要等到事物结束才是释放，这是二阶段锁协议

事物A								 事物B
begin
update t set k = k+ 1where id = 1;
                                      update t set k = k+1 where id = 2;
update t set k = k+ 1where id = 2;
									  update t set k = k+1 where id = 1;
									  
出现死锁，二中策略
一种是超时，   innodb_lock_wait_timeout 默认50 也不能设置很小,如果不是死锁，会误伤
一种是死锁检测 innodb_deadlock_detect 主动滚回链条中1个事物

带来性能问题？
o(n) 假设1000个并发更新同一个行，就是100w,消耗大量cpu
减少并发修改

搜索过程？
select * from t where k = 5

普通索引
找到满足条件的第一个记录(5,500)需要查找的哥不满足k=5的条件记录
唯一索引
找到满足条件的第一个记录就停止

性能上差距微乎其微?
因为innodb是按数据页为单位来读写的。当需要读一条记录的时候，以页为单位将整体读入内存16Kb多一次指针寻找和一次计算 如果不这一页，就再取，一个数据页可以放近千个key

更新过程？
需要更新一个数据页时，如果数据页在内存中就直接更新，如果不影响一致性前提下。inndb会将更新操作缓存在chageBuffer中，这样就不需要从磁盘读入这个数据页。如果下次查询需要访问这个数据页时候，将磁盘数据页加载内存，执行changeBuffer中跟这个页有关的操作（purge）。这样就能保证数据的正确性。  后台线程定期和关机执行purge.提高内存利用率

changebuffer设置 innodb_change_buffer_max_size(最多50%)

唯一索引
所有更新都要先判断这个操作违反唯一性。因此已经加入内存
普通索引

如果都在内存中，唯一多个判读是否唯一。其他一样
如果不在内存
唯一就需要把数据页读入内存
普通就直接更新记录在changebuffer

changebuffer使用场景
写多读少业务，日志等
如果写入后马上查询，会立即触发purge过程，io次数没减少，增加维护changebuffer代价



changeBuffer和redo log
mysql>insert into t(id,k) vlaues(id1,k1),(id2,k2);
k1在内存中，k2不在内存中

		innodb buffer pool							redo log(ib_log_filex)
1						    2 							3
change buffer				page1					 add(id1,k1) to page1
add(id2,k2)to page2   (a,b)|(id1,k1)|(c,d)			 new change buffer item "add(id2,k2)to page2"
	||									||
	||									||
system table space(ibdata1)		data(t.ibd)
changeBuffer					page1|xxx|page2
"add(id2,k2)to page2"			(a,b)|(c,d)|xxx|(e,f)|(g,h)



1. Page 1 在内存中，直接更新内存；
2. Page 2 没有在内存中，就在内存的 change buffer 区域，记录下“我要往 Page 2 插入一行”这个信息
3. 将上述两个动作记入 redo log 中（图中 3 和 4）。
做完上面这些，事务就可以完成了。所以，你会看到，执行这条更新语句的成本很低，就是写了
两处内存，然后写了一处磁盘（两次操作合在一起写了一次磁盘），而且还是顺序写的



慢查询配置
show variables like '%slow_query_log%';
set global slow_query_log = 1;





window 修改密码
mysqld -nt --skip-grant-tables或者 mysqld skip-grant-tables
管理启动
use mysql
update user set authentication_string = password('root')


//赋权所有ip
GRANT ALL ON *.* to root@'%' IDENTIFIED BY 'root';
linux开启MySQL binlog日志
show variables like '%log_bin%'



server_id=1
log_bin = D:\software\mysql-5.7.22-winx64\mysql-bin 
binlog_format = ROW
expire_logs_days = 30

gtid-mode=ON
enforce_gtid_consistency=1
log-slave-updates=1

systemctl/service restart mysqld



update user set password=password("123") where user="root"




