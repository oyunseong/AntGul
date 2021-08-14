package com.antgul.antgul_android;

import android.util.Log;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityTestBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiExplorer extends BaseActivity<ActivityTestBinding> {

    @Override
    protected ActivityTestBinding getViewBinding() {
        return ActivityTestBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

        new Thread(() -> {
            Log.e("run","--run");
            try {
                Log.e("try","--try");
                binding.textView.setText(getHomeXmlData());
            } catch (UnsupportedEncodingException e) {
                Log.e("try","--catch");
                e.printStackTrace();
            }
        });
    }


    public String getHomeXmlData() throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();

        String text;

//        String sigungu = "Your 시군구 number";
//        String bjdong = "Your 법정동 number";
//        String bun = "Your 번지수 number";
//        String ji = "Your 번지수 number";
//        String numOfRows = "1";
//        String serviceKey = "Your Service Key";

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1160100/service/GetFinaStatInfoService/getBs"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*결과형식(xml/json)*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("공공데이터포털에서 받은 인증키", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("crno","UTF-8") + "=" + URLEncoder.encode("1101111848914", "UTF-8")); /*법인등록번호*/
        urlBuilder.append("&" + URLEncoder.encode("bizYear","UTF-8") + "=" + URLEncoder.encode("2018", "UTF-8")); /*법인에 대해 법령이 규정한 1회계기간으로서 법인세의 과세기간*/

        try {
            URL url = new URL(urlBuilder.toString());//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("buffer : "+buffer.toString());
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }

    @Override
    protected void initClickListener() {

    }

}

