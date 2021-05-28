package com.example.b_project_jomok_0528.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.b_project_jomok_0528.Adapter.FreeBoardAdapter;
import com.example.b_project_jomok_0528.Dto.FreeBoardDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.b_project_jomok_0528.Common.CommonMethod.ipConfig;


public class FreeSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main : FreeSelect";

    ArrayList<FreeBoardDTO> FreeBoardList;
    FreeBoardAdapter adapter;
    ProgressDialog progressDialog;

    public FreeSelect(ArrayList<FreeBoardDTO> freeBoardList, FreeBoardAdapter adapter, ProgressDialog progressDialog) {
        this.FreeBoardList = freeBoardList;
        this.adapter = adapter;
        this.progressDialog = progressDialog;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        FreeBoardList.clear();
        String result = "";
        String postURL = ipConfig + "/cari/FreeSelect";

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);

            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            String jsonStr = stringBuilder.toString();

            inputStream.close();*/

        } catch (Exception e) {
            Log.d("BoardFragment", e.getMessage());
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(progressDialog != null){
            progressDialog.dismiss();
        }

        Log.d("BoardFragment", "List Select Complete!!!");

        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                FreeBoardList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public FreeBoardDTO readMessage(JsonReader reader) throws IOException {

        int Board_num =0;
        String Board_id = "";
        String Board_subject = "";
        String Board_content = "";
        String Board_file = "";
        int Board_re_ref = 0;
        int Board_re_lev = 0;
        int Board_re_seq = 0;
        int Board_readcount = 0;
        String Board_date = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("Board_num")) {
                Board_num = reader.nextInt();
            } else if (readStr.equals("Board_id")) {
                Board_id = reader.nextString();
            } else if (readStr.equals("Board_subject")) {
                Board_subject = reader.nextString();
            } else if (readStr.equals("Board_content")) {
                Board_content = reader.nextString();
            } else if (readStr.equals("Board_file")) {
                Board_file = reader.nextString();
            } else if (readStr.equals("Board_re_ref")) {
                Board_re_ref = reader.nextInt();
            } else if (readStr.equals("Board_re_lev")) {
                Board_re_lev = reader.nextInt();
            } else if (readStr.equals("Board_re_seq")) {
                Board_re_seq = reader.nextInt();
            } else if (readStr.equals("Board_readcount")) {
                Board_readcount = reader.nextInt();
            } else if (readStr.equals("Board_date")) {
                String[] temp = reader.nextString().replace("월", "-").replace(",", "-")
                        .replace(" ", "").split("-");
                Board_date = temp[2] + "-" + temp[0] + "-" + temp[1];
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("FreeSelect:FreeBoardDTO", Board_num + ", " + Board_id);
        return new FreeBoardDTO(Board_num, Board_id, Board_subject, Board_content, Board_file,
                Board_re_ref, Board_re_lev, Board_re_seq, Board_readcount, Board_date);

    }
}