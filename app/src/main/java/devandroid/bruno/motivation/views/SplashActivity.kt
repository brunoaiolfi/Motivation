package devandroid.bruno.motivation.views;

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import devandroid.bruno.motivation.constants.CONSTANTS_SHAREDPREFERENCES_KEYS
import devandroid.bruno.motivation.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySplashBinding;
    private lateinit var preferences: SharedPreferences;
    private lateinit var editor: SharedPreferences.Editor;

    override fun onCreate(savedInstaceState: Bundle?) {
        super.onCreate(savedInstaceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener(this);

        preferences =
            getSharedPreferences(CONSTANTS_SHAREDPREFERENCES_KEYS.KEY_APPLICATION, MODE_PRIVATE);
        editor = preferences.edit();

        validateUsername();
    }

    override fun onClick(view: View?) {
        val username_input = binding.usernameInput.text.toString();

        if (username_input.isNullOrBlank()) {
            binding.usernameInput.setError("Digite um nome de usuário válido!");
            return;
        }

        editor.putString(CONSTANTS_SHAREDPREFERENCES_KEYS.KEY_USERNAME, username_input);
        editor.apply();

        navigateToMainActivity();
    }

    fun validateUsername() {
        val username = preferences.getString(CONSTANTS_SHAREDPREFERENCES_KEYS.KEY_USERNAME, "");

        if (username.isNullOrBlank()) {
            binding.loadingSpinner.visibility = View.GONE;
            binding.formContainer.visibility = View.VISIBLE;
        } else {
            navigateToMainActivity();
        }
    }

    fun navigateToMainActivity() {
        val mainScreen = Intent(this, MainActivity::class.java);
        startActivity(mainScreen);
    }

}
