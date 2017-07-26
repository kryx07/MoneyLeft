package com.kryx07.moneyleft.logic

import org.joda.time.Days
import org.joda.time.LocalDate
import timber.log.Timber

/**
 * Created by wd41 on 26.07.17.
 */
object ODateCalculator {

    fun getLastDayOfMonth(date: LocalDate): LocalDate {
        Timber.e("Thread Check" + Thread.currentThread().id)

        val day = date.dayOfMonth().withMaximumValue().dayOfMonth
        val month = date.monthOfYear
        val year = date.year
        return (LocalDate(year, month, day))
    }

    fun getDaysDiff(date1: LocalDate, date2: LocalDate): Int {
        Timber.e("Thread Check" + Thread.currentThread().id)

        return (Days.daysBetween(date1, date2).days)
    }

    fun getDaysCountToEndOfMonth(): Int {
        Timber.e("Thread Check" + Thread.currentThread().id)

        return (getDaysDiff(LocalDate.now(), getLastDayOfMonth(LocalDate.now())))
    }
}