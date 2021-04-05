package com.example.somestrangeautomattask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AutomateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.automate_fragment, container, false);
    }

    public void updateAutomateData(Automate automate) {
        LinearLayout automateView = (LinearLayout) getView();
        ((TextView)automateView.getChildAt(0)).setText(automate.name);
        ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(0)).setText(automate.status);
        ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("");
        if(automate.status!="Простаивает")
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("ID студента: " + automate.students.get(0).id);
        ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(0)).setText(((Integer)automate.students.size()).toString());
        ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText("");
        if(automate.status=="Приём заказа")
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText("Расчитывается");
        else if(automate.status!="Простаивает")
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText(((Integer)automate.students.get(0).get_sum_of_order()).toString());
        RecyclerView recyclerView = (RecyclerView) automateView.getChildAt(2);
        ProductAdapter adapter = new ProductAdapter(automate.context, automate.students.get(0).wished_products);
        recyclerView.setAdapter(adapter);
    }
}
