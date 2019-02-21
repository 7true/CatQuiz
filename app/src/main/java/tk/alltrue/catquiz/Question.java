package tk.alltrue.catquiz;

public class Question {
    private int mTextResId;
    private boolean mIsRightAnswer;

    public Question(int textResId, boolean isRightAnswer) {
        mTextResId = textResId;
        mIsRightAnswer = isRightAnswer;
    }


    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isRightAnswer() {
        return mIsRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        mIsRightAnswer = rightAnswer;
    }
}
