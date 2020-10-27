package gads.mobile.ecom05.fragments.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_checkout_payment_screen.*

class CheckoutPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout_payment_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subtotal_value.text = arguments?.getString("paymentSubTotal")
        shipping_value.text = arguments?.getString("paymentShipFee")
        total_value.text = arguments?.getString("paymentTotal")

        tv_process_to_payment.setOnClickListener {
            val summaryBundle = Bundle()
            summaryBundle.putString("summarySubTotal", subtotal_value.text.toString())
            summaryBundle.putString("summaryShipFee", shipping_value.text.toString())
            summaryBundle.putString("summaryTotal", total_value.text.toString())
            it.findNavController().navigate(R.id.action_checkoutPaymentFragment_to_summaryFragment,
            summaryBundle)
        }

    }


}