put 
flip  write mod 2 read mod  
read 
clear /compact(清除已经读过的)    write mode 2 read mode
allocate(创建)
channel.read(buf) (从Channel写到Buffer的例子)
rewind(position设回0，所以你可以重读Buffer中的所有数据。limit保持不变)
mark
reset

		   read         write
capacity    
position   开始读位置   开始写的位置
limit      写的位置		capacity


Byte,Char,Doube,Float,Int,Long,ShortBuffer 和特殊的mapperdByteBuffer,direct buffer(堆外内存)


难点：为啥堆外内存会少依次复制。堆内不行？
答：写的地址会变化，因为gc整理内存



