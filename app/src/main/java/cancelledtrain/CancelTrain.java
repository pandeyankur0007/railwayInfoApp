package cancelledtrain;

/**
 * Created by ankur on 7/20/16.
 */
public class CancelTrain {
    String trainName;
    String trainType;
    String startTime;
    String sourceStation;
    String destinationStation;
    String lastUpdate;

    public CancelTrain(String trainName, String trainType, String sourceStation, String startTime, String destinationStation) {
        this.trainName = trainName;
        this.trainType = trainType;
        this.sourceStation = sourceStation;
        this.startTime = startTime;
        this.destinationStation = destinationStation;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
