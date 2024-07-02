package devandroid.bruno.motivation.views

import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import devandroid.bruno.motivation.R
import devandroid.bruno.motivation.constants.CONSTANTS_SHAREDPREFERENCES_KEYS
import devandroid.bruno.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var preferences: SharedPreferences;
    private lateinit var binding: ActivityMainBinding;

    private var username: String? = null;
    private var category: String? = "category_happy";

    private var dictionaryPhrasesHappy = mutableMapOf<Int, String>();
    private var dictionaryPhrasesLife = mutableMapOf<Int, String>();
    private var dictionaryPhrasesNature = mutableMapOf<Int, String>();
    private var dictionaryPhrases = mutableMapOf<String, Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);

        setContentView(binding.root)

        binding.genPhraseButton.setOnClickListener(this);
        binding.categoryHappyButton.setOnClickListener(this);
        binding.categoryLifeButton.setOnClickListener(this);
        binding.categoryNatureButton.setOnClickListener(this);

        preferences =
            getSharedPreferences(CONSTANTS_SHAREDPREFERENCES_KEYS.KEY_APPLICATION, MODE_PRIVATE);

        username = preferences.getString(CONSTANTS_SHAREDPREFERENCES_KEYS.KEY_USERNAME, "Usuário");

        binding.helloUserText.text = "Olá, ${username}";

        configurePhrases();
        generateNewPhrase(binding.root);
    }

    override fun onClick(view: View) {
        val idViewSelected = view.id;
        val dictionary = mutableMapOf<Int, (View) -> Unit>()

        dictionary[R.id.gen_phrase_button] = ::generateNewPhrase;
        dictionary[R.id.category_happy_button] = ::selectCategory;
        dictionary[R.id.category_life_button] = ::selectCategory;
        dictionary[R.id.category_nature_button] = ::selectCategory;

        dictionary[idViewSelected]?.invoke(view);
    }

    fun configurePhrases() {
        this.dictionaryPhrasesHappy[1] = "Felicidade - A persistência é o caminho do êxito."
        this.dictionaryPhrasesHappy[2] = "Felicidade - O que não provoca minha morte faz com que eu fique mais forte."
        this.dictionaryPhrasesHappy[3] =
            "Felicidade - Pedras no caminho? Eu guardo todas. Um dia vou construir um castelo."
        this.dictionaryPhrasesHappy[4] = "Felicidade - É parte da cura o desejo de ser curado."
        this.dictionaryPhrasesHappy[5] =
            "Felicidade - Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado."


        this.dictionaryPhrasesLife[1] = "Vida - A persistência é o caminho do êxito."
        this.dictionaryPhrasesLife[2] = "Vida - O que não provoca minha morte faz com que eu fique mais forte."
        this.dictionaryPhrasesLife[3] =
            "Vida - Pedras no caminho? Eu guardo todas. Um dia vou construir um castelo."
        this.dictionaryPhrasesLife[4] = "Vida - É parte da cura o desejo de ser curado."
        this.dictionaryPhrasesLife[5] =
            "Vida - Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado."

        this.dictionaryPhrasesNature[1] = "Natureza - A persistência é o caminho do êxito."
        this.dictionaryPhrasesNature[2] = "Natureza - O que não provoca minha morte faz com que eu fique mais forte."
        this.dictionaryPhrasesNature[3] =
            "Natureza - Pedras no caminho? Eu guardo todas. Um dia vou construir um castelo."
        this.dictionaryPhrasesNature[4] = "Natureza - É parte da cura o desejo de ser curado."
        this.dictionaryPhrasesNature[5] =
            "Natureza - Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado."

        this.dictionaryPhrases["category_happy"] = this.dictionaryPhrasesHappy;
        this.dictionaryPhrases["category_life"] = this.dictionaryPhrasesLife;
        this.dictionaryPhrases["category_nature"] = this.dictionaryPhrasesNature;
    }

    fun generateNewPhrase(view: View) {
        val dictionaryPhrasesSelected = this.dictionaryPhrases[this.category] as Map<Int, String>;

        val number = (1..5).random()
        val phrase = dictionaryPhrasesSelected[number]

        if (phrase == binding.phraseText.text) return generateNewPhrase(view);

        binding.phraseText.text = phrase;
    }

    fun selectCategory(view: View) {
        val categoriesButtonsList: List<ImageButton> = listOf(
            binding.categoryHappyButton,
            binding.categoryLifeButton,
            binding.categoryNatureButton
        );

        val categorySelected = view.tag.toString();

        categoriesButtonsList.forEach() {
            val color = if (categorySelected == it.tag) "#ffffff" else "#630D71"
            it.imageTintList = ColorStateList.valueOf(Color.parseColor(color))
        }

        this.category = categorySelected;
    }
}