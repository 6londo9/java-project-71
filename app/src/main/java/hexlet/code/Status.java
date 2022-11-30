package hexlet.code;

public final class Status {
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private String statusName;
    private Object oldValue;
    private Object newValue;

    public Status(String incomeStatusName, Object incomeOldValue, Object incomeNewValue) {
        this.statusName = incomeStatusName;
        this.oldValue = incomeOldValue;
        this.newValue = incomeNewValue;
    }

    public Status(String incomeStatusName, Object incomeOldValue) {
        this.statusName = incomeStatusName;
        if (statusName.equals(ADDED)) {
            this.newValue = incomeOldValue;
        } else {
            this.oldValue = incomeOldValue;
        }
    }

    public String getStatusName() {
        return statusName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
