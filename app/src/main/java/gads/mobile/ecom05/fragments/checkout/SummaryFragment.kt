package gads.mobile.ecom05.fragments.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_checkout_summary_screen.*

class SummaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout_summary_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subtotal_value.text = arguments?.getString("summarySubTotal")
        shipping_fee.text = arguments?.getString("summaryShipFee")
        total_value.text = arguments?.getString("summaryTotal")

        tv_confirm.setOnClickListener {
            val cardPaymentBundle = Bundle()
            cardPaymentBundle.putString("totalPayment", total_value.text.toString())
            it.findNavController().navigate(R.id.action_summaryFragment_to_cardPaymentFragment,
                cardPaymentBundle)
        }

    }

}