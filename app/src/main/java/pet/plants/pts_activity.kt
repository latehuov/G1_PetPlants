package pet.plants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.app.AlertDialog
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog

private lateinit var btn_upload: Button
private lateinit var image_view: ImageView
private lateinit var diagnosys: TextView

class pts_activity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference

    companion object {
        private val PICK_IMAGE_CODE = 1000
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(requestCode == PICK_IMAGE_CODE){
            alertDialog.show()
            val uploadTask = storageReference!!.putFile(data!!.data!!)
            val task = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this@pts_activity, "Failed", Toast.LENGTH_SHORT).show()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pts)

        image_view = findViewById(R.id.imageviv)
        btn_upload = findViewById(R.id.upload)
        diagnosys = findViewById(R.id.diagnosis)
        alertDialog = SpotsDialog.Builder().setContext(this).build();
        storageReference = FirebaseStorage.getInstance().getReference("image_upload")


        btn_upload.setOnClickListener(){
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "select picture"), PICK_IMAGE_CODE)
        }

    }


}