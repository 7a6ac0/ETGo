package tabacowang.me.etgo.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    var fragments = listOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}