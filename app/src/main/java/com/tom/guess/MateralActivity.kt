package com.tom.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_materal.*

class MateralActivity : AppCompatActivity() {
    val guessNumber = GuessNumber()
    val TAG = MateralActivity::class.java.simpleName
    var win = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materal)
        setSupportActionBar(findViewById(R.id.toolbar))
        Log.d(TAG, "secret: " + guessNumber.SecretNumber)   //後台顯示secret的數字

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton("yes", {dialog, which ->
                    guessNumber.reset()
                    counter.setText(guessNumber.count.toString())
                    ed_number.setText("")
                    Log.d(TAG, "secret: " + guessNumber.SecretNumber)   //後台顯示secret的數字
                    win = false
                }) //要寫按鈕功能,先{}內寫上dialog, which ->, ->有指引括號的功能,接著就可以寫想要的功能
                .setNeutralButton("No",null)
                .show()
        }
        //設計APP上小按鈕的功能

        counter.setText(guessNumber.count.toString())
        //計算猜的次數
        //這個Text取guessNumber的count顯示,後面要接轉換toString()才不一直找資源
    }

    fun check(view : View) {     //已事先設定Boutton按下後會執行check指令
        var n = ed_number.text.toString().toInt()
        println("Number: $n")       //不推薦用來除錯
        Log.d(TAG,"Number: " + n)
        //d為debug(除錯)的意思,還有其他簡寫指令
        //推薦使用次指令來除錯，此指令在App執行上會自動隱藏

        val getNumber = guessNumber.validate(n)
        var godGuess = 3
        var guessMessage = getString(R.string.yes_you_got_it)
        //在字串上打alt+enter,在選resource,可以直接將此字串變成資源,若已有也可以自己打
        if(win == false) {
            if (getNumber < 0) {
                guessMessage = getString(R.string.bigger)
            } else if (getNumber > 0) {
                guessMessage = getString(R.string.smaller)
            }else if (guessNumber.count < godGuess) {
                guessMessage = getString(R.string.excellent_the_number_is)+ guessNumber.SecretNumber
                win = true
            }else win = true
        }else guessMessage = getString(R.string.you_win)

        counter.setText(guessNumber.count.toString())
        //更動計數器的數字

//        Toast.makeText(this, guessMessage , Toast.LENGTH_LONG).show()
        //Tosat.makeText為快速的小視窗,顯示一下就會消失
        // ()內用ctrl+P可以叫出使用規範
        // this可以代表MainActivity的物件，而MainActivity是context的子類別
        // 最後的要在,後加0或1,這邊用Toast比較好判讀,然後用show()顯示

        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(guessMessage)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
        //AlertDialog的Builder可以跑出一個對話框
    }
}