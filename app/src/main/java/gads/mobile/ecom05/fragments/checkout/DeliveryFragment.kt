package gads.mobile.ecom05.fragments.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_checkout_delivery_screen.*

class DeliveryFragment : Fragment() {

    private val shippingFee = 200

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout_delivery_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subtotal_value.text = arguments?.getString("totalPrice")

        shipping_value.text = shippingFee.toString()

        val subTotal = (subtotal_value.text as String?)?.toInt()
        val total = subTotal?.plus(shippingFee)

        total_value.text = total.toString()

        tv_process_to_payment.setOnClickListener {
            val deliveryBundle = Bundle()
            deliveryBundle.putString("paymentSubTotal", subtotal_value.text.toString())
            deliveryBundle.putString("paymentShipFee", shipping_value.text.toString())
            deliveryBundle.putString("paymentTotal", total_value.text.toString())
            it.findNavController().navigate(R.id.action_deliveryFragment_to_checkoutPaymentFragment,
                deliveryBundle)
        }


    }

}