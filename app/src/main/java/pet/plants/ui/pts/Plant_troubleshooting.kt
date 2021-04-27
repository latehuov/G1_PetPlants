package pet.plants.ui.pts

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pet.plants.R

import android.util.Log
import android.app.AlertDialog
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog



class Plant_troubleshooting : Fragment() {

    private var currentUser: FirebaseUser? = null


    private lateinit var btn_upload: Button
    private lateinit var btn_back: Button
    private lateinit var image_view: ImageView
    private lateinit var diagnosys: TextView
    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference

    var mContext : Context? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(requestCode == PICK_IMAGE_CODE){
            alertDialog.show()
            val uploadTask = storageReference!!.putFile(data!!.data!!)
            val task = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show()
                }
                else{
                    val downloadUrl = task.result
                    val url = downloadUrl!!.toString()
                    Log.d("DIRECTLINK", url)
                    alertDialog.dismiss()

                    Picasso.get().load(url).into(image_view)
                    diagnosys.setText("Your plant looks good and well! Nothing to worry about!")
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUrl = task.result
                    val url = downloadUrl!!.toString()
                    Log.d("DIRECTLINK", url)
                    alertDialog.dismiss()

                    Picasso.get().load(url).into(image_view)
                    diagnosys.setText("Your plant looks good and well! Nothing to worry about!")
                }
            }
        }
    }


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_plant_troubleshooting, container, false)
            image_view = view.findViewById(R.id.imageviv)
            btn_upload = view.findViewById(R.id.upload)
            btn_back = view.findViewById(R.id.back)
            diagnosys = view.findViewById(R.id.diagnosis)
            alertDialog = SpotsDialog.Builder().setContext(mContext).build();
            storageReference = FirebaseStorage.getInstance().getReference("image_upload")


            btn_upload.setOnClickListener(){
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "select picture"), PICK_IMAGE_CODE)
            }
            btn_back.setOnClickListener(){
                findNavController().navigate(R.id.navigation_LoggedIn)
            }
        return view

    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    companion object {
        private val PICK_IMAGE_CODE = 1000
        fun newInstance(): Plant_troubleshooting {
            return Plant_troubleshooting()
        }
    }
}