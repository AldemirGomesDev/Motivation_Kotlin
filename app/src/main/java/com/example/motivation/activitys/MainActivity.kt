package com.example.motivation.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.mock.Mock
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter: Int = MotivationConstants.PHRASE.ALL
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mMock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        setListener()
        handleFilter(R.id.imgAll)
        refreshPhrase()
        verifyUserName()
    }

    private fun verifyUserName() {
        tvUserName.text = mSecurityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
    }

    private fun setListener() {
        imgAll.setOnClickListener(this)
        imgSun.setOnClickListener(this)
        imgHappy.setOnClickListener(this)
        btnNewPhrase.setOnClickListener(this)
    }
    override fun onClick(view: View) {
        val id = view.id
        val listId = listOf(
            R.id.imgAll,
            R.id.imgSun,
            R.id.imgHappy
        )
        if(id in listId){
            handleFilter(id)
        }else if (id == R.id.btnNewPhrase) {
            refreshPhrase()
        }
    }
    private fun handleFilter(id: Int) {
        imgAll.setImageResource(R.drawable.ic_all_no_select)
        imgSun.setImageResource(R.drawable.ic_sun_no_select)
        imgHappy.setImageResource(R.drawable.ic_happy_no_select)
        when (id) {
            R.id.imgAll -> {
                mFilter = MotivationConstants.PHRASE.ALL
                imgAll.setImageResource(R.drawable.ic_all_select)
            }
            R.id.imgSun -> {
                mFilter = MotivationConstants.PHRASE.SUN
                imgSun.setImageResource(R.drawable.ic_sun_select)
            }
            R.id.imgHappy -> {
                mFilter = MotivationConstants.PHRASE.HAPPY
                imgHappy.setImageResource(R.drawable.ic_happy_select)
            }
        }

    }
    private fun refreshPhrase() {
        tvPhrase.text = mMock.getPhrase(mFilter)
    }
}
