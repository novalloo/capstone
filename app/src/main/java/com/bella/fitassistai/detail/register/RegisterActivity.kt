package com.bella.fitassistai.detail.register

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
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bella.fitassistai.R
import com.bella.fitassistai.api.ApiConfig
import com.bella.fitassistai.api.response.RegisterResponse
import com.bella.fitassistai.databinding.ActivityRegisterBinding
import com.bella.fitassistai.detail.login.LoginActivity
import com.bella.fitassistai.main.MainActivity
import com.bella.fitassistai.utils.UserModel
import com.bella.fitassistai.utils.UserPreference
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
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        animationPlay()
        binding.edtPasswordRegis.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                validationButton()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validationButton()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.btnRegis.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val email = binding.edtEmailRegis.text.toString()
            val password = binding.edtPasswordRegis.text.toString()

            registerViewModel.saveUser(UserModel(username, email, password, false))
            dataRegister(username, email, password)

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.tvHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validationButton() {
        val result = binding.edtPasswordRegis.text
        binding.btnRegis.isEnabled =
            result != null && result.toString().isNotEmpty() && result.length >= 8
    }

    private fun dataRegister(name: String, email: String, password: String) {
        val client = ApiConfig.getApiService().regisUser(name, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>,
            ) {
                if (response.isSuccessful) {
                    val responsBody = response.body()
                    if (responsBody != null && !responsBody.error) {
                        Toast.makeText(
                            this@RegisterActivity,
                            responsBody.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(this@RegisterActivity, "Gagal instance Retrofit", Toast.LENGTH_SHORT)
                    .show()
            }
        })
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
        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[RegisterViewModel::class.java]
    }

    private fun animationPlay() {
        val username = ObjectAnimator.ofFloat(binding.edtUsername, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.edtEmailRegis, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.edtPasswordRegis, View.ALPHA, 1f).setDuration(500)
        val btnRegis = ObjectAnimator.ofFloat(binding.btnRegis, View.ALPHA, 1f).setDuration(500)
        val tvLogin = ObjectAnimator.ofFloat(binding.tvHaveAccount, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(username, email, password, btnRegis, tvLogin)
            start()
        }

    }

    companion object {
        const val TAG = "RegisterActivity"
    }
}