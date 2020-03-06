package com.bernotsha.port2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class imageupload extends AppCompatActivity {
    private StorageReference folder;
private static final int ImageBack=1;
ImageView Notibutton;
Button uploadwithoutimage;
EditText Notitext,notititle;
DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageupload);
        folder = FirebaseStorage.getInstance().getReference().child("Imagefolder");
        Notibutton = findViewById(R.id.notibutton);
        Notitext = findViewById(R.id.notitext);
        uploadwithoutimage=findViewById(R.id.uploadwithoutimage);
        notititle=findViewById(R.id.notititle);
        ref = FirebaseDatabase.getInstance().getReference().child("Notification");
        Notibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadtext();
            }
        });
        uploadwithoutimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id2=ref.push().getKey();
                String notitext = Notitext.getText().toString().trim();
                String notitle = notititle.getText().toString().trim();
                ref.child(id2).child("title").setValue(notitext);
                ref.child(id2).child("desc").setValue(notitle);
                ref.child(id2).child("image").setValue(" ");

            }
        });


    }

    public void uploaddata(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,ImageBack);
    }
    public void uploadtext()
    {
        String id2 = ref.push().getKey();
        String notitext = Notitext.getText().toString().trim();
        String noti = notititle.getText().toString().trim();
        Notitext.setText("");
        noti n = new noti(notitext);
        String id = ref.push().getKey();
        ref.child("Notification").child(id).setValue(notitext);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ImageBack)
        {
            if(resultCode==RESULT_OK)
            {
                final String id1 = ref.push().getKey();
                Uri Imagedata=data.getData();
                final StorageReference imagename=folder.child(id1+Imagedata.getLastPathSegment());
                imagename.putFile(Imagedata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("image", String.valueOf(uri));
                                String notitext = Notitext.getText().toString().trim();
                                String notitile = notititle.getText().toString().trim();
                                String id3 = ref.push().getKey();

                                ref.child(id3).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(imageupload.this,"finally completed",Toast.LENGTH_LONG).show();
                                    }
                                });
                                ref.child(id3).child("desc").setValue(notitext);
                                ref.child(id3).child("title").setValue(notitile);
                            }
                        });
                    }
                });
            }
        }
    }
}
