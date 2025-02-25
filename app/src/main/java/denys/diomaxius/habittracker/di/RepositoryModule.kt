package denys.diomaxius.habittracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.habittracker.data.dao.HabitDao
import denys.diomaxius.habittracker.data.dao.HabitProgressDao
import denys.diomaxius.habittracker.data.datastore.DataStoreManager
import denys.diomaxius.habittracker.data.datastore.OnboardingDataStoreManager
import denys.diomaxius.habittracker.data.repository.HabitProgressRepositoryImpl
import denys.diomaxius.habittracker.data.repository.HabitRepositoryImpl
import denys.diomaxius.habittracker.data.repository.UserFirstEntryRepositoryImpl
import denys.diomaxius.habittracker.data.repository.YearStorageRepositoryImpl
import denys.diomaxius.habittracker.domain.repository.HabitProgressRepository
import denys.diomaxius.habittracker.domain.repository.HabitRepository
import denys.diomaxius.habittracker.domain.repository.UserFirstEntryRepository
import denys.diomaxius.habittracker.domain.repository.YearStorageRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideYearStorageRepository(
        dataStoreManager: DataStoreManager
    ): YearStorageRepository = YearStorageRepositoryImpl(dataStoreManager)

    @Provides
    fun provideUserFirstEntryRepository(
        onboardingDataStoreManager: OnboardingDataStoreManager
    ): UserFirstEntryRepository = UserFirstEntryRepositoryImpl(onboardingDataStoreManager)

    @Provides
    fun provideHabitProgressRepository(
        dao: HabitProgressDao
    ): HabitProgressRepository = HabitProgressRepositoryImpl(dao)

    @Provides
    fun provideHabitRepository(
        dao: HabitDao
    ): HabitRepository = HabitRepositoryImpl(dao)
}