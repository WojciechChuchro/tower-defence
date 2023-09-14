/*
Build Tower
Build a pyramid-shaped tower, as an array/list of strings, given a positive integer number of floors. A tower block is represented with "*" character.

For example, a tower with 3 floors looks like this:

[
  "  *  ",
  " *** ",
  "*****"
]
And a tower with 6 floors looks like this:

[
  "     *     ",
  "    ***    ",
  "   *****   ",
  "  *******  ",
  " ********* ",
  "***********"
]
 */

function towerBuilder(nFloors) {
    let arr = []
    let stars = ''
    let iterator = nFloors - 1
    let length = ((nFloors - 1) * 2) + 1
    let chars
    let index = 0

    for (let i = 0; i < length; i++) {
        stars += '*'
    }

    for (let i = 0; i < nFloors; i++) {
        arr[i] = stars
    }

    for (let i = 0; i < nFloors; i++) {
        for (let j = 0; j < iterator; j++) {
            chars = arr[index].split('')
            chars[j] = ' '
            chars[chars.length - (j + 1)] = ' '
            arr[index] = chars.join('')
        }
        index++
        iterator--;
    }

    return arr
}