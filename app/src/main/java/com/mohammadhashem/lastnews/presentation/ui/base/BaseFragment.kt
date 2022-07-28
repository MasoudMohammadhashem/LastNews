package com.mohammadhashem.lastnews.presentation.ui.base


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.MaterialElevationScale
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!
    val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        exitTransition = MaterialElevationScale(false);
        reenterTransition = MaterialElevationScale(true);
        _binding = this.setBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    abstract fun setBinding(): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> collectStateFlows(flow: Flow<T>, collect: suspend(T) -> Unit){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                flow.collect(collect)
            }
        }
    }

    fun getThemeMode(): Int {
        return requireActivity().baseContext.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
    }

}
