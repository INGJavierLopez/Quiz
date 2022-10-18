package lopez.contreras.javier.quiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import lopez.contreras.javier.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizModelView by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOW, false) ?: false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view : View ->
            checkAnswer(true,view)
        }
        binding.falseButton.setOnClickListener { view : View ->
            checkAnswer(false,view)
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            actualizarPregunta()
        }
        binding.cheatButton?.setOnClickListener {
            //start cheat
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            cheatLauncher.launch(intent)
        }
        actualizarPregunta()
    }

    private fun actualizarPregunta(){
        val pregunta = quizViewModel.currentQuestionText
        binding.questionTextView.setText(pregunta)
    }
    private fun checkAnswer(answer: Boolean,view: View){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (answer == correctAnswer){
                R.string.correctToast
                val mySnack = Snackbar.make(view,R.string.correctToast,Snackbar.LENGTH_LONG)
                mySnack.setBackgroundTint(getColor(R.color.green))
                mySnack.show()
            }
            else{
                R.string.incorrectToast
                val mySnack = Snackbar.make(view,R.string.incorrectToast,Snackbar.LENGTH_LONG)
                mySnack.setBackgroundTint(getColor(R.color.red))
                mySnack.show()
            }
        actualizarPregunta()

    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"En el onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"En el onDestroy")
    }
}