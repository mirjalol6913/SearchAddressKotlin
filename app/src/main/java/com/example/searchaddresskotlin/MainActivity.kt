package com.example.searchaddresskotlin

//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val mEtAddress: EditText by lazy { findViewById(R.id.et_address) }
    private val zoneCode: EditText by lazy { findViewById(R.id.zonecode) }
    lateinit var getSearchResult: ActivityResultLauncher<Intent>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSearchResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val zonecode = it.data?.getStringExtra("zonecode").toString()
                val address = it.data?.getStringExtra("address").toString()
                mEtAddress.setText(address)
                zoneCode.setText(zonecode)
            }
        }

        mEtAddress.setFocusable(false)
        mEtAddress.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            getSearchResult.launch(intent)
        }


    }



}