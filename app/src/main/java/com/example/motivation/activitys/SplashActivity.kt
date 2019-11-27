package com.example.motivation.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.Room
import com.example.motivation.R
import com.example.motivation.room.AppDatabase
import com.example.motivation.room.User
import com.example.motivation.room.UserDao
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*
import android.os.AsyncTask



class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurity: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurity = SecurityPreferences(this)
        btnSave.setOnClickListener(this)
        verifyUserName()
    }


    private fun verifyUserName() {
        val userName = mSecurity.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        if (userName != ""){
            startActivity(Intent(this, MainActivity::class.java))
        }
        edtName.setText(userName)
    }
    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.btnSave) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name: String = edtName.text.toString()

        if (name == "") {
            Toast.makeText(this, "Por favor informe seu nome", Toast.LENGTH_LONG).show()
        } else {
            var db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            val dao = db.userDao()
            val user = User(1, name, "lastName")
            Log.w("usandoRoom", "Usuario $user")


            AsyncTask.execute {
                // Insert Data
                dao.insert(user)

                val list: List<User> = dao.getAll()
                Log.w("usandoRoom", "List de Usuarios ${list.size}")

            }

            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
