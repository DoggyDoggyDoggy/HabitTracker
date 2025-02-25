package denys.diomaxius.habittracker.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.habittracker.domain.usecase.CheckFirstLaunchUseCase
import denys.diomaxius.habittracker.domain.usecase.RegisterFirstEntryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    checkFirstLaunchUseCase: CheckFirstLaunchUseCase,
    private val registerFirstEntryUseCase: RegisterFirstEntryUseCase
) :ViewModel() {
    val isFirstLaunch: Flow<Boolean> = checkFirstLaunchUseCase()

    fun registerFirstEntry() {
        viewModelScope.launch {
            registerFirstEntryUseCase()
        }
    }
}