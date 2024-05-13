package com.targetindia.com.targetindia.arrays

import com.targetindia.com.targetindia.utils.readArray
import com.targetindia.com.targetindia.utils.readInt

fun main() {
    var array = arrayOf(10, 20, 30, 40, 50, 12, 23, 34, 45, 56);

    do {
        val menu = "\nArray Content: ${array.joinToString()}\n" +
                "\n" +
                "0. Exit\n" +
                "1. Insert a number at given index\n" +
                "2. Remove a number at given index\n" +
                "3. Search a number in the array\n" +
                "4. Reverse the array\n" +
                "5. Insert sub-array at given index\n" +
                "6. Delete sub-array at given index\n" +
                "7. Check if a sub-array exists\n" +
                "8. Print Array\n"

        println(menu)
        val choice: Int = readInt("Enter your choice: ")

        when(choice) {
            0 -> {
                println("\nProgram terminated.")
            }
            1 -> {
                val index = readInt("Enter the index to enter the number: ")
                val num = readInt("Enter the number to enter: ")
                array = insertAtIndex(array, index, num)
            }
            2 -> {
                val index = readInt("Enter the index to delete the number: ")
                array = deleteAtIndex(array, index)
            }
            3 -> {
                val num = readInt("Enter the number to search: ")
                val index = searchInArray(array, num)
                if(index == -1) {
                    println("\n$num is not present in array.")
                } else {
                    println("\n$num is present at $index of the array.")
                }
            }
            4 -> {
                array = reverseArray(array)
            }
            5 -> {
                val index = readInt("Enter the index to add sub-array to: ")
                val subArray = readArray("Enter size of sub-array: ")
                array = insertSubArray(array, index, subArray)
            }
            6 -> {
                val index = readInt("Enter the index to delete sub-array from: ")
                val size = readInt("Enter size of sub-array to delete: ")
                array = deleteSubArray(array, index, size)
            }
            7 -> {
                val subArray = readArray("Enter the size of sub-array to find: ")
                val index = findSubArray(array, subArray)
                if(index == -1) {
                    println("\nSub-array is not present in array.")
                } else {
                    println("\nSub-array is present at $index of the array.")
                }
            }
            8 -> {
                continue
            }
        }
    } while(choice != 0)
}

fun insertAtIndex(array: Array<Int>, index: Int, num: Int): Array<Int> {
    if (index !in 0..array.size) {
        println("\n`index` must be between 0 and ${array.size}")
        return array;
    }

    val newArray = Array(array.size + 1) {0}

    for (i in newArray.indices) {
        if(i == index) {
            newArray[index] = num
        } else if (i > index) {
            newArray[i] = array[i - 1]
        } else {
            newArray[i] = array[i]
        }
    }

    return newArray
}

fun deleteAtIndex(array: Array<Int>, index: Int): Array<Int> {
    if (index !in array.indices) {
        println("\n`index` must be between 0 and ${array.size}")
        return array
    }

    val newArray = Array(array.size - 1) {0}

    for (i in newArray.indices) {
        if (i >= index) {
            newArray[i] = array[i + 1]
        } else {
            newArray[i] = array[i]
        }
    }

    return newArray
}

fun searchInArray(array: Array<Int>, num: Int): Int {
    for(i in array.indices) {
        if(array[i] == num) {
            return i
        }
    }

    return -1
}

fun reverseArray(array: Array<Int>): Array<Int> {
    val newArray = Array(array.size) {0}

    for(i in array.indices) {
        newArray[array.size - 1 - i] = array[i]
    }

    return newArray
}

fun insertSubArray(array: Array<Int>, index: Int, subArray: Array<Int>) : Array<Int> {
    if(index >= array.size) {
        println("\n`index` must lie within array size: ${array.size}")
        return array
    }

    val newArray = Array(array.size + subArray.size) {0}

    var pos = 0
    for(i in newArray.indices) {
        if(i < index || i >= index + subArray.size) {
            newArray[i] = array[pos++]
        } else if(i == index) {
            for (j in subArray.indices) {
                newArray[i + j] = subArray[j]
            }
        }
    }

    return newArray
}

fun deleteSubArray(array: Array<Int>, index: Int, size: Int): Array<Int> {
    if(index >= array.size) {
        println("\n`index` must lie within array size: ${array.size}")
        return array
    }

    val newArray = Array(array.size - size){0}

    for(i in array.indices) {
        if(i < index) {
            newArray[i] = array[i]
        } else if(i >= index + size) {
            newArray[i - size] = array[i]
        }
    }

    return newArray
}

fun findSubArray(array: Array<Int>, subArray: Array<Int>): Int {
    for (i in array.indices) {
        if(array[i] == subArray[0]) {
            for (j in subArray.indices) {
                if (array[i + j] != subArray[j]) {
                    break
                }
            }
            return i
        }
    }

    return -1
}
