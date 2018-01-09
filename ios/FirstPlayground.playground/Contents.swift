//: Playground - noun: a place where people can play

import UIKit

let one: Int = 1

let two = Float(one) * 3.0


var hi: String? = "Hi"

var greet: String? = "hi"

greet = "Hello" + "!"

greet = nil

hi = nil

hi?.append("Hi")

hi

if var myGreet = greet {
    myGreet.append("***")
    myGreet
}

class Car {
    var speed = 0
}

let car1 = Car()
car1.speed = 5

struct CarStruct {
    var speed = 0
}

let car2 = CarStruct()
var car3 = CarStruct()

car3.speed = 56

car3 = car2
car3.speed = 22

car2

var a = [1,2,3]

var b = [4,5,6]

a = b

a.append(7)

b

b.append(7)

func square(_ y: Int) -> Int {
    return y * y
}

square(5)

let f: ((Int) -> Int)? = square

f!(10)

func add1AndApply(n number: Int, f apply: (Int) -> Int) -> Int {
    return apply(number+1)
}

add1AndApply(n: 4, f: square)


let res = [1, 5, 3]

let mapped = res.map(square)

mapped

let filtered = res.filter({ (x: Int) in x % 2 == 0  })

filtered


let reduced = res.reduce(0, {(res: Int, x: Int) in x + res })

reduced
