package com.dzakdzaks.footbalclubleague.teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dzakdzaks.footbalclubleague.R
import com.dzakdzaks.footbalclubleague.response.Team
import com.dzakdzaks.footbalclubleague.R.id.team_badge
import com.dzakdzaks.footbalclubleague.R.id.team_name
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamsAdapter(private val team: List<Team>, private val listener: (Team) -> Unit): RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(p0.context, p0)))
    }
    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.bindItem(team[p1], listener)
    }

    override fun getItemCount(): Int = team.size


    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }

                }
            }
        }

    }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val teamBadge: ImageView = view.find(team_badge)
        private val teamName: TextView = view.find(team_name)
        fun bindItem(team: Team, listener: (Team) -> Unit){
            Glide.with(itemView).load(team.teamBadge).into(teamBadge)
            teamName.text = team.teamName
            itemView.onClick { listener(team) }
        }
    }

}