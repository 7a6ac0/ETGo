package tabacowang.me.etgo.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tabacowang.me.etgo.databinding.TextFragmentBinding

class TextFragment : Fragment() {

    private lateinit var binding: TextFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TextFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}