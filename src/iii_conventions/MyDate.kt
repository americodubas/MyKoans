package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.getCalendar().compareTo(other.getCalendar())
    }

    private fun getCalendar(): Calendar {
        val c = Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        return c
    }
}

operator fun MyDate.plus(t: TimeInterval) = this.addTimeIntervals(t, 1)
operator fun MyDate.plus(r: RepeatedTimeInterval) = this.addTimeIntervals(r.t, r.n)
operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    operator fun contains(d: MyDate): Boolean {
        return d >= start && d <= endInclusive
    }

    override fun iterator() = DataIterator(this)
}

class DataIterator(val dateRange: DateRange): Iterator<MyDate> {
    var current = dateRange.start

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext() = current <= dateRange.endInclusive
}

class RepeatedTimeInterval(val t: TimeInterval, val n: Int)