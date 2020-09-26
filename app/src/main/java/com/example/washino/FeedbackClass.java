package com.example.washino;

public class FeedbackClass {

    String feedbackReview, ratings, submittedBy;
    public FeedbackClass() {

    }

    public FeedbackClass(String feedbackReview, String ratings, String submittedBy) {
        this.feedbackReview = feedbackReview;
        this.ratings = ratings;
        this.submittedBy = submittedBy;
    }

    public String getFeedbackReview() {
        return feedbackReview;
    }

    public void setFeedbackReview(String feedbackReview) {
        this.feedbackReview = feedbackReview;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
}
