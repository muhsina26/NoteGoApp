package com.example.notego.Adapter

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notego.Models.Note
import com.example.notego.R
import kotlin.random.Random

class NotesAdapter(private  val context: Context,val listener:NotesClickListener):RecyclerView.Adapter<NotesAdapter.NoteViewHolder> (){
    private val NotesList= ArrayList<Note>()
    private val fullList=ArrayList<Note>()





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return  NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return NotesList.size
    }
    fun updateList(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }
    fun filterList(search:String){
        NotesList.clear()
        for(item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase())==true || item.note?.lowercase()?.contains(search.lowercase())==true){
                NotesList.add(item)

            }
        }
        notifyDataSetChanged()

    }
    fun randomColor():Int{
        val list =ArrayList<Int>()
        list.add(R.color.pastel_blue)
        list.add(R.color.pastel_pink)
        list.add(R.color.pastel_teal)
        list.add(R.color.pastel_green)
        list.add(R.color.pastel_orange)
        list.add(R.color.pastel_purple)
        list.add(R.color.pastel_yellow)
        list.add(R.color.pastel_lavender)

        val seed=System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=NotesList[position]
        holder.title.text=currentNote.title
        holder.title.isSelected=true
        holder.Note_tv.text=currentNote.note
        holder.date.text=currentNote.date
        holder.date.isSelected=true
        holder.priority.text = "Priority: ${currentNote.priority ?: "N/A"}"

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor()))
        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listener.
            onLongItemClicked(
                NotesList[holder.adapterPosition],
                holder.notes_layout)
            true

        }


    }
    inner class  NoteViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title=itemView.findViewById<TextView>(R.id.tv_title)
        val Note_tv=itemView.findViewById<TextView>(R.id.tv_note)
        val date= itemView.findViewById<TextView>(R.id.tv_date)
        val priority = itemView.findViewById<TextView>(R.id.tv_priority)



    }
    interface  NotesClickListener{
        fun onItemClicked(note:Note)
        fun  onLongItemClicked(note: Note,cardView: CardView)
    }
}