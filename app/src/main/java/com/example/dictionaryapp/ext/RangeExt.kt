package com.example.dictionaryapp.ext


fun IntRange.containedIn(range: IntRange): Boolean {
    return this.contains(range.first)
}

fun IntRange.isNoEmptySubRange(range: IntRange): Boolean {
    return this.last +1 != range.first
}