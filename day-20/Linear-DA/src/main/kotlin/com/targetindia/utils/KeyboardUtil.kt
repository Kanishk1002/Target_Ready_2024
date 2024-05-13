package com.targetindia.com.targetindia.utils

fun readInt(prompt: String): Int {
    print(prompt)
    return readln().toInt()
}

fun readArray(prompt: String): Array<Int> {
    print(prompt)
    val size = readln().toInt()
    val array = Array(size){0}

    print("Enter $size integers: ")
    val input = readln().split(" ")

    for(i in 0..<size){
        array[i] = input[i].toInt()
    }

    return array
}