package se.bhg.photos.model;

import java.util.Date;

public class Metadata {
    String make;
    String model;
    Date timestamp;
    int exposureTime; // 1 / x
    float aperture;
    float focalLength;
    int height;
    int width;
    double longitude;
    double latitude;
    boolean flash;
    String flashType;

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public int getExposureTime() {
        return exposureTime;
    }
    public void setExposureTime(int exposureTime) {
        this.exposureTime = exposureTime;
    }
    public float getAperture() {
        return aperture;
    }
    public void setAperture(float aperture) {
        this.aperture = aperture;
    }
    public float getFocalLength() {
        return focalLength;
    }
    public void setFocalLength(float focalLength) {
        this.focalLength = focalLength;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public boolean isFlash() {
        return flash;
    }
    public void setFlash(boolean flash) {
        this.flash = flash;
    }
    public String getFlashType() {
        return flashType;
    }
    public void setFlashType(String flashType) {
        this.flashType = flashType;
    }
}
