package net.kathir.kotlinmvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.kathir.kotlinmvi.R
import net.kathir.kotlinmvi.model.Users
import net.kathir.kotlinmvi.util.DataState
import java.lang.StringBuilder

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetUserEvents)
    }

    private fun subscribeObservers()
    {
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState)
            {
                is DataState.Success<List<Users>> -> {
                    displayProgressBar(false)
                    appendUserTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    dataState.exception.message?.let { displayError(it) }
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }

            }
        })
    }

    private fun displayError(message: String?)
    {
        if(message != null){
            text.text = message
        }
        else
        {
            text.text = "Unknown Error"
        }

    }

    private fun displayProgressBar(isDisplayed: Boolean)
    {
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendUserTitles(users: List<Users>)
    {
        val sb = StringBuilder()
        for(user in users)
        {
            sb.append(user.login + "\n")
        }
        text.text = sb.toString()
    }
}