package cz.utb.fai.myaplication3.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.moshi.Moshi;

import cz.utb.fai.myaplication3.databinding.Frag1LayoutBinding;
import cz.utb.fai.myaplication3.ui.main.pojo.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Frag1 extends Fragment {

    public static final String BASE_URL = "https://api.mymemory.translated.net/";
    private Frag1LayoutBinding binding;
    private MyApiTranslationInterface apiService;

    public String historyList = "";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        apiService = retrofit.create(MyApiTranslationInterface.class);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        binding = Frag1LayoutBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.getTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTranslation();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getTranslation() {
        String q = binding.etTranslateInput.getText().toString();
        String langpair = "en|cs";

        Call<ApiResponse> call = apiService.getResponse(q, langpair);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                int statusCode = response.code();
                ApiResponse data = response.body();
                //ResponseData responseData = data.responseData;

                historyList += q +" -> " + data.responseData.translatedText + "\n";

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("honza",historyList);
                editor.apply();

                Log.v("MYAPP", data.responseData.translatedText);
                binding.tvTranslationOutput.setText(data.responseData.translatedText);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }


}
