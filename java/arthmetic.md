o(1) hashmap
o(n) 数数
o(n*n) 冒泡 n个对象 每个n次
o(n*logn)  n个对象 每个从中分2，直至1个为止

排序方法	平均情况	最好情况	最坏情况	辅助空间	稳定性
冒泡排序	O(n^2)	O(n)	O(n^2)	O(1)	稳定      				 n*n  冒泡交换
简单选择排序	O(n^2)	O(n^2)	O(n^2)	O(1)	稳定  				 n*n  间隔交换 
直接插入排序	O(n^2)	O(n)	O(n^2)	O(1)	稳定  				
希尔排序	O(nlogn)~O(n^2)	O(n^1.3)	O(n^2)	O(1)	不稳定	     选择间隔3,2,1 最后合并
堆排序	O(nlogn)	O(nlogn)	O(nlogn)	O(1)	不稳定			 二叉树
归并排序	O(nlogn)	O(nlogn)	O(nlogn)	O(n)	稳定		 分2,然后合并
快速排序	O(nlogn)	O(nlogn)	O(n^2)	O(logn)~O(n)	不稳定   随便选择1个数 左右。 依次。最后

http://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
