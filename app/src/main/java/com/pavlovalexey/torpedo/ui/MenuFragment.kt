package com.pavlovalexey.torpedo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.viewmodel.MenuViewModel

/** фрагмент для верхнего меню игры. Содержит справку по ресурсам и события, кнопки*/


import androidx.fragment.app.activityViewModels

class MenuFragment : Fragment() {

    private val viewModel: MenuViewModel by activityViewModels()

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val soundButton = view.findViewById<Button>(R.id.soundButton)
        val autoPlayButton = view.findViewById<Button>(R.id.autoPlayButton)
        val settingsButton = view.findViewById<Button>(R.id.settingsButton)
        val closeSaveButton = view.findViewById<Button>(R.id.closeSaveButton)
        val menuLayout = view.findViewById<View>(R.id.fragment_menu)
//        (requireActivity() as MainActivity).hideOptionsLayout()

        soundButton.setOnClickListener {
            toast("Вы нажали кнопку 'Звук' Функция в разработке")
        }

        autoPlayButton.setOnClickListener {
            toast("Вы нажали кнопку 'Автоплей' Функция в разработке")
        }

        settingsButton.setOnClickListener {
            toast("Вы нажали кнопку 'Настройки' Функция в разработке")
        }

        closeSaveButton.setOnClickListener {
            toast("Вы нажали кнопку 'Закрыть + Сохранить Прогресс' Функция в разработке")
        }

        menuLayout.setOnClickListener {
//            (requireActivity() as MainActivity).showOptionsLayout()
//            parentFragmentManager.beginTransaction().remove(this@MenuFragment).commit()

            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }

        return view
    }
}
