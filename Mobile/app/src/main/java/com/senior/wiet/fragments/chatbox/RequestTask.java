package com.senior.wiet.fragments.chatbox;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import ai.api.AIServiceContext;
import ai.api.AIServiceException;
import ai.api.android.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class RequestTask  extends AsyncTask<AIRequest, Void, AIResponse> {

    Activity activity;
    Fragment fragment;
    private AIDataService aiDataService;
    private AIServiceContext customAIServiceContext;

    RequestTask(Fragment fragment, AIDataService aiDataService, AIServiceContext customAIServiceContext){
        this.fragment = fragment;
        this.aiDataService = aiDataService;
        this.customAIServiceContext = customAIServiceContext;
    }

    @Override
    protected AIResponse doInBackground(AIRequest... aiRequests) {
        final AIRequest request = aiRequests[0];
        try {
            return aiDataService.request(request, customAIServiceContext);
        } catch (AIServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(AIResponse aiResponse) {
        ((ChatFragment)fragment).callback(aiResponse);
    }
}