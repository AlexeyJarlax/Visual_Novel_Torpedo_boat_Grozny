package com.pavlovalexey.torpedo.presentation.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.presentation.viewmodel.MenuViewModel

/** фрагмент для верхнего меню игры. Содержит справку по ресурсам и события, кнопки*/

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pavlovalexey.torpedo.presentation.viewmodel.MainViewModel

class MenuFragment() : Fragment() {

    private val viewModel: MenuViewModel by viewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

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
            AlertDialog.Builder(requireContext())
                .setTitle("Начать игру заново?")
                .setMessage("Вы уверены, что хотите начать игру заново?")
                .setPositiveButton("Да") { dialog, _ ->
                    mainViewModel.gameRepository.clearSavedResources()
                    mainViewModel.resetGame()
                    mainViewModel.setCurrentDialogueIndex(0)
                    dialog.dismiss()
                    menuLayout.performClick()
                }
                .setNegativeButton("Нет") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        menuLayout.setOnClickListener {
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }

        return view
    }
}
