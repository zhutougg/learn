asm
汇编语言，需要了解汇编语言


javassist
不需要 了解汇编语言


cglib
基于 asm


AOP 底层技术	功能	性能	面向接口编程	编程难度
直接改写 class 文件	完全控制类	无明显性能代价	不要求	高，要求对 class 文件结构和 Java 字节码有深刻了解
JDK Instrument	完全控制类	无论是否改写，每个类装入时都要执行 hook 程序	不要求	高，要求对 class 文件结构和 Java 字节码有深刻了解
JDK Proxy	只能改写 method	反射引入性能代价	要求	低
ASM	几乎能完全控制类	无明显性能代价	不要求	中，能操纵需要改写部分的 Java 字节码




(二) 测试结论： 
1. ASM和JAVAASSIST字节码生成方式不相上下，都很快，是CGLIB的5倍。 
2. CGLIB次之，是JDK自带的两倍。 
3. JDK自带的再次之，因JDK1.6对动态代理做了优化，如果用低版本JDK更慢，要注意的是JDK也是通过字节码生成来实现动态代理的，而不是反射。 
4. JAVAASSIST提供者动态代理接口最慢，比JDK自带的还慢。 
(这也是为什么网上有人说JAVAASSIST比JDK还慢的原因，用JAVAASSIST最好别用它提供的动态代理接口，而可以考虑用它的字节码生成方式) 

(三) 差异原因： 
各方案生成的字节码不一样， 
像JDK和CGLIB都考虑了很多因素，以及继承或包装了自己的一些类， 
所以生成的字节码非常大，而我们很多时候用不上这些， 
而手工生成的字节码非常小，所以速度快， 
具体的字节码对比，后面有贴出，可自行分析。 

(四) 最终选型： 
最终决定使用JAVAASSIST的字节码生成代理方式， 
虽然ASM稍快，但并没有快一个数量级， 
而JAVAASSIST的字节码生成方式比ASM方便， 
JAVAASSIST只需用字符串拼接出Java源码，便可生成相应字节码， 
而ASM需要手工写字节码。 

https://javatar.iteye.com/blog/814426