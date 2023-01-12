package cz.utb.fai.myaplication3.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cz.utb.fai.myaplication3.R;

public class Frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2_layout, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            String translated = sharedPref.getString("honza", "No translation yet!");

            Log.v("FRAG2", translated);

            View view = getView();
            if (view != null) {
                TextView translation = (TextView) view.findViewById(R.id.screen2out);
                translation.setText(translated);
            }
        }
    }
}