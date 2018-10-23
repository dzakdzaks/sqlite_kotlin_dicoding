package com.dzakdzaks.footbalclubleague.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dzakdzaks.footbalclubleague.favorite_teams.FavoriteTeamsFragment
import com.dzakdzaks.footbalclubleague.R
import com.dzakdzaks.footbalclubleague.R.id.favorites
import com.dzakdzaks.footbalclubleague.R.id.teams
import com.dzakdzaks.footbalclubleague.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                teams -> {
                    teamsFragment(savedInstanceState)
                }

                favorites -> {
                    favoriteTeamsFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.teams
    }

    private fun teamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun favoriteTeamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
}
