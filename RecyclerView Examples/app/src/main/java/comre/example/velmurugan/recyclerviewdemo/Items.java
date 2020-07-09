package comre.example.velmurugan.recyclerviewdemo;

public class Items {

    private String name;
    private int price;

    Items(String mName,int mPrice){
        this.name = mName;
        this.price = mPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
