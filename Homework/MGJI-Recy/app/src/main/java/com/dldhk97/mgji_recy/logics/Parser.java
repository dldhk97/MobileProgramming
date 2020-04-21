package com.dldhk97.mgji_recy.logics;

import android.util.Log;

import com.dldhk97.mgji_recy.MyException;
import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.ExceptionType;
import com.dldhk97.mgji_recy.enums.MealTimeType;
import com.dldhk97.mgji_recy.models.Menu;
import com.dldhk97.mgji_recy.utilities.DateUtility;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Parser{
    private ArrayList<Menu> resultArr;
    private ExceptionType resultException;

    public ArrayList<Menu> parse(CafeteriaType cafeteriaType, Calendar date) throws Exception{
        if(cafeteriaType == CafeteriaType.UNKNOWN){
            throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
        }

        // url 설정
        String url = cafeteriaType.getURL();

        // 해당 날짜로 설정
        url += "mode=menuList&srDt=" + DateUtility.DateToString(date, '-');

        // 오늘이 일요일이면 하루 뺀다.
        // 일요일이면 웹페이지에서 하루 넘어가기 때문임.
        if(date.get(Calendar.DAY_OF_WEEK) == 1){
            date.add(Calendar.DATE, -1);
        };

       // 학교 홈페이지에서 파싱
        ParseThread pt = new ParseThread(url, cafeteriaType);
        try{
            pt.setOnParseCompleteReceivedEvent(new ParseCompleteListener());
            Thread thread = new Thread(pt);
            thread.start();
            thread.join();
        }
        catch(Exception e){
            throw new MyException(ExceptionType.PARSE_FAILED, "Parse failed.");
        }

        if(resultException != null){
            throw new MyException(resultException, "금오공과대학교 홈페이지에 연결하는데 실패했습니다!");
        }

        if(resultArr == null){
            return new ArrayList<>();
        }

        // 날짜 역순으로 정렬
        Collections.sort(resultArr, new Comparator<Menu>() {
            @Override
            public int compare(Menu menu1, Menu menu2) {
                return menu2.getDate().compareTo(menu1.getDate());
            }
        });

        return resultArr;
    }

    // 데이터 수신을 위한 리스너
    private class ParseCompleteListener implements ParseThread.IParseCompleteListener {

        // 데이터 수신
        @Override
        public void onParseComplete(ExceptionType exceptionType, ArrayList<Menu> parsedArr) {
            if(exceptionType != null){
                resultArr = null;
                resultException = exceptionType;
            }
            else{
                resultArr = parsedArr;
            }
        }
    }
}

// 파싱용 스레드
class ParseThread implements Runnable{

    private static final int TIMEOUT = 1000;
    private String url;
    private CafeteriaType cafeteriaType;

    private IParseCompleteListener parseCompleteListener;

    public ParseThread(String url, CafeteriaType cafeteriaType){
        this.url = url;
        this.cafeteriaType = cafeteriaType;
    }

    //파싱 메소드
    @Override
    public void run() {
        ArrayList<Menu> resultArr = new ArrayList<>();
        try{
            Connection connection = Jsoup.connect(url);
            connection.timeout(TIMEOUT);
            Document doc = connection.get();

            // 등록된 메뉴가 있는지 체크
            Elements existCheck = doc.select("table > tbody > td");
            if (existCheck == null){
                throw new MyException(ExceptionType.MENU_NOT_EXIST, "등록된 메뉴가 없음");
            }

            // 이번주의 시작 날짜 겟
            Elements startDateHtml = doc.select("fieldset > div > div > p");
            String startDateStr = startDateHtml.get(0).text().replace("\t","").replace("\n","").replace("\r","");
            startDateStr = startDateStr.split("~")[0].trim();
            Calendar startDate = DateUtility.StringToDate(startDateStr);

            // 이번 주 모든 메뉴 겟
            Elements menuListHtml = doc.select("table > tbody > tr > td");
            int cnt = 0;
            for(Element menuHtml : menuListHtml){

                // 식사시간 알아내기
                MealTimeType mealTimeType = MealTimeType.UNKNOWN;
                if(this.cafeteriaType == CafeteriaType.SNACKBAR){   // 분식이면 식사시간을 일품요리로 고정
                    mealTimeType = MealTimeType.ONECOURSE;
                }
                else{
                    String mealTimeStr = menuHtml.select("p").get(0).text();
                    mealTimeType = MealTimeType.strToValue(mealTimeStr);
                }

                // 현재 메뉴의 날짜 설정
                Calendar currentMenuDate = Calendar.getInstance();
                currentMenuDate.setTime(startDate.getTime());
                currentMenuDate.add(Calendar.DATE, cnt % 7);        // 윗줄 7개는 중식이고, 아랫줄 7개는 석식이다.

                // 메뉴 객체 생성
                Menu currentMenu = new Menu(currentMenuDate, cafeteriaType, mealTimeType);

                // 음식 파싱 후 메뉴에 삽입
                Elements foodDetailHtml = menuHtml.select("ul > li");
                for(Element foodElemHtml : foodDetailHtml){
                    String str = foodElemHtml.text();
                    currentMenu.addFood(str);
                }

                // 파싱된 메뉴 있을때만 사진 설정
                if(currentMenu.getFoods().size() > 0 && !currentMenu.getFood(0).contains("식당 운영 없음")){
                    // 사진 대충 설정
                    int offset = new Random().nextInt(6);
                    int imgId = R.drawable.food0 + offset;
                    currentMenu.setImageId(imgId);
                }

                //전달할 배열에 추가
                resultArr.add(currentMenu);
                cnt++;
            }

            // 파싱 완료 알리고 배열 전달
            parseCompleteListener.onParseComplete(null, resultArr);
        }
        catch(Exception e){
            Log.d("[ParseThread.run]\n","Parse Failed\n" + e.getMessage());
            parseCompleteListener.onParseComplete(ExceptionType.PARSE_FAILED, resultArr);
        }

    }



    // 데이터 전달을 위한 리스너 설정
    public void setOnParseCompleteReceivedEvent(IParseCompleteListener listener){
        parseCompleteListener = listener;
    }

    // 데이터 전달을 위한 인터페이스
    public interface IParseCompleteListener {
        void onParseComplete(ExceptionType exceptionType, ArrayList<Menu> parsedArr);
    }
}