package gads.mobile.ecom05.fragments.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.paystack.android.Paystack.TransactionCallback
import co.paystack.android.PaystackSdk
import co.paystack.android.Transaction
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_card_payment_screen.*

class CardPaymentFragment : Fragment() {

    private lateinit var card : Card

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_payment_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//      Don't forget to add a payStack public test key gotten from its dashboard
//      in manifest file under payStack metadata

        PaystackSdk.initialize(context)
        tv_total_to_pay_value.text = arguments?.getString("totalPayment")

        card = Card(
            edit_card_number.text.toString(), spinner_month.text.toString().toInt(),
            spinner_year.text.toString().toInt(), edit_cvv.text.toString()
        )

        tv_confirm_payment.setOnClickListener {
            if (card.isValid){
                performCharge()
            }else {
                Toast.makeText(context, "Error charging card", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun performCharge() {
        //create a Charge object
        val charge = Charge()
        charge.card = card //sets the card to charge
        charge.amount = tv_total_to_pay_value.text.toString().toInt()
        charge.currency = "NGN"

        PaystackSdk.chargeCard(activity, charge, object : TransactionCallback {
            override fun onSuccess(transaction: Transaction?) {
                // This is called only after transaction is deemed successful.
                // Retrieve the transaction, and send its reference to your server
                // for verification.
                val paymentReference = transaction!!.reference
                Toast.makeText(context, "Transaction Successful $paymentReference",
                    Toast.LENGTH_SHORT).show()
            }

            override fun beforeValidate(transaction: Transaction?) {
                // This is called only before requesting OTP.
                // Save reference so you may send to server. If
                // error occurs with OTP, you should still verify on server.

            }

            override fun onError(error: Throwable?, transaction: Transaction?) {
                //handle error here
            }
        })

    }


}