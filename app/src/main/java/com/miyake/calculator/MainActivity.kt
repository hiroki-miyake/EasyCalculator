package com.miyake.calculator

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import org.jetbrains.anko.*

class MainActivity : Activity() {

    // 数字を表示するView
    var mainView : TextView? = null

    // 演算用数字
    var number : Long = 0
    var saveNumber : Long = 0
    val defaultNumber : Long = 0

    // 演算用フラグ
    var addFlg  = false
    var subFlg = false
    var mulFlg = false
    var divFlg = false
    var equalFlg = false
    var symbolFlg = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {

            // タイトル
            textView {
                setText("Easy Calculator")
                textColor = Color.BLACK
                textSize = 30f
            }.lparams {
                width = matchParent
                height = dip(40)
                margin = dip(10)
            }

            // 表示するところ
            relativeLayout {
                backgroundColor = Color.rgb(200, 200, 200)

                linearLayout {
                    backgroundColor = Color.WHITE

                    mainView = textView {
                        setText(number.toString())
                        textSize = 40f
                        textColor = Color.BLACK
                        gravity = Gravity.CENTER_VERTICAL.or(Gravity.END)
                        maxLines = 1
                    }.lparams {
                        width = matchParent
                        height = dip(70)
                        rightMargin = dip(10)
                    }
                }.lparams {
                    width = matchParent
                    height = dip(70)
                    gravity = Gravity.CENTER
                }
            }.lparams {
                width = matchParent
                height = dip(80)
            }

            // 押すところ
            relativeLayout {
                backgroundColor = Color.rgb(200, 200, 200)

                verticalLayout {
                    for (i in 0 .. 4){
                        linearLayout {
                            for (j in 0 .. 3){
                                textView {
                                    val setChar = when(i){
                                        0 -> {
                                            when(j){
                                                0 -> "AC"
                                                1 -> "C"
                                                2 -> "B"
                                                3 -> "/"
                                                else -> {}
                                            }
                                        }
                                        1 -> when(j){
                                            0 -> "7"
                                            1 -> "8"
                                            2 -> "9"
                                            3 -> "*"
                                            else -> {}
                                        }
                                        2 -> when(j){
                                            0 -> "4"
                                            1 -> "5"
                                            2 -> "6"
                                            3 -> "-"
                                            else -> {}
                                        }
                                        3 -> when(j){
                                            0 -> "1"
                                            1 -> "2"
                                            2 -> "3"
                                            3 -> "+"
                                            else -> {}
                                        }
                                        4 -> when(j){
                                            0 -> ""
                                            1 -> "0"
                                            2 -> ""
                                            3 -> "="
                                            else -> {}
                                        }
                                        else -> {}
                                    }
                                    setText(setChar.toString())
                                    textSize = 40f
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                    backgroundColor = Color.WHITE

                                    onClick {
                                        try {
                                            when(i) {
                                                0 -> {
                                                    when (j) {

                                                        // AllClear
                                                        0 -> {
                                                            number = 0
                                                            saveNumber = 0
                                                            addFlg = false
                                                            subFlg = false
                                                            mulFlg = false
                                                            divFlg = false
                                                            equalFlg = false
                                                            mainView!!.setText(number.toString())
                                                        }

                                                        // Clear
                                                        1 -> {
                                                            number = 0
                                                            mainView!!.setText(number.toString())
                                                        }

                                                        // 1文字削除
                                                        2 -> {
                                                            number /= 10
                                                            mainView!!.setText(number.toString())
                                                        }

                                                        // 除算
                                                        3 -> {
                                                            if (saveNumber == defaultNumber) saveNumber = number
                                                            if (divFlg && !equalFlg){
                                                                saveNumber /= number
                                                                number = 0
                                                            }
                                                            symbolClick()

                                                            if(saveNumber > 9999999999999){
                                                                mainView!!.setText("error")
                                                            }else {
                                                                mainView!!.setText(saveNumber.toString())
                                                                mainView!!.hint = saveNumber.toString()
                                                            }

                                                            divFlg = true
                                                            symbolFlg = true
                                                        }
                                                    }
                                                }
                                                1 -> when (j) {

                                                    // 7
                                                    0 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 7
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 8
                                                    1 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 8
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 9
                                                    2 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 9
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 乗算
                                                    3 -> {
                                                        if (saveNumber == defaultNumber) saveNumber = number
                                                        if (mulFlg && !equalFlg){
                                                            saveNumber *= number
                                                            number = 0
                                                        }
                                                        symbolClick()

                                                        if(saveNumber > 9999999999999){
                                                            mainView!!.setText("error")
                                                        }else {
                                                            mainView!!.setText(saveNumber.toString())
                                                            mainView!!.hint = saveNumber.toString()
                                                        }

                                                        mulFlg = true
                                                        symbolFlg = true
                                                    }
                                                }
                                                2 -> when (j) {

                                                    // 4
                                                    0 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 4
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 5
                                                    1 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 5
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 6
                                                    2 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 6
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 減算
                                                    3 -> {
                                                        if (saveNumber == defaultNumber) saveNumber = number
                                                        if (subFlg && !equalFlg){
                                                            saveNumber -= number
                                                            number = 0
                                                        }
                                                        symbolClick()

                                                        if(saveNumber > 9999999999999){
                                                            mainView!!.setText("error")
                                                        }else {
                                                            mainView!!.setText(saveNumber.toString())
                                                            mainView!!.hint = saveNumber.toString()
                                                        }

                                                        subFlg = true
                                                        symbolFlg = true
                                                    }
                                                }
                                                3 -> when (j) {

                                                    // 1
                                                    0 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 1
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 2
                                                    1 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 2
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 3
                                                    2 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number = number * 10 + 3
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }

                                                    // 加算
                                                    3 -> {
                                                        if (saveNumber == defaultNumber) saveNumber = number
                                                        if (addFlg && !equalFlg)  {
                                                            saveNumber += number
                                                            number = 0
                                                        }
                                                        symbolClick()

                                                        if(saveNumber > 9999999999999){
                                                            mainView!!.setText("error")
                                                        }else {
                                                            mainView!!.setText(saveNumber.toString())
                                                            mainView!!.hint = saveNumber.toString()
                                                        }

                                                        addFlg = true
                                                        symbolFlg = true
                                                    }
                                                }
                                                4 -> when (j) {
                                                    0 -> {}

                                                    // 0
                                                    1 -> {
                                                        flgCheck()
                                                        if (number < 1000000000000){
                                                            number *= 10
                                                            mainView!!.setText(number.toString())
                                                        }
                                                    }
                                                    2 -> {}

                                                    // イコール
                                                    3 -> {
                                                        equalFlg = true
                                                        symbolFlg = true
                                                        if (addFlg) saveNumber += number
                                                        if (subFlg) saveNumber -= number
                                                        if (mulFlg) saveNumber *= number
                                                        if (divFlg) saveNumber /= number

                                                        if(saveNumber > 99999999999999){
                                                            mainView!!.setText("error")
                                                        }else {
                                                            mainView!!.setText(saveNumber.toString())
                                                        }
                                                    }
                                                }
                                            }
                                        }catch (ex : Exception){
                                            ex.message
                                        }
                                    }
                                }.lparams {
                                    width = dip(100)
                                    height =dip(100)
                                    margin = dip(2)
                                }
                            }
                        }
                    }
                }
            }.lparams {
                width = matchParent
                height = matchParent
            }
        }

    }

    // フラグをチェックする
    fun flgCheck(){

        // イコールフラグが立っている場合
        if (equalFlg){
            saveNumber = 0
            number = 0
            equalFlg = false
        }

        // 何かフラグが立っている場合
        if(addFlg || subFlg || mulFlg || divFlg) {
            if (symbolFlg) {
                number = 0
                symbolFlg = false
            }
        }
    }

    // フラグが複数立っていた場合、フラグを初期化し押した記号フラグのみたてる
    fun symbolClick(){

        // フラグの初期化を行う
        if(addFlg || subFlg || mulFlg || divFlg){
            addFlg = false
            subFlg = false
            mulFlg = false
            divFlg = false
            equalFlg = false
        }
    }
}