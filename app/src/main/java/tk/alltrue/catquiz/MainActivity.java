package tk.alltrue.catquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "INDEX";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mAnswersButton;
    private TextView mQuestionTextView;


    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_1, false),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mAnswersButton = (Button) findViewById(R.id.buttonPeep);
        mAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue = mQuestions[mCurrentIndex].isRightAnswer();
                Intent intent = AnswersActivity.sendIntent(MainActivity.this,
                        answerIsTrue, mCurrentIndex);
                startActivity(intent);
            }
        });

        mTrueButton = (Button) findViewById(R.id.buttonTrue);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.buttonFalse);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mQuestionTextView = (TextView) findViewById(R.id.textViewQuestion);

        mQuestionTextView = (TextView) findViewById(R.id.textViewQuestion);
        getNextQuestion();

        mNextButton = (Button) findViewById(R.id.buttonNext);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                getNextQuestion();
            }
        });


    }

    private void getNextQuestion() {
        int question = mQuestions[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean rightAnswer) {
        boolean answerIsTrue = mQuestions[mCurrentIndex].isRightAnswer();
        int messageResId;
        if (rightAnswer == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
