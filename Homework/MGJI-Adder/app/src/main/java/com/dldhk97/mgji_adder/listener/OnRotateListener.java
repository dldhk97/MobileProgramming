package com.dldhk97.mgji_adder.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OnRotateListener implements View.OnClickListener {
    private TextView resultView;
//    private Direction direction;

//    public OnRotateListener(TextView resultView, Direction direction){
//        this.resultView = resultView;
//        this.direction = direction;
//    }

    @Override
    public void onClick(View view) {
//        try{
//            String binaryStr = resultView.getText().toString();
//            if(binaryStr.isEmpty()){
//                return;
//            }
//
//            Rotater rotater = new Rotater();
//            String result;
//            if(direction == Direction.LEFT){
//                result = rotater.rotateLeft(binaryStr);
//            }
//            else if(direction == Direction.RIGHT){
//                result = rotater.rotateRight(binaryStr);
//            }
//            else{
//                throw new MyException(ExceptionType.UNKNOWN_DIRECTION, "알 수 없는 회전 방향입니다.");
//            }
//
//            resultView.setText(result);
//        }
//        catch(Exception e){
//            UIHandler.getInstance().showAlert(e.getMessage());
//        }
    }
}