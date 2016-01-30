package com.example.nguyen.artsvista.models;

/**
 * Created by nguyen on 13/11/15.
 */
public class EventModel {
   // private String event;
    private String id;
    private String title;
    private String ImageID;
    private String Image;
    private String ImageEvent_ID;
    private String ticket;
    private String video;
    private String description;
    private String vendor_name; //name of the vendor
    private String vendor_address;

    private double latitude;
    private double longitude;
    private String start_date;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImageEvent_ID() {
        return ImageEvent_ID;
    }

    public void setImageEvent_ID(String imageEvent_ID) {
        ImageEvent_ID = imageEvent_ID;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getVendor_Name(){
        return vendor_name;
    }

    public void setVendor_Name(String vendor_name){
        this.vendor_name = vendor_name;
    }

    public String getVendor_Address(){
        return vendor_address;
    }

    public void setVendor_Address(String vendor_address){
        this.vendor_address = vendor_address;
    }

    public Double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude=latitude;
    }

    public Double getLongitude(){
        return longitude;
    }
    public void setLongitude(Double longitude){
        this.longitude=longitude;
    }

    public String getStart_Date(){
        return start_date;
    }

    public void setStart_Date(String start_date){
        this.start_date=start_date;
    }


}



