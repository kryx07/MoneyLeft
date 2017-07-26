package com.kryx07.moneyleft

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import timber.log.Timber
import android.view.Menu
import android.view.MenuItem
import com.kryx07.moneyleft.logic.ODateCalculator
import com.kryx07.moneyleft.logic.OMoneyCalculator
import kotlinx.android.synthetic.main.activity_input.*
import org.joda.time.LocalDate
import org.nfunk.jep.JEP
import java.math.BigDecimal
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.appwidget.AppWidgetManager
import android.content.Intent
import com.kryx07.moneyleft.widget.NewAppWidget


class InputActivity : AppCompatActivity() {

    lateinit var sharedPrefs: SharedPreferencesManager
    val jep = JEP()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        sharedPrefs = SharedPreferencesManager(this)

        Timber.e(App.app.toString())
        Timber.e("Thread Check" + Thread.currentThread().id)
        // Timber.e(jep.evaluate(jep.parse("2+2")).toString())


        money_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //getDiff()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    override fun onStart() {
        super.onStart()
        showDates()
        readInput()
        updateWidget()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_input, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_input_add_item) {
            //do sth
            Timber.e("menu clicked")
            getDiff()
            writeInput()
            updateWidget()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun getDiff() {
        Timber.e("Thread Check" + Thread.currentThread().id)
        var input = money_input.text.toString()

        var moneyLeft: BigDecimal = BigDecimal.ZERO
        var moneyPerDay: BigDecimal = BigDecimal.ZERO

        val executor: ExecutorService = Executors.newCachedThreadPool()
        executor.submit {
            moneyLeft = BigDecimal(jep.evaluate(jep.parse(input)).toString())
            moneyPerDay = OMoneyCalculator
                    .getMoneyLeftPerDay(
                            ODateCalculator.getInclusiveDaysDiff(today(), ODateCalculator.getPayDate(today())), moneyLeft)
        }
        executor.shutdown()

        while (!executor.isTerminated) {

        }

        money_left_text.text = moneyLeft.toString()
        money_per_day_text.text = moneyPerDay.toString()

    }

    private fun showDates() {
//        val paycheckDay = ODateCalculator.getLastDayOfMonth(today())
        val paycheckDay = ODateCalculator.getPayDate(today())
        val diff = ODateCalculator.getInclusiveDaysDiff(today(), paycheckDay)
        today_text.text = today().toString()
        paycheck_text.text = paycheckDay.toString()
        days_left_text.text = ODateCalculator.getInclusiveDaysDiff(today(), paycheckDay).toString()
    }

    private fun writeInput() {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.submit {
            sharedPrefs.write(R.string.input_text, money_input.text.toString())
            sharedPrefs.write(R.string.money_per_day_amount, money_per_day_text.text.toString())
        }
        executor.shutdown()

    }

    private fun readInput() {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.submit {
            money_left_text.text = sharedPrefs.read(R.string.input_text)
            money_per_day_text.text = sharedPrefs.read(R.string.money_per_day_amount)
        }
        executor.shutdown()
    }

    private fun today(): LocalDate {
        return LocalDate.now()
    }

    private fun updateWidget() {
        val intent = Intent(this, NewAppWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        val ids = intArrayOf(18)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        sendBroadcast(intent)
    }
}

