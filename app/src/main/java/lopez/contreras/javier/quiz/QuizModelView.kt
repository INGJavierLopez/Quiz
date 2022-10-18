package lopez.contreras.javier.quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
class QuizModelView(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private var currentIndex: Int
    get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
    set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private val questionBank = listOf(
        Quiz(R.string.Quest1,false),
        Quiz(R.string.Quest2,true),
        Quiz(R.string.Quest3,false),
        Quiz(R.string.Quest4,false),
        Quiz(R.string.Quest5,true),
        Quiz(R.string.Quest6,true),
        Quiz(R.string.Quest7,false),
        Quiz(R.string.Quest8,false),
        Quiz(R.string.Quest9,false),
        Quiz(R.string.Quest10,false),
        Quiz(R.string.Quest11,false),
        Quiz(R.string.Quest12,false),
        Quiz(R.string.Quest13,false),
        Quiz(R.string.Quest14,false),
        Quiz(R.string.Quest15,false),
        Quiz(R.string.Quest16,false),
        Quiz(R.string.Quest17,false),
        Quiz(R.string.Quest18,false),
        Quiz(R.string.Quest9,true),
        Quiz(R.string.Quest20,false)
    )

    var isCheater : Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    val currentQuestionAnswer:Boolean
    get() = questionBank[currentIndex].respuesta
    val currentQuestionText : Int
    get() = questionBank[currentIndex].textoPregunta

    fun nextQuestion(){
        currentIndex = (currentIndex + 1) %questionBank.size
    }
    fun backQuestion(){
        if (currentIndex == 0){
            currentIndex = questionBank.size-1
        }else {
            currentIndex = (currentIndex - 1) %questionBank.size
        }
    }
}