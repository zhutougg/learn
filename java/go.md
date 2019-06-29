var 也是特殊类型
指针
p: =&i
fmt.Println(p)
fmt.Println(*p)
一系列字段
type Vertex struct {
	X int
	Y int
}
map类型
map[string]int{"x": 1}

fun类型
package main

import (
	"fmt"
	"math"
)

func compute(fn func(float64, float64) float64) float64 {
	return fn(3, 4)
}

func main() {
	hypot := func(x, y float64) float64 {
		return math.Sqrt(x*x + y*y)
	}
	fmt.Println(hypot(5, 12))

	fmt.Println(compute(hypot))
	fmt.Println(compute(math.Pow))
}


go没有class
但是类型方法
package main

import (
	"fmt"
	"math"
)

type Vertex struct {
	X, Y float64
}

func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func main() {
	v := Vertex{3, 4}
	fmt.Println(v.Abs())
}

接口方法
var i interface{} = "hello"
s := i.(string)


ch用法
s := []int{7, 2, 8, -9, 4, 0}
fmt.Println(s[:len(s)/2])
fmt.Println(s[len(s)/2:])
c := make(chan int)
go sum(s[:len(s)/2], c)
go sum(s[len(s)/2:], c)
x, y := <-c, <-c // receive from c
fmt.Println(x, y, x+y)
	
[7 2 8]
[-9 4 0]
-5 17 12

特殊情况
Another note: Channels aren't like files; you don't usually need to close them. Closing is only necessary when the receiver must be told there are no more values coming, such as to terminate a range loop.

select
select {
	case <-c:
		fmt.Printf("Unblocked %v later.\n", time.Since(start))
	case <-ch1:
		fmt.Printf("ch1 case...")
	case <-ch2:
		fmt.Printf("ch1 case...")
	/*default:
		fmt.Printf("default go...")*/
	}
	
import 地址
The path you're seeing in your import line is not a url, but only the path the package is located in (normally relative to $GOROOT/src/pkg or $GOPATH/src). So your package heredoc is most probably located in the directory $GOPATH/src/github.com/MakeNowJust/heredoc


https://blog.golang.org/organizing-go-code
https://golang.org/doc/code.html
https://reading.developerlearning.cn/interview/interview-os/

slice append 指向对象一直
map[]User    map[].id 无法编译


defer 先进后出
 defer func() { fmt.Println("打印前") }()
 defer func() { fmt.Println("打印中") }()
 defer func() { fmt.Println("打印后") }()
 panic("")
 
for _,stu := range stu地址一致问题




安装
export GOPATH=/url/local/go
export PATH=$PATH:GOPATH/bin
export GOPROXY=https://goproxy.io

同时赋值
go  a,b= b,a
test
TestXX(t *testing.T)
OS.ARGS
OS.EXIT

go run 
go build

const(
Monday = iota+1
Tuesday
)

const(
Open = 1<< iota
Close
)

fallthrough关键字
switch 1 {
case 1:
    fmt.Println("I will print")
    fallthrough
case 0:
    fmt.Println("I will also print")
}

匿名函数
fuc(arr*3int){
	(*arr)[0]=7
}(&x)

一定要用 不用_
import _ fmt

nil 标志符用于表示interface、函数、maps、slices、channels、error、指针等的“零值”。如果你不指定变量的类型，编译器将无法编译你的代码

const identifier [type] = value

字面量:
有个不太好，但是足以说明问题的解释，那就是 对象字面量就是引号引起来的部分，必须是等号右边的部分
const int b = 10; //b为常量，10为字面量
string str = “hello world！”; // str 为变量，hello world！为字面量


if simplestmt; expression {
    ... ...
}

{ // 隐式code block
    simplestmt
    if expression { // 显式的code block
            ... ...
    } 
} 

package 
大写对外，小写不对外

安装包
go get -u xxxx  
(optionally) downloads,
compiles,
and installs  

go install xxxx   安装在GOPATH/pkg/
compiles
and installs

go get -d library, which only downloads it;
make the change on the downloaded package;
go install library to install the local version.



当某个包被导入时，如果该包还导入了其它的包，那么会先将其它包导入进来，然后再对这些包中的包级常量和变量进行初始化，接着执行init()函数（如果有的话），依次类推。

https://gowalker.org/github.com/google/kythe/third_party/netty


GO111MODULE=off 无模块支持，go 会从 GOPATH 和 vendor 文件夹寻找包。
GO111MODULE=on 模块支持，go 会忽略 GOPATH 和 vendor 文件夹，只根据 go.mod下载依赖。
GO111MODULE=auto 在 GOPATH/src外面且根目录有 go.mod文件时，开启模块支持


var arrAge  = [5]int{18, 20, 15, 22, 16}
var arrPack = [...]int{10, 5: 100} //指定索引位置初始化，数组长度与此有关 {10,0,0,0,0,100}
var arrRoom [20]int
var arrBed = new([20]int) 指针


make  分配内存  a*[]int = new([]int)
new   分配内存 指针 a[]int =make([]int)


规则一 当defer被声明时，其参数就会被实时解析
规则二 defer执行顺序为先进后出
规则三 defer可以读取有名返回值，也就是可以改变有名返回参数的值

close
用于通道，对于通道c，内置函数close(c)将不再在通道c上发送值。 如果c是仅接收通道，则会出错。 发送或关闭已关闭的通道会导致运行时错误。 关闭nil通道也会导致运行时错误。

make(T, n)       slice        T为切片类型，长度和容量都为n
make(T, n, m)     slice        T为切片类型，长度为n，容量为m （n<=m ，否则错误）
new() 用于值类型的内存分配，并且置为零值。 make() 只用于切片、字典以及通道这三种引用数据类型的内存分配和初始化。
new(T) 分配类型 T 的零值并返回其地址，也就是指向类型 T 的指针。 make(T) 返回类型T的值（不是* T）

make(T)          map        T为字典类型
make(T, n)        map        T为字典类型，初始化n个元素的空间

make(T)          channel      T为通道类型，无缓冲区
make(T, n)        channel      T为通道类型，缓冲区长度为n


fplus := func(x, y int) int { return x + y }

新类型不会拥有原基础类型所附带的方法 自定义类型不会继承原有类型的方法，但接口方法或组合类型的内嵌元素则保留原有的方法
自定义新类型Aa，没有基础类型A的方法 type Aa A
type 定义类型别名 type Aa=A // 类型别名 

函数也是一个确定的类型，就是以函数签名作为类型。这种类型的定义例如
type  typeFunc func ( int, int) int 

struct (可以镶嵌interface),interface
匿名成员 嵌入与聚合
type Human struct {
	name string
}

type Student struct { // 含内嵌结构体Human
	Human // 匿名（内嵌）字段
	int   // 匿名（内嵌）字段
}

type Student struct { // 含内嵌结构体Human
	Human human // 匿名（内嵌）字段
	int   // 匿名（内嵌）字段
}


Go 语言中的所有类型包括自定义类型都实现了interface{}接口
var i interface{} = 99 // i可以是任何类型
i = 44.09
i = "All"  // i 可接受任意类型的赋值


左边接口，右边实现
type B interface {
	f()
}
type I int

func (i I) f() {
	fmt.Println("I.f() ", i)
}
var b2 B = I(299) // 接口类型可接受新类型I的值，因为新类型I实现了接口
	b2.f()
	
接口中的方法必须要全部实现，才能实现接口


接口与动态类型
Go 语言动态类型的实现通常需要编译器静态检查的支持：当变量被赋值给一个接口类型的变量时，编译器会检查其是否实现了该接口的所有方法。我们也可以通过类型断言来检查接口变量是否实现了相应类型


x.m()和X.m(x)


规则一：如果使用指针方法来实现一个接口，那么只有指向那个类型的指针才能够实现对应的接口。
规则二：如果使用值方法来实现一个接口，那么那个类型的值和指针都能够实现对应的接口。
package main

type T struct {
	Name string
}
type Intf interface {
	M1()
	M2()
}

func (t T) M1() {
	t.Name = "name1"
}

func (t *T) M2() {
	t.Name = "name2"
}
func main() {

	var t1 T = T{"t1"}
	t1.M1()
	t1.M2()

	var t2 Intf = &t1  //改成t1不存在
	t2.M1()
	t2.M2()
}


并发： 指的是程序的逻辑结构。如果程序代码结构中的某些函数逻辑上可以同时运行，但物理上未必会同时运行。
并行： 并行是指程序的运行状态。并行则指的就是在物理层面也就是使用了不同CPU在执行不同或者相同的任务


  M - p -G-G-G
  M - p -G-G-G
  
  var m sync.Map	
	f := func(k, v interface{}) bool {
		fmt.Println(k, v)
		return true
	}
	m.Range(f)  
	
	
var pointer *Point3D	
符号 * 可以放在一个指针前，如 (*pointer)，那么它将得到这个指针指向地址上所存储的值，这称为反向引用。不过在Go语言中，(*pointer).x可以简写为pointer.x	


如果想确切知道变量分配的位置，可在执行go build或go run时加上-m gc标志（即go run -gcflags -m app.go
go run -gcflags -m main.go
# command-line-arguments
.\main.go:12:31: m.Alloc / 1024 escapes to heap
.\main.go:11:23: main &m does not escape
.\main.go:12:12: main ... argument does not escape


其中a ...interface{}表示参数不定的意思。如果要根据不同的参数实现不同的功能，要在方法内检测传递的参数。
func Println(a ...interface{}) (n int, err error) {
	return Fprintln(os.Stdout, a...)
}
测试函数包括Test开头的单元测试函数和以Benchmark开头的基准测试函数两种，测试辅助代码是为测试函数服务的公共函数、初始化函数、测试数据等，而示例函数则是以Example开头的说明被测试函数用法的函数，而示例函数通常被保存在*_test.go文件中


Type()返回的是静态类型，是interface{}
而kind()返回的是具体类型,
element 是type	


https://github.com/ffhelicopter/Go42/blob/master/content/42_28_unsafe.md

内核中的缓冲：无论进程是否提供缓冲，内核都是提供缓冲的，系统对磁盘的读写都会提供一个缓冲（内核高速缓冲），将数据写入到块缓冲进行排队，当块缓冲达到一定的量时，才把数据写入磁盘。

进程中的缓冲：是指对输入输出流进行了改进，提供了一个流缓冲，当调用一个函数向磁盘写数据时，先把数据写入缓冲区，当达到某个条件，如流缓冲满了，或刷新流缓冲，这时候才会把数据一次送往内核提供的块缓冲中，再经块缓冲写入磁盘。
