package lopez.contreras.javier.quiz

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import lopez.contreras.javier.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var trampa: Boolean = false
    private val quizViewModel: QuizModelView by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result
        if (result.resultCode == RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOW, false) ?: false
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
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
            actualizarPregunta(true)
        }
        binding.backButton?.setOnClickListener {
            actualizarPregunta(false)
        }
        binding.cheatButton?.setOnClickListener {
            //start cheat
            trampa = true
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            cheatLauncher.launch(intent)
        }
        binding.questionTextView.setText(quizViewModel.currentQuestionText)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blurCheatButton()
        }
    }

    private fun actualizarPregunta( context: Boolean){
        trampa = false
        if(context){
            quizViewModel.nextQuestion()
        }
        else{
            quizViewModel.backQuestion()
        }
        trampa = false
        val pregunta = quizViewModel.currentQuestionText
        binding.questionTextView.setText(pregunta)
    }
    private fun checkAnswer(answer: Boolean,view: View){
        val correctAnswer = quizViewModel.currentQuestionAnswer

         if (answer == correctAnswer){
            if (trampa){
                val mySnack = Snackbar.make(view,R.string.escarmiento,Snackbar.LENGTH_LONG)
                mySnack.setBackgroundTint(getColor(R.color.orange))
                mySnack.show()
            }
            else {
                R.string.correctToast
                val mySnack = Snackbar.make(view, R.string.correctToast, Snackbar.LENGTH_LONG)
                mySnack.setBackgroundTint(getColor(R.color.green))
                mySnack.show()
            }
             actualizarPregunta(true)
         }
         else{
                R.string.incorrectToast
                val mySnack = Snackbar.make(view,R.string.incorrectToast,Snackbar.LENGTH_LONG)
                mySnack.setBackgroundTint(getColor(R.color.red))
                mySnack.show()
         }

    }
    @RequiresApi(Build.VERSION_CODES.S)
    private fun blurCheatButton(){
        val effect = RenderEffect.createBlurEffect(
            10.0f,
            10.0f,
            Shader.TileMode.CLAMP
        )
        binding.cheatButton?.setRenderEffect(effect)
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