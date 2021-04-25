package pet.plants.ui.payment

import android.R
import android.app.FragmentManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import butterknife.BindView
import butterknife.ButterKnife
import com..checkoutflow.CCFragment.CCNameFragment
import com.droidmentor.checkoutflow.CCFragment.CCNumberFragment
import com.droidmentor.checkoutflow.CCFragment.CCSecureCodeFragment
import com.droidmentor.checkoutflow.CCFragment.CCValidityFragment
import com.droidmentor.checkoutflow.Utils.ViewPagerAdapter
import pet.plants.ui.payment.CreditCardUtils.isValid
import pet.plants.ui.payment.CreditCardUtils.isValidDate


class CheckOutActivity : FragmentActivity(), FragmentManager.OnBackStackChangedListener {
    @BindView(R.id.btnNext)
    var btnNext: Button? = null
    var cardFrontFragment: CardFrontFragment? = null
    var cardBackFragment: CardBackFragment? = null

    //This is our viewPager
    private var viewPager: ViewPager? = null
    var numberFragment: CCNumberFragment? = null
    var nameFragment: CCNameFragment? = null
    var validityFragment: CCValidityFragment? = null
    var secureCodeFragment: CCSecureCodeFragment? = null
    var total_item = 0
    var backTrack = false
    private var mShowingBack = false
    var cardNumber: String? = null
    var cardCVV: String? = null
    var cardValidity: String? = null
    var cardName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        ButterKnife.bind(this)
        cardFrontFragment = CardFrontFragment()
        cardBackFragment = CardBackFragment()
        if (savedInstanceState == null) {
            // Add the fragment to the 'fragment_container' FrameLayout
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, cardFrontFragment).commit()
        } else {
            mShowingBack = fragmentManager.backStackEntryCount > 0
        }
        fragmentManager.addOnBackStackChangedListener(this)

        //Initializing viewPager
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager!!.offscreenPageLimit = 4
        setupViewPager(viewPager)
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                if (position == total_item) btnNext!!.text = "SUBMIT" else btnNext!!.text = "NEXT"
                Log.d("track", "onPageSelected: $position")
                if (position == total_item) {
                    flipCard()
                    backTrack = true
                } else if (position == total_item - 1 && backTrack) {
                    flipCard()
                    backTrack = false
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        btnNext!!.setOnClickListener {
            val pos = viewPager!!.currentItem
            if (pos < total_item) {
                viewPager!!.currentItem = pos + 1
            } else {
                checkEntries()
            }
        }
    }

    fun checkEntries() {
        cardName = nameFragment.getName()
        cardNumber = numberFragment.getCardNumber()
        cardValidity = validityFragment.getValidity()
        cardCVV = secureCodeFragment.getValue()
        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(this@CheckOutActivity, "Enter Valid Name", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(cardNumber) || !isValid(cardNumber!!.replace(" ", ""))) {
            Toast.makeText(this@CheckOutActivity, "Enter Valid card number", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(cardValidity) || !isValidDate(cardValidity!!)) {
            Toast.makeText(this@CheckOutActivity, "Enter correct validity", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(cardCVV) || cardCVV!!.length < 3) {
            Toast.makeText(this@CheckOutActivity, "Enter valid security number", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this@CheckOutActivity, "Your card is added", Toast.LENGTH_SHORT).show()
    }

    override fun onBackStackChanged() {
        mShowingBack = fragmentManager.backStackEntryCount > 0
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        numberFragment = CCNumberFragment()
        nameFragment = CCNameFragment()
        validityFragment = CCValidityFragment()
        secureCodeFragment = CCSecureCodeFragment()
        adapter.addFragment(numberFragment)
        adapter.addFragment(nameFragment)
        adapter.addFragment(validityFragment)
        adapter.addFragment(secureCodeFragment)
        total_item = adapter.getCount() - 1
        viewPager!!.adapter = adapter
    }

    private fun flipCard() {
        if (mShowingBack) {
            fragmentManager.popBackStack()
            return
        }
        // Flip to the back.
        //setCustomAnimations(int enter, int exit, int popEnter, int popExit)
        mShowingBack = true
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, cardBackFragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        val pos = viewPager!!.currentItem
        if (pos > 0) {
            viewPager!!.currentItem = pos - 1
        } else super.onBackPressed()
    }

    fun nextClick() {
        btnNext!!.performClick()
    }
}