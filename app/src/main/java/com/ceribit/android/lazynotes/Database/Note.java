package com.ceribit.android.lazynotes.Database;

public class Note {
    private String title;
    private String description;
    private Importance importance;

    public Note(String title, String description, String importance){
        this.title = title;
        this.description = description;
        this.importance = Importance.valueOf(importance.toUpperCase());
    }

    public Note(String title, String description, Importance importance){
        this.title = title;
        this.description = description;
        this.importance = importance;
    }

    public Note(String title, String description, int importance){
        this.title = title;
        this.description = description;
        this.importance = Importance.values()[importance];
    }

    public String getTitle() {
        return title;
    }

    public Importance getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }

    public int getImportanceLevel() {
        return importance.getImportanceLevel();
    }

    public enum Importance {
        NOT_IMPORTANT(0), NEUTRAL(1), IMPORTANT(2), VERY_IMPORTANT(3);

        private final int importanceLevel;

        Importance(int level) {this.importanceLevel = level;}

        public int getImportanceLevel(){
            return importanceLevel;
        }
    }
}
