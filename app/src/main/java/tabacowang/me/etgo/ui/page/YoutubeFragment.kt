package tabacowang.me.etgo.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tabacowang.me.etgo.databinding.YoutubeFragmentBinding

class YoutubeFragment : Fragment() {

    private lateinit var binding: YoutubeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = YoutubeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}