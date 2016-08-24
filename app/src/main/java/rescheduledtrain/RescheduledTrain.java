package rescheduledtrain;

/**
 * Created by ankur on 7/21/16.
 */
public class RescheduledTrain {
    String trainName;
    String fromStation;
    String toStation;
    String rescheduledDate;
    String rescheduledTime;
    String timeDifference;

    public RescheduledTrain(String trainName, String fromStation, String toStation, String rescheduledDate, String timeDifference, String rescheduledTime) {
        this.trainName = trainName;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.rescheduledDate = rescheduledDate;
        this.timeDifference = timeDifference;
        this.rescheduledTime = rescheduledTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(String rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
    }

    public String getRescheduledTime() {
        return rescheduledTime;
    }

    public void setRescheduledTime(String rescheduledTime) {
        this.rescheduledTime = rescheduledTime;
    }

    public String getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(String timeDifference) {
        this.timeDifference = timeDifference;
    }
}
