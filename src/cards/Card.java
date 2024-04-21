package cards;

public class Card {
	private int id;
    private String matTruoc;
    private String matSau;
    private String date_Created;

    public Card(String matTruoc, String matSau) {
    	this.matTruoc = matTruoc;
    	this.matSau = matSau;
    }
    
    public Card(String matTruoc, String matSau, String date_Created) {
        this.matTruoc = matTruoc;
        this.matSau = matSau;
        this.date_Created = date_Created;
    }
    
    public Card(int id, String matTruoc, String matSau, String date_Created) {
    	this.id = id;
    	this.matTruoc = matTruoc;
        this.matSau = matSau;
        this.date_Created = date_Created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatTruoc() {
        return matTruoc;
    }

    public void setMatTruoc(String matTruoc) {
        this.matTruoc = matTruoc;
    }

    public String getMatSau() {
        return matSau;
    }

    public void setMatSau(String matSau) {
        this.matSau = matSau;
    }

    public String getDate_Created() {
        return date_Created;
    }

    public void setDate_Created(String date_Created) {
        this.date_Created = date_Created;
    }

    @Override
    public String toString() {
        return "Mặt Trước: " + matTruoc + '\'' +
                ", Mặt Sau: '" + matSau + '\'' +
                ", Ngày tạo Flashcard: " + date_Created;
    }
	
}
