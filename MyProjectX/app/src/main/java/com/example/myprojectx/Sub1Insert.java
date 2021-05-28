package com.example.myprojectx;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.myprojectx.ATask.ListInsert;
import com.example.myprojectx.Common.CommonMethod;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.myprojectx.Common.CommonMethod.ipConfig;
import static com.example.myprojectx.Common.CommonMethod.isNetworkConnected;


public class Sub1Insert extends AppCompatActivity {

    private static final String TAG = "main:Sub1Insert";

    EditText etId , etName;
    DatePicker datePicker;

    String id = "", name = "", date = "";
    Button photoBtn;
    Button photoLoad;

    ImageView imageView;
    MediaController m;

    public String imageRealPathA, imageDbPathA;

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;

    java.text.SimpleDateFormat tmpDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1_insert);

        tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        photoBtn = (Button) findViewById(R.id.btnPhoto);
        photoLoad = (Button) findViewById(R.id.btnLoad);

        imageView = findViewById(R.id.imageView);

        Date tempDate = new Date();
        date = new DecimalFormat("0000").format(tempDate.getYear()) + "/" +
                new DecimalFormat("00").format(tempDate.getMonth() + 1)
                + "/" + new DecimalFormat("00").format(tempDate.getDay());

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                        date = new DecimalFormat("0000").format(year) + "/" +
                                new DecimalFormat("00").format(month + 1)
                                + "/" + new DecimalFormat("00").format(day);
                    }
                });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(picIntent.resolveActivity(getPackageManager()) != null){
                    file = null;
                    try {
                        file = createFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // 파일 프로바이더 정의
                    Uri imgUri = FileProvider.getUriForFile(getApplicationContext(),
                            getApplicationContext().getPackageName()
                                    + ".fileprovider", file);

                    // 누가버전 이상이면 파일 프로바이더 사용
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){  // API 24 이상
                        picIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                    }else {
                        picIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    }

                    startActivityForResult(picIntent, CAMERA_REQUEST);
                }

            }
        });

        photoLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });


    }

    private File createFile() throws IOException {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "MY" + timestamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File curFile = null;
        try {
            curFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageRealPathA = curFile.getAbsolutePath();
        Log.d(TAG, "createFile: " + imageDbPathA);

        return curFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //사진 찍기
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = ipConfig + "/app/resources/" + uploadFileName;

                // 이미지 저장하기
                galleryAddPic();
                // 이미지 가져오기
                setPic();

            } catch (Exception e){
                e.printStackTrace();
            }
            //이미지 가져오기
        }else if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {

            try {
                String path = "";
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Get the path from the Uri
                    path = getPathFromURI(selectedImageUri);
                }
                // 이미지 돌리기 및 리사이즈
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if(newBitmap != null){
                    imageView.setImageBitmap(newBitmap);
                }else{
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = path;
                Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA);
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // 갤러리에 사진 추가
    private void galleryAddPic() {
        Intent mediaScanIntent =
                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imageRealPathA);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    // 이미지뷰의 크기에 맞게 디코딩하여 이미지 가져오기
    private void setPic() {
        // 이미지뷰의 크기 알아오기
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // 비트맵 크기 알아오기
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // 얼마나 조정하여 이미지를 다운 스케일 할것인가 결정한다
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // 이미지파일을 비트맵 사이즈에 맞게 조정한다
        bmOptions.inJustDecodeBounds =false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imageRealPathA, bmOptions);
        imageView.setImageBitmap(bitmap);

    }

    // Get the real path from the URI
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void btnAddClicked(View view){
        if(isNetworkConnected(this) == true){

            if(fileSize <= 30000000){  // 파일크기가 30메가 보다 작아야 업로드 할수 있음
                id = etId.getText().toString();
                name = etName.getText().toString();
                date = new DecimalFormat("0000").format(datePicker.getYear()) + "/" +
                        new DecimalFormat("00").format(datePicker.getMonth() + 1)
                        + "/" + new DecimalFormat("00").format(datePicker.getDayOfMonth());

                ListInsert listInsert = new ListInsert(id, name, date, imageDbPathA,imageRealPathA);
                listInsert.execute();

                Intent showIntent = new Intent(getApplicationContext(), Sub1Activity.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
                startActivity(showIntent);

                finish();
            }else{
                // 알림창 띄움
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("알림");
                builder.setMessage("파일 크기가 30MB초과하는 파일은 업로드가 제한되어 있습니다.\n30MB이하 파일로 선택해 주십시요!!!");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }

        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void btnCancelClicked(View view){
        finish();
    }

}