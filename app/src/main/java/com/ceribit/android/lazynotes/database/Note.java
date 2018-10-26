package com.ceribit.android.lazynotes.database;

public class Note {
    public static int NO_ID = -1;

    private int id;
    private String title;
    private String description;
    private Importance importance;

    public Note(String title, String description, String importance){
        this.id = NO_ID;
        this.title = title;
        this.description = description;
        this.importance = Importance.valueOf(importance.toUpperCase());
    }

    public Note(String title, String description, Importance importance){
        this.id = NO_ID;
        this.title = title;
        this.description = description;
        this.importance = importance;
    }

    public Note(String title, String description, int importance){
        this.id = NO_ID;
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

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public enum Importance {
        NOT_IMPORTANT(0), NEUTRAL(1), IMPORTANT(2), VERY_IMPORTANT(3);

        private final int importanceLevel;

        Importance(int level) {this.importanceLevel = level;}

        public int getImportanceLevel(){
            return importanceLevel;
        }
    }
}
