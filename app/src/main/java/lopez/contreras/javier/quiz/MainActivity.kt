package lopez.contreras.javier.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import lopez.contreras.javier.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentIndex = 0
    private val questionBank = listOf(
        Quiz(R.string.Quest1,false),
        Quiz(R.string.Quest2,false),
        Quiz(R.string.Quest3,false),
        Quiz(R.string.Quest4,true),
        Quiz(R.string.Quest5,false)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view : View ->
            val mySnack = Snackbar.make(view,R.string.correctToast,Snackbar.LENGTH_LONG)
            mySnack.setBackgroundTint(resources.getColor(R.color.green))
            mySnack.setAction("Deshacer",{view:View->})
            mySnack.show()
        }
        binding.falseButton.setOnClickListener { view : View ->
            val mySnack = Snackbar.make(view,R.string.incorrectToast,Snackbar.LENGTH_LONG)
            mySnack.setBackgroundTint(resources.getColor(R.color.red))
            mySnack.setAction("Deshacer",{view:View->})
            mySnack.show()
        }
        binding.nextButton.setOnClickListener {
            actualizarPregunta()
            //validarRespuesta()
        }
        actualizarPregunta()
    }

    private fun actualizarPregunta(){
        val pregunta = questionBank[currentIndex].textoPregunta
        binding.questionTextView.setText(pregunta)
        if(currentIndex == 4){
            currentIndex = 0
        }
        else{
            currentIndex +=1
        }

    }
}