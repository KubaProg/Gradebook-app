package pl.polsl.gradebook.Grade;

public enum GradeType {
    TEST("Test"),
    EXAM("Exam"),
    OTHER("Other");

    private final String displayName;

    GradeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Optional: Method to convert a String representation of a grade type to its corresponding GradeType
    public static GradeType fromString(String gradeType) {
        for (GradeType type : values()) {
            if (type.displayName.equalsIgnoreCase(gradeType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid grade type: " + gradeType);
    }
}
