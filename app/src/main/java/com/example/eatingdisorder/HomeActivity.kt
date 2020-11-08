package com.example.eatingdisorder

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    //declaring variable
    lateinit var option: Spinner
    private var gender: String = "Male"
    private var activityType: String = "lightly active"
    private final val REQUEST_CALL = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /*calling functions*/
        onClick()
        //calories spinner
        spinnerBox()
        //gender selection with radio group
        genderSelection()


    }

    @SuppressLint("SetTextI18n")
    private fun calculateCalories() {
        //declaring variables
        var activityNumber = 0.0
        val edAge = findViewById<EditText>(R.id.edAge)
        val edWeight = findViewById<EditText>(R.id.edWeight)
        val edHeight = findViewById<EditText>(R.id.edHeight)
        val tvCalculateCalories = findViewById<TextView>(R.id.tvCalculateCalories)

        //getting the value from spinner..lightly active", "moderately active", "very active", "extra active"

        when (activityType) {
            "Not active" -> activityNumber = 1.2
            "lightly active" -> activityNumber = 1.375
            "moderately active" -> activityNumber = 1.55
            "very active" -> activityNumber = 1.725
            "extra active" -> activityNumber = 1.9
        }


        //checking whether the string is empty or not..
        if (edAge.text.toString().isEmpty() || edWeight.text.toString()
                .isEmpty() || edHeight.text.toString().isEmpty()
        ) {
            Snackbar.make(edAge, "All fields are important!!", Snackbar.LENGTH_LONG).show()
            tvCalculateCalories.visibility = View.INVISIBLE
        } else {
            if (gender == "Male") {
                val number =
                    66 + (6.3 * edWeight.text.toString()
                        .toDouble()) + (12.9 * edHeight.text.toString()
                        .toDouble()) - (6.8 * edAge.text.toString()
                        .toInt())
                Snackbar.make(
                    edAge,
                    "Your calories intake should be: " + (activityNumber * number),
                    Snackbar.LENGTH_LONG
                )
                    .show()
                tvCalculateCalories.visibility = View.VISIBLE
                tvCalculateCalories.text = "Your calories intake should be: " + String.format(
                    "%.3f",
                    (activityNumber * number)
                ).toDouble() + " Kcal"

            } else {
                val number =
                    655 + (4.3 * edWeight.text.toString()
                        .toDouble()) + (4.7 * edHeight.text.toString()
                        .toDouble()) - (4.7 * edAge.text.toString()
                        .toInt())
                Snackbar.make(
                    edAge,
                    "Your calories are: " + (activityNumber * number),
                    Snackbar.LENGTH_LONG
                )
                    .show()
                tvCalculateCalories.visibility = View.VISIBLE
                tvCalculateCalories.text = "Your calories intake should be: " + String.format(
                    "%.3f",
                    (activityNumber * number)
                ).toDouble() + " Kcal"

            }

        }

    }


    //gender option group
    private fun genderSelection() {
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioBtn1) {
                gender = radioBtn1.text.toString()
            }
            if (checkedId == R.id.radioBtn2) {
                gender = radioBtn2.text.toString()

            }
            if (checkedId == R.id.radioBtn3) {
                gender = radioBtn3.text.toString()
            }
        }
    }

    //spinner box
    private fun spinnerBox() {
        option = findViewById<Spinner>(R.id.spinner)
        val tvSelection = findViewById<TextView>(R.id.tvSelection)

        val textOptions = arrayOf(
            "Select an option",
            "Not active", "lightly active", "moderately active", "very active", "extra active"
        )
        option.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textOptions)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                activityType = textOptions.get(position)
            }

        }
    }

    private fun onClick() {
        fabOnClick()

        //calculating Calories on click of calculate
        val calculate = findViewById<TextView>(R.id.calculate)
        calculate.setOnClickListener {
            calculateCalories()

        }


        //opening questions activity..
        val startQuestionnaire = findViewById<TextView>(R.id.startQuestionnaire)
        startQuestionnaire.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slie_out_left
            )
        }

        //opening dialog
        val treatmentReadMore = findViewById<TextView>(R.id.treatmentReadMore)
        treatmentReadMore.setOnClickListener {
            openTreatmentDialog()
        }

        val preventionReadMore = findViewById<TextView>(R.id.preventionReadMore)
        preventionReadMore.setOnClickListener {
            openPreventionDialog()
        }

        val awarenessReadMore = findViewById<TextView>(R.id.awarenessReadMore)
        awarenessReadMore.setOnClickListener {
            val intent = Intent(this, AwarnessActivity::class.java)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slie_out_left
            )
        }


    }

    private fun openPreventionDialog() {
        val dialog = Dialog(this, android.R.style.Theme_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.prevention_dialog)
        dialog.show()

        val preventTextview = dialog.findViewById<TextView>(R.id.preventTextview)
        preventTextview.movementMethod = ScrollingMovementMethod()


        val preClose = dialog.findViewById<ImageView>(R.id.prevention)
        preClose.setOnClickListener {
            dialog.hide()

        }
    }

    private fun openTreatmentDialog() {
        val dialog = Dialog(this, android.R.style.Theme_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.treatment_dialog)
        dialog.show()

        val treatClose = dialog.findViewById<ImageView>(R.id.awareness)
        treatClose.setOnClickListener {
            dialog.hide()

        }
    }

    private fun fabOnClick() {
        //declaring variables
        var isOpen = false
        val headButton = findViewById<FloatingActionButton>(R.id.headButton)
        val callButton = findViewById<FloatingActionButton>(R.id.callButton)
        val chatButton = findViewById<FloatingActionButton>(R.id.chatButton)
        val textChat = findViewById<TextView>(R.id.textChat)
        val textCall = findViewById<TextView>(R.id.textCall)

        //defining animations
        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabAnticlockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        headButton.setOnClickListener {
            if (isOpen) {
                callButton.startAnimation(fabClose)
                textChat.startAnimation(fabClose)
                chatButton.startAnimation(fabClose)
                textCall.startAnimation(fabClose)
                headButton.startAnimation(fabClockwise)

                isOpen = false
            } else {

                callButton.startAnimation(fabOpen)
                textChat.startAnimation(fabOpen)
                chatButton.startAnimation(fabOpen)
                textCall.startAnimation(fabOpen)
                headButton.startAnimation(fabAnticlockwise)

                chatButton.isClickable
                callButton.isClickable

                isOpen = true
            }

            chatButton.setOnClickListener {
                Snackbar.make(it, "Connecting you with the consultant", Snackbar.LENGTH_LONG).show()

                val intent = Intent(this, ChatActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slie_out_left
                )
            }

            callButton.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL
                    );
                } else {
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:18666334220")
                    startActivity(callIntent)
                    Snackbar.make(it, "Making an emergency call..", Snackbar.LENGTH_LONG).show()
                }

            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CALL -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

}

