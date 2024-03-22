package com.pavlovalexey.torpedo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.repository.GameRepository

class GameViewModel(val gameRepository: GameRepository) : ViewModel() {
    private val _currentDialogueIndex = MutableLiveData(0)
    val currentDialogueIndex: LiveData<Int>
        get() = _currentDialogueIndex

    private val _currentScene = MutableLiveData<Scene>()
    val currentScene: LiveData<Scene>
        get() = _currentScene

    private val _resources = MutableLiveData<Resource>()
    val resources: LiveData<Resource>
        get() = _resources

    init {
        _currentScene.value = gameRepository.getInitialScene()
        _resources.value = Resource(0, 0, 0)
    }

    fun selectOption(optionIndex: Int) {
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        val selectedOption = gameRepository.getDialogueByIndex(currentDialogueIndex)?.options?.getOrNull(optionIndex)
        selectedOption?.let { option ->
            val nextDialogueIndex = option.nextDialogueIndex
            val currentResource = _resources.value ?: Resource(0, 0, 0)
            _resources.value = Resource(
                currentResource.rubles + option.resourceEffect.rubles,
                currentResource.fame + option.resourceEffect.fame,
                currentResource.teamLoyalty + option.resourceEffect.teamLoyalty
            )
            _currentScene.value = gameRepository.getSceneByDialogueIndex(nextDialogueIndex)
            _currentDialogueIndex.value = nextDialogueIndex
        }
    }

    // Функция для переключения на следующий диалог без выбора пользователя
    fun goToNextDialogue() {
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        _currentDialogueIndex.value = currentDialogueIndex + 1
        _currentScene.value = gameRepository.getSceneByDialogueIndex(currentDialogueIndex + 1)
    }
}