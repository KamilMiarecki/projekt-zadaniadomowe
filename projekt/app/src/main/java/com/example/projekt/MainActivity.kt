package com.example.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: ZadaniaŁącznik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        todoAdapter = ZadaniaŁącznik(mutableListOf())

        binding.rvListaZadan.adapter = todoAdapter
        binding.rvListaZadan.layoutManager = LinearLayoutManager(this)

        binding.btnDodajzadanie.setOnClickListener {
            val tytulZadania = binding.etDodajzadanie.text.toString()
            if (tytulZadania.isNotEmpty()) {
                val zadania = Zadania(tytulZadania)
                todoAdapter.dodajZadanie(zadania)
                binding.etDodajzadanie.text.clear()
            }
        }

        binding.btnUsunzadanie.setOnClickListener {
            todoAdapter.usunZadanie()
        }
    }
}