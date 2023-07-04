package com.example.projekt



import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt.databinding.ItemzadanieBinding

class ZadaniaŁącznik(
    private val todos: MutableList<Zadania>
) : RecyclerView.Adapter<ZadaniaŁącznik.Widok>() {
    class Widok(private val binding: ItemzadanieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Widok {
        val binding = ItemzadanieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Widok(binding)
    }

    fun dodajZadanie(todo: Zadania) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun usunZadanie() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: Widok, position: Int) {
        val obecneZadanie = todos[position]
        val binding = ItemzadanieBinding.bind(holder.itemView)

        binding.tvTytuZadania.text = obecneZadanie.title
        binding.cbDone.isChecked = obecneZadanie.isChecked
        toggleStrikeThrough(binding.tvTytuZadania, obecneZadanie.isChecked)

        binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(binding.tvTytuZadania, isChecked)
            obecneZadanie.isChecked = !obecneZadanie.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}