package hust.plane.utils.pojo;

//模板pojo类
public class InfoTplData {
    private String title;
    private String img;
    private String body;

    public InfoTplData() {
    }

    public InfoTplData(String title, String img, String body) {
        this.title = title;
        this.img = img;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

