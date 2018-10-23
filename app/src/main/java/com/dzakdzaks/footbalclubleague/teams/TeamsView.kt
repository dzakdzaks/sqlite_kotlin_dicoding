package com.dzakdzaks.footbalclubleague.teams

import com.dzakdzaks.footbalclubleague.response.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}