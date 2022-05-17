package com.jlaj5.statplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jlaj5.statplayer.databinding.ActivityMainBinding
import com.jlaj5.statplayer.databinding.StatPlayerMainBinding
import com.jlaj5.statplayer.repository.Repository
import okhttp3.internal.format

class MainActivity : AppCompatActivity() {

    private lateinit var binding: StatPlayerMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StatPlayerMainBinding.inflate(layoutInflater)
        val view = binding.root
        //setContentView(R.layout.activity_main)
        setContentView(view)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){

                val IMG_URL: String = response.body()?.Object?.get(0)?.Player?.img.toString().replace("http:", "https:")

                Glide.with(this).load(IMG_URL).circleCrop().into(binding.iVLogo)

                Log.d("url da imagem", response.body()?.Object?.get(0)?.Player?.img.toString() )
                binding.tvPlayer.text = response.body()?.Object?.get(0)?.Player?.name.toString()
                binding.tvCountry.text = response.body()?.Object?.get(0)?.Player?.country.toString()
                binding.tvPosition.text = response.body()?.Object?.get(0)?.Player?.pos.toString()
                binding.tvPercentual.text = String.format("%.2f",response.body()?.Object?.get(0)?.Player?.percentual)
                binding.tvWinMax.text = response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Vencidas?.pos.toString()+"°"
                binding.cmvBar.max = response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Vencidas?.max?.toInt()!!
                binding.cmvBar.setProgress(response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Vencidas?.pla?.toInt()!!)
                binding.cmvBarProgress.text = response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Vencidas?.pla.toString()
                binding.tvDisMax.text = response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Disputadas?.pos.toString()+"°"
                binding.cmdBar.max = response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Disputadas?.max?.toInt()!!
                binding.cmdBar.setProgress(response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Disputadas?.pla?.toInt()!!)
                binding.cmdBarProgress.text = response.body()?.Object?.get(0)?.Player?.Barras?.Copas_do_Mundo_Disputadas?.pla.toString()

            }
        })
    }
}