package com.bella.fitassistai.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bella.fitassistai.R

class EditProfileActivity : AppCompatActivity() {

    var editGender: EditText? = null
    var editAge: EditText? = null
    var editHeight: EditText? = null
    var editWeight: EditText? = null
    var saveButton: Button? = null

    var genderUser: String? = null
    var ageUser: String? = null
    var heightUser: String? = null
    var weightUser: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        // reference = FirebaseDatabase.getInstance().getReference("users")
        editGender = findViewById<EditText>(R.id.edt_gender)
        editAge = findViewById<EditText>(R.id.edt_gender)
        editHeight = findViewById<EditText>(R.id.edt_height)
        editWeight = findViewById<EditText>(R.id.edt_weight)
        saveButton = findViewById<Button>(R.id.btn_save)

        showData()

        saveButton.setOnClickListener(View.OnClickListener {
            if (isGenderChanged() || isAgeChanged() || isHeightChanged() || isWeightChanged()) {
                Toast.makeText(this@EditProfileActivity, "Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@EditProfileActivity, "No Changes Found", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun isGenderChanged(): Boolean {
        return if (genderUser != editGender!!.text.toString()) {
            reference.child(usernameUser).child("name").setValue(editGender!!.text.toString())
            genderUser = editGender!!.text.toString()
            true
        } else {
            false
        }
    }

    fun isAgeChanged(): Boolean {
        return if (ageUser != editAge!!.text.toString()) {
            reference.child(usernameUser).child("email").setValue(editAge!!.getText().toString())
            ageUser = editAge!!.getText().toString()
            true
        } else {
            false
        }
    }

    fun isHeightChanged(): Boolean {
        return if (heightUser != editHeight!!.getText().toString()) {
            reference.child(usernameUser).child("password").setValue(editHeight!!.getText().toString())
            heightUser = editHeight!!.getText().toString()
            true
        } else {
            false
        }
    }

    fun isWeightChanged(): Boolean {
        return if (weightUser != editWeight!!.getText().toString()) {
            reference.child(usernameUser).child("password").setValue(editWeight!!.getText().toString())
            weightUser = editWeight!!.getText().toString()
            true
        } else {
            false
        }
    }

    fun showData() {
        val intent = intent
        genderUser = intent.getStringExtra("Your Gender")
        ageUser = intent.getStringExtra("Your Age")
        heightUser = intent.getStringExtra("Your Weight")
        weightUser = intent.getStringExtra("Your Height")
        editGender!!.setText(genderUser)
        editAge!!.setText(ageUser)
        editHeight!!.setText(heightUser)
        editWeight!!.setText(weightUser)
    }
}