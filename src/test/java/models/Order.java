package models;

public class Order {
//    firstName	string
//    Имя заказчика, записывается в поле firstName таблицы Orders
    private String firstName;
//
//    lastName	string
//    Фамилия заказчика, записывается в поле lastName таблицы Orders
//
    private String lastName;
//    address	string
//    Адрес заказчика, записывается в поле adress таблицы Orders
//
    private String address;
//    metroStation	string
//    Ближайшая к заказчику станция метро, записывается в поле metroStation таблицы Orders
//
    private String phone;
//    phone	string
//    Телефон заказчика, записывается в поле phone таблицы Orders
//
    private Integer rentTime;
//    rentTime	number
//    Количество дней аренды, записывается в поле rentTime таблицы Orders
//
    private String deliveryDate;
//    deliveryDate	string
//    Дата доставки, записывается в поле deliveryDate таблицы Orders
//
    private String comment;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    //    comment	string
//    Комментарий от заказчика, записывается в поле comment таблицы Orders
//
    private String[] color;
//    colorнеобязательный	string[]
//    Предпочитаемые цвета, записываются в поле color таблицы Orders


}
