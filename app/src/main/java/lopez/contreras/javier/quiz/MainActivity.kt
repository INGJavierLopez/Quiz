package lopez.contreras.javier.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import lopez.contreras.javier.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG,"Got a QuizViewModel:$quizViewModel")

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