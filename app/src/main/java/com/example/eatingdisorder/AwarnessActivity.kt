package com.example.eatingdisorder

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AwarnessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_awarness)

        onClick()

    }

    private fun onClick() {
        val readMoreAnorexia = findViewById<TextView>(R.id.readMoreAnorexia)
        val readMoreBulimia = findViewById<TextView>(R.id.readMoreBulimia)
        val readMoreBinge = findViewById<TextView>(R.id.readMoreBinge)
        val backAwareness = findViewById<TextView>(R.id.backAwareness)

        backAwareness.setOnClickListener {
            onBackPressed()
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_rigth
            )
        }

        readMoreAnorexia.setOnClickListener {
            openDialogAwareness(
                "An individual suffering from anorexia nervosa may reveal one or several signs and symptoms such as:",
                "•\tChronic restrictive eating or dieting, beyond the norm\n" +
                        "•\tRapidly losing weight or being significantly underweight and emaciated\n" +
                        "•\tObsession with calories and fat contents of food\n" +
                        "•\tEngaging in ritualistic eating patterns, such as cutting food into tiny pieces, eating alone, and/or hiding food\n" +
                        "•\tContinued fixation with food, recipes, or cooking; the individual may cook intricate meals for others but refrain from partaking\n" +
                        "•\tAmenorrhea: an abnormal absence of menstruation, or loss of 3 consecutive menstrual cycles\n" +
                        "•\tDepression or lethargic stage\n" +
                        "•\tDevelopment of lanugo: soft, fine hair that grows on face and body\n" +
                        "•\tReported sensation of feeling cold, particularly in extremities\n" +
                        "•\tLoss or thinning of hair\n" +
                        "•\tAvoidance of social functions, family, and friends. May become isolated and withdrawn"
            )
        }

        readMoreBulimia.setOnClickListener {
            openDialogAwareness(
                "An individual suffering from bulimia nervosa may reveal several signs and symptoms, many which are the direct result of self-induced vomiting or other forms of purging, especially if the binge/purge cycle is repeated several times a week and/or day.",
                "•\tConstant weight fluctuations\n" +
                        "•\tElectrolyte imbalances, which can result in cardiac arrhythmia, cardiac arrest, or ultimately death\n" +
                        "•\tBroken blood vessels within the eyes\n" +
                        "•\tEnlarged glands in the neck and under the jaw line\n" +
                        "•\tOral trauma, such as lacerations in the lining of the mouth or throat from repetitive vomiting\n" +
                        "•\tInflammation of the esophagus\n" +
                        "•\tChronic gastric reflux after eating  or peptic ulcers\n" +
                        "•\tDisappearance of large amounts of food\n" +
                        "•\tEating in secrecy\n" +
                        "•\tLack of control when eating\n" +
                        "•\tSwitching between periods of overeating and fasting\n" +
                        "•\tFrequent use of the bathroom after meals\n" +
                        "•\tHaving the smell of vomit"
            )

        }

        readMoreBinge.setOnClickListener {
            openDialogAwareness(
                "As individuals suffering from binge eating disorder experience embarrassment or shame about their eating habits, symptoms may often be hidden.\n" +
                        "The following are some behavioral and emotional signs and symptoms of BED:",
                "•\tContinually eating even when full\n" +
                        "•\tInability to stop eating or control what is eaten\n" +
                        "•\tStockpiling food to consume secretly at a later time\n" +
                        "•\tEating normally in the presence of others but gorging when isolated\n" +
                        "•\tExperiencing feelings of stress or anxiety that can only be relieved by eating\n" +
                        "•\tFeelings of numbness or lack of sensation while bingeing\n" +
                        "•\tNever experiencing satiation: the state of being satisfied, no matter the amount of food consumed"
            )

        }

    }

    private fun openDialogAwareness(headText: String, bodyText: String) {
        val dialog = Dialog(this, android.R.style.Theme_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.awarness_dialog)
        dialog.show()

        val symptomHeadText = dialog.findViewById<TextView>(R.id.symptomHeadText)
        val symptomBodyText = dialog.findViewById<TextView>(R.id.symptomBodyText)

        symptomHeadText.text = headText
        symptomBodyText.text = bodyText

        val treatClose = dialog.findViewById<ImageView>(R.id.QuestionAwareness)
        treatClose.setOnClickListener {
            dialog.hide()
        }
    }
}