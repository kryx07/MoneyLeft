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

    fun getInclusiveDaysDiff(date1: LocalDate, date2: LocalDate): Int {
        Timber.e("Thread Check" + Thread.currentThread().id)

        return (Days.daysBetween(date1, date2).days) + 1
    }

    fun getDaysCountToEndOfMonth(): Int {
        Timber.e("Thread Check" + Thread.currentThread().id)

        return (getInclusiveDaysDiff(LocalDate.now(), getLastDayOfMonth(LocalDate.now())))
    }

    fun getPayDate(today: LocalDate): LocalDate {
        val PAY_DAY = 28
        val DECEMBER = 12
        val JANUARY = 1

        if (today.dayOfMonth < PAY_DAY) {
            return today.withDayOfMonth(PAY_DAY)
        } else {
            if (today.monthOfYear != DECEMBER) {
                return today.withDayOfMonth(PAY_DAY).withMonthOfYear(today.monthOfYear + 1)
            } else {
                return today.withDayOfMonth(PAY_DAY).withMonthOfYear(JANUARY)
            }
        }
    }
}