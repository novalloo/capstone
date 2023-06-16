package com.bella.fitassistai.detail.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bella.fitassistai.R
import com.bella.fitassistai.api.ApiConfig
import com.bella.fitassistai.api.response.LoginResponse
import com.bella.fitassistai.databinding.ActivityLoginBinding
import com.bella.fitassistai.detail.register.RegisterActivity
import com.bella.fitassistai.main.MainActivity
import com.bella.fitassistai.utils.UserModel
import com.bella.fitassistai.utils.UserPreference
import com.bella.fitassistai.utils.UserToken
import com.bella.fitassistai.utils.ViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Setting")

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        animationPlay()
        binding.edtPasswordLog.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                validationPassword()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validationPassword()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.tvHaventAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLog.text.toString()
            val password = binding.edtPasswordLog.text.toString()

            dataLogin(email, password)
            loginViewModel.login()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dataLogin(email: String, password: String) {
        val client = ApiConfig.getApiService().loginUser(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        val token = responseBody.loginResult.token
                        loginViewModel.saveUserData(UserToken(token))
                        Toast.makeText(this@LoginActivity, responseBody.message, Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(this@LoginActivity, "Gagal instance Retrofit", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun validationPassword() {
        val result = binding.edtPasswordLog.text
        binding.btnLogin.isEnabled =
            result != null && result.toString().isNotEmpty() && result.length >= 8
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }

    private fun animationPlay() {
        val email = ObjectAnimator.ofFloat(binding.edtEmailLog, View.ALPHA, 1f).setDuration(700)
        val pass = ObjectAnimator.ofFloat(binding.edtPasswordLog, View.ALPHA, 1f).setDuration(700)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(700)
        val tvReg = ObjectAnimator.ofFloat(binding.tvHaventAccount, View.ALPHA, 1f).setDuration(700)

        AnimatorSet().apply {
            playSequentially(email, pass, btnLogin, tvReg)
            start()
        }

    }

    companion object {
        const val TAG = "LoginActivity"
    }

}