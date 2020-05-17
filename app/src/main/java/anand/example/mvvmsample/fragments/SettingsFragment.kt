package anand.example.mvvmsample.fragments

import anand.example.mvvmsample.R
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

/**
 * Settings fragment to update cache duration of application
 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}