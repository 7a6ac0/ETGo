package tabacowang.me.etgo.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import tabacowang.me.etgo.ui.page.TextFragment
import tabacowang.me.etgo.ui.page.YoutubeFragment

class PagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val fragments = arrayOf(TextFragment(), YoutubeFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}