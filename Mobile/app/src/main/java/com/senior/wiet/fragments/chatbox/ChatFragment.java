package com.senior.wiet.fragments.chatbox;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.senior.wiet.R;
import com.senior.wiet.databinding.FragmentChatBinding;
import com.senior.wiet.databinding.FragmentRecommendBinding;
import com.senior.wiet.fragments.BaseFragment;
import com.senior.wiet.lib.model.ChatModel;

import java.util.UUID;

import javax.inject.Inject;

import ai.api.AIServiceContext;
import ai.api.AIServiceContextBuilder;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;


public class ChatFragment extends BaseFragment<ChatContract.Presenter> implements ChatContract.View {

    private static final int USER = 10001;
    private static final int BOT = 10002;

    private String uuid = UUID.randomUUID().toString();
    private LinearLayout layout;
    private EditText editText;

    // Android client for older V1 --- recommend not to use this
    private AIRequest aiRequest;
    private AIDataService aiDataService;
    private AIServiceContext customAIServiceContext;
    public Context context;

    public View view;
    private FragmentChatBinding binding;

    @Inject
    public ChatFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        binding = DataBindingUtil.bind(view);

        binding.chatScrollView.post(() -> binding.chatScrollView.fullScroll(ScrollView.FOCUS_DOWN));

        editText = binding.queryEditText;
        binding.sendBtn.setOnClickListener(this::sendMessage);

        editText.setOnKeyListener((view, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        sendMessage(binding.sendBtn);
                        return true;
                    default:
                        break;
                }
            }
            return false;
        });

        // Android client for older V1 --- recommend not to use this
        initChatbot();
        return view;
    }

    private void initChatbot() {
        final AIConfiguration config = new AIConfiguration("e119d72ab0de4c0eb2f7b6c91dd6e1b7",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiDataService = new AIDataService(getContext(), config);
        customAIServiceContext = AIServiceContextBuilder.buildFromSessionId(uuid);// helps to create new session whenever app restarts
        aiRequest = new AIRequest();
    }

    private void sendMessage(View view) {
        String msg = editText.getText().toString();
        if (msg.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please enter your query!", Toast.LENGTH_LONG).show();
        } else {
            showTextView(msg, USER);
            editText.setText("");
            // Android client for older V1 --- recommend not to use this
            aiRequest.setQuery(msg);
            RequestTask requestTask = new RequestTask(ChatFragment.this, aiDataService, customAIServiceContext);
            requestTask.execute(aiRequest);

            // Java V2
            //QueryInput queryInput = QueryInput.newBuilder().setText(TextInput.newBuilder().setText(msg).setLanguageCode("en-US")).build();
            //new RequestJavaV2Task(MainActivity.this, session, sessionsClient, queryInput).execute();
        }
    }

    public void callback(AIResponse aiResponse) {
        if (aiResponse != null) {
            // process aiResponse here
            String botReply = aiResponse.getResult().getFulfillment().getSpeech();
            showTextView(botReply, BOT);
        } else {
            showTextView("There was some communication issue. Please Try again!", BOT);
        }
    }

    private void showTextView(String message, int type) {
        FrameLayout layout;
        switch (type) {
            case USER:
                layout = getUserLayout();
                break;
            case BOT:
                layout = getBotLayout();
                break;
            default:
                layout = getBotLayout();
                break;
        }
        layout.setFocusableInTouchMode(true);
        binding.chatLayout.addView(layout); // move focus to text view to automatically make it scroll up if softfocus
        TextView tv = layout.findViewById(R.id.chatMsg);
        tv.setText(message);
        layout.requestFocus();
        editText.requestFocus(); // change focus back to edit text to continue typing
    }

    FrameLayout getUserLayout() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        return (FrameLayout) inflater.inflate(R.layout.user_msg_layout, null);
    }

    FrameLayout getBotLayout() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        return (FrameLayout) inflater.inflate(R.layout.bot_msg_layout, null);
    }

    @Override
    public void attachModel(ChatModel chatModel) {

    }

    @Override
    public FragmentChatBinding bindView() {
        return null;
    }
}
