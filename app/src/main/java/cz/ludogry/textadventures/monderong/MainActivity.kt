package cz.ludogry.textadventures.monderong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cz.ludogry.textadventures.monderong.R

/**
 * This is just the main activity, that holds all the fragments.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
