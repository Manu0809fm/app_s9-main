package com.example.app_s9
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var editTextUsername: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonResetCounter: Button
    private lateinit var textViewResult: TextView
    private lateinit var textViewVisits: TextView
    private lateinit var switchTheme: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferencesHelper = SharedPreferencesHelper(this)
        initViews()
        setupListeners()
        checkFirstTime()
        updateVisitCounter()
        applySavedTheme()
    }

    private fun initViews() {
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        buttonResetCounter = findViewById(R.id.buttonResetCounter)
        textViewResult = findViewById(R.id.textViewResult)
        textViewVisits = findViewById(R.id.textViewVisits)
        switchTheme = findViewById(R.id.switchTheme)
    }

    private fun setupListeners() {
        buttonSave.setOnClickListener {
            saveData()
        }

        buttonLoad.setOnClickListener {
            loadData()
        }

        buttonClear.setOnClickListener {
            clearAllData()
        }

        buttonResetCounter.setOnClickListener {
            sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_VISIT_COUNT, 0)
            updateVisitCounter()
            Toast.makeText(this, "Contador reiniciado", Toast.LENGTH_SHORT).show()
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_THEME_MODE, isChecked)
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun saveData() {
        val username = editTextUsername.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }

        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, username)
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_LASTNAME, lastName)
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_EMAIL, email)
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())

        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        editTextUsername.setText("")
        editTextLastName.setText("")
        editTextEmail.setText("")
    }

    private fun loadData() {
        val username = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME)
        val lastName = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_LASTNAME)
        val email = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_EMAIL)
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)

        val result = """
            Usuario: $username $lastName
            ID: $userId
            Email: $email
            Primera vez: ${if (isFirstTime) "Sí" else "No"}
        """.trimIndent()

        textViewResult.text = result
    }

    private fun clearAllData() {
        sharedPreferencesHelper.clearAll()
        textViewResult.text = ""
        editTextUsername.setText("")
        editTextLastName.setText("")
        editTextEmail.setText("")
        updateVisitCounter()
        Toast.makeText(this, "Todas las preferencias han sido eliminadas", Toast.LENGTH_SHORT).show()
    }

    private fun checkFirstTime() {
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        if (isFirstTime) {
            Toast.makeText(this, "¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateVisitCounter() {
        var count = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_VISIT_COUNT, 0)
        count++
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_VISIT_COUNT, count)
        textViewVisits.text = "Visitas: $count"
    }

    private fun applySavedTheme() {
        val isDarkMode = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_THEME_MODE, false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
        switchTheme.isChecked = isDarkMode
    }
}
