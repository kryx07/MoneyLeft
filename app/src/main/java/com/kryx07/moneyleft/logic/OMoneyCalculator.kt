package com.kryx07.moneyleft.logic

import io.reactivex.Observable
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode

object OMoneyCalculator {

    fun getMoneyLeftPerDay(daysCount: Int, moneyLeft: BigDecimal): BigDecimal {
        Timber.e("Thread Check" + Thread.currentThread().id)

        return moneyLeft.divide(BigDecimal(daysCount), 2, RoundingMode.CEILING)
    }
}