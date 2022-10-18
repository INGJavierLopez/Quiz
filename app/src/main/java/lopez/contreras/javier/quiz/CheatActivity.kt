package lopez.contreras.javier.quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import lopez.contreras.javier.quiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOW = "com.enfranchiser.android.Quiz.answer_show"
const val EXTRA_ANSWER_IS_TRUE =
    "com.enfranchiser.android.Quiz.answer_is_true"
class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)
        binding.showAnswerButton.setOnClickListener { view : View ->
            val mySnack = Snackbar.make(view,R.string.judgmentToast,Snackbar.LENGTH_LONG)
            mySnack.setBackgroundTint(getColor(R.color.orange))
            mySnack.show()
            val answerText = when {
                answerIsTrue -> R.string.trueButton
                else -> R.string.falseButton
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)

        }
    }
    private fun setAnswerShownResult(isAnserShow: Boolean){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOW, isAnserShow)
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}