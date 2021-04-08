package com.example.lab4_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import name.ank.lab4.BibDatabase
import name.ank.lab4.Keys
import org.w3c.dom.Text
import java.io.InputStream
import java.io.InputStreamReader

class Adapter(base: InputStream) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val reader = InputStreamReader(base)
    private val database = BibDatabase(reader)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = database.getEntry(position % database.size())
        holder.author.text = "Author(s): " + currentItem.getField(Keys.AUTHOR)
        holder.pages.text = "Pages: " + currentItem.getField(Keys.PAGES)
        holder.journal.text = "Journal: " + currentItem.getField(Keys.JOURNAL)
        holder.title.text = "Title: " + currentItem.getField(Keys.TITLE)
    }

    override fun getItemCount() = database.size()
        //Int.MAX_VALUE
        //database.size()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.author
        val pages: TextView = itemView.pages
        val title: TextView = itemView.title
        val journal: TextView = itemView.journal
    }
}