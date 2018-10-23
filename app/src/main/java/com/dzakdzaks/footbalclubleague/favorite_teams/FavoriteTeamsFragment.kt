package com.dzakdzaks.footbalclubleague.favorite_teams


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footbalclubleague.R.color.colorAccent
import com.dzakdzaks.footbalclubleague.favorite_teams.db.Favorite
import com.dzakdzaks.footbalclubleague.favorite_teams.db.database
import com.dzakdzaks.footbalclubleague.teams.detail.TeamDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamsFragment : Fragment(), AnkoComponent<Context> {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listEvent = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamsAdapter(favorites){
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }

        listEvent.adapter = adapter
        showFav()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFav()
        }
    }

    private fun showFav(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val fav = result.parseList(classParser<Favorite>())
            favorites.addAll(fav)
            adapter.notifyDataSetChanged()
        }
    }
}
