package com.example.shoppingchartapp
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.shoppingchartapp.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var color = Color.rgb(0,0,0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getPreferences(MODE_PRIVATE)
        editor = prefs.edit()

        binding.seekBarRed.progress = prefs.getInt("Red", 0)
        binding.seekBarGreen.progress = prefs.getInt("Green", 0)
        binding.seekBarBlue.progress = prefs.getInt("Blue", 0)
        onSeekBarChange()
        binding.seekBarRed.max = 255
        binding.seekBarGreen.max = 255
        binding.seekBarBlue.max = 255
        setSeekBarListener(binding.seekBarRed)
        setSeekBarListener(binding.seekBarGreen)
        setSeekBarListener(binding.seekBarBlue)

        binding.saveButton.setOnClickListener{
            editor.putInt("Red", binding.seekBarRed.progress)
            editor.putInt("Green", binding.seekBarGreen.progress)
            editor.putInt("Blue", binding.seekBarBlue.progress)
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun onSeekBarChange(){
        color = Color.rgb(binding.seekBarRed.progress,binding.seekBarGreen.progress,binding.seekBarBlue.progress)
        binding.colorView.setColorFilter(color)
    }

    private fun setSeekBarListener(seekBar: SeekBar){
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                onSeekBarChange()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }
}