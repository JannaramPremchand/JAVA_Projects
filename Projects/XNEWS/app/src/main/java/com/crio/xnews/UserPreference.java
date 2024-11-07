package com.crio.xnews;

// TODO: CRIO_TASK_MODULE_PROJECT
// Refer users.json in the app/src/main/resources directory to declare the necessary variables.
// Utilize your IDE to generate getters and setters for these variables.
// Use your IDE to generate the toString method to create a string representation of the variables.

public class UserPreference {
    // Declare necessary variables based on the JSON structure
    private String name;
    private String query;
    private String language;
    private String sortBy;

    // Generate getters and setters for the variables

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    // Generate a toString method for better logging and debugging
    @Override
    public String toString() {
        return "UserPreference{" +
                "name='" + name + '\'' +
                ", query='" + query + '\'' +
                ", language='" + language + '\'' +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }
}
