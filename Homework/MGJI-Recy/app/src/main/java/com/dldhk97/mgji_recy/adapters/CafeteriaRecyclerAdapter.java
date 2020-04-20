package com.dldhk97.mgji_recy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dldhk97.mgji_recy.DataController;
import com.dldhk97.mgji_recy.MyException;
import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.ExceptionType;
import com.dldhk97.mgji_recy.models.Menu;
import com.dldhk97.mgji_recy.viewholders.CafeteriaRecyclerViewHolder;

import java.util.ArrayList;

public class CafeteriaRecyclerAdapter extends RecyclerView.Adapter<CafeteriaRecyclerViewHolder> {
    private LayoutInflater inflater;

    public CafeteriaRecyclerAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CafeteriaRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_menu, parent, false);
        return new CafeteriaRecyclerViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CafeteriaRecyclerViewHolder holder, int position) {
        try{
            ArrayList<Menu> targetArray;
            switch (DataController.getInstance().currentCafeteriaType){
                case STUDENT:
                    targetArray = DataController.getInstance().getStudentMenus();
                    break;
                case STAFF:
                    targetArray = DataController.getInstance().getStaffMenus();
                    break;
                case SNACKBAR:
                    targetArray = DataController.getInstance().getSnackbarMenus();
                    break;
                default:
                    throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
            }
            holder.onBind(targetArray.get(position));
        }
        catch(Exception e){
            UIHandler.getInstance().showToast("[Adapter.onBindViewHolder]\n" + e.getMessage());
        }

    }


    @Override
    public int getItemCount() {
        try{
            switch (DataController.getInstance().currentCafeteriaType){
                case STUDENT:
                    if(DataController.getInstance().getStudentMenus() != null)
                        return DataController.getInstance().getStudentMenus().size();
                    break;
                case STAFF:
                    if(DataController.getInstance().getStaffMenus() != null)
                        return DataController.getInstance().getStaffMenus().size();
                    break;
                case SNACKBAR:
                    if(DataController.getInstance().getSnackbarMenus() != null)
                        return DataController.getInstance().getSnackbarMenus().size();
                    break;
            }
        }
        catch (Exception e){
            UIHandler.getInstance().showToast("[Adapter.getItemCount]\n" + e.getMessage());
        }

        return 0;
    }
}
