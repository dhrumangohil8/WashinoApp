package com.example.washino;

public class FeedbackClass {

    String feedbackReview, ratings;
    public FeedbackClass() {

    }

    public FeedbackClass(String feedbackReview, String ratings) {
        this.feedbackReview = feedbackReview;
        this.ratings = ratings;
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
}
