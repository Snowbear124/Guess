package com.tom.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val guessNumber = GuessNumber()
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret: " + guessNumber.SecretNumber)
    }

    fun check(view :View) {     //已事先設定Boutton按下後會執行check指令
        var n = ed_number.text.toString().toInt()
        println("Number: $n")       //不推薦用來除錯
        Log.d(TAG,"Number: " + n)
        //d為debug(除錯)的意思,還有其他簡寫指令
        //推薦使用次指令來除錯，此指令在App執行上會自動隱藏

        val getNumber = guessNumber.validate(n)
        var guessMessage = getString(R.string.yes_you_got_it)
        //在字串上打alt+enter,在選resource,可以直接將此字串變成資源,若已有也可以自己打
        if(getNumber < 0) {
            guessMessage = getString(R.string.bigger)
        }else if(getNumber > 0) {
            guessMessage = getString(R.string.smaller)
        }
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