package pl.mybooker

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

import android.content.Intent
import android.accounts.AccountManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_home -> {
        message.setText(R.string.title_home)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_dashboard -> {
        message.setText(R.string.title_dashboard)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_notifications -> {
        message.setText(R.string.title_notifications)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val am = AccountManager.get(this)
    val accounts = am.getAccountsByType("pl.mybooker")

    if (accounts.isEmpty()) { // If user has no logged accounts
      val intent = Intent(this, NotLoggedInActivity::class.java)
      startActivity(intent)
    } else {
      if (accounts.size == 1) { // If user has only one account saved then proceed to the main view
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
      } else { // Display a dialog to pick one account to use

      }
    }
  }
}
