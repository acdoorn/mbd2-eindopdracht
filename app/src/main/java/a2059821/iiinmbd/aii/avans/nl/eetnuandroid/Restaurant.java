package a2059821.iiinmbd.aii.avans.nl.eetnuandroid;

/**
 * Created by Alexander on 13-4-2015.
 */
public class Restaurant {
    private int id;
    private String name;
    private String telephone;
    private int rating;
    private String street;
    private String zipcode;
    private String city;
    private double lat;
    private double lng;

    public Restaurant(int id, String name, String telephone, int rating, String street, String zipcode, String city, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.rating = rating;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
    }

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
