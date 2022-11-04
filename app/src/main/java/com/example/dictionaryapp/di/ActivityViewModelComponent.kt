package com.example.dictionaryapp.di

import androidx.activity.ComponentActivity
import com.example.dictionaryapp.di.vm.ViewModelModule
import dagger.BindsInstance
import dagger.Component


@PerActivityFragments
@Component(dependencies = [AppComponent::class],modules = [ViewModelModule::class, RepositoryModule::class])
interface ActivityViewModelComponent {

//    fun getLaunchesViewModel(): LaunchesViewModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun componentActivity(activity: ComponentActivity): Builder
        fun build(): ActivityViewModelComponent
        fun appComponent(appComponent: AppComponent): Builder
    }

//    fun inject(missionListFragment: MissionListFragment)
//
//    fun inject(missionDetailFragment: MissionDetailFragment)
}
