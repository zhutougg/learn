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