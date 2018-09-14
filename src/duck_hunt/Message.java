package duck_hunt;

import java.io.Serializable;

public class Message implements Serializable {
    public String message;
    public String from;
    public String to;

    public Message(String message, String from, String to) {
        this.message = message;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("Message : %s\nFrom : %s\nTo : %s"
                , message, from, to);

    }
}
