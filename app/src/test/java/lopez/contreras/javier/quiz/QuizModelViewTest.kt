package lopez.contreras.javier.quiz
import androidx.lifecycle.SavedStateHandle
import org.junit.Test
import org.junit.Assert.assertEquals


class QuizModelViewTest{
    @Test
    fun proveeElTextoDePreguntaEsperado(){
        val savedStateHande = SavedStateHandle()
        val quizModelView = QuizModelView(savedStateHande)
        assertEquals(R.string.Quest1,quizModelView.currentQuestionText)
        quizModelView.nextQuestion()
        assertEquals(R.string.Quest2,quizModelView.currentQuestionText)
    }
    @Test
    fun funcionaElBancoDePreguntas(){
        val savedStateHande = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 10))
        val quizModelView = QuizModelView(savedStateHande)
        assertEquals(R.string.Quest11,quizModelView.currentQuestionText)
        quizModelView.nextQuestion()
        assertEquals(R.string.Quest12,quizModelView.currentQuestionText)
    }

}