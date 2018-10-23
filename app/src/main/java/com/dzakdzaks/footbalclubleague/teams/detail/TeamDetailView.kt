package com.dzakdzaks.footbalclubleague.teams.detail

import com.dzakdzaks.footbalclubleague.response.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}