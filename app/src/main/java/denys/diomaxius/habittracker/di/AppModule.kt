package denys.diomaxius.habittracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.habittracker.data.database.HabitDatabase
import denys.diomaxius.habittracker.data.datastore.DataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        HabitDatabase::class.java,
        HabitDatabase.NAME
    ).build()

    @Provides
    @Singleton
    fun provideHabitDao(db: HabitDatabase) = db.habitDao()

    @Provides
    @Singleton
    fun provideProgressDao(db: HabitDatabase) = db.habitProgressDao()

    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context) = DataStoreManager(context)
}