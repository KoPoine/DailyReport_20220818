package com.neppplus.dailyreport_20220818

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.neppplus.dailyreport_20220818.databinding.ActivitySignUpBinding
import com.neppplus.dailyreport_20220818.datas.BasicResponse
import com.neppplus.dailyreport_20220818.utils.URIPathHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    val REQ_FOR_GALLERY = 1000

    var profileImg : MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_FOR_GALLERY) {
                val dataUrl = data?.data

                val file = File(URIPathHelper().getPath(mContext, dataUrl!!))

                val requestBody = RequestBody.create(MediaType.get("image/*"), file)

                profileImg = MultipartBody.Part
                    .createFormData("profile_image", "myProfile.jpg", requestBody)

                Glide.with(mContext).load(dataUrl).into(binding.profileImg)
            }
        }
    }

    override fun setupEvents() {
        binding.profileImg.setOnClickListener {

            val pl = object : PermissionListener{
                override fun onPermissionGranted() {
                    val myIntent = Intent()
                    myIntent.action = Intent.ACTION_PICK
                    myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
                    startActivityForResult(myIntent, REQ_FOR_GALLERY)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                }
            }

            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()

        }

        binding.signUpBtn.setOnClickListener {
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.pwEdt.text.toString()
            val inputNick = binding.nickEdt.text.toString()

            val map = HashMap<String, RequestBody>()

            map["email"] = RequestBody.create(MediaType.get("text/plain"), inputEmail)
            map["password"] = RequestBody.create(MediaType.get("text/plain"), inputPw)
            map["nick_name"] = RequestBody.create(MediaType.get("text/plain"), inputNick)

            apiList.putRequestSignUp(map, profileImg)
                .enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            val nick = br.data.user.nickname

                            Toast.makeText(mContext, "${nick}님 회원가입을 축하합니다.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val errorBodyStr = response.errorBody()!!.string()
                            val jsonObj = JSONObject(errorBodyStr)
                            val message = jsonObj.getString("message")

                            Log.e("signUpError", message)
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
        }
    }

    override fun setValues() {

    }
}