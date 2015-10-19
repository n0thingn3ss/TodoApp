package training.simpletodo;

public class TodoItem {
    public String mName;
    public Integer mPriority = 0;

    public TodoItem(String name, Integer priority) {
        mName = name;
        mPriority = priority;
    }

    public String toString() {
        return mName + ',' + mPriority;
    }
}
