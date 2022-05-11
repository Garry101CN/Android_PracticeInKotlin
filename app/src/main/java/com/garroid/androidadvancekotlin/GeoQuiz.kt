package com.garroid.androidadvancekotlin

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.garroid.androidadvancekotlin.BuildConfig.VERSION_CODE

private const val TAG = "GeoQuiz"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

class GeoQuiz : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var lastButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_quiz)

        var currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        lastButton = findViewById(R.id.last_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        lastButton.setOnClickListener {
            quizViewModel.moveToLast()
            updateQuestion()
        }

        cheatButton.setOnClickListener{ view ->
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@GeoQuiz, answerIsTrue)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val option = ActivityOptions
                    .makeClipRevealAnimation(view, 0,0, view.width, view.height)
                startActivityForResult(intent, REQUEST_CODE_CHEAT, option.toBundle())
            }else{
                startActivityForResult(intent, REQUEST_CODE_CHEAT)
            }
        }

        updateQuestion()
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(answer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val currentIndex = quizViewModel.currentIndex

        val messageResId = when{
            quizViewModel.isCheater[currentIndex] -> R.string.judgement_toast
            answer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val currentIndex = quizViewModel.currentIndex

        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK) return

        if(requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater[currentIndex] = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            Log.d("GeoQuiz", quizViewModel.isCheater.toString())
        }
    }
}